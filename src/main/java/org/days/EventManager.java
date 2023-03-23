package org.days;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventManager {

    private static EventManager instance;
    public static EventManager getInstance(){
        if(instance == null) {
            instance = new EventManager();
        }
        return instance;
    }
    public List<Event> events;

    private EventManager(){
        events = new ArrayList<>();
        events.add(new Event(LocalDate.now(), "basic day", "today"));
    }

    public List<Event> getEvents(CommandList options){
        List<Event> allEvents = events;
        return queryEvents(allEvents, options);
    }

    private List<Event> queryEvents(List<Event> allEvents, EventFilterOptions options){
        return allEvents;
    }

}
