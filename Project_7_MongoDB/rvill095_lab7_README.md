# Lab 7

## Student information

* Full name: Ricardo Villacana
* E-mail: rvill095@ucr.edu
* UCR NetID:rvill095
* Student ID:862312585

## Answers

* (Q1) What is your command to import the `contact.json` file?

Answer: mongoimport --jsonArray --db cs167 --collection contacts --file /home/cs167/Downloads/contacts.json

* (Q2) What is the output of the import command?

Answer: 2023-02-22T04:10:11.313+0000	connected to: mongodb://localhost/
	           2023-02-22T04:10:11.514+0000	1000 document(s) imported successfully. 0 document(s) failed to import.

* (Q3) What is your command to retrieve all users sorted by Name in ascending order?

 Answer: db.contacts.find().sort( { Name: 1 } )

* (Q4) What is your command to retrieve only the `_id` and `Name` sorted in reverse order by `Name`?

Answer: db.contacts.find({}, {_id: 1, Name: 1}).sort({Name: -1})

* (Q5) Is the comparison of the attribute `Name` case-sensitive?

Answer: Yes, The comparison of the attribute Name is case-sensitive by default in MongoDB.

* (Q6) Explain how you answered (Q5). Show the commands that you ran and how would the output tell you if MongoDB applied case-sensitive or case-insensitive.

Answer: The commands that I ran to show that name is case sensitive is:
                   db.contacts.insertOne({Name: "apple"})
		   db.contacts.insertOne({Name: "Berry"})
		   db.contacts.find().sort({Name: 1})
		   The result of this was that "Berry" shows up before "apple", which shows that MongoDB applied case-sensitive.
		
* (Q7) What is the command that retrieves the results in sorted order but without the `_id` field?

Answer: db.contacts.find({}, {Name: 1, _id: 0}).sort({Name: -1})

* (Q8) What is the command to insert the sample document? What is the result of running the command?

    Command: db.contacts.insertOne({Name: {First: "Yuan", Last: "Zhang"}})

    Result: {
			"acknowledged" : true,
			"insertedId" : ObjectId("63f59d1c064ef6f6e06b3f1d")
		    }


* (Q9) Does MongoDB accept this document while the `Name` field has a different type than other records?

Answer: Yes, MongoDB does accept this document event though the Name field has a different type than other records.

* (Q10) What is your command to insert the record `{Name: ["Yuan", "Zhang"]}`?

 Answer: db.contacts.insertOne({Name: ["Yuan", "Zhang"]})

* (Q11) Where did the two new records appear in the sort order?

Answer: This is where the new records appeared
 
{ "_id" : ObjectId("63f59d1c064ef6f6e06b3f1d"), "Name" : { "First" : "Yuan", "Last" : "Zhang" } }
{ "_id" : ObjectId("63f595a3db076691e9ce39e0"), "Name" : "Zoey Stevens" }
{ "_id" : ObjectId("63f595a3db076691e9ce3c18"), "Name" : "Zoey Nguyen" }
{ "_id" : ObjectId("63f595a3db076691e9ce3c5f"), "Name" : "Zoey Leonard" }
{ "_id" : ObjectId("63f595a3db076691e9ce3ad2"), "Name" : "Zoey Khalil" }
{ "_id" : ObjectId("63f595a3db076691e9ce3bcb"), "Name" : "Zoey Henry" }
{ "_id" : ObjectId("63f595a3db076691e9ce3a5d"), "Name" : "Zoey Freeman" }
{ "_id" : ObjectId("63f59e94064ef6f6e06b3f1e"), "Name" : [ "Yuan", "Zhang" ] }
{ "_id" : ObjectId("63f595a3db076691e9ce3c20"), "Name" : "Yuna Williamson" }
{ "_id" : ObjectId("63f595a3db076691e9ce3b26"), "Name" : "Yuna Sanchez" }
{ "_id" : ObjectId("63f595a3db076691e9ce3d48"), "Name" : "Yuna Perez" }
{ "_id" : ObjectId("63f595a3db076691e9ce3d0c"), "Name" : "Yuna Kennedy" }
{ "_id" : ObjectId("63f595a3db076691e9ce3b50"), "Name" : "Yuna Jones" }
{ "_id" : ObjectId("63f595a3db076691e9ce3ab2"), "Name" : "Yuna Hall" }
{ "_id" : ObjectId("63f595a3db076691e9ce3d92"), "Name" : "Yuna Andrade" }
{ "_id" : ObjectId("63f595a3db076691e9ce3ace"), "Name" : "Yumi Reyes" }
{ "_id" : ObjectId("63f595a3db076691e9ce3a4c"), "Name" : "Yumi Reed" }
{ "_id" : ObjectId("63f595a3db076691e9ce3a87"), "Name" : "Yumi Meyer" }
{ "_id" : ObjectId("63f595a3db076691e9ce3a98"), "Name" : "Yumi Lopez" }
{ "_id" : ObjectId("63f595a3db076691e9ce3c90"), "Name" : "Yumi Griffin" }


* (Q12) Why did they appear at these specific locations?

Answer: They appear in these specific locations when sorting in descending order becuase the documents with object-type Name field and the documents with array-type Name field are within their own groups and sorted separately


* (Q13) Where did the two records appear in the ascending sort order? Explain your observation.

Answer: This is where the new records appear in descending order

{ "_id" : ObjectId("63f59e94064ef6f6e06b3f1e"), "Name" : [ "Yuan", "Zhang" ] }
{ "_id" : ObjectId("63f595a3db076691e9ce3a5d"), "Name" : "Zoey Freeman" }
{ "_id" : ObjectId("63f595a3db076691e9ce3bcb"), "Name" : "Zoey Henry" }
{ "_id" : ObjectId("63f595a3db076691e9ce3ad2"), "Name" : "Zoey Khalil" }
{ "_id" : ObjectId("63f595a3db076691e9ce3c5f"), "Name" : "Zoey Leonard" }
{ "_id" : ObjectId("63f595a3db076691e9ce3c18"), "Name" : "Zoey Nguyen" }
{ "_id" : ObjectId("63f595a3db076691e9ce39e0"), "Name" : "Zoey Stevens" }
{ "_id" : ObjectId("63f59d1c064ef6f6e06b3f1d"), "Name" : { "First" : "Yuan", "Last" : "Zhang" } }

It appears in this order because MongoDB sorts string values in ascending order based on the first name. The last document is also of a different value type in the name field, which makes it the last document.

* (Q14) Is MongoDB able to build the index on that field with the different value types stored in the `Name` field?

Answer: Yes, MongoDB does allow us to build the index on the Name field even though there are different value types. 

* (Q15) What is your command for building the index?

Answer: db.contacts.createIndex({Name: 1})

* (Q16) What is the output of the create index command?

Answer: {
	"createdCollectionAutomatically" : false,
	"numIndexesBefore" : 1,
	"numIndexesAfter" : 2,
	"ok" : 1
}
