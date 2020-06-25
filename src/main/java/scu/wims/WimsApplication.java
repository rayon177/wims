package scu.wims;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description
 * @Author hyr
 * @Version
 * @Date 2020-05-17  23:32
 */
@SpringBootApplication
@MapperScan("scu.wims.mapper")
public class WimsApplication {
    public static void main(String[] args) {
        SpringApplication.run(WimsApplication.class, args);
    }

}
