package actors

import akka.actor.{Actor, ActorRef, Props}
import akka.io.{IO, Tcp}
import akka.util.ByteString
import java.net.InetSocketAddress

import actors.Messages.StopMessage



object ServerActor{
  def props(port: Int) = Props(new ServerActor(port))
}

class ServerActor(port: Int) extends Actor {

  import Tcp._
  import context.system

  IO(Tcp) ! Bind(self, new InetSocketAddress("localhost", port))

  def receive = {
    case b @ Bound(localAddress) =>
      println(s"Connected to Port: $port")

    case CommandFailed(_: Bind) => context stop self

    case c @ Connected(remote, local) =>
      val handler = context.actorOf(Props[ClientHandler])
      val connection = sender()
      connection ! Register(handler)

    case StopMessage =>
      println(self.path.toString + " StopMessage")
      context stop self
  }

}