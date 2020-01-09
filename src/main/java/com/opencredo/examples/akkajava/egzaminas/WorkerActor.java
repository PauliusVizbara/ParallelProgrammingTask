package com.opencredo.examples.akkajava.egzaminas;

import akka.actor.*;
import com.opencredo.examples.akkajava.streamers.Stream;

import static akka.japi.pf.ReceiveBuilder.match;

public class WorkerActor extends AbstractLoggingActor {


    ActorSelection resultsActor;

    public static Props props() {
        return Props.create(WorkerActor.class, () -> new WorkerActor());
    }

    private WorkerActor(){



        resultsActor =  context().actorSelection("/user/ResultsActor");

        receive(match(Stream.class, streamer-> {

            Thread.sleep(2000);
            log().info("Received a streamer: " + streamer.user_name.toString());
            resultsActor.tell(streamer.user_name , self());
        }).matchAny(message -> {
            log().info(message.toString());
            //context().actorSelection("/user/ResultsActor").tell("Hello results actor", self());
            resultsActor.tell(message.toString() + " + 1", self());
    }).build());


    }

}