package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.util.Pair;

public class UnboundedMap implements IWorldMap {

	public List<Car> cars;
	public HashMap<Position, Object> objectsOnMap;
	Position leftDown;
	Position rightUp;
	
	public UnboundedMap(List<HayStack> _HayStacks)
	{
		leftDown = new Position(0,0);
		rightUp = new Position(0,0);
		objectsOnMap = new HashMap<Position, Object>();
		for (HayStack stack : _HayStacks)
		{
			objectsOnMap.put(stack.getPosition(), stack);
			if (stack.getPosition().isLarger(rightUp))
				rightUp = stack.getPosition();
			else if (stack.getPosition().isSmaller(leftDown))
				leftDown = stack.getPosition();
		}
	}
	
	@Override
	public boolean canMoveTo(Position position) {
		return !isOccupied(position);
	}

	@Override
	public boolean add(Car car) {
		if (isOccupied(car.getPosition()))
			throw new IllegalArgumentException(car.getPosition() + " Is already occupied");
		objectsOnMap.put(car.getPosition(), car);
		cars.add(car);
		if (car.getPosition().isLarger(rightUp))
			rightUp = car.getPosition();
		else if (car.getPosition().isSmaller(leftDown))
			leftDown = car.getPosition();
		return true;
	}

	@Override
	public void run(MoveDirection[] directions) {
		for(int i = 0; i<directions.length; i++)
		{
			objectsOnMap.remove(cars.get(i%cars.size()).getPosition());
			cars.get(i%cars.size()).move(directions[i]);
			objectsOnMap.put(cars.get(i%cars.size()).getPosition(), cars.get(i%cars.size()));
		}
	}

	@Override
	public boolean isOccupied(Position position) {
		if (objectsOnMap.containsKey(position))
			return true;
		return false;
	}

	@Override
	public Object objectAt(Position position) {
		return objectsOnMap.get(position);
	}
	
	private Pair<Position, Position> getCorners()
	{
		return new Pair<>(leftDown, rightUp);
	}
	
	public String toString(){
		Pair<Position, Position> corners = getCorners();
		return new MapVisualizer().dump(this, corners.getKey(), corners.getValue());
	}

}
