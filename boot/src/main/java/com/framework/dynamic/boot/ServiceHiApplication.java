package com.framework.dynamic.boot;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
@RestController
@EnableSwagger2
@EnableHystrix
@EnableHystrixDashboard
@EnableCircuitBreaker
@EnableTurbine
@Service
@Api(description = "测试接口", value = "测试测试")
public class ServiceHiApplication {

    @Autowired
    ApplicationContext context;

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),
            @HystrixProperty(name = "execution.timeout.enabled", value = "false")},
            fallbackMethod = "findByIdFallback")
    @ApiOperation("i service")
    @GetMapping("i")
    public String iService(String name) {
        System.out.println(context);
        return restTemplate.getForObject("http://SERVICE-HI/hi?name=" + name, String.class);
    }

    public String hiError(String name) {
        return "hi," + name + ",sorry,error!";
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(ServiceHiApplication.class).web(WebApplicationType.SERVLET).build().run(args);
    }

    @Value("${server.port}")
    String port;

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }


    @Bean
    public Docket createRestApi() {// 创建API基本信息
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.framework"))// 扫描该包下的所有需要在Swagger中展示的API，@ApiIgnore注解标注的除外
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {// 创建API的基本信息，这些信息会在Swagger UI中进行显示
        return new ApiInfoBuilder()
                .title("dynamic测试项目")// API 标题
                .description("spring-cloud")// API描述
                .contact(new Contact("zhouwenlai", "", "zhouwenlai@chehejia.com"))// 联系人
                .version("1.0.0.0")// 版本号
                .build();
    }

    @ApiOperation("hi interface")
    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    public String home(@RequestParam(value = "name", defaultValue = "forezp") String name) {
        return "hi " + name + " ,i am from port:" + port;
    }

}