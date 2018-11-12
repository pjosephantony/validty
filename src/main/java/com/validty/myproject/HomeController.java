package com.validty.myproject;



import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileNotFoundException;


@Controller
public class HomeController {

    DistanceCalc distanceCalc;

    @Autowired
    HomeController(DistanceCalc distanceCalc){
        this.distanceCalc =distanceCalc;
    }

    @RequestMapping(value = "/customer", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public JSONObject homeHandler() throws FileNotFoundException {
        JSONObject result = distanceCalc.distanceCalculation();
        return result;
    }
}
