

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

<details>
  <summary>Question 3: Best Time to Buy and Sell Stocks (https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/)</summary>
  
  ##### Solution
  * Lets first solve a simpler problem. Lets try to find the solution when at max only one transaction is allowed.
    * Recurrence Relation: Answer_MaxOne(i) = Max(Answer_MaxOne(i-1), Min(i) < Val[i] ? Val[i]-Min(i): 0) {Val is the input array}.
    * Recurrence Relation: Min(i) = Minimum(Min(i-1), Val[i])
    * Answer_MaxOne(n-1) represents the solution for the complete array.
    * Min(i) represents the minimum value encountered till index i (starting from 0).
    * Base Case: Answer_MaxOne(0) = 0. Min(0) = Val(0).
  * Similar to above, we can calculate answer from the reverse direction as well. Lets call the above Answer_MaxOne as Answer_MaxOne_Right and the solution to the simpler problem in the reverse direction as Answer_MaxOne_Left.
  * Recurrence Relation for the Actual Problem: Maximum(Answer_MaxOne_Right(n-1), Answer_MaxOne_Left(0), Maximum{i: 0 to n-2}(Answer_MaxOne_Right(i), Ans_MaxOne_Left(i+1)))
  
  ##### Time Complexity
  * O(n)
  ##### Space Complexity
  * O(n)
  ##### Base Cases
  * Empty Input.
  * Decreasing Order.
  * Single Input Value.  
  
</details>






