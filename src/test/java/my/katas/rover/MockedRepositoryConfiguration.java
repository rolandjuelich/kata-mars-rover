package my.katas.rover;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import my.katas.rover.terrain.TerrainRepository;

@Configuration
@Profile("!production")
public class MockedRepositoryConfiguration {

	@Bean
	public TerrainRepository terrains() {
		return Mockito.mock(TerrainRepository.class);
	}
	
	@Bean
	public RoverRepository rovers() {
		return Mockito.spy(new RoverRepository());
	}

}
