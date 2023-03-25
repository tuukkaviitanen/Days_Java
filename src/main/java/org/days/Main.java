package org.days;

import com.beust.jcommander.JCommander;
import jdk.jshell.spi.ExecutionControl;



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
                    throw new ExecutionControl.NotImplementedException("Not Implemented");
                case "delete":
                    throw new ExecutionControl.NotImplementedException("Not Implemented");
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