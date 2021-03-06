//package cn.spark.core
//
//import scala.reflect.ClassTag
//import scala.util.Random
//
///**
//  * 水塘抽样算法
//  */
//object ReservoirSampleAndCount {
//
//  /**
//    * Reservoir sampling implementation that also returns the input size.
//    *
//    * @param input input size
//    * @param k     reservoir size
//    * @param seed  random seed
//    * @return (samples, input size)
//    */
//  def reservoirSampleAndCount[T: ClassTag](input: Iterator[T],k: Int, seed: Long = Random.nextLong())
//  : (Array[T], Long) = {
//    val reservoir = new Array[T](k)
//    // Put the first k elements in the reservoir.
//    var i = 0
//    while (i < k && input.hasNext) {
//      val item = input.next()
//      reservoir(i) = item
//      i += 1
//    }
//
//    // If we have consumed all the elements, return them. Otherwise do the replacement.
//    if (i < k) {
//      // If input size < k, trim the array to return only an array of input size.
//      val trimReservoir = new Array[T](i)
//      System.arraycopy(reservoir, 0, trimReservoir, 0, i)
//      (trimReservoir, i)
//    } else {
//      // If input size > k, continue the sampling process.
//      var l = i.toLong
//      val rand = new XORShiftRandom(seed)
//      while (input.hasNext) {
//        val item = input.next()
//        val replacementIndex = (rand.nextDouble() * l).toLong
//        if (replacementIndex < k) {
//          reservoir(replacementIndex.toInt) = item
//        }
//        l += 1
//      }
//      (reservoir, l)
//    }
//
//  }
