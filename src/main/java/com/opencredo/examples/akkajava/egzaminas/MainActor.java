package com.opencredo.examples.akkajava.egzaminas;


import akka.actor.*;
import akka.routing.RoundRobinPool;
import com.opencredo.examples.akkajava.streamers.API;
import com.opencredo.examples.akkajava.streamers.Stream;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static akka.japi.pf.ReceiveBuilder.match;

public class MainActor extends AbstractLoggingActor {
    ActorSystem system;
    ActorRef workerRouter;

    public static String PROCESSED_STREAM_MESSAGE = "Processed stream";

    public static Props props(ActorSystem system, int streamAmount) {
        return Props.create(MainActor.class, () -> new MainActor(system, streamAmount));
    }

    private MainActor(ActorSystem system, int streamAmount) throws IOException {

        workerRouter = system.actorOf(WorkerActor.props().withRouter(new RoundRobinPool(streamAmount/3)));

        ActorRef resultsActor = system.actorOf(ResultsActor.props(streamAmount),"ResultsActor");


        receive(match(Stream.class, stream -> {
            workerRouter.tell(stream, resultsActor);
        }).matchAny(message -> {
            log().info(message.toString());
        }).build());

    }


}
