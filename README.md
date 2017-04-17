# Individual-Project
This repository is used to record the progress of COMP4560 project.

## What's new this week:
(18/04) 
I added two files into folder MathPairs_Java.
1. 'MathPairs.java'is the code for finding out all the association pairs and the final results are stored in 'OuterMap', which works well.
2. However, I found that the association role now in OuterMap is two-way, so I can't simplely calculate the support value using this result, since the final count for two tags may be separate now (TagA->TagB and TagB->TagA). I tried to declare a new list to store one-way pairs. To do that, I declared a new type Item<String, String, Intanger>, but something wrong happened. I uploaded the code as 'newMathPair.java'. Could you please have a look? Or do you have any suggestion on how to calculate the support value? Thank you~

### MathPairs_Java:
The folder for storing the code and files used in finding association-rule pairs of MathDataBase.
### D3ExerciseFiles:
The folder for storing the exercises of D3.js.

