import actors.{RootActor, ServerActor}
import akka.actor.{ActorSystem, PoisonPill, Props}

/**
  * Created by Ahmed on 4/18/17.
  */
object main extends App{
  val system = ActorSystem("tcp_server")
  val serverActor = system.actorOf(Props[RootActor], "rootActor")
}
