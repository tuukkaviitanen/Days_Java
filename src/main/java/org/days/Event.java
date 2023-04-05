package org.days;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

import java.time.LocalDate;

public class Event {
    public Event()
    {

    }
    public Event(LocalDate date, String category, String description){
        this.date = date;
        this.category = category;
        this.description = description;
    }
    @CsvBindByName
    @CsvDate("yyyy-MM-dd")
    public LocalDate date;
    @CsvBindByName
    public String category;
    @CsvBindByName
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

