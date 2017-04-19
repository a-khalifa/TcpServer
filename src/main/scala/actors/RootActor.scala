package actors

import actors.Messages.StopMessage
import actors.patterns.MainReaper
import akka.actor.{Actor, ActorLogging, PoisonPill, Props}

/**
  * Created by Ahmed on 4/18/17.
  */
class RootActor extends Actor with ActorLogging{
  import context._

  import scala.concurrent.duration._

  val reaper = context.actorOf(Props[MainReaper], "reaper")
  val systemPropertiesActor = context.actorOf(Props[SystemPropertiesActor], "systemPropertiesActor")
  val controllerActor = context.actorOf(Props[ControllerActor], "controllerActor")


  system.scheduler.scheduleOnce(10000 millis, self, StopMessage)


  override def receive = {
    case StopMessage =>
      log.info("Going to Stop")
      systemPropertiesActor ! PoisonPill
      controllerActor ! StopMessage
      context stop self
    case _ => log.error("Unsupported Message")
  }

}
