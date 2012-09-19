package pwmkr;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainClass
{

	private static Pattern pat;
	private static Matcher myMatch;
	private static Random rand;

	private static Integer Aprime, Bprime, Cprime, Dprime;
	private static BigInteger bfi;

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

		Aprime = Math.abs(A.hashCode());
		Bprime = Math.abs(B.hashCode());

		Cprime = Math.abs((B + Aprime.toString()).hashCode());
		Dprime = Math.abs((A + Bprime.toString()).hashCode());

		System.out.println("deadbeef: " + "deadbeef".hashCode());
		System.out.println(A + ": " + Aprime);
		System.out.println(B + ": " + Bprime);
		System.out.println(B + Aprime.toString() + ": " + Cprime);
		System.out.println(A + Bprime.toString() + ": " + Dprime);

		bfi = BigInteger.valueOf(Cprime);
		bfi = bfi.multiply(BigInteger.valueOf(Dprime));

		String bfiString = bfi.toString();

		while (bfiString.endsWith("0"))
		{
			bfiString = bfiString.substring(0, bfiString.length() - 1);
		}
		bfi = new BigInteger(bfiString);

		while (bfiString.length() < 500)
		{
			bfi = bfi.multiply(bfi);
			bfiString = bfi.toString();
		}

		System.out.println("bfi: " + bfi);
		try
		{
			System.out.println(getPW("hi", row, column, length));
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

	private static String getPW(String filename, int rowPos, int colPos,
			int length) throws FileNotFoundException
	{
		File readFile = new File(filename + ".txt");
		Scanner scan = new Scanner(readFile);
		ArrayList<String> inRAMFile = new ArrayList<String>();

		while (scan.hasNextLine())
		{
			inRAMFile.add(scan.nextLine());
		}

		int rows = inRAMFile.size();
		int columns = inRAMFile.get(0).length();

		int nrow = Math.abs((Aprime * rowPos) % rows);
		int ncol = Math.abs((Bprime * colPos) % columns);
		int nLength = Math.abs((Cprime * length) % (rows * columns));

		char[] offset = new char[nLength];
		for (int i = 0; i < nLength; i++)
		{
			offset[i] = inRAMFile.get((nrow + (i + ncol) / columns) % rows)
					.charAt((ncol + i) % columns);
		}
		System.out.println(nrow + "," + ncol + "," + nLength);

		String bfiString = bfi.toString();

		ArrayList<String> subs = new ArrayList<String>();

		for (int i = 0; i < length; i++)
		{
			subs.add(bfiString.substring(i * bfiString.length() / length, (i + 1)
					* bfiString.length() / length));
		}

		char[] pw = new char[length];

		for (int i = 0; i < length; i++)
		{
			BigInteger tempBig = new BigInteger(subs.get(i));
			tempBig = tempBig.mod(BigInteger.valueOf(nLength));
			Integer temp = new Integer(tempBig.toString());

			pw[i] = offset[temp];
		}
		String theSlab = new String(pw);
		return theSlab;
	}
}
