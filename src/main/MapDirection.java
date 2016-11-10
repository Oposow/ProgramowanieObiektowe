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
				return "Pó³noc";
			case South:
				return "Po³udnie";
			case West:
				return "Zachód";
			case East:
				return "Wschód";
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
