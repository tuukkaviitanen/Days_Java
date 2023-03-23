package org.days;

import com.beust.jcommander.JCommander;

public class Main {
    public static void main(String[] args) {

        CommandList list = new CommandList();

        JCommander.newBuilder()
                .addCommand(list)
                .build()
                .parse(args);

        EventManager eventManager = EventManager.getInstance();


        for (Event event : eventManager.getEvents(list)) {
            System.out.println(event);
        }
    }
}