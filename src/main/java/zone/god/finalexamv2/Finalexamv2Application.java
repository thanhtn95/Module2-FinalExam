package zone.god.finalexamv2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import zone.god.finalexamv2.service.CategoryService;
import zone.god.finalexamv2.service.CategoryServiceImpl;
import zone.god.finalexamv2.service.ProductService;
import zone.god.finalexamv2.service.ProductServiceImpl;


@SpringBootApplication
@EnableSpringDataWebSupport
@EnableWebMvc
@EnableTransactionManagement

public class Finalexamv2Application {

    public static void main(String[] args) {
        SpringApplication.run(Finalexamv2Application.class, args);
    }
    @Bean
    public CategoryService categoryService(){return new CategoryServiceImpl();
    }
    @Bean
    ProductService productService(){return  new ProductServiceImpl();
    }
}
