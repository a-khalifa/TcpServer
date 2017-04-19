package actors

import actors.patterns.WatchedActor
import akka.actor.Actor
import akka.actor.Actor.Receive

/**
  * Created by Ahmed on 4/18/17.
  */
class SystemPropertiesActor extends WatchedActor{
  override def receive: Receive = {
    case _ => log.error("Unsupported");
  }
}
