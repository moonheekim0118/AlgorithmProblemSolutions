package boj10451;
import java.util.*;
import java.io.*;
/*
 * BOJ 10451 순열사이클
 * DFS 이용해서 사이클 유무 찾기 
 * 부모노드의 재귀가 아직 안끝났는데, 이미 visited 된 ( 재귀 스택에 들어간) 노드를 방문하려고하면
 * backward edge가 있는 것 == 사이클 있음 
 * */

public class Main {
	private static boolean[] visited; 
	private static ArrayList<ArrayList<Integer>>graph;
	private static int[] result;
	private static boolean is_cycle;
	private static int index=0;
	private static int recursionstep=0;
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk= new StringTokenizer(br.readLine());
		int tc = Integer.parseInt(stk.nextToken()); //test case 
		String[] s; 
		result = new int[tc]; //we will store the answers 
		for(int i=0; i<tc; i++) {
			stk= new StringTokenizer(br.readLine());
			int n = Integer.parseInt(stk.nextToken()); //순열사이클 갯수
			visited = new boolean[n+1]; 
			Arrays.fill(visited, false); 
			recursionstep=0; 
			graph =new ArrayList<ArrayList<Integer>>(); // 이렇게 다시 선언 꼭 필요 
			for(int j = 0; j<n+1 ; j++) { //생성 
				graph.add(new ArrayList<Integer>());
			}
			s= new String[n+1]; 
			s = br.readLine().split(" ");
			for(int j = 0; j< n; j++) {
				graph.get(j).add(Integer.parseInt(s[j]));  } 
			//ArrayList는 0부터 안넣으면 ArrayIndexOutofBound Error 발생 
			for(int j = 0 ; j< n ; j++) {
				if(!visited[j+1]) {  //아직 방문안되었다면 
					dfs(j+1); 
					recursionstep=0;
					if(is_cycle) { ///사이클 있음 
						result[index]++;
						is_cycle=false;
						
					}
					
				}
			}
			index++; //다음 테스트케이스로 
		}
		for(int i: result) {
			System.out.println(i);
		}
	}
	
	
	private static void dfs(int s) {
		visited[s]=true; //방문표시 
		for(Integer v : graph.get(s-1)) { //간선탐색 
			if(!visited[v]) {
				dfs(v);
			}
			else if(visited[v] && recursionstep==0) { //재귀가 끝나지도 않았는데 이미 visited된 노드를
				//dfs하려고 하는 것은 backward edge 가 있다는 것으로 사이클 있음을 나타낸다. 
				is_cycle=true; //true로 표시 
			}
		}
		recursionstep=1; //재귀 끝을 의미 
	}
}
