package akka.actor

import akka.message.AggData

/**
  * Created by C.J.YOU on 2016/8/16.
  */
class MasterActor extends  UntypedActor {


  val aggregateActor = getContext().actorOf(Props(classOf[AggregateActor]), "aggregateActor")

  val mapActor = getContext().actorOf(Props(classOf[MapActor]),"mapActor")

  @scala.throws[Throwable](classOf[Throwable])
  override def onReceive(message: Any): Unit = {

    message match {
      case mes: String => // 如果收到的是字符消息通知MapActor
        println("master got message:" + mes)
        mapActor.tell(mes,this.sender())

      case data: AggData => // 如果收到的是AggData消息，告诉aggregateActor 处理
        aggregateActor.tell(data,this.sender())
      case _ =>
        println("master unhandled message")
        unhandled(message)
    }

  }

}
