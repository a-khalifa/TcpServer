package actors.patterns

import akka.actor.{Actor, ActorLogging, ActorRef, Terminated}

import scala.collection.mutable.ArrayBuffer

object Reaper {
  // Used by others to register an Actor for watching
  case class WatchMe(ref: ActorRef)
}

abstract class Reaper extends Actor with ActorLogging {
  import Reaper._

  // Keep track of what we're watching
  val watched = ArrayBuffer.empty[ActorRef]

  // Derivations need to implement this method.  It's the
  // hook that's called when everything's dead
  def allSoulsReaped(): Unit

  // Watch and check for termination
  final def receive = {
    case WatchMe(ref) =>
      log.info(s"Watching Actor: $ref")
      context.watch(ref)
      watched += ref
    case Terminated(ref) =>
      log.info(s"Actor: $ref Terminated")
      watched -= ref
      if (watched.isEmpty) allSoulsReaped()
  }
}


class MainReaper extends Reaper {
  // Shutdown
  def allSoulsReaped(): Unit = context.system.terminate()
}