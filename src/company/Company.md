Write down the code in any language for a simple employee hierarchy which has 3 types of employees.
- CEO
- Manager
- employee
Where an employee can have only 1 mgr, and a mgr has 1+ employees.
We were asked to input employee details(name, id, salary, rating etc) in any order (employees might be input before his manager), create the hierarchy and implement these functionalities:

- Print hierarchy given any employee/mgr/CEO (used an n-ary tree + hash table)
- Given a bonus and performance rating of each employee divide it to the lowest level employees(in the hierarchy ) in the ratio of their rating. i.e 100 divided among 2:3 is 40 and 60. and print the bonus of each ( simple recursive solution)
- Top 10 employees with ratio of bonus:salary (used maxheap)