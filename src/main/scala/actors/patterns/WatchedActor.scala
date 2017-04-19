package actors.patterns

import actors.patterns.Reaper.WatchMe
import akka.actor.Actor

/**
  * Created by ahmed.khalifa on 4/19/2017.
  */
abstract class WatchedActor extends Actor{
  val reaperActor = context.actorSelection("/user/root/reaper")
  println(s"Sending watch me From $self")
  reaperActor ! WatchMe(self)

}
