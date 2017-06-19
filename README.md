
Mining Domain Landscape from Social Q&A Websites
====================================================================

Project Description:
———————————————————————————————————————————————————————————————————-
This is the package of my individual project of COMP4560 in Australian National University.

The project is to mine association rules from the math database collected from Mathematics StackExchange. The purpose of this project is to develop a website where people can use to find a summary of math landscape. The entire process of the project has three stages, including association rule mining, data visualisation, and web implementation.———————————————————————————————————————————————————————————————————-
 

Usages:
———————————————————————————————————————————————————————————————————-
All the files are tested under macOS Sierra Version 10.12.4

#Folder_1 ‘1_Association_Rule_Mining’:

##Contents: 

	## MathPairs_Java:
	The folder is for storing the code and files used in finding 			association-rule pairs of MathDataBase.

	.JAVA FILES:
	1.’MathPairs.java’: for finding out all the association pairs 
	2.’CountFrequency.java’:count the frequency of tags and record the first 	10 tags
	3.’First50Subset.java’: pick the sub-tagConbo for the 10 tags.

##To run the code:

	IntelliJ IDEA 2016.3.6. Can be found in https://www.jetbrains.com/idea/


	Others:
	1. 'MTagCombo.txt' is the original data that I used for finding 		association rules
	2. 'MTagName.csv' is a file of all the tags' names, which was 	not used in the final version of the algorithm
	3. 'Results' is used to store the results based on different 	support values & confident values.
	4. The rest are result files.


#Folder_2 ‘2_Web_Implementation’:

	##Math_Web:
	The folder is for storing the code for implementing the website.

	CORE FILES:
	1. MathLandScape.html
	2. graph.js
	3. newData.json

##To run code:
Copy the above core files into the same path of the project. And run the MathLandScape.html in Google Chrome.

	##png: some .png files that show the graphs


#Folder_3 ‘3_Gephi’:
Some Gephi projects for data community detection.

##To run the project
	Gephi 0.9.1. Can be found in https://gephi.org/users/download/


#Folder_4 ‘Otrher work’:
The other relevant work that has been done during the project.

#Weekly_Meeting_Logbook:
Some notes on Github repository. 
The link is https://github.com/TongTY/Individual-Project

(The raw data of math database can be download at https://archive.org/download/stackexchange)


# Meeting records:

# Individual-Project
This repository is used to record the progress of COMP4560 project.

## What's new this week:

## Files:

### Final_Presentation:
This folder will be used to store the final version of my work.

### MathPairs_Java:
The folder for storing the code and files used in finding association-rule pairs of MathDataBase.
1. 'MTagCombo.txt' is the original data that I used for finding association rules
2. 'MTagName.csv' is a file of all the tags' names, which was not used in the final version of the algorithm
3. 'MathPairs.java'is the code for finding out all the association pairs 
4. 'Results' is used to store the results based on different support values & confident values.

### EnglishPairs:
1. 'ETagCombo.txt'
2. 'Results'

### Data Visualisation:
This forder is used to store all the files that are relatived to data visualisation.
1. The files named '1_...' is the outcomes based on support value = 0.005 and confidence value = 0.1 and has no labels yet.
2. The files named '2_...' is based on '1_...' but added labels to the nodes.

### D3ExerciseFiles:
The folder for storing the exercises of D3.js.

## Information record for each week:
(18/04) 
I added two files into folder MathPairs_Java.
1. 'MathPairs.java'is the code for finding out all the association pairs and the final results are stored in 'OuterMap', which works well.
2. However, I found that the association role now in OuterMap is two-way, so I can't simplely calculate the support value using this result, since the final count for two tags may be separate now (TagA->TagB and TagB->TagA). I tried to declare a new list to store one-way pairs. To do that, I declared a new type Item<String, String, Intanger>, but something wrong happened. I uploaded the code as 'newMathPair.java'. Could you please have a look? Or do you have any suggestion on how to calculate the support value? Thank you~

(20/04)
1. I have create a new folder 'Data Visualisation' and the content files are described in the following section.
2. I have uploaded a new file named Results.

(29/04)
1. Under 'Individual-Project/MathPairs_Java/...'<br />
   1.1 'MathPairs.java': the final version of the algorithm<br />
   1.2 'Result':new results based on sv=0.5% and cv=50% and 10% were added<br />
2. Under 'Individual-Project/Data Visualisation/...'<br />
   '2_graph_labels.png' is the graph which has labels.<br />
   '2_graph_v4.html' is the sorce code and I used d3.v4.js instead of d3.v3.js in this code.<br />
3. Folder 'EnglishPairs' is totallt new. Just stores the same work as 'MathPairs_Java' does.<br/>

(11/05)
 1. Under 'Individual-Project/Data Visualisation/...'<br />
    1.1 '3_final_sv0.008cv0.09.json' is the final data I will use for visualisation.
 2. Under 'MathPair'<br/>
    1.1 'First50Subset.java' <br />
    1.2 'subPairs.java' is the code for find out the subset for each tag with high frequency. 
 3. 'Final_Presentation'.
    1.1 The 'subGraph' is one example of all sub-graphs.
    
    (18/05)
1. Under 'Final_Presentation':<br />
   1.1 LinkWithColor.png shows the final version of the whole graph which will be displayed on the homepage.<br />

