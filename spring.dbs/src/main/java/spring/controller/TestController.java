package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spring.dao.SqlFormatException;
import spring.entity.SpringTable;
import spring.service.TestService;

import java.util.List;

/**
 * Created by frinder6 on 2016/12/21.
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService<SpringTable> testService;

    /**
     * insert
     *
     * @param entity
     * @return
     * @throws SqlFormatException
     */
    @RequestMapping("/add")
    public Response insert(SpringTable entity) throws SqlFormatException {
        testService.persist(entity.getK(), entity);
        return Response.sucess();
    }

    /**
     * delete
     *
     * @param entity
     * @return
     * @throws SqlFormatException
     */
    @RequestMapping("/remove")
    public Response delete(SpringTable entity) throws SqlFormatException {
        testService.delete(entity.getK(), entity);
        return Response.sucess();
    }

    /**
     * find by id
     *
     * @param entity
     * @return
     * @throws SqlFormatException
     */
    @RequestMapping("/id")
    public SpringTable findById(@RequestParam("key") String key, SpringTable entity) throws SqlFormatException {
        return testService.selectForSingle(key, entity);
    }


    /**
     * select
     *
     * @param entity
     * @return
     * @throws SqlFormatException
     */
    @RequestMapping("/query")
    public List<SpringTable> select(@RequestParam("key") String key, SpringTable entity) throws SqlFormatException {
        return testService.selectForList(key, entity);
    }

}
