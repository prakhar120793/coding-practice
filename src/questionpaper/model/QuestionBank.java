package questionpaper.model;

public interface QuestionBank {
    void addQuestion(String questionId, Question question);

    void deleteQuestion(String questionId);

    void modifyQuestion(String existingQuestionId, Question modifiedQuestion);

    void getQuestion(Difficulty difficulty, Topic topic, int marks, int numberOfQuestion);
}
