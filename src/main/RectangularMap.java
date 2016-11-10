package main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RectangularMap implements IWorldMap {

	public int Width;
	public int Height;
	public List<Car> Cars;
	
	public RectangularMap(int width, int height)
	{
		Width = width-1;
		Height = height-1;
		Cars = new ArrayList<Car>();
	}
	
	@Override
	public boolean canMoveTo(Position position) {
		return position.isSmaller(new Position(Width, Height)) && position.isLarger(new Position(0,0));
	}

	@Override
	public boolean add(Car car) {
		if (isOccupied(car.getPosition()))
			return false;
		Cars.add(car);
		return true;
	}

	@Override
	public void run(MoveDirection[] directions) {
		for(int i = 0; i<directions.length; i++)
			Cars.get(i%Cars.size()).move(directions[i]);
	}

	@Override
	public boolean isOccupied(Position position) {
		for(Car car : Cars)
			if (car.getPosition().equals(position))
				return true;
		return false;
	}

	@Override
	public Object objectAt(Position position) {
		for(Car car : Cars)
			if (car.getPosition().equals(position))
				return car;
		return null;
	}
	
	public String toString(){
		return new MapVisualizer().dump(this, new Position(0,0), new Position(Width, Height));
	}

}
