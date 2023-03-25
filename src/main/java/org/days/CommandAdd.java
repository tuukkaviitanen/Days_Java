package org.days;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(commandNames = "add", commandDescription = "Add event")
public class CommandAdd {

    @Parameter(names = "--category")
    public String category;
    @Parameter(names = "--description", required = true)
    public String description;
    @Parameter(names = "--date")
    public String date;

}
