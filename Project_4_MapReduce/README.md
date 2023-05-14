Ricardo Villacana
rvill095
rvill095@ucr.edu
862312585

Question 1: What do you think the line job.setJarByClass(Filter.class); does?

Answer: I think the line job.setJarByClass(Filter.class); specifies to hadoop the jar files that will be sent to the nodes for the purpose of map and reduce, but in order to do so it needs to do so it needs to specify a class, which is the filter class.

Question 2: What is the effect of the line job.setNumReduceTasks(0);?

Answer: The line job.setNumReduceTasks(0); indicates that there will be no reducer and so there will be no reduce task to handle the map task results. This means that the key and value pairs created by the map tasks will be the end result.

Question 3: Where does the main function run? (Driver node, Master node, or a slave node).

Answer: The main function runs in the driver node.

Quesiton 4: How many lines do you see in the output?

Answer: I see 27972 lines.

Question 5: How many files are produced in the output?

Answer: There is only one significant file.

Question 6: Explain this number based on the input file size and default block size.

Answer: The file is only about 2.2 mb and the default block size is configured to be 32mb. Therefore, only one block is needed, which results in one file.

Question 7: How many files are produced in the output?

Answer: There is only one significant file.

Question 8: Explain this number based on the input file size and default block size.

Answer: Again, the file is only about 2.2 mb and the default block size is greater and so we only need one block, which results in one file.

Question 9: How many files are produced in the output directory and how many lines are there in each file?

Answer: There is a total of 2 files(part-r-00000 and part-r-00001), as well as _SUCCESS. One of them has 4 lines and the other has 0 lines.

Question 10: Explain these numbers based on the number of reducers and number of response codes in the input file.

Answer: There are 2 files because the number of reducers is set to 2. The file with the 4 lines stores the results of the input file. There are 4 response codes and so there are 4 lines of output.

Question 11: How many files are produced in the output directory and how many lines are there in each file?

Answer: There are a total of 2 files(part-r-00000 and part-r-00001), as well as _SUCCESS. One of them has 5 lines and the other has 2 lines

Question 12: Explain these numbers based on the number of reducers and number of response codes in the input file.

Answer: Again, the number of reducers is set to 2, so there will be 2 output files. There are a total of 7 response codes and the hashing function assigned
5 of them to one reducer and 2 of the response codes to the other reducer. Therefore, there is 5 lines in one output file and 2 lines in the other output file.

Question 13: How many files are produced in the output directory and how many lines are there in each file?

Answer: There are a total of 2 files(part-r-00000 and part-r-00001), as well as _SUCCESS. One of them has 1 line and the other has 0 lines.

Question 14: Explain these numbers based on the number of reducers and number of response codes in the input file.

Answer: The total number of reducers is set to 2, so there will be 2 files. The first output file has 1 line because we used the output of filter, which only looked for the response code of 200,
which results in only 1 line in the output file of aggregation.
	
