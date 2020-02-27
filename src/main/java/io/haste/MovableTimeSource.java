package io.haste;

import java.time.Clock;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public final class MovableTimeSource implements TimeSource {

    private Clock clock;

    MovableTimeSource(Clock clock) {
        this.clock = Clock.fixed(clock.instant(), clock.getZone());
    }

    @Override
    public LocalDateTime now() {
        return LocalDateTime.now(clock);
    }

    /**
     * Move internal clock by given amount of time
     *
     * @param delayTime amount of time to move
     * @param timeUnit  time unit of delay parameter
     */
    public void advanceTimeBy(long delayTime, TimeUnit timeUnit) {
        Duration duration = Duration.ofNanos(timeUnit.toNanos(delayTime));
        advanceTimeBy(duration);
    }

    /**
     * Move internal clock by given duration
     *
     * @param duration amount of time to move
     */
    public void advanceTimeBy(Duration duration) {
        clock = Clock.offset(clock, duration);
    }

    @Override
    public long currentTimeMillis() {
        return clock.millis();
    }

}
