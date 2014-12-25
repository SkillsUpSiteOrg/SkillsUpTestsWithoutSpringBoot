package main.java.ua.dp.skillsup.tests;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import main.java.ua.dp.skillsup.tests.dao.ApplicationDAO;
import main.java.ua.dp.skillsup.tests.dao.entity.TestDescription;

/**
 * Created by Daniel on 25.12.2014.
 */
public class ApplicationMain {

    /*@Autowired
    @Qualifier(value = "applicationDao")
    public static ApplicationDAO dao;*/

    public static void main(String[] args){
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:/main/webapp/WEB-INF/applicationContext.xml");  //*webapp/WEB-INF/applicationContext.xml
        ApplicationDAO dao = context.getBean("applicationDao", ApplicationDAO.class);

        TestDescription test1 = new TestDescription();
        test1.setTestName("Java-0");
        test1.setDateOfCreation(new DateTime(2014, 12, 23, 0, 0));
        test1.setMaxTimeToPassInMinutes(90);

        dao.addTestDescription(test1);

        System.out.println("Current number of tests in DB: " + dao.getAllTestDescriptions().size());
        for(TestDescription test : dao.getAllTestDescriptions()){
            System.out.println(test);
        }
    }
}
