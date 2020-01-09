package com.opencredo.examples.akkajava.egzaminas;

import akka.actor.AbstractLoggingActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.routing.RoundRobinPool;
import com.opencredo.examples.akkajava.Order;

import static akka.japi.pf.ReceiveBuilder.match;

public class ResultsActor extends AbstractLoggingActor {

    public static Props props() {
        return Props.create(ResultsActor.class, () -> new ResultsActor());
    }

    private ResultsActor() {
        receive(match(Order.class, order -> {
        }).matchAny(message -> {
            log().info(message.toString());
        }).build());

    }


}
