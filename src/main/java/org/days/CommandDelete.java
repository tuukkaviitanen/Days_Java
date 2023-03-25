package org.days;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import java.time.LocalDate;
import java.util.List;

@Parameters(commandNames = "delete", commandDescription = "Delete event(s). Displays all deleted/would-be-deleted events")
public class CommandDelete implements EventDeleteOptions {


    @Parameter(names = "--category")
    public String category;

    @Parameter(names = "--description")
    public String description;

    @Parameter(names = "--date")
    public String date;

    @Parameter(names = "--before-date")
    public String before_date;

    @Parameter(names = "--after-date")
    public String after_date;

    @Parameter(names = "--today")
    public Boolean is_today = false;

    @Parameter(names = "--no-category")
    public Boolean no_category = false;

    @Parameter(names = "--exclude")
    public Boolean is_excluded = false;

    @Parameter(names = "--dry-run")
    public Boolean is_dry_run = false;

    @Parameter(names = "--all")
    public Boolean is_delete_all = false;


    @Override
    public boolean getDryRun() {
        return false;
    }

    @Override
    public boolean getDeleteAllEvents() {
        return false;
    }

    @Override
    public String getCategory() {
        return null;
    }

    @Override
    public List<String> getCategories() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public LocalDate getDate() {
        return null;
    }

    @Override
    public boolean isToday() {
        return false;
    }

    @Override
    public LocalDate getAfterDate() {
        return null;
    }

    @Override
    public LocalDate getBeforeDate() {
        return null;
    }

    @Override
    public boolean isNoCategory() {
        return false;
    }

    @Override
    public boolean isExcluded() {
        return false;
    }



}
