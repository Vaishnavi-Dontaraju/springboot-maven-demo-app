package com.example.sb.mvn.demo.app.controllers;

import com.example.sb.mvn.demo.app.models.DemoData;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class DemoMapController {

    private static Map<String,String> demoMap = new HashMap<>();
    static {
        demoMap.put("Key1","Value1");
        demoMap.put("Key2","Value2");
        demoMap.put("Key3","Value3");
    }


    @GetMapping("/data/all")
    public Map<String,String> getAllDemoMap() {
        return demoMap;
    }

    @GetMapping("/data/{key}")
    public DemoData getDemoMapByKey(@PathVariable String key) {
        DemoData demoData = new DemoData();
        if(demoMap.containsKey(key)){
            demoData.setKey(key);
            demoData.setValue(demoMap.get(key));
            return demoData;
        }

        demoData.setKey(key);
        demoData.setValue("Not found");
        return demoData;
    }

    @PostMapping("/data")
    public DemoData putDemoMap(@RequestBody DemoData demoData) {
        String oldValue = demoMap.put(demoData.getKey(), demoData.getValue());

        DemoData responseData = new DemoData();
        responseData.setKey(demoData.getKey());
        responseData.setValue(oldValue);
        return responseData;
    }

    @DeleteMapping("/data")
    public DemoData deleteDemoMap(@RequestParam String key) {
        String oldValue = demoMap.remove(key);

        DemoData responseData = new DemoData();
        responseData.setKey(key);
        responseData.setValue(oldValue);
        return responseData;
    }

}
