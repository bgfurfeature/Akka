package akka.actor

/**
  * Created by C.J.YOU on 2016/8/16.
  */
class AggregateActor extends  UntypedActor {

  @scala.throws[Throwable](classOf[Throwable])
  override def onReceive(message: Any): Unit = {

    message match {
      case data: String =>
        println("Aggregate got message:" + data)
        println("Aggregate ok!")
      case _ =>
        println("map unhandled message")
        unhandled(message)
    }
  }
}
