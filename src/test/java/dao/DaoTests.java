package dao;

import org.hibernate.service.spi.InjectService;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.dp.skillsup.tests.dao.ApplicationDAO;
import ua.dp.skillsup.tests.dao.ApplicationDAOImpl;
import ua.dp.skillsup.tests.dao.entity.TestDescription;

/**
 * Created by Daniel on 25.12.2014.
 */
@Ignore
@RunWith(BlockJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class DaoTests {

    /*@Inject
    private ApplicationContext applicationContext;*/

    @Autowired
    private ApplicationDAOImpl dao;

    @Before
    public void setUpTests(){
        //ApplicationContext context =
    }

    @Test
    public void testAddTestDescription() throws Exception {
        TestDescription testDescription1 = new TestDescription();
        testDescription1.setTestName("Java-0");
        testDescription1.setDateOfCreation(new DateTime(2014, 12, 15, 0, 0));
        testDescription1.setMaxTimeToPassInMinutes(90);

        TestDescription testDescription2 = new TestDescription();
        testDescription2.setTestName("Java-1");
        testDescription2.setDateOfCreation(new DateTime(2010, 2, 8, 0, 0));
        testDescription2.setMaxTimeToPassInMinutes(120);

        dao.addTestDescription(testDescription1);
        dao.addTestDescription(testDescription2);

        TestDescription testDescription1Actual = dao.getTestDescription(1L);
        TestDescription testDescription2Actual = dao.getTestDescription(2L);

        //String[] ignore = {"id"};
        Assert.assertNotNull("expected save to work", testDescription1Actual);
        Assert.assertNotNull("expected save to work", testDescription2Actual);
    }
}
