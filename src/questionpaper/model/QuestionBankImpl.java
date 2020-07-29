package questionpaper.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class QuestionBankImpl implements QuestionBank {
    private final ConcurrentMap<QuestionKey, Map<String, Question>> keyToQuestionsMap;


    //it is made protected since there is a factory method to create an instance of this class. and there should only
    // be one way to create instance of this class for the clients, so that there is no confusion. protected is taken
    // instead of private since in future we may have to extend this class (with private we wont be able to do that,
    // since the class would have to be made final).
    protected QuestionBankImpl(
            ConcurrentMap<QuestionKey, Map<String, Question>> keyToQuestionsMap) {
        this.keyToQuestionsMap = keyToQuestionsMap;
    }

    public static QuestionBankImpl createQuestionBank() {
        return new QuestionBankImpl(new ConcurrentHashMap<>());
    }

    @Override
    public void addQuestion(String questionId, Question question) {
        //validation
        if (questionId == null || questionId.equals("")) {
            throw new IllegalArgumentException();
        }
        if (question == null) {
            throw new IllegalArgumentException();
        }
        QuestionKey questionKey = new QuestionKey(question.getDifficulty(), question.getTopic());

        synchronized (question.getDifficulty().toString() + "_" + question.getTopic().toString()) {
            Map<String, Question> questions = this.keyToQuestionsMap.getOrDefault(questionKey,
                    new ConcurrentHashMap<>());
            questions.put(questionId, question);
            this.keyToQuestionsMap.put(questionKey, questions);
        }
    }

    //If we have to optimise delete then we can keep another map <String (questionId), QuestionKey>
    @Override
    public void deleteQuestion(String questionId) {
        for (Map.Entry<QuestionKey, Map<String, Question>> keyToQuestionsEntry : this.keyToQuestionsMap.entrySet()) {
            if (keyToQuestionsEntry.getValue().containsKey(questionId)) {
                keyToQuestionsEntry.getValue().remove(questionId);
                break;
            }
        }
    }

    @Override
    public void modifyQuestion(String existingQuestionId, Question modifiedQuestion) {
        for (Map.Entry<QuestionKey, Map<String, Question>> keyToQuestionsEntry : this.keyToQuestionsMap.entrySet()) {
            if (keyToQuestionsEntry.getValue().containsKey(existingQuestionId)) {
                keyToQuestionsEntry.getValue().put(existingQuestionId, modifiedQuestion);
                break;
            }
        }
    }

    @Override
    public void getQuestion(Difficulty difficulty, Topic topic, int marks, int numberOfQuestions) {
        //
    }

    private static class QuestionKey {
        private final Difficulty difficulty;
        private final Topic topic;

        private QuestionKey(Difficulty difficulty, Topic topic) {
            this.difficulty = difficulty;
            this.topic = topic;
        }

        @Override
        public boolean equals(Object questionKey) {
            try {
                return this.difficulty == ((QuestionKey) questionKey).difficulty &&
                        this.topic == ((QuestionKey) questionKey).topic;
            } catch (RuntimeException e) {
                return false;
            }
        }

        @Override
        public int hashCode() {
            return this.difficulty.hashCode() + this.topic.hashCode();
        }
    }
}
