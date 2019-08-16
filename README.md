

#### Dynamic Programming
<details>
  <summary>Question 1: Edit Distance (https://leetcode.com/problems/edit-distance/)</summary>
  
  ##### Recursive formula
  * answer(i,j) = Min of probable solutions.
  * probable solutions:
    * word1[i] == word2[j] ? answer(i-1, j-1): answer(i-1.j-1) + 1
    * (Insertion) answer(i,j-1) + 1
    * (Deletion) answer(i-1,j) + 1
  ##### Base case
  * answer(-1,-1) = 0
  * answer(-1,0) = answer(0,-1) = 1
  ##### Time Complexity
  * O(n^2)
  
  ##### Space Complexity
  * O(n^2)
  
  ##### Edge Cases
  * Word1 || Word2 is empty
  * Word1 && Word2 is empty

  
</details>

<details>
  <summary>Question 2: Largest Area in Histogram (https://leetcode.com/problems/largest-rectangle-in-histogram/)</summary>
    
  ##### Solution
  * (Probable Solution) For every i, find the area treating the ith bar as the smallest bar in the rectangle.
  * Max of all the probable solutions is the answer
  * To find answer for ith bar we need to know the next smallest bar on the left and right side.
  * Next smallest for all the bar (on both sides) can be found using the stack based approach in O(n) time. Link to solution: https://www.geeksforgeeks.org/largest-rectangular-area-in-a-histogram-set-1/
  ##### Time Complexity
  * O(n)
  ##### Space Complexity
  * O(n)     
  ##### Base Cases
  * Empty input
  * Single bar as input
  * For ith bar: No bar is smaller on the right
  * For ith bar: No bar is smaller on the left
    
</details>






