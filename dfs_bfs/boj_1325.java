package boj1325;
import java.util.*;
import java.io.*;
/*
 * boj 1325 효율적 해킹
 * 시간초과가 계속났었다.
 * ArrayList를 배열로 바꾸어주고 ans[]에 해당 노드로 진입하는 간선개수 저장해주니 통과
 * 
 * */

public class Main {
	private static int count=0;
	private static boolean[] visited;
	private static int[] ans;
	private static int result;
	private static ArrayList<Integer>[] graph= (ArrayList<Integer>[]) new ArrayList[10001];
	public static void main(String[] args)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		visited = new boolean[n+1];
		ans = new int[n+1];
		for(int i=1; i<n+1; i++) 
		{
			graph[i]=new ArrayList<Integer>();
		} //초기화 
		
		int to;
		int from;
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			to = Integer.parseInt(st.nextToken());
			from=Integer.parseInt(st.nextToken());
			graph[to].add(from);
		}

		for(int i=1 ; i<=n ;i++) {
			DFS(i);
			Arrays.fill(visited, false);
		}
		
		for(int i=1 ; i<= n; i++) {
			if(ans[i]==result) {
				System.out.print(i +" ");
			}
		}
	
	}

	
	private static void DFS(int s) {
		visited[s]=true;
		for(int e: graph[s]) {
			if(visited[e]==false) {
				ans[e]++; //e로 진입하는 간선 수 (indegree) 즉 outdegree가 아니라 indegree 저장 
				result= Math.max(result, ans[e]); //max 계산 
				DFS(e);
			}
		}

	}
}