package my.katas.rover;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.google.common.eventbus.EventBus;

@Configuration
@ComponentScan(basePackages = { "my.katas.*" })
public class RoverConfiguration {

	@Bean
	public EventBus eventBus() {
		return new EventBus();
	}

}
