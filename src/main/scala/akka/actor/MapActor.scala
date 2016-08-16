package akka.actor

/**
  * Created by C.J.YOU on 2016/8/16.
  */
class MapActor  extends  UntypedActor {

  val reduceActor = getContext().actorOf(Props(classOf[ReduceActor]),"reduceActor")

  @scala.throws[Throwable](classOf[Throwable])
  override def onReceive(message: Any): Unit = {

    message match {
      case data: String => // 告诉 reduce 我map好了，map好的消息可用对应message实体进行封装
        println("map got message:" + data)
        reduceActor.tell("map ok!",this.sender())
      case _ =>
        println("map unhandled message")
        unhandled(message)
    }

  }
}
