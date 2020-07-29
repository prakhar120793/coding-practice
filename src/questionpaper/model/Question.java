package questionpaper.model;

public class Question {
    private final Difficulty difficulty;
    private final Topic topic;
    private final int marks;
    private final String text;
    private final String questionId;

    public Question(Difficulty difficulty, Topic topic, int marks, String text, String questionId) {
        this.difficulty = difficulty;
        this.topic = topic;
        this.marks = marks;
        this.text = text;
        this.questionId = questionId;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public Topic getTopic() {
        return topic;
    }

    public int getMarks() {
        return marks;
    }

    public String getText() {
        return text;
    }

    public String getQuestionId() {
        return questionId;
    }
}
