package com.opencredo.examples.akkajava.egzaminas;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.routing.RoundRobinPool;
import com.opencredo.examples.akkajava.streamers.API;
import com.opencredo.examples.akkajava.streamers.Stream;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class Main {
    public static void main(String[] args) throws Exception {


        int streamAmount = 30;

        final Config config = ConfigFactory.load();
        final ActorSystem system = ActorSystem.create("ExamSystem", config);

        ActorRef mainActor = system.actorOf(MainActor.props(system, streamAmount), "MainActor");


        Stream[] topStreams = API.getTopStreams(streamAmount);
        for (Stream stream : topStreams){
            mainActor.tell(stream,ActorRef.noSender());
        }




    }
}
