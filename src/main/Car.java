package main;

public class Car {
	private MapDirection dir;
	private Position pos;
	private IWorldMap map;
	
	public Car()
	{
		dir = MapDirection.North;
		pos = new Position(2,2);
	}
	
	public Car(IWorldMap map)
	{
		dir = MapDirection.North;
		pos = new Position(0,0);
		this.map = map;
	}
	
	public Car(IWorldMap map, int x, int y)
	{
		dir = MapDirection.North;
		pos = new Position(x,y);
		this.map = map;
	}
	
	public String toString()
	{
		return dir.name().substring(0, 0);
	}
	
	public Position getPosition()
	{
		return pos;
	}
	
	
	
	public void move(MoveDirection arg)
	{
		Position old =  pos;
		if (arg == MoveDirection.Left)
			dir = MapDirection.West;
		else if (arg == MoveDirection.Right)
			dir = MapDirection.East;
		else if (arg == MoveDirection.Forward)
		{
			if (dir == MapDirection.North)
				pos = pos.add(new Position(0,1));
			else if (dir == MapDirection.South)
				pos = pos.add(new Position(0,-1));
			else if (dir == MapDirection.West)
				pos = pos.add(new Position(-1,0));
			else if (dir == MapDirection.East)
				pos = pos.add(new Position(1,0));
		}
		else
		{
			if (dir == MapDirection.North)
				pos = pos.add(new Position(0,-1));
			else if (dir == MapDirection.South)
				pos = pos.add(new Position(0,1));
			else if (dir == MapDirection.West)
				pos = pos.add(new Position(1,0));
			else if (dir == MapDirection.East)
				pos = pos.add(new Position(-1,0));
		}
		
		if (!map.canMoveTo(pos))
				pos = old;
	}
}
