package io.haste.event;

import static org.junit.jupiter.api.Assertions.assertTrue;

import io.haste.TestTimeService;
import io.haste.TimeService;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;

public class EventTest{

    @Test
    public void shouldEventStartsAfterStartDate(){
        //given
        TestTimeService timeService = TimeService.createTimeServiceForTestsWithCurrentTime();
        LocalDateTime eventTime = LocalDateTime.now().plusHours(1);
        Event event = new Event(eventTime,timeService);
        //when
        timeService.hackIntoFuture(2, TimeUnit.HOURS); //Probably temporally method name ;)
        //then
        assertTrue(event.hasAlreadyBegun());
    }
}
