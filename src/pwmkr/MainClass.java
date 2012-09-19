package pwmkr;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainClass
{

	private static Pattern pat;
	private static Matcher myMatch;
	private static Random rand;

	public static void main(String[] args)
	{
		int defaultSeed = "deadbeef".hashCode();
		int seed = defaultSeed;

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
			System.out.println(Algorithm.getPW("hi", row, column, length));
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	private static int nextIntWrap(Random rand)
	{
		int next;
		char nextChar;

		do
		{
			next = rand.nextInt(128);
			nextChar = (char) next;
			String nextCharStr = new String("" + nextChar);
			myMatch = pat.matcher(nextCharStr);
		}
		while (!myMatch.matches());

		return next;
	}

	private static void defaultGen(String name, String characterClass, int rows,
			int columns)
	{
		pat = Pattern.compile(characterClass);
		rand = new Random();

		generateFile(name, rows, columns);
	}

	private static void seedGen(String name, String characterClass, int rows,
			int columns, int seed)
	{
		pat = Pattern.compile(characterClass);
		rand = new Random(seed);

		generateFile(name, rows, columns);
	}

	private static void generateFile(String name, int rows, int columns)
	{
		int[] intArray = new int[rows * columns];
		for (int i = 0; i < rows * columns; i++)
		{
			intArray[i] = nextIntWrap(rand);
		}

		try
		{
			PrintWriter fout = new PrintWriter(new FileWriter(name + ".txt"));
			for (int j = 0; j < rows; j++)
			{
				for (int k = 0; k < columns; k++)
				{
					fout.print((char) intArray[j * rows + k]);
				}
				fout.println();
			}
			fout.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
