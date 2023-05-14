#!/usr/bin/env sh
mvn clean package

hadoop jar target/rvill095_lab3-1.0-SNAPSHOT.jar edu.ucr.cs.cs167.rvill095.App 3 20 5
hadoop jar target/rvill095_lab3-1.0-SNAPSHOT.jar edu.ucr.cs.cs167.rvill095.App 3 20 3,5
hadoop jar target/rvill095_lab3-1.0-SNAPSHOT.jar edu.ucr.cs.cs167.rvill095.App 3 20 3v5