package pwmkr;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class Algorithm {

	private static Integer Aprime, Bprime, Cprime, Dprime;
	private static BigInteger bfi;
	
	public Algorithm() { }
	
	private static String reverseString(String input)
	{
		String ret = new String();
		
		for(int i=input.length()-1;i!=-1;i--)
			ret = ret + input.charAt(i);
		
		return ret;
	}
	
	private static void setValues(String A, String B, Integer C, Integer D)
	{
		Aprime = Math.abs(reverseString(A).hashCode());
		Bprime = Math.abs(reverseString(B).hashCode());

		Cprime = Math.abs((B + C.toString()).hashCode());
		Dprime = Math.abs((A + D.toString()).hashCode());

		bfi = BigInteger.valueOf(Cprime);
		bfi = bfi.multiply(BigInteger.valueOf(Dprime));

		BigInteger temp = BigInteger.valueOf(Aprime).multiply(BigInteger.valueOf(Bprime));
		
		bfi = bfi.add(temp);
		
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
	}
	
	public static String getPW(String filename, String sinput1, String sinput2, int iinput1, int iinput2,
			int length) throws FileNotFoundException
	{
		setValues(sinput1,sinput2,iinput1,iinput2);
		
		File readFile = new File(filename + ".txt");
		Scanner scan = new Scanner(readFile);
		ArrayList<String> inRAMFile = new ArrayList<String>();

		while (scan.hasNextLine())
		{
			inRAMFile.add(scan.nextLine());
		}
		scan.close();
		
		int rows = inRAMFile.size();
		int columns = inRAMFile.get(0).length();

		int nrow = Math.abs((Aprime * iinput1) % rows);
		int ncol = Math.abs((Bprime * iinput2) % columns);
		int nLength = Math.abs(((Cprime + Dprime) * length) % (rows * columns));

		if(nLength < length)
		{
			nLength = length * rows;
		}
		
		char[] offset = new char[nLength];
		for (int i = 0; i < nLength; i++)
		{
			offset[i] = inRAMFile.get((nrow + (i + ncol) / columns) % rows)
					.charAt((ncol + i) % columns);
		}

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