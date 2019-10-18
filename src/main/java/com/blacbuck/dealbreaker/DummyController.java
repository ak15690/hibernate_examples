package com.blacbuck.dealbreaker;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/data")
@Controller
public class DummyController {

    @Autowired
    private TestService testService;


    @RequestMapping(value = "/queryTable2", method = RequestMethod.GET)
    @ResponseBody
    public void getDataFromTable2() {
        testService.getDataFromTable2();

    }

    @RequestMapping(value = "/saveTable1", method = RequestMethod.PUT)
    @ResponseBody
    public void updateTable1(@RequestBody UpdateT1Data updateT1Data) {
        testService.setUpdateT1Data(updateT1Data);


    }

    @RequestMapping(value = "/savePerson", method = RequestMethod.PUT)
    @ResponseBody
    public void updatePerson(@RequestBody List<UpdatePersonData> updatePersonData) {
        testService.createOrUpdatePerson(updatePersonData);


    }

    @RequestMapping(value = "/getAddress", method = RequestMethod.GET)
    @ResponseBody
    public List<Person> getAddress(@RequestParam Long id) {
       return testService.getAddressData(id);


    }

    @RequestMapping(value = "/getPerson", method = RequestMethod.GET)
    @ResponseBody
    public Person getPerson(@RequestParam Long id) {
        return testService.getPerson(id);


    }
}
