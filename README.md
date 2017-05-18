# Individual-Project
This repository is used to record the progress of COMP4560 project.

## What's new this week:
(18/05)

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
