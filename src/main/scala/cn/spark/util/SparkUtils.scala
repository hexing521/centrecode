package cn.spark.util

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * Created by admin on 2017/11/10.
  */
object SparkUtils {
  //  private val commonProp = PropertiUtils.init("common.properties")

  /**
    * 根据不同的操作系统创建sparkSession 实例
    *
    * @param appName
    * @return
    */
  //  def getSparkSession(appName:String):SparkSession={
  //    val os = System.getProperties().getProperty("os.name")
  //    println("===========os.name=>"+os);
  //    if(os.contains("Windows")) {
  //      System.setProperty("hadoop.home.dir", "D:\\documents\\GitHub\\winutils\\hadoop-2.6.0")
  //      val spark = SparkSession.builder()
  //        .appName(appName)
  //        .master(commonProp.getProperty("master"))//设置master
  //        .config("spark.sql.warehouse.dir", commonProp.getProperty("warehouseLocation"))//用于指定warehouse的默认数据路径
  //        .enableHiveSupport() //使用hive
  //        .getOrCreate()
  //      spark
  //    }else{
  //      val spark = SparkSession.builder()
  //        .appName(appName)
  //        .config("spark.sql.warehouse.dir", commonProp.getProperty("warehouseLocation"))//用于指定warehouse的默认数据路径
  //        .enableHiveSupport() //使用hive
  //        .getOrCreate()
  //      spark
  //    }
  //  }

  def getLocalSparkContext(appName: String) = {
    val sconf = new SparkConf()
    //    System.setProperty("hadoop.home.dir", "D:\\documents\\GitHub\\winutils\\hadoop-2.6.0")
    sconf.setMaster("local[2]")
    sconf.setAppName(appName)
    new SparkContext(sconf)
  }


  def getLocalStreamingContext(appName: String, timeInterval: Int) = {
    val sconf = new SparkConf()
    sconf.setMaster("local[2]")
    sconf.setAppName(appName)
    new StreamingContext(sconf, Seconds(timeInterval))
  }

  def getLocalSparkSession(appName: String) = {
    SparkSession.builder().appName(appName).master("local[2]").getOrCreate()
  }
}
