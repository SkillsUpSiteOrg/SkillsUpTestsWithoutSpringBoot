package ua.dp.skillsup.tests.dao.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 26.12.2014.
 */
@Entity
@Table(name = "QUESTION_ANSWER")
public class QuestionAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long questionAnswerId;

    @Column(name = "QUESTION")
    private String question;

    @Column(name = "ANSWER")
    private String answer;

    @ManyToMany(mappedBy = "questionAnswers", cascade={CascadeType.MERGE})
    @Fetch(FetchMode.JOIN)
    private List<TestDescription> testDescriptions;

    public long getQuestionAnswerId() {
        return questionAnswerId;
    }

    public void setQuestionAnswerId(long questionAnswerId) {
        this.questionAnswerId = questionAnswerId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public List<TestDescription> getTestDescriptions() {
        return testDescriptions;
    }

    public void setTestDescriptions(List<TestDescription> testDescriptions) {
        /*for(TestDescription test : testDescriptions){
            if(test.getQuestionAnswers()==null || test.getQuestionAnswers().isEmpty()){
                List<QuestionAnswer> questionAnswers = new ArrayList<>();
                questionAnswers.add(this);
                test.setQuestionAnswers(questionAnswers);
            }
            if(!test.getQuestionAnswers().contains(this)){
                List<QuestionAnswer> questionAnswers = test.getQuestionAnswers();
                questionAnswers.add(this);
                test.setQuestionAnswers(questionAnswers);
            }
        }*/
        this.testDescriptions = testDescriptions;
    }

    @Override
    public String toString() {
        return "QuestionAnswer{" +
                "questionAnswerId=" + questionAnswerId +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", used in tests=" + testDescriptions +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionAnswer that = (QuestionAnswer) o;

        if (questionAnswerId != that.questionAnswerId) return false;
        if (answer != null ? !answer.equals(that.answer) : that.answer != null) return false;
        if (question != null ? !question.equals(that.question) : that.question != null) return false;
        if (testDescriptions != null ? !testDescriptions.equals(that.testDescriptions) : that.testDescriptions != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (questionAnswerId ^ (questionAnswerId >>> 32));
        result = 31 * result + (question != null ? question.hashCode() : 0);
        result = 31 * result + (answer != null ? answer.hashCode() : 0);
        result = 31 * result + (testDescriptions != null ? testDescriptions.hashCode() : 0);
        return result;
    }
}
