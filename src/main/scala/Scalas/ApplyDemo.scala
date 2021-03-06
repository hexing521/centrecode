package Scalas

/**
  *【Class中的Apply方法:】
  * 测试apply方法的使用
  * apply方法常用于创建类实例的工厂方法。
  * 当对象（伴生对象）以函数的方式进行调用时，scala 会隐式地将调用改为在该对象上调用apply方法。
  * 例如： Demo(“hello”) 实际调用的是 Demo.apply(“hello”), 因此apply方法又被称为注入方法。
  *
  * 直接调用ApplyDemo伴生对象里面的apply方法,实例化一个ApplyDemo对象，无需使用new去创建一个对象
  * 然后将实例对象复制给变量test   test就可以直接调用ApplyDemo的haveatry方法
  *
  *【Array数组的apply方法：】
  *同理，可以直接使用val arr=Array(1,2,3)  无需用new，使用Array对象就可以定义一个数组，应为调用Array(1,2,3)就会触发
  * Array的apply()方法的调用  方法如下:
  * def apply[T: ClassTag](xs: T*): Array[T] = {
  * val array = new Array[T](xs.length)
  * var i = 0
  * for (x <- xs.iterator) { array(i) = x; i += 1 }
  * array
  * }
  *
  */
class ApplyDemo {

  //定义ApplyDemo的apply方法
  def apply() = {
    println("apply applyDemo class")
  }

  def haveatry(): Unit = {
    println("hava a try")
  }

}


//todo 伴生对象
object ApplyDemo {
  //伴生对象的apply方法  在创建ApplyDemo对象的时候自动调用apply方法
  def apply(): ApplyDemo = {
    println("apply applyDemo object")
    new ApplyDemo() //新建一个ApplyDemo实例
  }
}

//todo 测试类
object test {
  def main(args: Array[String]): Unit = {
    //    val test = new ApplyDemo()
    val test = ApplyDemo()
    test.haveatry()
    println(test) //ApplyDemo class 的引用地址
    println(test()) //会触发ApplyDemo class 的apply方法
  }
}
