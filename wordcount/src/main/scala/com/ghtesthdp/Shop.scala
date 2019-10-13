package com.ghtesthdp



import org.apache.log4j.{Level, Logger}
import org.apache.spark.mllib.recommendation.{ALS, MatrixFactorizationModel, Rating}
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import scala.util.matching.Regex

object Shop {
  def main(args: Array[String]): Unit = {
    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)
    var id=1
    var message:Map[Int,String] = Map()
    var re = 10.0
    val regex = new Regex(" ")
    val conf = new SparkConf().setMaster("yarn-client").setAppName("shop")
    val sc = new SparkContext(conf)
    val input = sc.textFile("hdfs://192.168.190.204:9000/shop/data/OnlineRetail.csv")
    //val input = sc.textFile("file:\\E:\\有用的文档\\Hadoop大数据\\实践\\题目三\\OnlineRetail.csv")
    val lines = input.map(line => line.split(",")).filter(s => !s(1).equals("") && !s(6).equals("") && !s(6).equals("CustomerID") && regex.findFirstIn(s(1)).isEmpty)
    val products = lines.map(s=>(s(1),s(2))).distinct.cache()
    val customers = lines.map(s=>s(6).toInt).distinct.collect()
    val buytime = lines.map(s => ((s(6), s(1)), 1)).reduceByKey((x, y) => x + y)
    val buyquantity = lines.map(s=>((s(6),s(1)),s(3).toInt)).reduceByKey((x,y) => x + y)
    val ratings1 = buytime.join(buyquantity).map{case ((a,b),(x,y))=>((a,b),pingfen(x,y))}.cache()
    var a=0

      val ratings=ratings1.map(s => ({a=a+1
      a%10}, Rating(s._1._1.toInt, s._1._2.toInt, s._2.toDouble))).cache()
          val numPartitions = 4
      val training = ratings.filter(s=>s._1<6)
        .values
        .repartition(numPartitions)
        .cache()
      val validation = ratings.filter(s=>s._1>=7&&s._1<8)
        .values
        .repartition(numPartitions)
        .cache()
      val test = ratings.filter(c=>c._1>=8)
        .values
        .repartition(numPartitions)
        .cache()
  //    val model = ALS.train(training, 10, 10, 0.5)
      message+=(id -> ("完成数据装载，训练集(training):"+training.count() + ",验证集(validation):" + validation.count() + ",测试集(test):" + test.count()))
               id=id+1
          val ranks = List(10,12)
          val lambdas =List(0.1,0.2)
          val numIters = List(6,8,10)
         var bestModel:Option[MatrixFactorizationModel] = None
         var bestValidationRmse = Double.MaxValue
         var bestRank = 0
         var bestLambda = 0.0
         var bestNumber = 0
      message+=(id->"training为训练集，model = ALS.train(training,rank,numIter,lambda)，启动不同参数(rank,numIter,lambda)的训练模型")
      id=id+1
      message+=(id->"使用验证集计算不同模型的均方差值进行比较得出最好的计算模型并保存用来测试集(test)测试")
      id=id+1
          for(rank <- ranks;lambda <- lambdas;numIter <- numIters){
            val model = ALS.train(training,rank,numIter,lambda)
               re= computeRmse(model,validation)
            message+=(id->("描述:--当前rank="+rank +",lambda："+lambda+",numIter："+numIter+",此时验证集均方差误差为:"+re))
            id=id+1
            if(re<bestValidationRmse ){
              bestModel = Some(model)
              bestValidationRmse = re
              bestRank = rank
              bestLambda = lambda
              bestNumber = numIter
            }
          }
      message+=(id->"----------------------------------------------------------------------------")
      id=id+1
      message+=(id->("最佳模型：rank = "+bestRank+",lambda="+bestLambda+",numIter="+bestNumber+",验证最佳均方差误差="+bestValidationRmse))
      id=id+1
      val re1= computeRmse(bestModel.get,test)
      message+=(id->"----------------------------------------------------------------------------")
      id=id+1
      message+=(id->("进行测试集测试均方差误差为："+re1))
      id=id+1
        message+=(id->"****************************************************************************")
      id=id+1
      val model = ALS.train(ratings.values,bestRank,bestNumber,bestLambda)
      for (e <- customers){
        val pre = model.recommendProducts(e,2).toSeq.map(x=>(x.product,x.rating))
        for (elem <- pre) {
          val pro=products.filter(s=>s._1.toInt ==elem._1).first()
          message+=(id->("为用户"+e+"推荐商品："+pro._2+",推荐评分："+elem._2))
          id=id+1
        }
        message+=(id->"----------------------------------------------------------------------------")
        id=id+1
      }
        sc.makeRDD(message.toSeq.sortBy(_._1).toArray).saveAsTextFile("hdfs://192.168.190.204:9000/shop/message")
        //message.toSeq.sortBy(_._1).toArray.foreach(println)

}
  //评分
  //数据记录共267605
  //经计算 购买次数大于50的记录共5条,大于30的记录共41条，大于20的记录共124条,大于10的记录共1006条 最大购买次数记为10 购买次数为1有198881条
  //经计算 购买数量大于100的记录共5772条，大于300的记录共1470条，大于500的记录共782条，大于100的记录共5722条 最大购买数量记为100 最小购买数量记为0
  def pingfen(a:Int,b:Int): Double ={
    var bt=a.toDouble;var bq=b.toDouble
    val minTime = 0
    val maxTime = 10
    val minQuan = 0
    val maxQuan = 100
    if(bt>10)bt=maxTime
    else {
      if(bt==1)bt=5
      else if(bt>1)bt=5*math.log10(bt*6)
    }
    if(bq>100)bq=maxQuan
    if(bq<0){bt=5;bq=minQuan}
    (bt+math.log10(bq+1)).formatted("%.2f").toDouble
  }
  //均方差
  def computeRmse(model: MatrixFactorizationModel, data: RDD[Rating]): Double ={
    val userproducts = data.map{case Rating(user, product, rate)=>(user,product)}
    val predict = model.predict(userproducts).map{case Rating(user,product,rate)=>((user,product),rate)}
    val rateandpreds=data.map{case Rating(user,product,rate)=>((user,product),rate)}.join(predict)
    rateandpreds.map{case ((user,product),(r1,r2))=>val err=r1-r2
      err*err}.mean()
  }
}
