package main;

public class Position {
	public final int x;
	public final int y;
	
	public Position(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public String toString()
	{
		StringBuilder tmp = new StringBuilder();
		tmp = tmp.append("(").append(x).append(", ").append(y).append(")");
		return tmp.toString();
	}
	
	
	public Boolean isSmaller(Object other)
	{
		Position arg = (Position)other;
		return x <= arg.x && x <= arg.y;
	}
	
	public Boolean isLarger(Position arg)
	{
		return x >= arg.x && y >= arg.y;
	}
	
	public boolean isEqual(Position arg)
	{
		return arg.x == x && arg.y == y;
	}
	
	public Position add(Position arg)
	{
		return new Position(arg.x + x, arg.y + y);
	}
}
