package io.haste;

import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

final class CurrentTimeMillsTest {

    @Test
    void shouldReturnCorrectMillisTimeForCreatedMovableTimeSource() {
        Instant instant = Instant.ofEpochMilli(0);
        ZoneId zoneId = ZoneId.systemDefault();
        Clock clock = Clock.fixed(instant, zoneId);
        MovableTimeSource timeSource = Haste.TimeSource.withFixedClock(clock);

        long currentTime = timeSource.currentTimeMillis();

        assertEquals(0, currentTime);
    }

    @Test
    void shouldReturnCorrectMillisTimeForMovableTimeSourceWithMovedTime() {
        Instant instant = Instant.ofEpochMilli(0);
        ZoneId zoneId = ZoneId.systemDefault();
        Clock clock = Clock.fixed(instant, zoneId);
        MovableTimeSource timeSource = Haste.TimeSource.withFixedClock(clock);

        timeSource.advanceTimeBy(10, TimeUnit.MILLISECONDS);

        long currentTime = timeSource.currentTimeMillis();
        assertEquals(10, currentTime);
    }

    @Test
    void shouldReturnCorrectMillisTimeForMovableTimeSourceWithMovedTimeByDuration() {
        Instant instant = Instant.ofEpochMilli(0);
        ZoneId zoneId = ZoneId.systemDefault();
        Clock clock = Clock.fixed(instant, zoneId);
        MovableTimeSource timeSource = Haste.TimeSource.withFixedClock(clock);

        timeSource.advanceTimeBy(Duration.ofMillis(10));

        long currentTime = timeSource.currentTimeMillis();
        assertEquals(10, currentTime);
    }


    @Test
    void shouldReturnCorrectMillisTimeForCreatedvar() {
        Instant instant = Instant.ofEpochMilli(0);
        ZoneId zoneId = ZoneId.systemDefault();
        Clock clock = Clock.fixed(instant, zoneId);
        var service = Haste.ScheduledExecutionService.withFixedClock(clock);

        long currentTime = service.currentTimeMillis();
        assertEquals(0, currentTime);
    }

    @Test
    void shouldReturnCorrectMillisTimeForvarWithMovedTime() {
        Instant instant = Instant.ofEpochMilli(0);
        ZoneId zoneId = ZoneId.systemDefault();
        Clock clock = Clock.fixed(instant, zoneId);
        var service = Haste.ScheduledExecutionService.withFixedClock(clock);

        service.advanceTimeBy(10, TimeUnit.MILLISECONDS);

        long currentTime = service.currentTimeMillis();
        assertEquals(10, currentTime);
    }

    @Test
    void shouldReturnCorrectMillisTimeForWithMovedTimeByDuration() {
        Instant instant = Instant.ofEpochMilli(0);
        ZoneId zoneId = ZoneId.systemDefault();
        Clock clock = Clock.fixed(instant, zoneId);
        var service = Haste.ScheduledExecutionService.withFixedClock(clock);

        service.advanceTimeBy(Duration.ofMillis(10));

        long currentTime = service.currentTimeMillis();
        assertEquals(10, currentTime);
    }

}
