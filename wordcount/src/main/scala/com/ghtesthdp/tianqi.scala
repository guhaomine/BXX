package com.ghtesthdp


import org.apache.log4j.{Level, Logger}
import org.apache.spark.mllib.regression.IsotonicRegression
import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.regression.LinearRegressionModel
import org.apache.spark.mllib.regression.LinearRegressionWithSGD


object tianqi {
  def main(args: Array[String]): Unit = {
    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)
    val conf = new SparkConf().setMaster("local").setAppName("tianqi")
    val sc = new SparkContext(conf)


    val spark = SparkSession.builder
      .master("local[1]")
      .appName("tianqi")
      .getOrCreate()
    val df = spark.read.option("header", "true").option("inferSchema", true).csv("E:\\有用的文档\\Hadoop大数据\\实践\\任务3数据1\\csvs_per_year\\madrid_2017.csv")
    val data=df.na.drop(Array("PM10","CO", "NO_2", "NOx", "O_3", "SO_2"))

    val parsedData = data.rdd.map { s =>
      LabeledPoint(s.getAs[Double]("PM10"),
        Vectors.dense(s.getAs[Double]("CO"),
          s.getAs[Double]("NO_2"),
          s.getAs[Double]("NOx"),
          s.getAs[Double]("O_3"),
          s.getAs[Double]("SO_2")) )}
 val Array(train,test)=parsedData.randomSplit(Array(0.6,0.4),4)


    // Building the model
//    val numIterations = 10
//    val stepSize = 0.03
//    val model = LinearRegressionWithSGD.train(train, numIterations, stepSize)
//        val valuesAndPreds = test.map { point =>
//          val prediction = model.predict(point.features)
//          (point.label, prediction)
//        }
//    valuesAndPreds.foreach(println)
//          val MSE = valuesAndPreds.map{ case(v, p) => math.pow((v - p), 2) }.mean()
//
//          println("a="+numIterations+"b="+stepSize+",均方差:"+MSE)
    val iters=List(9,10)
    val steps=List(0.01,0.03,0.05,0.001,0.003,0.005,0.006)
    for(a <- iters;b <- steps){
      val model = LinearRegressionWithSGD.train(train, a, b)
      val valuesAndPreds = test.map { point =>
        val prediction = model.predict(point.features)
        (point.label, prediction)}
      val MSE = valuesAndPreds.map{ case(v, p) => math.pow(v - p, 2) }.mean()
      println("a="+a+"b="+b+",均方差:"+MSE)
    }
  }

}
