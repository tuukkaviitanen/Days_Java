package org.days;

public interface EventDeleteOptions extends EventFilterOptions {
    /** Delete command won't actually delete the shown Events */
    boolean isDryRun();
    /** All stored Event will be deleted */
    boolean isDeleteAllEvents();
}
