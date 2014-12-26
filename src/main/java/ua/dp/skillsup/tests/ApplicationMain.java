package ua.dp.skillsup.tests;

import org.joda.time.DateTime;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.dp.skillsup.tests.dao.ApplicationDAO;
import ua.dp.skillsup.tests.dao.ApplicationDAOImpl;
import ua.dp.skillsup.tests.dao.entity.QuestionAnswer;
import ua.dp.skillsup.tests.dao.entity.TestDescription;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 25.12.2014.
 */
public class ApplicationMain {

    public static void main(String[] args){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:applicationContext.xml"); //FileSystemXmlApplicationContext("C:/Dan/Education/SkillzUp/Java practice 2/TestsWithoutSpringBoot/Tests/src/main/webapp/WEB-INF/applicationContext.xml");
        ApplicationDAO dao = context.getBean("applicationDao", ApplicationDAO.class);

        TestDescription test1 = new TestDescription();
        test1.setTestName("Java-0");
        test1.setDateOfCreation(new DateTime(2014, 12, 23, 0, 0));
        test1.setMaxTimeToPassInMinutes(90);

        dao.addTestDescription(test1);
        long test1Id = dao.getTestDescriptionIdFromDB(test1);
        System.out.println("Id in DB: " + test1Id);

        System.out.println("Current number of tests in DB: " + dao.getAllTestDescriptions().size());
        for(TestDescription test : dao.getAllTestDescriptions()){
            System.out.println(test);
        }

        QuestionAnswer qa1 = new QuestionAnswer();
        qa1.setQuestion("What are main three words?");
        qa1.setAnswer("Inheritance, Polymorphism, Incapsulation");
        dao.addQuestionAnswer(qa1);
        QuestionAnswer qa2 = new QuestionAnswer();
        qa2.setQuestion("What can you tell about anti-pattern 'Public Morozov'?");
        qa2.setAnswer("When descendant class rises parent-method accessibility");
        dao.addQuestionAnswer(qa2);

        System.out.println("Current number of questions-answers in DB: " + dao.getAllQuestionAnswers().size());
        for(QuestionAnswer qa : dao.getAllQuestionAnswers()){
            System.out.println(qa);
        }

        List<QuestionAnswer> questionAnswers = new ArrayList<QuestionAnswer>();
        questionAnswers.add(qa1);
        questionAnswers.add(qa2);
        test1.setQuestionAnswers(questionAnswers);
        dao.updateTestDescription(test1Id, test1);

        System.out.println("!!!!!!!!!!!!!!!!!!!!Current number of tests in DB: " + dao.getAllTestDescriptions().size());
        for(TestDescription test : dao.getAllTestDescriptions()){
            System.out.println(test);
        }
        System.out.println("!!!!!!!!!!!!!!!!!!!!Current number of questions-answers in DB: " + dao.getAllQuestionAnswers().size());
        for(QuestionAnswer qa : dao.getAllQuestionAnswers()){
            System.out.println(qa);
        }
    }
}
