package boj1012;
import java.util.*;

/*
 * boj 1012 ��������
 *  dfs ��� 
 *  */

public class Main {
	static int[][] graph; //���� �迭 
	static boolean[][] visited; //�湮���� 
	static int[] res; //����� ���� 
	static int m; //�׷��� ���α��� 
	static int n;// �׷��� ���α��� 
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
			
			for(int j=0; j< k ; j++) //���� �ɾ����ִ� ��ġ ����  
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
						count ++ ; //dfs �ѹ� �������� count+1 (���ߵ��� ���� �������ִ� �����̹Ƿ� )
					}
				}
			}
			res[index]=count; //�迭�� �������ش� . 
			index++;
			
		}
		
		for(int i=0; i<index; i++)  //����� ��� 
		{
			System.out.println(res[i]);
		}
		
	}
}
