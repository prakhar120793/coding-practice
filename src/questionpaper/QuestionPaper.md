## Question
Design and Implement a Question Paper Generator. Questions can have attributes {text, subject, topic, difficulty, marks}.
The questions are generated on the basis of a template. A template specifies a total number marks and distribution of marks based on an attribute. Two templates can be combined.

* 100 questions
* 100 marks|Difficulty|Easy:20%,Medium:50%,Hard:30%
* 100 marks|Topic|OS:40%, Algo:60%

Additional features: Provide Add, Delete, Modify. Generate question will have 10x throughput of add/modify/delete. Handle concurrency issues.

## Action Class

* QuestionPaperGenerator
    * QuestionPaper generateQuestionPaper(InputTemplate)
    * QuestionBank should be part of it.

## Model (can be of different types: input/output models, data structures, entities)

* QuestionPaper
    * List<Question>
    ....
* Question
    * Attributes which are defined
* InputTemplate
* Difficulty
    * Enum with fixed values.
* Topic
    * Enum with fixed values.    

## Data Structure
* QuestionBank
    * addQuestion(Question) 
    * deleteQuestion(Question)
    * modifyQuestion(existingQuestion, newQuestion)
    * getQuestionBasedOnDifficultyAndTopic(difficulty, topic, numberOfQuestions, marks) {this is called the maximum times, 
    should have the minimum complexity}
    

