package actors

import akka.actor.Actor
import akka.actor.Actor.Receive

/**
  * Created by Ahmed on 4/18/17.
  */
class SystemPropertiesActor extends Actor{
  override def receive: Receive = {
    case _ => println("Unsupported");
  }
}
