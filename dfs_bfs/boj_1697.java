package boj1697;
import java.io.*;
import java.util.*;

/*
 * boj 1697 숨바꼭질
 * bfs 사용해서 뒤로 가는 경우 앞으로가는 경우 순간이동하는 경우를 돌아주면 된다.
 * parent 값을 저장해서 최단경로 찾기 
 * */

class ListGraph{
	private ArrayList<ArrayList<Integer>> listGraph;
	public ListGraph(int initSize) {
		this.listGraph = new ArrayList<ArrayList<Integer>>();
		for(int i=0; i<=initSize; i++)
			listGraph.add(new ArrayList<Integer>());
	}
	public ArrayList<ArrayList<Integer>>getGraph(){
		return this.listGraph;
	}
	
	public void put(int x, int y) {
		listGraph.get(x).add(y);
	}
	
}

public class Main {
	private static int [] visit;
	private static Queue<Integer> q = new LinkedList<Integer>();
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); //수빈 위치 
		int k = Integer.parseInt(st.nextToken()); //동생 위치
		int max;
		if(n>k) max= n;
		else max=k;
        visit = new int[1000002];
		Arrays.fill(visit, -1);
		int ans = fastest_time(n , k,1000002);
		System.out.println(ans);
	}

	private static int fastest_time(int n , int k, int max) {
		if(n==k) return 0;
		q.add(n); //수빈이 위치 
		visit[n]=0;
		while(!q.isEmpty()) {
			int peekNum=q.poll();
			if(peekNum == k) {
				return visit[k] ;
			}
			if(peekNum-1 >=0 && visit[peekNum-1]==-1) {
				q.add(peekNum-1);
				visit[peekNum-1]=visit[peekNum]+1;
			}
			if(peekNum+1 <max && visit[peekNum+1]==-1) {
				q.add(peekNum+1);
				visit[peekNum+1]=visit[peekNum]+1;
			}
			
			if(peekNum*2<max && visit[peekNum*2]==-1) {
				q.add(peekNum*2);
				visit[peekNum*2]=visit[peekNum]+1;
			}
		}
		return 0;
	}
}
