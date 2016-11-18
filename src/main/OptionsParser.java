package main;

import java.util.LinkedList;
import java.util.List;

public class OptionsParser {
	
	public List<MoveDirection> parse(String str)
	{
		str = str.toLowerCase();
		List<MoveDirection> dirs = new LinkedList<MoveDirection>();
		char[] arr = str.toCharArray();
		for (int i =0; i<arr.length; i++)
			switch(arr[i])
			{
				case 'f':
					dirs.add(MoveDirection.Forward);
					break;
				case 'b':
					dirs.add(MoveDirection.Backward);
					break;
				case 'l':
					dirs.add(MoveDirection.Left);
					break;
				case 'r':
					dirs.add(MoveDirection.Right);
					break;
				default:
					throw new IllegalArgumentException(arr[i] + " is not legal move specification");
			}
		return dirs;
	}
}
