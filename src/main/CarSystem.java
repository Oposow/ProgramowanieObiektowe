package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CarSystem {
	
	public static void main(String[] args)
	{
		List<MoveDirection> directions = new OptionsParser().parse("fffrlfffrrff");
		ArrayList<HayStack> tmp = new ArrayList<HayStack>();
		tmp.add(new HayStack(new Position(-4,-4)));
		tmp.add(new HayStack(new Position(7,7)));
		tmp.add(new HayStack(new Position(3,6)));
		tmp.add(new HayStack(new Position(2,0)));
		IWorldMap map = new UnboundedMap(tmp);
		map.add(new Car(map));
		map.add(new Car(map,3,4));
		map.run(directions.toArray(new MoveDirection[0]));
		System.out.println(map);
	}
	
}

