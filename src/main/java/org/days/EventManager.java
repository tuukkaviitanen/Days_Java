package org.days;

import com.opencsv.bean.*;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Singleton manager that handles reading and writing events to csv file
 */
public class EventManager {

    /**
     * Single instance of this Manager
     */
    private static EventManager instance;

    /**
     * Returns the single instance and creates it if not yet created
     * @return EventManager instance
     */
    public static EventManager getInstance() {
        if (instance == null) {
            instance = new EventManager();
        }
        return instance;
    }

    /**
     * Private constructor for only to be called via getInstance()
     * Allows singleton structure
     */
    private EventManager() {
    }

    /**
     * Gets events from csv
     * @param options filters for events
     * @return filtered events
     * @throws FileNotFoundException File not found
     */
    public List<Event> getEvents(EventFilterOptions options) throws FileNotFoundException {
        List<Event> allEvents = ReadEventsFromCSV();
        return queryEvents(allEvents, options);
    }

    /**
     * * Deletes the given events
     * @param options filters for deletion
     * @return deleted events
     * @throws Exception Error with Reading or writing to file
     */
    public List<Event> deleteEvents(EventDeleteOptions options) throws Exception {
        List<Event> allEvents = ReadEventsFromCSV();

        var toBeDeletedEvents = (options.isDeleteAllEvents())
                ? new ArrayList<>(allEvents)
                : queryEvents(new ArrayList<>(allEvents), options);

        if(!options.isDeleteAllEvents() && toBeDeletedEvents.size() == allEvents.size()){
            toBeDeletedEvents.clear();
        }

        if (!options.isDryRun()) {
            allEvents.removeAll(toBeDeletedEvents);
        }

        writeCsvFromBean(allEvents);
        return toBeDeletedEvents;
    }

    /**
     * Adds given event to csv
     * @param options build options for event
     * @return created event
     * @throws Exception Error with Reading or writing to file
     */
    public Event addEvents(CommandAdd options) throws Exception {
        List<Event> allEvents = ReadEventsFromCSV();

        var newEvent = new Event(options.date, options.category, options.description );
        allEvents.add(newEvent);

        writeCsvFromBean(allEvents);

        return newEvent;
    }

    /**
     * Filters events by given options
     * @param allEvents events to be filtered
     * @param options filters
     * @return filtered list
     */
    private List<Event> queryEvents(List<Event> allEvents, EventFilterOptions options) {

        List<Event> filteredEvents = allEvents;

        if (options.getCategory() != null) {
            filteredEvents = filteredEvents.stream()
                    .filter(x -> options.getCategory().equalsIgnoreCase(x.category))
                    .collect(Collectors.toList());
        }

        if (options.isNoCategory()) {
            filteredEvents = filteredEvents.stream()
                    .filter(x -> x.category == null)
                    .collect(Collectors.toList());
        }

        if (options.isToday()) {
            filteredEvents = filteredEvents.stream()
                    .filter(x -> Objects.equals(x.date, LocalDate.now()))
                    .collect(Collectors.toList());
        }

        if (options.getCategories() != null) {
            filteredEvents = filteredEvents.stream()
                    .filter(x -> x.category != null && options.getCategories().stream().anyMatch(x.category::equalsIgnoreCase))
                    .collect(Collectors.toList());
        }

        if (options.getDescription() != null) {
            filteredEvents = filteredEvents.stream()
                    .filter(x -> x.description.toLowerCase().contains(options.getDescription().toLowerCase()))
                    .collect(Collectors.toList());
        }

        if (options.getBeforeDate() != null) {
            filteredEvents = filteredEvents.stream()
                    .filter(x -> x.date.isBefore(options.getBeforeDate()))
                    .collect(Collectors.toList());
        }

        if (options.getAfterDate() != null) {
            filteredEvents = filteredEvents.stream()
                    .filter(x -> x.date.isAfter(options.getAfterDate()))
                    .collect(Collectors.toList());
        }

        if (options.getDate() != null) {
            filteredEvents = filteredEvents.stream()
                    .filter(x -> x.date.isEqual(options.getDate()))
                    .collect(Collectors.toList());
        }

        if(options.isExcluded())
        {
            allEvents.removeAll(filteredEvents);
            filteredEvents = allEvents;
        }

        return filteredEvents;
    }

    /**
     * Gets .days/events.csv file path if exists
     * @return Filepath
     */
    private static Path getCsvFilePath(){
        String userHomeDirectory = System.getProperty("user.home");
        if (userHomeDirectory.isBlank()) {
            throw new InvalidPathException(userHomeDirectory, "Unable to determine user home directory");
        }

        Path daysPath = Paths.get(userHomeDirectory, ".days");
        if (Files.notExists(daysPath)) {
            throw new InvalidPathException(daysPath.toString(), "Directory does not exist, please create it");
        }
        Path eventsPath = daysPath.resolve("events.csv");
        if (Files.notExists(eventsPath)) {
            throw new InvalidPathException(eventsPath.toString(), "events.csv file not found");
        }
        return eventsPath;
    }

    /**
     * Reads events from csv file
     * @return Events from csv file
     * @throws FileNotFoundException File doesn't exist
     */
    private static List<Event> ReadEventsFromCSV() throws FileNotFoundException {
        Path eventsFile = getCsvFilePath();
        var filePath = eventsFile.toString();
        List<Event> events = new CsvToBeanBuilder(new FileReader(filePath))
                .withType(Event.class)
                .build()
                .parse();

        events.sort(Comparator.comparing(o -> o.date));

        return events;
    }

    /**
     * Writes given list to csv file
     * @param events Events to write
     * @throws IOException Something goes wrong with writing to file
     * @throws CsvRequiredFieldEmptyException Some field is empty?
     * @throws CsvDataTypeMismatchException Error with column types
     */
    private static void writeCsvFromBean(List<Event> events) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {

        Path eventsFile = getCsvFilePath();

        // Create strategy
        HeaderColumnNameMappingStrategy<Event> strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(Event.class);

        // Build the header line which respects the declaration order
            String headerLine = Arrays.stream(Event.class.getDeclaredFields())
                .map(field -> field.getAnnotation(CsvBindByName.class))
                .filter(Objects::nonNull)
                .map(CsvBindByName::column)
                .collect(Collectors.joining(","));

        // Initialize strategy by reading a CSV with header only
        try (StringReader reader = new StringReader(headerLine)) {
            CsvToBean<Event> csv = new CsvToBeanBuilder<Event>(reader)
                    .withType(Event.class)
                    .withMappingStrategy(strategy)
                    .build();
            for (Event event : csv) {} // Necessary loop for some reason. Otherwise the headers are capitalized
        }

        try (var writer = Files.newBufferedWriter(eventsFile)) {
            StatefulBeanToCsv<Event> csv = new StatefulBeanToCsvBuilder<Event>(writer)
                    .withMappingStrategy(strategy)
                    .build();
            csv.write(events);
        }

    }

}
