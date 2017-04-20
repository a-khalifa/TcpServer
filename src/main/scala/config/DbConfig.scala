package config

/**
  * Created by Ahmed on 4/19/17.
  */
case class DbConfig (driverClass:String, url: String, userName: String, password: String){

  override def toString: String = s"$driverClass, $url, $userName, $password"
}
