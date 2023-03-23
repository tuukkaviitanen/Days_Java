package org.days;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import java.time.LocalDate;
import java.util.List;

@Parameters(commandNames = "list", commandDescription = "List events to console")
public class CommandList implements EventFilterOptions {

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
    public Boolean is_today;

    @Parameter(names = "--no-category")
    public Boolean no_category;

    @Parameter(names = "--excluded")
    public Boolean is_excluded;

    @Override
    public String getCategory() {
        return null;
    }

    @Override
    public void setCategory(String category) {

    }

    @Override
    public List<String> getCategories() {
        return categories;
    }

    @Override
    public void setCategories(List<String> categories) {

    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean isToday() {
        return is_today;
    }

    @Override
    public void setToday(boolean today) {
        is_today = today;
    }

    @Override
    public LocalDate getAfterDate() {
        return after_date;
    }

    @Override
    public void setAfterDate(LocalDate afterDate) {
        after_date = afterDate;
    }

    @Override
    public LocalDate getBeforeDate() {
        return before_date;
    }

    @Override
    public void setBeforeDate(LocalDate beforeDate) {
        before_date = beforeDate;
    }

    @Override
    public boolean isNoCategory() {
        return no_category;
    }

    @Override
    public void setNoCategory(boolean noCategory) {
        no_category = noCategory;
    }

    @Override
    public boolean isExcluded() {
        return is_excluded;
    }

    @Override
    public void setExcluded(boolean excluded) {
        is_excluded = excluded;
    }
}