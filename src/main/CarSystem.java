package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CarSystem {
	
	public static void main(String[] args)
	{
		List<MoveDirection> directions = new OptionsParser().parse(args[0]);
		IWorldMap map = new RectangularMap(10, 5);
		map.add(new Car(map));
		map.add(new Car(map,3,4));
		map.run((MoveDirection[])directions.toArray());
	}
	
}

