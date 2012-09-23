package pwmkr;

import java.io.FileNotFoundException;
import java.util.HashSet;

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
		
		try
		{
			int c = 0;
			int m = 0;
			int a = 0;
			HashSet<String> pws = new HashSet<String>();
			for(int i=0;i<1000;i++)
			{
				for(int j=0;j<1000;j++)
				{
					String temp = Algorithm.getPW("hi",A,B,i,j,length);
					if(pws.contains(temp))
					{
						//System.out.println(temp);
						System.out.println("i: "+ i +" j: " + j + " c: " + (c++));
					}
					else
					{
						pws.add(temp);
						if(m%1000 == 0)
						{
							System.out.println("added: " + temp + " is the " + m + "th term");
						}
						m++;
					}
					System.out.println(a++);
				}
			}
			//System.out.println(Algorithm.getPW("hi", row, column, length));
			System.out.println("total unique: " + pws.size());
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}
