package org.days;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import java.time.LocalDate;
import java.util.List;

@Parameters(commandNames = "delete", commandDescription = "Delete event(s). Displays all deleted/would-be-deleted events")
public class CommandDelete implements EventDeleteOptions {


    @Parameter(names = "--category")
    public String category;

    @Parameter(names = "--categories")
    public List<String> categories;

    @Parameter(names = "--description")
    public String description;

    @Parameter(names = "--date")
    public LocalDate date;

    @Parameter(names = "--before-date")
    public LocalDate before_date;

    @Parameter(names = "--after-date")
    public LocalDate after_date;

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
    public boolean isDryRun() {
        return is_dry_run;
    }

    @Override
    public boolean isDeleteAllEvents() {
        return is_delete_all;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public List<String> getCategories() {
        return categories;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public LocalDate getDate() {
        return date;
    }

    @Override
    public boolean isToday() {
        return is_today;
    }

    @Override
    public LocalDate getAfterDate() {
        return after_date;
    }

    @Override
    public LocalDate getBeforeDate() {
        return before_date;
    }

    @Override
    public boolean isNoCategory() {
        return no_category;
    }

    @Override
    public boolean isExcluded() {
        return is_excluded;
    }



}
