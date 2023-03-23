package org.days;

import java.time.LocalDate;

public class Event {
    public Event(LocalDate date_p, String category_p, String description_p){
        date = date_p;
        category = category_p;
        description = description_p;
    }
    public LocalDate date;
    public String category;
    public String description;

    @Override
    public String toString() {
        StringBuilder message = new StringBuilder();
        message.append(date + ": " + description);
        if(category != null){
            message.append("(" + category + ")");
        }
        return message.toString();
    }
}

