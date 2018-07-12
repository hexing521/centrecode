package cn.spark.core.RDDS

import org.apache.spark.rdd.RDD
import org.apache.spark.{Partition, SparkContext, TaskContext}

class linePartition(idx: Int, val eles: Array[String]) extends Partition {
  override def index: Int = idx
}

/**
  * 自定义RDD 实现RDD的主要方法getPartitions和compute
  * @param sc
  * @param str
  * @param numPartitions
  */
class StringGenerateRDD(sc: SparkContext,
                        str: String,
                        numPartitions: Int
                       ) extends RDD[String](sc, Nil) {

  val stringSource = new StringBuilder()
  val currentOffset = 0L;

  override def getPartitions: Array[Partition] = {
    val splitStr = str.split("\n");
    val array = new Array[Partition](numPartitions)
    for (i <- 0 until numPartitions) {
      val start = ((i * splitStr.length) / numPartitions).toInt
      val end = (((i + 1) * splitStr.length) / numPartitions).toInt
      array(i) = new linePartition(i, splitStr.slice(start, end))
    }
    array
  }

  override def compute(split: Partition, context: TaskContext): Iterator[String] = {

    val eles = split.asInstanceOf[linePartition].eles
    eles.toIterator
  }
}