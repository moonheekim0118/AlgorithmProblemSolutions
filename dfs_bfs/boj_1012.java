package boj1012;
import java.util.*;

/*
 * boj 1012 유기농배추
 *  dfs 사용 
 *  */

public class Main {
	static int[][] graph; //인접 배열 
	static boolean[][] visited; //방문여부 
	static int[] res; //결과값 저장 
	static int m; //그래프 가로길이 
	static int n;// 그래프 새로길이 
	private static void dfs(int x, int y) 
	{
		visited[x][y]=true;
		if(x!=0 && !visited[x-1][y] && graph[x-1][y]==1) dfs(x-1,y);
		if(x!=m-1 && !visited[x+1][y] && graph[x+1][y]==1) dfs(x+1,y);
		if(y!=0 && !visited[x][y-1] && graph[x][y-1]==1) dfs(x,y-1);
		if(y!=n-1 && !visited[x][y+1] && graph[x][y+1]==1) dfs(x,y+1);
	}
	
	public static void main(String[] args) 
	{
		Scanner in = new Scanner(System.in);
		int tc=in.nextInt();
		res = new int[tc];
		int index = 0;
		for(int i=0 ; i<tc ;i++) 
		{
			m = in.nextInt();
			n = in.nextInt();
			int k = in.nextInt();
			graph= new int[m][n];
			visited=  new boolean[m][n];
			
			for(int j=0; j< k ; j++) //배추 심어져있는 위치 저장  
			{
				int x = in.nextInt();
				int y = in.nextInt();
				graph[x][y]=1;
			}
			int count=0;
			for(int j =0; j<m; j++) { //dfs
				for(int h = 0; h<n ; h++) {
					if(graph[j][h]==1 && !visited[j][h]) {
						dfs(j,h);
						count ++ ; //dfs 한번 돌때마다 count+1 (배추들이 서로 인접해있는 공간이므로 )
					}
				}
			}
			res[index]=count; //배열에 저장해준다 . 
			index++;
			
		}
		
		for(int i=0; i<index; i++)  //결과값 출력 
		{
			System.out.println(res[i]);
		}
		
	}
}
