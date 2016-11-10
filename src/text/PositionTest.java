package text;

import static org.junit.Assert.*;

import org.junit.Test;

public class PositionTest {

	@Test
	public void testsmaller(){
		assert(new main.Position(1, 2).isSmaller(new main.Position(-5,1)));
	}

	@Test
	public void testlarger(){
		assert(new main.Position(1, 2).isLarger(new main.Position(1,4)));
	}
	
	@Test
	public void testToString(){
		assertEquals(new main.Position(1, 2).toString(), "(1, 2)");
	}
	
	@Test
	public void testIsEqual(){
		assert(new main.Position(1, 2).isEqual(new main.Position(1,2)));
	}
}
