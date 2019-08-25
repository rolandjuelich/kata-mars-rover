package my.katas.rover;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import my.katas.rover.terrain.TerrainRepository;

@Configuration
@ComponentScan(basePackages = { "my.katas.rover.*" })
public class MockedRepositoryConfiguration {

	@Bean
	public TerrainRepository terrains() {
		return Mockito.mock(TerrainRepository.class);
	}

}
