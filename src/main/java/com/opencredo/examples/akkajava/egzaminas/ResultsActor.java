package com.opencredo.examples.akkajava.egzaminas;

import akka.actor.AbstractLoggingActor;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.opencredo.examples.akkajava.streamers.Stream;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static akka.japi.pf.ReceiveBuilder.match;

public class ResultsActor extends AbstractLoggingActor {

    int streamAmount;
    int processedStreams;

    public static Props props(int streamAmount) {
        return Props.create(ResultsActor.class, () -> new ResultsActor( streamAmount));
    }

    private ResultsActor( int streamAmount) throws IOException {

        this.streamAmount = streamAmount;
        this.processedStreams = 0;

        String fileName = "results.csv";

        FileWriter fw = new FileWriter(fileName);
        BufferedWriter writer = new BufferedWriter(fw);
        writer.write("User name,Viewer count, Started at, Stream duration (h), Stream growth (viewers/h)");
        writer.newLine();
        log().info("Started results actor");

        receive(match(Stream.class, stream -> {
            writer.write(String.format("%s,%d,%tc,%f,%f", stream.user_name, stream.viewer_count, stream.started_at, stream.streamHours, stream.streamGrowth));
            writer.newLine();
            log().info("Received a stream");
        }).match(String.class, (message) -> {
            if (message.equals(MainActor.PROCESSED_STREAM_MESSAGE)) {
                if (++processedStreams == streamAmount) {
                    log().info("End of work");
                    writer.close();
                    context().system().terminate();
                }
            }
        }).matchAny(message -> {

        }).build());

    }


}
