package edu.ucr.cs.cs167.rvill095
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.Map
/**
 * @author ${user.name}
 */
object App {
  
  def main(args : Array[String]) {
    val command: String = args(0)
    val inputfile: String = args(1)
    val ResponseCode: Int = 5
    val time: Int = 2
    val Bytes: Int = 6

    val conf = new SparkConf
    if (!conf.contains("spark.master"))
      conf.setMaster("local[*]")
    println(s"Using Spark master '${conf.get("spark.master")}'")
    conf.setAppName("CS167_Lab6_App")
    val sparkContext = new SparkContext(conf)
    try {
      val inputRDD: RDD[String] = sparkContext.textFile(inputfile)
      val validLines: RDD[String] = inputRDD.filter(s => !(s.contains("host\tlogname")))// TODO 1a: filter lines which do not start with "host\tlogname" from `inputRDD`
      val parsedLines: RDD[Array[String]] = validLines.map(line => line.split("\t"))// TODO 1b: split each line by "\t" from `validLines` via `map`
      val t1 = System.nanoTime
      var valid_command = true
      command match {
        case "count-all" =>
          // Count total number of records in the file
          val count: Long = parsedLines.count()// TODO 2: count total number of records in the file on `parsedLines`
            println(s"Total count for file '$inputfile' is $count")
        case "code-filter" =>
          // Filter the file by response code, args(2), and print the total number of matching lines
          val responseCode: String = args(2)
          val filteredLines: RDD[Array[String]] = parsedLines.filter(_(ResponseCode) == responseCode)// TODO 3: `filter` on `parsedLines` by `responseCode`
          val count: Long = filteredLines.count()
          println(s"Total count for file '$inputfile' with response code $responseCode is $count")
        case "time-filter" =>
          // Filter by time range [from = args(2), to = args(3)], and print the total number of matching lines
          val from: Long = args(2).toLong
          val to: Long = args(3).toLong
          val filteredLines: RDD[Array[String]] = parsedLines.filter(line => line(time).toLong >= from && line(time).toLong <= to)// TODO 4: `filter` on `parsedLines` by time (column 2) with `from` and `to`
          val count: Long = filteredLines.count()
          println(s"Total count for file '$inputfile' in time range [$from, $to] is $count")
        case "count-by-code" =>
          // Group the lines by response code and count the number of records per group
          val loglinesByCode: RDD[(String, Long)] = parsedLines.map(line => (line(ResponseCode), 1))// TODO 5a: `map` on `parsedLines` by response code (column 5)
          val counts: Map[String, Long] = loglinesByCode.countByKey()// TODO 5b: `countByKey` on `loglinesByCode`
            println(s"Number of lines per code for the file '$inputfile'")
          println("Code,Count")
          counts.toSeq.sortBy(_._1).foreach(pair => println(s"${pair._1},${pair._2}"))
        case "sum-bytes-by-code" =>
          // Group the lines by response code and sum the total bytes per group
          val loglinesByCode: RDD[(String, Long)] = parsedLines.map(codes => (codes(ResponseCode), codes(Bytes).toLong))// TODO 6a: `map` on `parsedLines` by response code (column 5) and bytes (column 6)
          val sums: RDD[(String, Long)] = loglinesByCode.reduceByKey((b1,b2) => (b1+b2))// TODO 6b: `reduceByKey` on `loglinesByCode`
            println(s"Total bytes per code for the file '$inputfile'")
          println("Code,Sum(bytes)")
          sums.sortByKey().collect().foreach(pair => println(s"${pair._1},${pair._2}"))
        case "avg-bytes-by-code" =>
          // Group the liens by response code and calculate the average bytes per group
          val loglinesByCode: RDD[(String, Long)] = parsedLines.map(line => (line(ResponseCode), line(Bytes).toLong))// TODO 7a: `map` on `parsedLines` by response code (column 5) and bytes (column 6)
          val sums: RDD[(String, Long)] = loglinesByCode.reduceByKey((x, y) => x + y)// TODO 7b: `reduceByKey` on `loglinesByCode`
          val counts: Map[String, Long] = loglinesByCode.countByKey()// TODO 7c: `countByKey` on `loglinesByCode`
            println(s"Average bytes per code for the file '$inputfile'")
          println("Code,Avg(bytes)")
          sums.sortByKey().collect().foreach(pair => {
            val code = pair._1
            val sum = pair._2
            val count = counts(code)
            println(s"$code,${sum.toDouble / count}")
          })
        // TODO 7d: replace the above codes for bonus with `aggregateByKey`
        case "top-host" =>
          // Print the host the largest number of lines and print the number of lines
          val loglinesByHost: RDD[(String, Long)] = parsedLines.map(codes => (codes(0), 1))// TODO 8a: `map` on `parsedLines` by host (column 0)
          val counts: RDD[(String, Long)] = loglinesByHost.reduceByKey((x, y) => x + y)// TODO 8b: `reduceByKey` on `loglinesByHost`
          val sorted: RDD[(String, Long)] = counts.sortBy(x => x._2, false)// TODO 8c: `sortBy` on `counts`
          val topHost: (String, Long) = sorted.first()// TODO 8d: `first` on `sorted`
            println(s"Top host in the file '$inputfile' by number of entries")
          println(s"Host: ${topHost._1}")
          println(s"Number of entries: ${topHost._2}")
        case _ => valid_command = false
      }
      val t2 = System.nanoTime
      if (valid_command)
        println(s"Command '$command' on file '$inputfile' finished in ${(t2 - t1) * 1E-9} seconds")
      else
        Console.err.println(s"Invalid command '$command'")
    } finally {
      sparkContext.stop
    }
  }
}
