package akka.actor

import akka.event.Logging

/**
  * Created by C.J.YOU on 2016/8/16.
  */
class ReduceActor extends  UntypedActor {

  val aggregateActor = getContext().actorOf(Props(classOf[AggregateActor]), "aggregateActor")

  val log = Logging(context.system, this)

  @scala.throws[Throwable](classOf[Throwable])
  override def onReceive(message: Any): Unit = {

    message match  {
      case mes: String =>
        log.info("reduce got message:" + mes)
        aggregateActor.tell("reduce ok!",this.sender())
      case _ =>
        log.info("map unhandled message")
        unhandled(message)
    }
  }
}
