import org.junit.Test;

public class TwoAdditionsShould {

	@Test
	public void add_two_numbers () {

		assertThat(
				sum(2, 2),
				is(4));
	}

	@Test
	public void add_two_different_numbers () {

		assertThat(
				sum(1, 1),
				is(2));
	}
}
