package ds.controller;

import com.alibaba.fastjson.JSON;
import ds.entity.Animal;
import ds.entity.Plant;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Created by frinder6 on 2016/12/26.
 */
@RestController
@RequestMapping("/d")
public class DistributeController {

    @RequestMapping("/s")
    public String distribute(
            @RequestParam("actionType") String actionType,
            @RequestParam("requestData") String requestData
    ) {
        JSON json = JSON.parseObject(requestData);
        if ("1".equalsIgnoreCase(actionType)) {
            Animal animal = JSON.toJavaObject(json, Animal.class);
            System.out.println(animal);
        } else if ("2".equalsIgnoreCase(actionType)) {
            Plant plant = JSON.toJavaObject(json, Plant.class);
            System.out.println(plant);
        }

        return "success";
    }

    @RequestMapping("/m")
    public String distributes(
            @RequestParam("actionType") String actionType,
            @RequestParam("requestData") String requestData
    ) {
        if ("1".equalsIgnoreCase(actionType)) {
            JSON json = JSON.parseArray(requestData);
            List<Animal> animals = JSON.toJavaObject(json, List.class);
            System.out.println(animals);
        }
        return "success";
    }

}
