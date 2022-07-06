import static java.lang.System.*;
import java.util.*;
  
public class ShortestSafePath
{
	static class Util 
	{
		int x;
		int y;
		int dist;
		
		Util(int x, int y, int dist)
		{
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}
	
   static void updateGrid(int[][] grid, int m, int n)
   {
	   boolean visited[][] = new boolean[m][n];
	   
	   for(int i=0; i<m; i++)
	   {
		   for(int j=0; j<n; j++)
		   {
			   if(grid[i][j] == 0 && !visited[i][j])
			   {
				   if(isSafe(grid, i-1, j, m, n))
				   {
					   grid[i-1][j] = 0;
					   visited[i-1][j] = true;
				   } 
				   if(isSafe(grid, i, j-1, m, n))
				   {
					   grid[i][j-1] = 0;
					   visited[i][j-1] = true;
				   }
				   if(isSafe(grid, i+1, j, m, n))
				   {
					   grid[i+1][j] = 0;
					   visited[i+1][j] = true;
				   }
				   if(isSafe(grid, i, j+1, m, n))
				   {
					   grid[i][j+1] = 0;
					   visited[i][j+1] = true;
				   }
			   }
		   }
	   }
   }
   
   static boolean isSafe(int[][] grid, int x,int y,int m,int n)
   {
	   return x<m && x>=0 && y<n && y>=0 && grid[x][y]!=0;
   }
   
   static int solve(int[][] grid, int x, int y, int m, int n)
   {
	   Queue<Util> q = new ArrayDeque<>();
	   boolean visited[][] = new boolean[m][n];
	   q.add(new Util(x, y, 0));
       visited[x][y] = true;
	   
	   while(!q.isEmpty())
	   {
				   Util u = q.remove();
				   int i = u.x;
				   int j = u.y;
				   int dist = u.dist;
		           
				   if(j==n-1)
					   return dist;
				   
				   if(isSafe(grid, i-1, j, m, n) && !visited[i-1][j])
				   {
					   q.add(new Util(i-1, j, dist+1));
					   visited[i-1][j] = true;
				   } 
				   if(isSafe(grid, i, j-1, m, n) && !visited[i][j-1])
				   {
					   q.add(new Util(i, j-1, dist+1));
					   visited[i][j-1] = true;
				   }
				   if(isSafe(grid, i+1, j, m, n) && !visited[i+1][j])
				   {
					   q.add(new Util(i+1, j, dist+1));
					   visited[i+1][j] = true;
				   }
				   if(isSafe(grid, i, j+1, m, n) && !visited[i][j+1])
				   {
					   q.add(new Util(i, j+1, dist+1));
					   visited[i][j+1] = true;
				   }		   		   
	   }
   	   
	   return Integer.MAX_VALUE;
   }
	
   public static void main(String [] args) throws Exception
   { 
      int grid[][] = {
        { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
        { 1, 0, 1, 1, 1, 1, 1, 1, 1, 1 },
        { 1, 1, 1, 0, 1, 1, 1, 1, 1, 1 },
        { 1, 1, 1, 1, 0, 1, 1, 1, 1, 1 },
        { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
        { 1, 1, 1, 1, 1, 0, 1, 1, 1, 1 },
        { 1, 0, 1, 1, 1, 1, 1, 1, 0, 1 },
        { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
        { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
        { 0, 1, 1, 1, 1, 0, 1, 1, 1, 1 },
        { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
        { 1, 1, 1, 0, 1, 1, 1, 1, 1, 1 }
    };
 
	  int m = grid.length;
	  int n = grid[0].length;
	  updateGrid(grid, m, n);
	  
	  for(int x[] : grid)
	  {
		  for(int y : x)
			  out.print(y+" ");
		  out.println();
	  }
	  
	  int min = Integer.MAX_VALUE;
	  
	  for(int i=0; i<m; i++)
	  {
		  if(grid[i][0] == 1)
			  min = Math.min(min, solve(grid, i, 0, m, n));
	  }
	  
	  out.println(min);
   }
}
