#!/usr/bin/env sh
mvn clean package

yarn jar target/rvill095_lab4-1.0-SNAPSHOT.jar edu.ucr.cs.cs167.rvill095.Filter file://`pwd`/nasa_19950801.tsv file://`pwd`/filter_output.tsv 200
yarn jar target/rvill095_lab4-1.0-SNAPSHOT.jar edu.ucr.cs.cs167.rvill095.Aggregation file://`pwd`/filter_output.tsv file://`pwd`/aggregation_output.tsv

