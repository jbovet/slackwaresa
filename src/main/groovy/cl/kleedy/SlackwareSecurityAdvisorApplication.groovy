package cl.kleedy

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.context.web.SpringBootServletInitializer
import org.springframework.context.annotation.ComponentScan
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@ComponentScan("cl.kleedy")
@EnableScheduling
class SlackwareSecurityAdvisorApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SlackwareSecurityAdvisorApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SlackwareSecurityAdvisorApplication.class, args);
    }
}
