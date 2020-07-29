package questionpaper.model;

import java.util.List;

public class QuestionPaper {
    private final List<Question> questionList;

    public QuestionPaper(final List<Question> questionList) {
        //validations
        this.questionList = questionList;
    }
}
