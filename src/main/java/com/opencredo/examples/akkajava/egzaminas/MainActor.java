package com.opencredo.examples.akkajava.egzaminas;


import akka.actor.*;
import akka.routing.RoundRobinPool;
import com.opencredo.examples.akkajava.streamers.API;
import com.opencredo.examples.akkajava.streamers.Stream;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static akka.japi.pf.ReceiveBuilder.match;


/**
 * Pagrindinio aktoriaus klasė, sukurianti maršrutizavimą ir rezultatų aktorių.
 *
 */
public class MainActor extends AbstractLoggingActor {

    ActorRef workerRouter;

    public static String PROCESSED_STREAM_MESSAGE = "Processed stream";

    public static Props props( int streamAmount) {
        return Props.create(MainActor.class, () -> new MainActor(streamAmount));
    }

    private MainActor( int streamAmount) throws IOException {

        // Sukuriamas RoundRobinPool maršrutizavimas siųsti žinutes darbininkų aktoriams
        workerRouter = context().system().actorOf(WorkerActor.props().withRouter(new RoundRobinPool(streamAmount/3)));


        ActorRef resultsActor = context().system().actorOf(ResultsActor.props(streamAmount),"ResultsActor");


        receive(match(Stream.class, stream -> {
            workerRouter.tell(stream, resultsActor);
        }).matchAny(message -> {
            log().info(message.toString());
        }).build());

    }


}
