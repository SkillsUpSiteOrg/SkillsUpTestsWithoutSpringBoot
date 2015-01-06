package ua.dp.skillsup.tests.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ua.dp.skillsup.tests.dao.entity.TestDescription;
import ua.dp.skillsup.tests.service.ApplicationService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 19.12.2014.
 */
@Controller
public class ApplicationController {

    @Autowired
    @Qualifier(value = "applicationService")
    private ApplicationService service;

    public ApplicationService getService() {
        return service;
    }

    public void setService(ApplicationService service) {
        this.service = service;
    }

    @RequestMapping(value = "/SkillsUpTests", method = RequestMethod.GET)
    public ModelAndView getPages() {
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/getAllTestDescriptions",
            method = RequestMethod.GET)
    public @ResponseBody
    List<TestDescription> getAllTestDescriptions() {
        List<TestDescription> testDescriptions = new ArrayList<TestDescription>();
        testDescriptions.addAll(service.getAllTestDescriptions());
        System.out.println(testDescriptions);
        return testDescriptions;
    }

    @RequestMapping(value = "/addNewTestDescription", method = RequestMethod.POST)
    public @ResponseBody String addNewTestDescription(
            @RequestParam(value = "testName", required = true) String testName,
            /*@RequestParam(value = "dateOfCreation", required = true) String dateOfCreation,*/
            @RequestParam(value = "maxTimeToPassInMinutes", required = true) int maxTimeToPassInMinutes) {
        TestDescription testDescription = new TestDescription();
        testDescription.setTestName(testName);
        testDescription.setMaxTimeToPassInMinutes(maxTimeToPassInMinutes);
        /*DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
        DateTime dateOfCreationFormatted = formatter.parseDateTime(dateOfCreation);
        testDescription.setDateOfCreation(dateOfCreationFormatted);*/
        service.addTestDescription(testDescription);
        return "{\"some\" : \"Successfully added new test "+testDescription.getTestName()+"\"}";
    }
}
