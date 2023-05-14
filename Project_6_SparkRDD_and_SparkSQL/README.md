Ricardo Villacana
rvill095
rvill095@ucr.edu
862312585

(Q1) What are these two arguments?
Answer: The first argument is the command that we want to use, which then indicates the action or transformation.
        The second argument is the name of the input file.

(Q3) What is the type of the attributes time and bytes this time? Why?
Answer: The attributes time and bytes are both of type string because we don't infer the schema with the values present
        in the file. Therefore, spark parses them as a string.