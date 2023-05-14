Lab 9
Student information

Full name: Ricardo Villacana
E-mail: rvill095@ucr.edu
UCR NetID: rvill095
Student ID: 862312585

Answers

(Q1) What is the schema of the file after loading it as a Dataframe

root
|-- Timestamp: string (nullable = true)
|-- Text: string (nullable = true)
|-- Latitude: double (nullable = true)
|-- Longitude: double (nullable = true)

(Q2) Why in the second operation, convert, the order of the objects in the tweetCounty RDD is (tweet, county) while in the first operation, count-by-county, the order of the objects in the spatial join result was (county, tweet)?

In the count-by-county case we spatialjoined the tweetsRDD to the countyRDD: countiesRDD.spatialJoin(tweetsRDD), which is why the order of the
objects in the tweetCounty RDD are (county, tweet). However, in the convert case we spatial joined the countyRDD to the tweetsRDD: tweetsRDD.spatialJoin(countiesRDD)
, which is why the order of the objects in the tweetCounty RDD for convert are (tweet,county).

(Q3) What is the schema of the tweetCounty Dataframe?

 root
|-- g: geometry (nullable = true)
|-- Timestamp: string (nullable = true)
|-- Text: string (nullable = true)
|-- CountyID: string (nullable = true)

(Q4) What is the schema of the convertedDF DataFrame?

root
|-- CountyID: string (nullable = true)
|-- keywords: array (nullable = true)
|    |-- element: string (containsNull = true)
|-- Timestamp: string (nullable = true)

(Q5) For the tweets_10k dataset, what is the size of the decompressed ZIP file as compared to the converted Parquet file?
    
Size of the original decompressed file:	770 KiB
Size of the Parquet file: 344 KiB

(Q6) (Bonus) Write down the SQL query(ies) that you can use to compute the ratios as described above. Briefly explain how your proposed solution works.

Not Applicable
