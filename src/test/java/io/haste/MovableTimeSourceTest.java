package io.haste;

import org.junit.jupiter.api.Test;

import java.time.*;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MovableTimeSourceTest {


    @Test
    void shouldNowReturnTimeWithOffsetWhenMovedByTimeUnit() {
        Instant instant = Instant.ofEpochMilli(0);
        ZoneId zoneId = ZoneId.systemDefault();
        Clock clock = Clock.fixed(instant, zoneId);
        MovableTimeSource timeSource = Haste.TimeSource.withFixedClock(clock);

        timeSource.advanceTimeBy(1, TimeUnit.HOURS);

        LocalDateTime now = timeSource.now();

        LocalDateTime expected = LocalDateTime.now(clock).plusHours(1);
        assertEquals(expected, now);
    }

    @Test
    void shouldNowReturnTimeWithOffsetWhenMovedByDuration() {
        Instant instant = Instant.ofEpochMilli(0);
        ZoneId zoneId = ZoneId.systemDefault();
        Clock clock = Clock.fixed(instant, zoneId);
        MovableTimeSource timeSource = Haste.TimeSource.withFixedClock(clock);

        timeSource.advanceTimeBy(Duration.ofHours(1));

        LocalDateTime now = timeSource.now();

        LocalDateTime expected = LocalDateTime.now(clock).plusHours(1);
        assertEquals(expected, now);
    }

}