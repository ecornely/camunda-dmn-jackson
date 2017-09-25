package be.nrb.o2.dmn;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Configuration
@ComponentScan(basePackageClasses= {LoggerDelegate.class})
public class DmnConfiguration {
  @Bean
  public ObjectMapper om(){
    ObjectMapper om = new ObjectMapper();
    om.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
    om.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    return om;
  }
  
}
