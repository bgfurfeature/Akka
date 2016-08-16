import akka.actor.{ActorSystem, MasterActor, Props}

/**
  * Created by C.J.YOU on 2016/8/16.
  */
object Scheduler {

  def main(args: Array[String]) {

    val system = ActorSystem("""TestAkkaSystem""")
    val masterActor = system.actorOf(Props[MasterActor], name = "MasterActor")
    masterActor.tell("lets do action",masterActor)
  }

}
