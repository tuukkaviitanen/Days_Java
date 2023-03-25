package org.days;

import java.time.LocalDate;

public class Event {
    public Event(LocalDate date, String category, String description){
        this.date = date;
        this.category = category;
        this.description = description;
    }
    public LocalDate date;
    public String category;
    public String description;


    @Override
    public String toString() {
        StringBuilder message = new StringBuilder();
        message.append(date).append(": ").append(description);
        if(category != null){
            message.append("(").append(category).append(")");
        }
        return message.toString();
    }
}

