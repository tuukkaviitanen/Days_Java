package org.days;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

import java.time.LocalDate;

public class Event {
    public Event() // this is necessary
    {}
    public Event(LocalDate date, String category, String description){
        this.date = date;
        this.category = category;
        this.description = description;
    }
    @CsvBindByName(column = "date")
    @CsvDate("yyyy-MM-dd")
    public LocalDate date;
    @CsvBindByName(column = "category")
    public String category;
    @CsvBindByName(column = "description")
    public String description;


    @Override
    public String toString() {
        StringBuilder message = new StringBuilder();
        message.append(date).append(": ").append(description);
        if(category != null && !category.isEmpty()){
            message.append(" (").append(category).append(")");
        }
        return message.toString();
    }
}

