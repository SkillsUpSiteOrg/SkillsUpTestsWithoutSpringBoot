package ua.dp.skillsup.tests;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.dp.skillsup.tests.dao.ApplicationDAO;
import ua.dp.skillsup.tests.dao.entity.TestDescription;

/**
 * Created by Daniel on 25.12.2014.
 */
public final class ApplicationMain {

    private ApplicationMain(){};

    public static void main(String[] args){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        ApplicationDAO dao = context.getBean("applicationDao", ApplicationDAO.class);

        TestDescription test1 = new TestDescription();
        test1.setTestName("Java-0");
        test1.setMaxTimeToPassInMinutes(90);

        dao.addTestDescription(test1);

        System.out.println("Current number of tests in DB: " + dao.getAllTestDescriptions().size());
        for(TestDescription test : dao.getAllTestDescriptions()){
            System.out.println(test);
        }
    }
}
