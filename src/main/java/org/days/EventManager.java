package org.days;

import com.opencsv.bean.CsvToBeanBuilder;
import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class EventManager {

    private static EventManager instance;

    public static EventManager getInstance() {
        if (instance == null) {
            instance = new EventManager();
        }
        return instance;
    }

    public List<Event> events;

    private EventManager() {
    }

    public List<Event> getEvents(EventFilterOptions options) throws FileNotFoundException {
        List<Event> allEvents = ReadEventsFromCSV();
        return queryEvents(allEvents, options);
    }

    public List<Event> deleteEvents(EventDeleteOptions options) {
        List<Event> allEvents = new ArrayList<>(events);

        var toBeDeletedEvents = (options.isDeleteAllEvents())
                ? new ArrayList<>(allEvents)
                : queryEvents(allEvents, options);

        if (!options.isDryRun()) {
            events.removeAll(toBeDeletedEvents);
        }

        return toBeDeletedEvents;
    }

    public Event addEvents(CommandAdd options) {
        List<Event> allEvents = new ArrayList<>(events);

        var newEvent = new Event(options.date, options.category, options.description );
        allEvents.add(newEvent);

        events = allEvents;

        return newEvent;
    }

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

}
