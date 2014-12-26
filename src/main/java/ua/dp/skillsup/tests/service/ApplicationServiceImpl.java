package ua.dp.skillsup.tests.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ua.dp.skillsup.tests.dao.ApplicationDAO;
import ua.dp.skillsup.tests.dao.entity.QuestionAnswer;
import ua.dp.skillsup.tests.dao.entity.TestDescription;

import java.util.List;

/**
 * Created by Daniel on 19.12.2014.
 */
@Service("applicationService")
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    @Qualifier(value = "applicationDao")
    private ApplicationDAO dao;

    public ApplicationDAO getDao() {
        return dao;
    }

    public void setDao(ApplicationDAO dao) {
        this.dao = dao;
    }

    @Override
    public TestDescription addTestDescription(TestDescription testDescription) {
        return dao.addTestDescription(testDescription);
    }

    @Override
    public void deleteTestDescription(TestDescription testDescription) {
        dao.deleteTestDescription(testDescription);
    }

    @Override
    public TestDescription getTestDescription(long id) {
        return dao.getTestDescription(id);
    }

    @Override
    public void updateTestDescription(long id, TestDescription testDescription) {
        dao.updateTestDescription(id, testDescription);
    }

    @Override
    public long getTestDescriptionIdFromDB(TestDescription testDescription){
        return dao.getTestDescriptionIdFromDB(testDescription);
    }

    @Override
    public List<TestDescription> getAllTestDescriptions() {
        return dao.getAllTestDescriptions();
    }

    @Override
    public QuestionAnswer addQuestionAnswer(QuestionAnswer questionAnswer) {
        return dao.addQuestionAnswer(questionAnswer);
    }

    @Override
    public void deleteQuestionAnswer(QuestionAnswer questionAnswer) {
        dao.deleteQuestionAnswer(questionAnswer);
    }

    @Override
    public QuestionAnswer getQuestionAnswer(long id) {
        return dao.getQuestionAnswer(id);
    }

    @Override
    public void updateQuestionAnswer(long id, QuestionAnswer questionAnswer) {
        dao.updateQuestionAnswer(id, questionAnswer);
    }

    @Override
    public List<QuestionAnswer> getAllQuestionAnswers() {
        return dao.getAllQuestionAnswers();
    }
}
