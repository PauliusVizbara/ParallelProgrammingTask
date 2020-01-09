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


        int streamerAmount = 30;

        Stream[] topStreams = API.getTopStreams(streamerAmount);


        int workerCount = streamerAmount / 3 ;

        final Config config = ConfigFactory.load();
        final ActorSystem system = ActorSystem.create("EgzaminoSistema", config);

        ActorRef master = system.actorOf(WorkerActor.props().withRouter(new RoundRobinPool(workerCount)));
        ActorRef resultsActor = system.actorOf(ResultsActor.props(), "ResultsActor");



        for (Stream stream : topStreams){
            master.tell(stream,master);

        }


    }
}
