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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
}
