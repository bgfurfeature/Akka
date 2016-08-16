package akka.actor

import akka.event.Logging
import akka.message.AggData

/**
  * Created by C.J.YOU on 2016/8/16.
  * actor是从系统创建的。也可以从其它的actor使用actor 上下文（context） 来创建. 其中的区别在于监管树是如何组织的。
  * 使用上下文时当前的actor将成为所创建的子actor的监管者。
  * 而使用系统时创建的actor将成为顶级actor，它由系统（内部监管actor）来监管
  */
class MasterActor extends  Actor {

  val aggregateActor = context.actorOf(Props(classOf[AggregateActor]), "aggregateActor")

  val mapActor = context.actorOf(Props(classOf[MapActor]),"mapActor")

  val log = Logging(context.system, this)

  /*@scala.throws[Throwable](classOf[Throwable])
  override def onReceive(message: Any): Unit = {

    message match {
      case mes: String => // 如果收到的是字符消息通知MapActor
        log.info("master got message:" + mes)
        mapActor.tell(mes,this.sender())

      case data: AggData => // 如果收到的是AggData消息，告诉aggregateActor 处理
        aggregateActor.tell(data,this.sender())
      case _ =>
        log.info("master unhandled message")
        unhandled(message)
    }

  }*/

  // 消息处理
  override def receive: Receive = {
    case mes: String => // 如果收到的是字符消息通知MapActor
      log.info("master got message:" + mes)
      mapActor.tell(mes,this.sender())

    case data: AggData => // 如果收到的是AggData消息，告诉aggregateActor 处理
      aggregateActor.tell(data,this.sender())
    case _ =>
      log.info("master unhandled message")
      unhandled(_)
  }

  @scala.throws[Exception](classOf[Exception])
  override def preStart(): Unit = {
    // 初始化Actor代码块
  }




}
