package boj2667;
import java.util.*;
import java.io.IOException;
import java.util.Arrays;

/*
 boj 2667 단지번호 붙이기
 dfs 사용
*/
public class Main {
	private static int count=0;
	private static int [][]graph= new int[25][25];
	private static boolean[][]visited; //visted 체크  
	private static int index=0;
	private static int n;
	private static void dfs(int i,int j) {
		count++; //재귀 깊이만큼 
		visited[i][j]=true;
		if(i!=0&& visited[i-1][j]!=true && graph[i-1][j]==1) {
			dfs(i-1,j);
		}
		if(i!=n-1&&visited[i+1][j]!=true && graph[i+1][j]==1)
			dfs(i+1,j);
		
		if(j!=0&&visited[i][j-1]!=true && graph[i][j-1]==1)
			dfs(i,j-1);
		if(j!=n-1&&visited[i][j+1]!=true && graph[i][j+1]==1)
			dfs(i,j+1);
		}
		
	public static void main(String[] args) throws IOException 
	{
		Scanner in=new Scanner(System.in);
		n=in.nextInt();
		graph= new int[n][n];
		visited = new boolean[n][n];
		int[] ans = new int[n*n];
		
		for(int i=0; i<n; i++){ //각 간선 받기 
            String input = in.next(); //공백없이 입력되므로 String으로 받은후 
            for(int j=0; j<n; j++){
                graph[i][j] = input.charAt(j)-'0'; //char형 - '0' 해서 int에 넣기 
            }
        }
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(graph[i][j]==1 && visited[i][j]==false) { 
					dfs(i,j);
					ans[index]=count; //출력할 배열에 저장 
					index++; //배열인덱스 
				 	count=0; //다음 단지를 위해 count는 0으로 
				}
			}
		}
		
		Arrays.sort(ans); //소팅 
		System.out.println(index); //총 단지 수 
		for(int element : ans ) { 
			if(element!=0) {//소팅되어서 0인 요소들이 앞에 채워져있음 
			System.out.println(element);}
			
		}	
		
	}
}
