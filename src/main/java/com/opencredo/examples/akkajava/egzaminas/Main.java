package com.opencredo.examples.akkajava.egzaminas;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.opencredo.examples.akkajava.streamers.API;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class Main {
    public static void main(String[] args) throws Exception {

        API.getTopStreamers(20);


//        int workerCount = 6;
//
//        final Config config = ConfigFactory.load();
//        final ActorSystem system = ActorSystem.create("EgzaminoSistema", config);
//
//        ActorRef master = system.actorOf(MasterActor.props(workerCount), "Master");
//        ActorRef resultsActor = system.actorOf(ResultsActor.props(), "ResultsActor");


    }
}
