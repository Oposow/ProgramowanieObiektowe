package main;

import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;

public class UnboundedMap implements IWorldMap {

	public List<Car> Cars;
	public List<HayStack> HayStacks;
	
	public UnboundedMap(List<HayStack> _HayStacks)
	{
		HayStacks = _HayStacks;
		Cars = new ArrayList<Car>();
	}
	
	@Override
	public boolean canMoveTo(Position position) {
		return !isOccupied(position);
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
		for(HayStack stack : HayStacks)
			if (stack.getPosition().equals(position))
				return true;
		return false;
	}

	@Override
	public Object objectAt(Position position) {
		for(Car car : Cars)
			if (car.getPosition().equals(position))
				return car;
		for(HayStack stack : HayStacks)
			if (stack.getPosition().equals(position))
				return stack;
		return null;
	}
	
	private Pair<Position, Position> getCorners()
	{
		Position leftDown = new Position(0,0);
		Position rightUp = new Position(0,0);
		for(Car car : Cars)
		{
			if (car.getPosition().isSmaller(leftDown))
				leftDown = car.getPosition();
			if (car.getPosition().isLarger(rightUp))
				rightUp = car.getPosition();			
		}
		for(HayStack stack : HayStacks)
		{
			if (stack.getPosition().isSmaller(leftDown))
				leftDown = stack.getPosition();
			if (stack.getPosition().isLarger(rightUp))
				rightUp = stack.getPosition();
		}
		return new Pair<>(leftDown, rightUp);
	}
	
	public String toString(){
		Pair<Position, Position> corners = getCorners();
		return new MapVisualizer().dump(this, corners.getKey(), corners.getValue());
	}

}
