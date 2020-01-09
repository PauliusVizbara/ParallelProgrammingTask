package com.opencredo.examples.akkajava.egzaminas;

import akka.actor.AbstractLoggingActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.routing.RoundRobinPool;
import com.opencredo.examples.akkajava.Order;

import static akka.japi.pf.ReceiveBuilder.match;

public class MasterActor extends AbstractLoggingActor {


    public static Props props(int workerCount) {
        return Props.create(MasterActor.class, () -> new MasterActor(workerCount));
    }

    private MasterActor(int workerCount) {
        receive(match(Order.class, order -> {
        }).matchAny(message -> {
            log().info(message.toString());
        }).build());


        ActorRef roundRobinRouter = getContext().actorOf(WorkerActor.props().withRouter(new RoundRobinPool(workerCount)),
                "Router");

        for (int i = 1; i <= 10; i++) {
            roundRobinRouter.tell(i, this.self());
        }

    }


}
