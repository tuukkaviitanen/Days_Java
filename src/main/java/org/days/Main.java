package org.days;

import com.beust.jcommander.JCommander;



public class Main {
    public static void main(String[] args) {

        CommandList list = new CommandList();
        CommandDelete delete = new CommandDelete();
        CommandAdd add = new CommandAdd();

        var jc = JCommander.newBuilder()
                .addCommand(list)
                .addCommand(delete)
                .addCommand(add)
                .build();
        jc.setProgramName("java -jar days.jar");

        try{
            jc.parse(args);

            EventManager eventManager = EventManager.getInstance();

            switch (jc.getParsedCommand()) {
                case "list":
                    for (Event event : eventManager.getEvents(list)) {
                        System.out.println(event);
                    }
                    break;
                case "add":
                    System.out.println(eventManager.addEvents(add));
                    break;
                case "delete":
                    for (Event event : eventManager.deleteEvents(delete)) {
                        System.out.println(event);
                    }
                    break;
                default:
                    jc.usage();
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            jc.usage();
        }

    }

}