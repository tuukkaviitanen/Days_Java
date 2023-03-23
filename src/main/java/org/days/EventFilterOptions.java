package org.days;

import java.time.LocalDate;
import java.util.List;

public interface EventFilterOptions {
    // Filters Events by single category
    String getCategory();

    void setCategory(String category);

    // Filters Events by multiple categories. Separated by comma ","
    List<String> getCategories();

    void setCategories(List<String> categories);

    // Filters Events by all Events that contain this description/part of description
    String getDescription();

    void setDescription(String description);

    // Filters Events by date
    LocalDate getDate();

    void setDate(LocalDate date);

    // Filters Events happening today
    boolean isToday();

    void setToday(boolean today);

    // Filters Events happening after this date
    LocalDate getAfterDate();

    void setAfterDate(LocalDate afterDate);

    // Filters Events happening before this date
    LocalDate getBeforeDate();

    void setBeforeDate(LocalDate beforeDate);

    // Filters Events with no category
    boolean isNoCategory();

    void setNoCategory(boolean noCategory);

    // Filters are reversed
    boolean isExcluded();

    void setExcluded(boolean excluded);
}