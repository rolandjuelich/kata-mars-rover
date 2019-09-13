package my.katas.command;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.junit.Ignore;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.google.common.eventbus.EventBus;

import my.katas.rover.Commands;

public class CommandProcessorUnitTest {
	
	
	public class SomeEvent {

	}

	
	@Test
	public void test() throws Exception {
		
	}
	
	@Test
	@Ignore
	public void testName() throws Exception {
		Object command = new Object();

		Commands commandBus = mock(Commands.class);
		EventBus eventBus = spy(new EventBus());
		BDDMockito.willAnswer(new Answer<Object>() {

			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				if (invocation.getMock().equals(commandBus)) {
					eventBus.post(new SomeEvent());
				}
				return null;
			}});
		
		new CommandProcessor(commandBus, eventBus).process(command);
		
		verify(commandBus).execute(command);
	}

}
