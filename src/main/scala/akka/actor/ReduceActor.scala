package akka.actor

/**
  * Created by C.J.YOU on 2016/8/16.
  */
class ReduceActor extends  UntypedActor {

  val aggregateActor = getContext().actorOf(Props(classOf[AggregateActor]), "aggregateActor")

  @scala.throws[Throwable](classOf[Throwable])
  override def onReceive(message: Any): Unit = {

    message match  {
      case mes: String =>
        println("reduce got message:" + mes)
        aggregateActor.tell("reduce ok!",this.sender())
      case _ =>
        println("map unhandled message")
        unhandled(message)
    }
  }
}
