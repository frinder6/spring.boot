package spring.yaml;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by frinder6 on 2016/9/16.
 */
@Configuration
@ConfigurationProperties(prefix = "yaml")
public class YamlBean {

    private List<String> list = new ArrayList<String>();

    private Map<String, Object> map = new HashMap<String, Object>();

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }
}
