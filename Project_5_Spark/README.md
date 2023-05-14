Ricardo Villacana
rvill095
rvill095@ucr.edu
862312585

(Q1) Do you think it will use your local cluster? Why or why not?
Answer: Yes it does run on the local cluster and not the cluster on spark because that is what is configured as the master node in the program

(Q2) Does the application use the cluster that you started? How did you find out?
Answer: Yes, the application did run on the cluster that I started because there is now a new entry into the Completed Applications section with the name CS167-Lab5

(Q3) What is the Spark master printed on the standard output on IntelliJ IDEA?
Answer: Using Spark master 'local[*]'
        Number of lines in the log file 30970 

(Q4) What is the Spark master printed on the standard output on the terminal?
Answer: Using Spark master 'spark://127.0.0.1:7077'
        Using Spark's default log4j profile: org/apache/spark/log4j-defaults.properties
        Number of lines in the log file 30970

(Q5) For the previous command that prints the number of matching lines, list all the processed input splits.
Answer: 23/02/08 06:22:25 INFO HadoopRDD: Input split: hdfs://localhost:9000/user/nasa_19950801.tsv:0+2339220
        23/02/08 06:22:25 INFO HadoopRDD: Input split: hdfs://localhost:9000/user/nasa_19950801.tsv:2339220+2339220

(Q6) For the previous command that counts the lines and prints the output, how many splits were generated?
Answer: There were a total of 4 splits generated

(Q7) Compare this number to the one you got earlier.
Answer: The amount of splits for question 6 is double the amount of splits for question 5

(Q8) Explain why we get these numbers.
Answer: We get these numbers because the input file is read twice. The input file is read twice becuase in this situation there are
two actions that need to be executed instead of just one.

(Q9) What can you do to the current code to ensure that the file is read only once?
Answer: You can use the cache() function before we print the results as such: matchingLines.cache();