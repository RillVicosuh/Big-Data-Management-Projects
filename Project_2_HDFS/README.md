Ricardo Villacana
rvill095
rvill095@ucr.edu
862312585

Question 1: Copied 2271210910 bytes from 'AREAWATER.csv' to 'output.txt' in 85.015618 seconds

Question 2: Using the command "time cp AREAWATER.csv output.txt" I got:
real	1m3.832s
user	0m0.105s
sys	0m4.482s

Question 3: The running time of copying the file through the program may depend on the complexity of the program's 
algorithm and the size of the file, while the operating system's cp command will likely have a more efficient and 
optimized algorithm for copying a file. The program may also have overhead in terms of initializing resources 
and closing connections, which adds to the total running time.

Question 4: No, if I use the hadoop command the project does not run because of a java.io.IOException: Input file not found error

Question 5: 
    Case 1: Copied 2271210910 bytes from file:///home/cs167/rvill095/cs167/workspace/rvill095_lab2/AREAWATER.csv to 
            hdfs://localhost:9000/user/AREAWATER_Copy.csv in 79.097237904 seconds
    Case 2: Copied 2048 bytes from 'hdfs://localhost:9000/user/README.md' to 
            'file:/home/cs167/rvill095/cs167/workspace/rvill095_lab2/README.md_COPY.csv' in 2.636381 seconds
    Case 3: Copied 2048 bytes from 'hdfs://localhost:9000/user/README.md' to 
            'hdfs://localhost:9000/user/README_COPY2.md' in 2.148007 seconds

Table: CASE1 : 79.101351273
       CASE2 : 85.379304317
       CASE# : 78.277101128
















