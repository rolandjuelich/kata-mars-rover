package my.katas.command;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import my.katas.command.CommandProcessor.EventCapturingCommandProcessor;
import my.katas.event.EventCaptor;

public class CommandProcessorUnitTest {

	@Rule
	public MockitoRule mockito = MockitoJUnit.rule();

	@Spy
	private CommandBus commandBus;

	@Spy
	private EventBus eventBus;

	@InjectMocks
	private CommandProcessor application;

	@Before
	public void setUp() {
		commandBus.register(this);
	}

	@Test
	public void shouldCaptureEvent() {
		// given
		final EventCaptor captor = spy(new EventCaptor(eventBus));
		final TestCommand command = new TestCommand();
		final Class<TestEvent> expectedEvent = TestEvent.class;

		// when
		with(captor).process(command, expectedEvent);

		// then
		final InOrder orders = inOrder(captor, commandBus);
		orders.verify(captor).start();
		orders.verify(commandBus).execute(command);
		orders.verify(captor).waitFor(expectedEvent);
		orders.verify(captor).stop();
	}

	@Test
	public void shouldReturnPostedEvent() {
		// given
		final TestCommand command = new TestCommand();

		// when
		final TestEvent event = application.process(command, TestEvent.class);

		// then
		assertThat(event).isNotNull();
	}

	@Test
	public void shouldReturnNullEventOnTimeout() {
		// given
		final TestCommand command = new TestCommand();
		commandBus.unregister(this);

		// when
		final TestEvent event = application.process(command, TestEvent.class);

		// then
		assertThat(event).isNull();
	}

	@Test
	public void shouldExecuteCommand() {
		// given
		final TestCommand command = new TestCommand();

		// when
		application.process(command);

		// then
		verify(commandBus).execute(command);
	}

	@Subscribe
	private void handle(final TestCommand command) {
		eventBus.post(new TestEvent());
	}

	private EventCapturingCommandProcessor with(final EventCaptor captor) {
		return new EventCapturingCommandProcessor(commandBus, captor);
	}

	private class TestCommand {

	}

	private class TestEvent {

	}

}
