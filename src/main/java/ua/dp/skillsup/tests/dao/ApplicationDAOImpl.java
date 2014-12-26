package ua.dp.skillsup.tests.dao;

import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.dp.skillsup.tests.dao.entity.QuestionAnswer;
import ua.dp.skillsup.tests.dao.entity.TestDescription;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Class to manage test descriptions in database.
 */
@Repository("applicationDao")
@Transactional
public class ApplicationDAOImpl implements ApplicationDAO {

    @PersistenceContext(unitName = "item-manager-pu")
    public EntityManager em;

    @Override
    @Transactional
    public TestDescription addTestDescription(TestDescription testDescription){
        return em.merge(testDescription);
    }

    @Override
    @Transactional
    public void deleteTestDescription(TestDescription testDescription){
        TestDescription test = em.find(TestDescription.class, testDescription.getTestDescriptionId());
        em.remove(test);
    }

    @Override
    @Transactional(readOnly = true)
    public TestDescription getTestDescription(long id){
        return em.find(TestDescription.class, id);
    }

    @Override
    @Transactional
    public void updateTestDescription(long id, TestDescription testDescription){
        TestDescription newTest;
        if(em.find(TestDescription.class, id) != null){
            //System.out.println("Not null in DAO update");
            newTest = em.find(TestDescription.class, id);
            newTest.setTestName(testDescription.getTestName());
            newTest.setDateOfCreation(new DateTime(testDescription.getDateOfCreation().getYear(),
                    testDescription.getDateOfCreation().getMonth(), testDescription.getDateOfCreation().getDate(),
                    testDescription.getDateOfCreation().getHours(), testDescription.getDateOfCreation().getMinutes()));
            newTest.setMaxTimeToPassInMinutes(testDescription.getMaxTimeToPassInMinutes());
            newTest.setQuestionAnswers(testDescription.getQuestionAnswers());
            em.merge(newTest);
        }
        else{
            System.out.println("There is no such entity in data base. Check the id.");
        }
    }

    @Override
    @Transactional
    public long getTestDescriptionIdFromDB(TestDescription testDescription){
        System.out.println("this.getAllTestDescriptions(): " + this.getAllTestDescriptions());
        System.out.println("testDescription: " + testDescription);
        for(TestDescription test : this.getAllTestDescriptions()){
            if(test.equals(testDescription)){
                System.out.println("Test found");
                return test.getTestDescriptionId();
            }
        }
        System.out.println("Test not found");
        return 0;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TestDescription> getAllTestDescriptions(){
        TypedQuery<TestDescription> namedQuery = em.createQuery("select c from TestDescription c", TestDescription.class);
        return namedQuery.getResultList();
    }

    @Override
    @Transactional
    public QuestionAnswer addQuestionAnswer(QuestionAnswer questionAnswer) {
        return em.merge(questionAnswer);
    }

    @Override
    @Transactional
    public void deleteQuestionAnswer(QuestionAnswer questionAnswer) {
        QuestionAnswer qa = em.find(QuestionAnswer.class, questionAnswer.getQuestionAnswerId());
        em.remove(qa);
    }

    @Override
    @Transactional(readOnly = true)
    public QuestionAnswer getQuestionAnswer(long id) {
        return em.find(QuestionAnswer.class, id);
    }

    @Override
    @Transactional
    public void updateQuestionAnswer(long id, QuestionAnswer questionAnswer) {
        QuestionAnswer newQuestionAnswer;
        if(em.find(QuestionAnswer.class, id) != null){
            //System.out.println("Not null in DAO update");
            newQuestionAnswer = em.find(QuestionAnswer.class, questionAnswer.getQuestionAnswerId());
            newQuestionAnswer.setQuestion(questionAnswer.getQuestion());
            newQuestionAnswer.setAnswer(questionAnswer.getAnswer());
            newQuestionAnswer.setTestDescriptions(questionAnswer.getTestDescriptions());
            em.merge(newQuestionAnswer);
        }
        else{
            System.out.println("There is no such entity in data base. Check the id.");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuestionAnswer> getAllQuestionAnswers() {
        TypedQuery<QuestionAnswer> namedQuery = em.createQuery("select c from QuestionAnswer c", QuestionAnswer.class);
        return namedQuery.getResultList();
    }
}
