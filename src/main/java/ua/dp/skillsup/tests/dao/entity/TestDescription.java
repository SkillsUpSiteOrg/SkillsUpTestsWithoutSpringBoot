package ua.dp.skillsup.tests.dao.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by Daniel on 16.12.2014.
 */
@Entity
@Table(name = "TEST_DESCRIPTION")
public class TestDescription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long testDescriptionId;

    @Column(name = "TEST_NAME")
    private String testName;

    @Column(name = "DATE")
    private Date dateOfCreation;

    @Column(name = "TIME_IN_MINUTES")
    private int maxTimeToPassInMinutes;

    @ManyToMany(cascade=CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private List<QuestionAnswer> questionAnswers;

    public long getTestDescriptionId() {
        return testDescriptionId;
    }

    public void setTestDescriptionId(long testDescriptionId) {
        this.testDescriptionId = testDescriptionId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(DateTime dateOfCreation) {
        this.dateOfCreation = dateOfCreation.toDate();
    }

    public int getMaxTimeToPassInMinutes() {
        return maxTimeToPassInMinutes;
    }

    public void setMaxTimeToPassInMinutes(int maxTimeToPassInMinutes) {
        this.maxTimeToPassInMinutes = maxTimeToPassInMinutes;
    }

    public List<QuestionAnswer> getQuestionAnswers() {
        return questionAnswers;
    }

    public void setQuestionAnswers(List<QuestionAnswer> questionAnswers) {
        this.questionAnswers = questionAnswers;
        /*for(QuestionAnswer qa : questionAnswers){
            if(qa.getTestDescriptions()==null || qa.getTestDescriptions().isEmpty()){
                List<TestDescription> testDescriptions = new ArrayList<>();
                testDescriptions.add(this);
                qa.setTestDescriptions(testDescriptions);
            }
            if(!qa.getTestDescriptions().contains(this)){
                List<TestDescription> testDescriptions = qa.getTestDescriptions();
                testDescriptions.add(this);
                qa.setTestDescriptions(testDescriptions);
            }
        }*/
    }

    @Override
    public String toString() {
        return "TestDescription{" +
                "Id=" + testDescriptionId +
                ", Name='" + testName + '\'' +
                ", Date of creation=" + dateOfCreation +
                ", Max time to pass=" + maxTimeToPassInMinutes +
                ", questionAnswers=" + questionAnswers +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestDescription that = (TestDescription) o;

        if (maxTimeToPassInMinutes != that.maxTimeToPassInMinutes) return false;
        //if (testDescriptionId != that.testDescriptionId) return false;
        /*if (this.getDateOfCreation() != null ? !this.getDateOfCreation().equals(that.getDateOfCreation()) : that.getDateOfCreation() != null)
            return false;*/
        /*if (questionAnswers != null ? !questionAnswers.equals(that.questionAnswers) : that.questionAnswers != null)
            return false;*/
        if (testName != null ? !testName.equals(that.testName) : that.testName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        //int result = (int) (testDescriptionId ^ (testDescriptionId >>> 32));
        int result = 1;
        result = 31 * result + (testName != null ? testName.hashCode() : 0);
        /*result = 31 * result + (this.getDateOfCreation() != null ? this.getDateOfCreation().hashCode() : 0);*/
        result = 31 * result + maxTimeToPassInMinutes;
        /*result = 31 * result + (questionAnswers != null ? questionAnswers.hashCode() : 0);*/
        return result;
    }
}
