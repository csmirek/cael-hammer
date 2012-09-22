package pwmkr;

import java.io.FileNotFoundException;
import java.util.TreeSet;

public class MainClass
{
	private final String alphaClass = "[a-zA-Z]";
	private final String alphaNumClass = "[a-zA-Z0-9@]";
	private final String symbolClass = "[a-zA-Z0-9@!#$%]";
	
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

		String A = "yahoo";
		String B = "xz62rP";

		int row = 10000;
		int column = 100;

		int length = 15;

		Algorithm.setValues(A, B, row, column);
		
		try
		{
			int c = 0;
			TreeSet<String> pws = new TreeSet<String>();
			for(int i=0;i<10000;i++)
			{
				for(int j=0;j<10000;j++)
				{
					Algorithm.setValues(A, B, i, j);
					String temp = Algorithm.getPW("hi",i,j,length);
					if(pws.contains(temp))
					{
						//System.out.println(temp);
						System.out.println(c++);
					}
					else
					{
						pws.add(temp);
					}
				}
			}
			//System.out.println(Algorithm.getPW("hi", row, column, length));
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}
