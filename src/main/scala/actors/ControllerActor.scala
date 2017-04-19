package actors

import actors.ControllerActor.RefreshMessage
import actors.Messages.StopMessage
import actors.patterns.WatchedActor
import akka.actor.{Actor, ActorRef, PoisonPill}

import scala.concurrent.duration._

object ControllerActor{
  case class RefreshMessage();
}

class ControllerActor extends WatchedActor{
  import context._
  val serversMap : Map[Int, ActorRef] = Map()

  override def preStart() =
    context.system.scheduler.scheduleOnce(500 millis, self, RefreshMessage)


  override def receive = {
    case RefreshMessage => refreshServersMap();
    case StopMessage =>
      println(self.path.toString + " StopMessage")
      serversMap.values.foreach(_ ! StopMessage)
      context stop self
    case _ => println("Unsupported Message")
  }


  def refreshServersMap () = {
    println("Retrieved Refresh Message")
    context.system.scheduler.scheduleOnce(1000 millis, self, RefreshMessage)
  };



}
