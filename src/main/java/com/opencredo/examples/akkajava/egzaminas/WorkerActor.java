package com.opencredo.examples.akkajava.egzaminas;

import akka.actor.*;
import com.opencredo.examples.akkajava.streamers.Stream;

import java.util.Random;

import static akka.japi.pf.ReceiveBuilder.match;

public class WorkerActor extends AbstractLoggingActor {


    ActorSelection resultsActor;
    Random random;

    public static Props props() {
        return Props.create(WorkerActor.class, () -> new WorkerActor());
    }

    private WorkerActor() {
        random = new Random();
        int minSleepValue = 1000;
        int maxSleepValue = 5000;

        int minStreamGrowth = 0;
        resultsActor = context().actorSelection("/user/ResultsActor");

        receive(match(Stream.class, streamer -> {
            Stream.calculateStreamGrowth(streamer);
            Thread.sleep(random.nextInt((maxSleepValue - minSleepValue) + 1) + minSleepValue);
            if (streamer.streamGrowth >= minStreamGrowth) resultsActor.tell(streamer, self());
            resultsActor.tell(MainActor.PROCESSED_STREAM_MESSAGE, self());
        }).matchAny(message -> {
            log().info(message.toString());
        }).build());


    }

}