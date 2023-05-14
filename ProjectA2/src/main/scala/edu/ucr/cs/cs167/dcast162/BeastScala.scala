package edu.ucr.cs.cs167.dcast162
import edu.ucr.cs.bdlab.beast.geolite.{Feature, IFeature}
import org.apache.spark.SparkConf
import org.apache.spark.beast.SparkSQLRegistration
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}

import scala.collection.Map

object BeastScala {
  def main(args: Array[String]): Unit = {
    // Initialize Spark context

    val conf = new SparkConf().setAppName("Beast Example")
    // Set Spark master to local if not already set
    if (!conf.contains("spark.master"))
      conf.setMaster("local[*]")

    conf.set("spark.executor.memory", "4g")
    conf.set("spark.sql.legacy.timeParserPolicy", "LEGACY")
    val spark: SparkSession.Builder = SparkSession.builder().config(conf)

    val sparkSession: SparkSession = spark.getOrCreate()
    val sparkContext = sparkSession.sparkContext
    SparkSQLRegistration.registerUDT
    SparkSQLRegistration.registerUDF(sparkSession)

    val operation: String = args(0)
    val inputFile: String = args(1)
    try {
      // Import Beast features
      import edu.ucr.cs.bdlab.beast._
      val t1 = System.nanoTime()
      var validOperation = true

      operation match {
        case "task-1" =>
          val crimeDF = sparkSession.read.format("csv")
            .option("sep", ",")
            .option("inferSchema", "true")
            .option("header", "true")
            .load(inputFile)

          var crimeDFWithGeo = crimeDF.selectExpr("*", "ST_CreatePoint(x, y) AS geometry")

          for (columnName <- crimeDFWithGeo.columns) {
            if (columnName.contains(" ")) {
              val newColumnName = columnName.replace(" ", "")
              crimeDFWithGeo = crimeDFWithGeo.withColumnRenamed(columnName, newColumnName)
            }
          }
          val crimeRDD = crimeDFWithGeo.toSpatialRDD
          val zipcodeRDD = sparkContext.shapefile("tl_2018_us_zcta510.zip")
          val crimeZipcodeRDD = crimeRDD.spatialJoin(zipcodeRDD)
          val crimeZipcode = crimeZipcodeRDD.map({ case (crime, zipcode) => Feature.append(crime, zipcode.getAs[String]("ZCTA5CE10"), "ZIPCode") })
            .toDataFrame(sparkSession)

          crimeZipcode.drop("geometry").write.mode(SaveMode.Overwrite).parquet("Chicago_Crimes_ZIP")
        //          crimeZipcode.drop("geometry").show()

        case "task-2" =>
          sparkSession.read.parquet("Chicago_Crimes_ZIP").createOrReplaceTempView("crimes")
          sparkSession.sql(
            s"""
              SELECT ZIPCode, count(*) AS count
              FROM crimes
              GROUP BY ZIPCode
            """).createOrReplaceTempView("ZIPCode_counts")
          sparkContext.shapefile("tl_2018_us_zcta510.zip").toDataFrame(sparkSession).createOrReplaceTempView("ZIPCodes")
          sparkSession.sql(
            s"""
              SELECT ZIPCode, g, count
              FROM ZIPCode_counts, ZIPCodes
              WHERE ZIPCode = ZCTA5CE10
            """).toSpatialRDD.coalesce(1).saveAsShapefile("ZIPCodeCrimeCount")

        case "task-3" =>
          val date1: String = args(2)
          val date2: String = args(3)
          //insert converted DF file
          sparkSession.read.parquet(inputFile).createOrReplaceTempView("crimes")
          val resultDF = sparkSession.sql(
            s"""
                  SELECT PrimaryType, COUNT(*) AS count
                  FROM (
                    SELECT to_timestamp(Date, 'MM/dd/yyyy hh:mm:ss a') AS Timestamp, PrimaryType
                    FROM crimes
                    WHERE to_date(Date, 'MM/dd/yyyy') BETWEEN to_date('$date1', 'MM/dd/yyyy') AND to_date('$date2', 'MM/dd/yyyy')
                  )
                  GROUP BY PrimaryTypeS
                  ORDER BY count DESC
                """)

          ///resultDF.foreach(row => println(s"${row.get(0)}\t${row.get(1)}"))

          // Write the result to a CSV file
          resultDF.coalesce(1)
            .write
            .mode(SaveMode.Overwrite)
            .option("header", "true")
            .csv("CrimeTypeCount")
      }
      val t2 = System.nanoTime()
      if (validOperation)
        println(s"Operation '$operation' on file '$inputFile' took ${(t2 - t1) * 1E-9} seconds")
      else
        Console.err.println(s"Invalid operation '$operation'")
    } finally {
      sparkSession.stop()
    }
  }
}