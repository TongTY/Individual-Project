# Individual-Project
This repository is used to record the progress of COMP4560 project.

## What's new this week:
1. Individual-Project/MathPairs_Java/MathPairs.java: the final version of the algorithm
2. Individual-Project/MathPairs_Java/Result:new results based on sv=0.5% and cv=50% and 10% were added


## Files:
### MathPairs_Java:
The folder for storing the code and files used in finding association-rule pairs of MathDataBase.
1. 'MTagCombo.txt' is the original data that I used for finding association rules
2. 'MTagName.csv' is a file of all the tags' names, which was not used in the final version of the algorithm
3. 'MathPairs.java'is the code for finding out all the association pairs 
4. 'Results' is used to store the results based on different support values & confident values.


### D3ExerciseFiles:
The folder for storing the exercises of D3.js.

### Data Visualisation:
This forder is used to store all the files that are relatived to data visualisation.
1. '1_Data.json': the data in this file is based on support value=5%.
2. '1_Graph.png': the first graph of the data in 1_Data.json.
3. '1_mathtry.html': the html file.

## Information record for each week:
(18/04) 
I added two files into folder MathPairs_Java.
1. 'MathPairs.java'is the code for finding out all the association pairs and the final results are stored in 'OuterMap', which works well.
2. However, I found that the association role now in OuterMap is two-way, so I can't simplely calculate the support value using this result, since the final count for two tags may be separate now (TagA->TagB and TagB->TagA). I tried to declare a new list to store one-way pairs. To do that, I declared a new type Item<String, String, Intanger>, but something wrong happened. I uploaded the code as 'newMathPair.java'. Could you please have a look? Or do you have any suggestion on how to calculate the support value? Thank you~

(20/04)
1. I have create a new folder 'Data Visualisation' and the content files are described in the following section.
2. I have uploaded a new file named Results.
