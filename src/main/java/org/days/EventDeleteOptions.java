package org.days;

public interface EventDeleteOptions extends EventFilterOptions {
    /** Delete command won't actually delete the shown Events */
    boolean getDryRun();
    /** All stored Event will be deleted */
    boolean getDeleteAllEvents();
}
