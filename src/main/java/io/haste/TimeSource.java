package io.haste;

import java.time.LocalDateTime;

public interface TimeSource {

    /**
     * Provides the current date-time from clock given during the creation
     *
     * @return current date-time
     */
    LocalDateTime now();

}
