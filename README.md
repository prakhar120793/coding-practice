

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






