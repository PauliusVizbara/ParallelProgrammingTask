/**
 * Sesijos metu padaryta egzamino užduotis, kurioje su
 * Akka karkasu yra implementuota L1/L2
 *
 * @author  Paulius Vizbara
 * @version 1.0
 * @since   2014-03-31
 */

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


        int streamAmount = 30; // Iš Twitch API norimas transliacijų kiekis

        final Config config = ConfigFactory.load();
        final ActorSystem system = ActorSystem.create("ExamSystem", config); // Sukuriama aktorių sistema

        // Sukuriamas pagrindinis aktorius
        ActorRef mainActor = system.actorOf(MainActor.props(streamAmount), "MainActor");

        // Atsisiunčiami ir į pagrindinį aktorių persiunčiami duomenys
        Stream[] topStreams = API.getTopStreams(streamAmount);
        for (Stream stream : topStreams){
            // Persiunčiamas iš main() atsiųsta transliacija
            mainActor.tell(stream,ActorRef.noSender());
        }




    }
}
