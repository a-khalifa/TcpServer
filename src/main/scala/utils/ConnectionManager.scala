package utils

import java.sql.Connection

import com.mchange.v2.c3p0.ComboPooledDataSource
import config.DbConfig

/**
  * Created by Ahmed on 4/19/17.
  */
object ConnectionManager{
  private var cpds : ComboPooledDataSource = null

  def init(dbConfig: DbConfig): Unit ={
    val tcpds = new ComboPooledDataSource()
    tcpds.setDriverClass(dbConfig.driverClass)
    tcpds.setJdbcUrl(dbConfig.url)
    tcpds.setUser(dbConfig.userName)
    tcpds.setPassword(dbConfig.password)
    cpds = tcpds
  }

  def getConnection(): Connection ={
    if(cpds != null) cpds getConnection
    else
      throw new Exception("Datasource not initialized")
  }
}