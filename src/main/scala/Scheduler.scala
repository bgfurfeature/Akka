import akka.actor.{ActorSystem, MasterActor, Props}
import akka.message.Result
import org.apache.spark.SparkEnv

/**
  * Created by C.J.YOU on 2016/8/16.
  * 程序主入口
  */
object Scheduler {

  def main(args: Array[String]) {

    val driverPort = 7777
    val driverHost = "localhost"

    val actorName = "helloer"

    val system = ActorSystem("""sparkDriver""")
    val masterActor = system.actorOf(Props[MasterActor], name = "MasterActor")

    masterActor.tell("lets do action",masterActor)

    Thread.sleep(3000)  // 等待计算结束通知

    masterActor ! new Result() // 告诉masterActor 我需要得到计算结果

    system.terminate() // 结束actor system

    /*[INFO] [08/18/2016 14:34:26.636] [TestAkkaSystem-akka.actor.default-dispatcher-5] [akka://TestAkkaSystem/user/MasterActor] master got message:lets do action
    [INFO] [08/18/2016 14:34:26.640] [TestAkkaSystem-akka.actor.default-dispatcher-6] [akka://TestAkkaSystem/user/MasterActor/reduceActor] reduce got message:map ok!
    [INFO] [08/18/2016 14:34:26.640] [TestAkkaSystem-akka.actor.default-dispatcher-6] [akka://TestAkkaSystem/user/MasterActor/aggregateActor] Aggregate got message:reduce ok!
    [INFO] [08/18/2016 14:34:26.640] [TestAkkaSystem-akka.actor.default-dispatcher-6] [akka://TestAkkaSystem/user/MasterActor/aggregateActor] Aggregate ok!
    注意: reduceActor,aggregateActor 都为 MasterActor监管，actor之间发送消息的实例需要和接受消息的实例需要一致，否则出现消息无法处理 */


  }

}
