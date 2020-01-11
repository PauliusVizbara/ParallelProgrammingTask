package com.opencredo.examples.akkajava.egzaminas;

import akka.actor.*;
import com.opencredo.examples.akkajava.streamers.Stream;

import java.util.Random;

import static akka.japi.pf.ReceiveBuilder.match;

/**
 * Darbininko aktorius, priemantis, apdorojantis, atrenkantis transliacijas bei persiunčia jas rezultatų aktoriui.
 *
 */
public class WorkerActor extends AbstractLoggingActor {


    ActorRef resultsActor;
    Random random;

    public static Props props() {
        return Props.create(WorkerActor.class, () -> new WorkerActor());
    }

    private WorkerActor() {
        random = new Random();
        int minSleepValue = 1000;
        int maxSleepValue = 5000;

        int minStreamGrowth = 5000;

        receive(match(Stream.class, streamer -> {
            resultsActor = context().sender(); // Gaunamas rezultatų aktorius
            Stream.calculateStreamGrowth(streamer);
            // Papildomas gijos užmigdymas (įsitikinimui, kad viskas gerai veikia)
            Thread.sleep(random.nextInt((maxSleepValue - minSleepValue) + 1) + minSleepValue);
            // Jeigu transliacija atitinka atrinkimo funkciją
            if (streamer.streamGrowth >= minStreamGrowth) {
                resultsActor.tell(streamer, self());
            }

            //Rezultatų aktoriui pasakoma, kad transliacija buvo apdorota
            resultsActor.tell(MainActor.PROCESSED_STREAM_MESSAGE, self());
        }).matchAny(message -> {
            log().info(message.toString());
        }).build());


    }

}