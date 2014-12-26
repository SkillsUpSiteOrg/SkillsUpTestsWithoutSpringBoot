package ua.dp.skillsup.tests.dao;

import ua.dp.skillsup.tests.dao.entity.QuestionAnswer;
import ua.dp.skillsup.tests.dao.entity.TestDescription;

import java.util.List;

/**
 * Created by Daniel on 16.12.2014.
 */
public interface ApplicationDAO {
    TestDescription addTestDescription(TestDescription testDescription);
    void deleteTestDescription(TestDescription testDescription);
    TestDescription getTestDescription(long id);
    void updateTestDescription(long id, TestDescription testDescription);
    long getTestDescriptionIdFromDB(TestDescription testDescription);
    List<TestDescription> getAllTestDescriptions();
    QuestionAnswer addQuestionAnswer(QuestionAnswer questionAnswer);
    void deleteQuestionAnswer(QuestionAnswer questionAnswer);
    QuestionAnswer getQuestionAnswer(long id);
    void updateQuestionAnswer(long id, QuestionAnswer questionAnswer);
    List<QuestionAnswer> getAllQuestionAnswers();
}
