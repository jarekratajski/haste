[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.krasnoludkolo/haste/badge.png)](https://maven-badges.herokuapp.com/maven-central/io.github.krasnoludkolo/haste)
[![Build Status](https://travis-ci.org/krasnoludkolo/haste.svg?branch=master)](https://travis-ci.org/krasnoludkolo/haste)
[![codecov](https://codecov.io/gh/krasnoludkolo/haste/branch/master/graph/badge.svg)](https://codecov.io/gh/krasnoludkolo/haste)

# haste
A small library for testing time-related stuff

## Goals and motivation

In a few applications I was struggling with time aspect during tests. 
It's not hard to write some kind of proxy or mocks to provide a proper
date but it is annoying to write them every time. 
Here comes the idea to create an open source library to help write tests
 based on the passage of time and also to help write more testable systems.

## Usage

```xml
<dependency>
    <groupId>io.github.krasnoludkolo</groupId>
    <artifactId>haste</artifactId>
    <version>0.1.0</version>
</dependency>
```
```groovy
compile 'io.github.krasnoludkolo:haste:0.1.0'
```


## Features

### TL;DR
<i>Haste</i> provides the implementation of [ScheduledExecutorService](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ScheduledExecutorService.html)
with ```advanceTimeBy``` method to simulate lapse of time.

### Full version

##### Test scheduled runnable
Some actions in your system may also plan another actions to be done in the future. 
E.g. when you add a sport fixture you may want to check the result after it has finished
When using normal java scheduler it is hard to test results of scheduled jobs without e.g. mocking. 
Here comes the implementation of ScheduledExecutorService with ```advanceTimeBy``` method.
 
 ```java
class FooTest{
    
        private static final Runnable EMPTY_RUNNABLE = () -> {};
        private static final Callable<Integer> RETURN_ONE_CALLABLE = () -> 1;
    
        @Test
        void shouldExecuteAllScheduledJobs() throws ExecutionException, InterruptedException {
            BlockingScheduledExecutionService executorService = BlockingScheduledExecutionService.withFixedClockFromNow();
    
            ScheduledFuture<Integer> schedule1 = executorService.schedule(RETURN_ONE_CALLABLE, 1, TimeUnit.SECONDS);
            ScheduledFuture schedule2 = executorService.schedule(EMPTY_RUNNABLE, 2, TimeUnit.SECONDS);
            ScheduledFuture schedule3 = executorService.schedule(EMPTY_RUNNABLE, 3, TimeUnit.SECONDS);
            ScheduledFuture schedule4 = executorService.schedule(EMPTY_RUNNABLE, 5, TimeUnit.SECONDS);
    
            executorService.advanceTimeBy(4, TimeUnit.SECONDS);
    
            assertEquals(Integer.valueOf(1), schedule1.get()); //not null
            
            assertTrue(schedule1.isDone()); 
            assertTrue(schedule2.isDone());
            assertTrue(schedule3.isDone());
            
            assertFalse(schedule4.isDone());
        }

}
```

## Disclaimer
Keep in mind that Haste is in early-alpha phase which means that some API details may change between versions.
