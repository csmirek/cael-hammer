package pwmkr;

import java.io.FileNotFoundException;

public class MainClass
{
	private final static String alphaClass = "[a-zA-Z]";
	private final static String alphaNumClass = "[a-zA-Z0-9@]";
	private final static String symbolClass = "[a-zA-Z0-9!@#$%^&*()_+=,.<>/';:{}]";
	
	public static void main(String[] args)
	{
		//if (seed == defaultSeed)
		//{
		//	 defaultGen("hi", "[a-zA-Z0-9@!#$%]", 1000, 1000);
		//}
		//else
		//{
		//	 seedGen("ho", "[a-zA-Z0-9@!#$%]", 1000, 1000, 1000);
		//}

		/*String A = "yahoo";
		String B = "xz62rP";

		int row = 10000;
		int column = 100;

		int length = 15;*/
		
		GenerateFile.seedGen("ho",symbolClass,100,100,100);
		
		try
		{
			System.out.println(Algorithm.getPW("ho", "yahoo", "abcdefg", 100, 200, 15));
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
