import org.junit.Test;

public class AdditionShould {

	@Test
	public void add_two_numbers () {

		assertThat(
				sum(2, 2),
				is(4));
	}
}
