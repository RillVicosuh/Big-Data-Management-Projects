#!/usr/bin/env sh
mvn clean package

hadoop jar target/rvill095_lab2-1.0-SNAPSHOT.jar edu.ucr.cs.cs167.rvill095_lab2.App file:///home/cs167/rvill095/cs167/workspace/rvill095_lab2/AREAWATER.csv hdfs://localhost:9000/user/AREAWATER_Copy.csv

hadoop jar target/rvill095_lab2-1.0-SNAPSHOT.jar edu.ucr.cs.cs167.rvill095_lab2.App hdfs://localhost:9000/user/AREAWATER_Copy.csv file:///home/cs167/rvill095/cs167/workspace/rvill095_lab2/AREAWATER_copy2.csv

hadoop jar target/rvill095_lab2-1.0-SNAPSHOT.jar edu.ucr.cs.cs167.rvill095_lab2.App hdfs://localhost:9000/user/AREAWATER_Copy.csv hdfs://localhost:9000/user/AREAWATER_Copy3.csv