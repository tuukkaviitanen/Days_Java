package org.days;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
        events = new ArrayList<>();
        events.add(new Event(LocalDate.now(), "basic day", "today"));
        events.add(new Event(LocalDate.now(), null, "todays"));
    }

    public List<Event> getEvents(CommandList options) {
        List<Event> allEvents = events;
        return queryEvents(allEvents, options);
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


}
