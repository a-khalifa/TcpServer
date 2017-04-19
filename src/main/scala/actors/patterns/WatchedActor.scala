package actors.patterns

import actors.patterns.Reaper.WatchMe
import akka.actor.{Actor, ActorLogging}

/**
  * Created by ahmed.khalifa on 4/19/2017.
  */
abstract class WatchedActor extends Actor with ActorLogging{
  val reaperActor = context.actorSelection("/user/root/reaper")
  log.info(s"Sending watch me From $self")
  reaperActor ! WatchMe(self)

}
