package my.katas.rover;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "my.katas.rover.*" })
public class MockedRepositoryConfiguration {

//	@Bean
//	public TerrainRepository terrains() {
//		return Mockito.mock(TerrainRepository.class);
//	}

}
