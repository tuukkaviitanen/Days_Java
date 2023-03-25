package org.days;

import java.time.LocalDate;
import java.util.List;

public interface EventFilterOptions {
/** Filters Events by single category*/
    String getCategory();


/** Filters Events by multiple categories. Separated by comma ","*/
    List<String> getCategories();


 /** Filters Events by all Events that contain this description/part of description*/
    String getDescription();


 /** Filters Events by date*/
    LocalDate getDate();


 /** Filters Events happening today*/
    boolean isToday();


 /** Filters Events happening after this date*/
    LocalDate getAfterDate();


 /** Filters Events happening before this date*/
    LocalDate getBeforeDate();


 /** Filters Events with no category*/
    boolean isNoCategory();


 /** Filters are reversed */
    boolean isExcluded();

}