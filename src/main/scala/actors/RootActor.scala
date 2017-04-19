package actors

import actors.Messages.StopMessage
import actors.patterns.ProductionReaper
import akka.actor.{Actor, PoisonPill, Props}

/**
  * Created by Ahmed on 4/18/17.
  */
class RootActor extends Actor{
  import context._

  import scala.concurrent.duration._

  val reaper = context.actorOf(Props[ProductionReaper], "reaper")
  val systemPropertiesActor = context.actorOf(Props[SystemPropertiesActor], "systemPropertiesActor")
  val controllerActor = context.actorOf(Props[ControllerActor], "controllerActor")


  system.scheduler.scheduleOnce(10000 millis, self, StopMessage)


  override def receive = {
    case StopMessage =>
      println(self.path.toString + " Stop")
      systemPropertiesActor ! StopMessage
      controllerActor ! StopMessage
      context stop self
    case _ => println("Unsupported Message")
  }

}
