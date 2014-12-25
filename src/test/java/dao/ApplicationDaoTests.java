package dao;

import org.apache.log4j.Logger;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.dp.skillsup.tests.dao.ApplicationDAO;
import ua.dp.skillsup.tests.dao.entity.TestDescription;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;

/**
 * Created by Daniel on 23.12.2014.
 */
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)       //BlockJUnit4ClassRunner
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ApplicationDaoTests extends AbstractTransactionalDataSourceSpringContextTests {

    /*@Autowired
    @Qualifier("applicationDao")*/
    private ApplicationDAO dao;

    private static final Logger logger = Logger.getLogger(ApplicationDaoTests.class);

    public ApplicationDaoTests() {
        super();
        logger.info("setting up test");
        ApplicationContext ctx = super.getApplicationContext();
        dao = (ApplicationDAO) ctx.getBean("applicationDao");
        //System.out.println("All TD from DAO:" + dao.getAllTestDescriptions());
        assertNotNull(dao);
    }

    @Override
    protected String[] getConfigLocations() {
        return new String[] {"applicationContext.xml"};
    }

    @Override
    protected void onSetUpInTransaction() throws Exception {
        DataSource dataSource = jdbcTemplate.getDataSource();
        Connection con = DataSourceUtils.getConnection(dataSource);
        IDatabaseConnection dbUnitCon = new DatabaseConnection(con);
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(
                Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream("classpath:testDescription-data.xml"));
        try {
            DatabaseOperation.REFRESH.execute(dbUnitCon, dataSet);
        } finally {
            DataSourceUtils.releaseConnection(con, dataSource);
        }
    }

    @Before
    public void setUpTests(){
        TestDescription testDescription1 = new TestDescription();
        testDescription1.setTestName("Java-0");
        testDescription1.setDateOfCreation(new DateTime(2014, 11, 15, 0, 0));
        testDescription1.setMaxTimeToPassInMinutes(90);

        TestDescription testDescription2 = new TestDescription();
        testDescription2.setTestName("Java-1");
        testDescription2.setDateOfCreation(new DateTime(2010, 2, 8, 0, 0));
        testDescription2.setMaxTimeToPassInMinutes(120);

        dao.addTestDescription(testDescription1);
        dao.addTestDescription(testDescription2);
    }

    @Test
    public void testAddTestDescription() throws Exception {

        TestDescription testDescription1Actual = dao.getTestDescription(1L);
        TestDescription testDescription2Actual = dao.getTestDescription(2L);

        //String[] ignore = {"id"};
        assertNotNull("expected save to work", testDescription1Actual);
        assertNotNull("expected save to work", testDescription2Actual);
    }

    @Test
    public void testGetAllTestDescriptions() throws Exception {
        List<TestDescription> testDescriptions = dao.getAllTestDescriptions();
        System.out.println(testDescriptions);
        assertEquals("did not get expected number of entities ", 2, testDescriptions.size());
    }

    @Test
    public void testDeleteTestDescription() throws Exception {
        TestDescription testDescription = dao.getTestDescription(1L);
        dao.deleteTestDescription(testDescription);
        TestDescription testDescriptionActual = dao.getTestDescription(1L);
        assertNull("delete did not work", testDescriptionActual);
    }

    @Test
    public void testUpdateTestDescription() throws Exception {
        TestDescription testDescription = dao.getTestDescription(1L);
        testDescription.setTestName("New Java");
        dao.updateTestDescription(1L, testDescription);
        TestDescription testDescriptionActual = dao.getTestDescription(1L);
        Assert.assertEquals(testDescription.getTestName(), testDescriptionActual.getTestName());
        Assert.assertEquals(dao.getAllTestDescriptions().size(), 2);
    }

    @Test
    public void testGetTestDescription() throws Exception {
        TestDescription testDescription = dao.getTestDescription(1L);

        Assert.assertEquals(testDescription.getTestDescriptionId(), 1L);
        Assert.assertEquals(testDescription.getTestName(), "Java-0");
        Assert.assertEquals(testDescription.getDateOfCreation().toString(), "2013-05-12 00:00:00.0");
        Assert.assertEquals(testDescription.getMaxTimeToPassInMinutes(), 120);
    }
}
