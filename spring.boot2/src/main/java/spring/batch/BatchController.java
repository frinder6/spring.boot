package spring.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created on 2016/10/17.
 */
@Controller
public class BatchController implements ApplicationContextAware {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job testJob;


    @RequestMapping("batch.print")
    public String printBeans(ModelMap map) {
        String[] beans = applicationContext.getBeanDefinitionNames();
        map.addAttribute("beans", beans);
        return "index";
    }


    @RequestMapping("job")
    public String job(@RequestParam("mod") Long mod) throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                // 传递参数到reader
                .addLong("mod", mod)
                // job可以被多次执行
                .addLong("mills", System.currentTimeMillis())
                .toJobParameters();
        jobLauncher.run(testJob, jobParameters);
        return "index";
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
