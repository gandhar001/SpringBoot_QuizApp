import java.util.Scanner;

public class DCATest 
{
	
public static void main(String args[])
{
	Scanner sc=new Scanner(System.in);
	String s=sc.nextLine();
	int l=sc.nextInt();
	int ans=0;
	int N=s.length();
	
	int rem=N%l;
	int part=N-rem;
	
	int i=0;
	for(i=0;i<N-l;) 
	{
		int k=i+l;
		String sub=s.substring(i, k);
		int count=0;
		for(int j=0;j<sub.length();j++)
		{
			
			if(sub.charAt(j)=='1')
			{
				count=count+1;
			}
			
		}
		i=k;
		if(count>=ans)
		{
			ans=count;
		}
				
	}
	System.out.println(ans+"ans1");
	
	if(N%l!=0)
	{
		

		System.out.println(part+" "+rem+"part rem");
		String sub=s.substring(part,part+rem);
		System.out.println(sub+" sub");
		int count=0;
		for(int j=0;j<sub.length();j++)
		{
			
			if(sub.charAt(j)=='1')
			{
				count=count+1;
			}
			
		}
		if(count>=ans)
		{
			ans=count;
		}
	}
	
	System.out.println(ans+"ans");
}
}
