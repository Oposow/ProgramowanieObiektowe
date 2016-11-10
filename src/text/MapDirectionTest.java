package text;

import static org.junit.Assert.*;

import org.junit.Test;

import main.MapDirection;

public class MapDirectionTest {

	@Test
	public void testnext() {
		assert(MapDirection.next(MapDirection.West) == MapDirection.North);
		assert(MapDirection.next(MapDirection.North) == MapDirection.East);
		assert(MapDirection.next(MapDirection.East) == MapDirection.South);
		assert(MapDirection.next(MapDirection.South) == MapDirection.West);
	}
	
	@Test
	public void testprevious() {
		assert(MapDirection.previous(MapDirection.West) == MapDirection.South);
		assert(MapDirection.previous(MapDirection.North) == MapDirection.West);
		assert(MapDirection.previous(MapDirection.East) == MapDirection.North);
		assert(MapDirection.previous(MapDirection.South) == MapDirection.East);
		
	}
	


}
