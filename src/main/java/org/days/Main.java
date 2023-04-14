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
                    var events = eventManager.getEvents(list);
                    if(events.size() > 0){
                        for (Event event : events) {
                            System.out.println(event);
                        }
                    }else{
                        System.out.println("No matching events found");
                    }
                    break;
                case "add":
                    var addedEvent = eventManager.addEvents(add);
                    if(addedEvent != null){
                        System.out.println(eventManager.addEvents(add));
                    }else{
                        System.out.println("Error with event creation");
                    }
                    break;
                case "delete":
                    var deletedEvents = eventManager.deleteEvents(delete);
                    if(deletedEvents.size() > 0){
                        for (Event event : deletedEvents) {
                            System.out.println(event);
                        }
                    }else{
                        System.out.println("No matching events found");
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