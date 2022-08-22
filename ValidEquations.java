/*
* Given a n numbers 
* And 4 operators {=,+,*,%}
* Put these operators in between the numbers 
* (there will be n-1 positions between the numbers)
* and count the equations for which left hand 
* side to the equal sign is same as the right hand side
*** SWC-4 (Date: 10/08/2022)
*/
// It is nothing but generating all the possible permutations (Since the objects are different)

import static java.lang.System.*;
import java.util.*;

public class ValidEquations
{
	static class Sol 
	{
		int count = 0;
	}
	
	static String ops[] =  {"+", "*", "%"};
	
	public static void main(String [] args)
	{
        int A[] = {2,8,5,1,10};
		int n = A.length;
		String[] B =  new String[2*n-1];
		int index = 0;
		
		for(int i=0; i<2*n-1; i+=2)
			B[i] = String.valueOf(A[index++]);
		
		Sol t = new Sol();
		
		t.count = 0;
		
		for(int i=1; i<2*n-1; i+=2)
		{
			B[i] = "=";
			solve(B, 0, 2*n-2, t);
			B[i] = null;
		}
		
		out.println(t.count);
	}
	
	static void solve(String[] B, int start, int end, Sol t)
	{
			if(start == end)
			{
				if(calc(B, 0, end))
					t.count++;
				return;
			}		
		
		    if(B[start] != null)
			{
				solve(B, start+1, end,t);
				return;
			}
		
			for(int j=0; j<3; j++)
			{
				B[start] = ops[j];
				solve(B, start+1, end, t);
				B[start] = null;			
			}
	}
	
	static boolean calc(String[] B, int start, int end)
	{		
	    for(String x : B)
			System.out.print(x+" ");
		System.out.println();
		
		
		Set<String> set = new HashSet<>();
        set.add("+");		
        set.add("*");		
        set.add("%");
        set.add("=");		
        
		Stack<String> stack1 = new Stack<>();
		Stack<String> stack2 = new Stack<>();
		int x = -1;
		stack1.push(B[start]);
		
		for(int i=start+1; i<=end; i++)
		{			
	        if(B[i] == null)
				return false;
			
			if(set.contains(B[i]))
				stack2.push(B[i]);
			else
			{
				int val = Integer.parseInt(stack1.pop());
				String sign = stack2.pop();
				int num = Integer.parseInt(B[i]);
				if(sign.equals("+"))
					val += num;
				else if(sign.equals("*"))
					val *= num;
				else if(sign.equals("%"))
                    val %= num;
				else if(sign.equals("="))
				{
					x = val;
					val = num;
				}				
						
			    stack1.push(String.valueOf(val));
			}
		}
		
		return Integer.parseInt(stack1.pop()) == x;
	}
}
