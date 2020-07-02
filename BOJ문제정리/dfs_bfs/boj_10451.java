package boj10451;
import java.util.*;
import java.io.*;
/*
 * BOJ 10451 ��������Ŭ
 * DFS �̿��ؼ� ����Ŭ ���� ã�� 
 * �θ����� ��Ͱ� ���� �ȳ����µ�, �̹� visited �� ( ��� ���ÿ� ��) ��带 �湮�Ϸ����ϸ�
 * backward edge�� �ִ� �� == ����Ŭ ���� 
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
			int n = Integer.parseInt(stk.nextToken()); //��������Ŭ ����
			visited = new boolean[n+1]; 
			Arrays.fill(visited, false); 
			recursionstep=0; 
			graph =new ArrayList<ArrayList<Integer>>(); // �̷��� �ٽ� ���� �� �ʿ� 
			for(int j = 0; j<n+1 ; j++) { //���� 
				graph.add(new ArrayList<Integer>());
			}
			s= new String[n+1]; 
			s = br.readLine().split(" ");
			for(int j = 0; j< n; j++) {
				graph.get(j).add(Integer.parseInt(s[j]));  } 
			//ArrayList�� 0���� �ȳ����� ArrayIndexOutofBound Error �߻� 
			for(int j = 0 ; j< n ; j++) {
				if(!visited[j+1]) {  //���� �湮�ȵǾ��ٸ� 
					dfs(j+1); 
					recursionstep=0;
					if(is_cycle) { ///����Ŭ ���� 
						result[index]++;
						is_cycle=false;
						
					}
					
				}
			}
			index++; //���� �׽�Ʈ���̽��� 
		}
		for(int i: result) {
			System.out.println(i);
		}
	}
	
	
	private static void dfs(int s) {
		visited[s]=true; //�湮ǥ�� 
		for(Integer v : graph.get(s-1)) { //����Ž�� 
			if(!visited[v]) {
				dfs(v);
			}
			else if(visited[v] && recursionstep==0) { //��Ͱ� �������� �ʾҴµ� �̹� visited�� ��带
				//dfs�Ϸ��� �ϴ� ���� backward edge �� �ִٴ� ������ ����Ŭ ������ ��Ÿ����. 
				is_cycle=true; //true�� ǥ�� 
			}
		}
		recursionstep=1; //��� ���� �ǹ� 
	}
}
