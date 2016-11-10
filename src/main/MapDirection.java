package main;

public enum MapDirection {
	North,
	South,
	West,
	East;
	
	public static String toString(MapDirection dir){
		switch(dir)
		{
			case North:
				return "P�noc";
			case South:
				return "Po�udnie";
			case West:
				return "Zach�d";
			case East:
				return "Wsch�d";
			default:
				return "";
		}
	}
	
	public static MapDirection next(MapDirection dir)
	{
		switch(dir)
		{
		case North:
			return East;
		case East:
			return South;
		case South:
			return West;
		default:
			return North;
		}
	}
	
	public static MapDirection previous(MapDirection dir)
	{
		switch(dir)
		{
		case North:
			return West;
		case East:
			return North;
		case South:
			return East;
		default:
			return South;
		}
	}
}
