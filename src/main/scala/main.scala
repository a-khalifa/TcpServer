import actors.RootActor
import akka.actor.{ActorSystem, Props}
import com.typesafe.config.{Config, ConfigFactory}
import config.DbConfig
import utils.ConnectionManager

/**
  * Created by Ahmed on 4/18/17.
  */
object main extends App{
  val config = ConfigFactory.load()
  val dbConfig = getDbConfig(config)
  println(dbConfig.toString)

  ConnectionManager.init(dbConfig)

  val system = ActorSystem("tcp_server")
  val serverActor = system.actorOf(Props[RootActor], "root")


  def getDbConfig(config: Config) : DbConfig = {
    val driverClass = config.getString("db.driverClass")
    val url = config.getString("db.url")
    val userName = config.getString("db.userName")
    val password = config.getString("db.password")
    new DbConfig(driverClass, url, userName, password)
  }
}
