package com.ghtesthdp

import org.apache.spark.{SparkConf, SparkContext}


object wordcount {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    conf.setMaster("local").setAppName("wordcount")
    val sc = new SparkContext(conf)
//    val input = sc.textFile("hdfs://192.168.190.204:9000/test/word.txt")
    val input = sc.textFile("file:\\E:\\有用的文档\\Hadoop大数据\\实践\\题目三\\Online Retail.csv")
    val lines=input.flatMap(line=>line.split(" "))
    val count = lines.map(word=>(word,1))
    val wordcounts = count.reduceByKey{case (x,y)=>x+y}
    //wordcounts.foreach(println)

  }
}
