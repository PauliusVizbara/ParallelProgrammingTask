package com.opencredo.examples.akkajava.egzaminas;

import akka.actor.*;
import akka.pattern.Patterns;
import akka.util.Timeout;
import com.opencredo.examples.akkajava.Order;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static akka.japi.pf.ReceiveBuilder.match;

public class WorkerActor extends AbstractLoggingActor {


    ActorSelection resultsActor;

    public static Props props() {
        return Props.create(WorkerActor.class, () -> new WorkerActor());
    }

    private WorkerActor(){



        resultsActor =  context().actorSelection("/user/ResultsActor");

        receive(match(Order.class, order -> {
        }).matchAny(message -> {
            log().info(message.toString());
            //context().actorSelection("/user/ResultsActor").tell("Hello results actor", self());
            resultsActor.tell(message.toString() + " + 1", self());
    }).build());


    }

}