package com.opencredo.examples.akkajava.egzaminas;


import akka.actor.AbstractLoggingActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.routing.RoundRobinPool;
import com.opencredo.examples.akkajava.streamers.Stream;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static akka.japi.pf.ReceiveBuilder.match;

public class MainActor extends AbstractLoggingActor {
    ActorSystem system;
    ActorRef workerRouter;

    public static String PROCESSED_STREAM_MESSAGE = "Processed stream";

    public static Props props(ActorSystem system, int workerCount) {
        return Props.create(MainActor.class, () -> new MainActor(system, workerCount));
    }

    private MainActor(ActorSystem system, int workerCount) {

        workerRouter = system.actorOf(WorkerActor.props().withRouter(new RoundRobinPool(workerCount)));

        receive(match(Stream.class, stream -> {
            workerRouter.tell(stream, ActorRef.noSender());
        }).matchAny(message -> {
            log().info(message.toString());
        }).build());

    }


}
