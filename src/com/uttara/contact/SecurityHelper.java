package com.uttara.contact;

import java.util.ArrayList;

public class SecurityHelper 
{
	public static String secureCode()
	{
		String [] smallLetter={"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
		String [] bigLetter={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		String [] numbers={"0","1","2","3","4","5","6","7","8","9"};
		
		ArrayList<String> code= new ArrayList<String>();
		String test="";
		
		test=bigLetter[(int)(Math.random()*bigLetter.length)];
		if(code.contains(test)==false)
		{
			code.add(test);
		}
		
		test=bigLetter[(int)(Math.random()*bigLetter.length)];
		if(code.contains(test)==false)
		{
			code.add(test);
		}
		
		test=numbers[(int)(Math.random()*numbers.length)];
		if(code.contains(test)==false)
		{
			code.add(test);
		}
		
		test=numbers[(int)(Math.random()*numbers.length)];
		if(code.contains(test)==false)
		{
			code.add(test);
		}
				
		test=bigLetter[(int)(Math.random()*bigLetter.length)];
		if(code.contains(test)==false)
		{
			code.add(test);
		}
		
		test=bigLetter[(int)(Math.random()*bigLetter.length)];
		if(code.contains(test)==false)
		{
			code.add(test);
		}
		Object[] arr=code.toArray();
		String finalCode="";
		for(Object s:arr)
		{
			finalCode+=s;
		}
		System.out.println("Secure code is "+finalCode);
		return finalCode;
	}
	
	
}
