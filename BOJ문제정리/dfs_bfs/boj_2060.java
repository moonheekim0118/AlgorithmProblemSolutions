package Graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.*;
/*
 boj 바이러스
 bfs 사용해서 bfs로 탐색 요소 ++
  */

public class Main {
	static int v,e,n;
	static boolean[]visited;
	static ArrayList<ArrayList<Integer>> list;
	static Queue<Integer> que;
	static int comp;
	public static void bfs() {
		que.offer(1); //1번부터 감염
		visited[1]=true;
		comp++;
		int tmp;
		while(!que.isEmpty()) {
			tmp=que.peek();
			que.poll();
			for(int i: list.get(tmp)) {
				if(!visited[i]) {
					que.offer(i);
					visited[i]=true;
					comp++;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();//노드 개수
		e = in.nextInt();  // 간선개수 
		comp=0; 
		list = new ArrayList<>(); //리스트 생성
		que = new LinkedList<>(); //큐 생성
		visited = new boolean[n+1]; //visited배열 생성
		for (int i=0; i<=n; i++) 
		{
			list.add(new ArrayList<>()); //리스트 생성-초기화 
		}
		for (int i=0; i<e; i++) { //간선입력
			int t= in.nextInt();
			int f =in.nextInt();
			list.get(f).add(t); //양방향 
			list.get(t).add(f);
		}
		
		bfs();
		System.out.println(comp-1);
		in.close();
	}
}

