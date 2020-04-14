package timemachine;
import java.io.*;
import java.util.*;

/* boj 11657 타임머신
 * 벨만포드 알고리즘 사용
 * 오버플로 때문에 제대로 음수 사이클 측정이 안될 수 있으므로 거리 저장 배열 타입은 long
 * */

class Graph //edge 정보 객체배열로 저장 
{
	Graph(int src, int dest ,int weight){
		this.src=src;
		this.dest=dest;
		this.weight=weight;
	}
	int src; //from
	int dest; //to 
	int weight;  //가중치 
}


public class Main {
	private static final int INF = Integer.MAX_VALUE;
	private static int N; //도시 개수 
	private static int M; //버스 노선 개수 
	private static Graph[] edge; //엣지 저장 
	private static long[] dist; //노드 1로부터 각각의 노드 까지의 최단경로 저장
   public static void main(String[] args) throws IOException{		
	   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	   StringTokenizer stk = new StringTokenizer(br.readLine());
	   N = Integer.parseInt(stk.nextToken());
	   M = Integer.parseInt(stk.nextToken());
		dist = new long[N+1]; //초기화 
		edge = new Graph[M]; //초기화 
		for(int i=0; i<M;i++) { //간선 입력 
			stk = new StringTokenizer(br.readLine());
			int src = Integer.parseInt(stk.nextToken());
			int dest = Integer.parseInt(stk.nextToken());
			int weight = Integer.parseInt(stk.nextToken());
			edge[i]=new Graph(src,dest,weight);
		}
		Arrays.fill(dist, INF); //초기화 
		dist[1]=0; //출발 노드 초기화 
		if(bellmanFord()==false) { //음수 사이클 있음 
			System.out.println("-1");
		}
		else { //음수 사이클 없음 
			for(int i = 2; i<=N; i++) {
				if(dist[i]==INF) {
					System.out.println("-1"); //연결 되어 있지 않음 
				}
				else {
					System.out.println(dist[i]); //최단경로 출력 
				}
			}
		}
	}
	
	
	public static boolean bellmanFord() {
		for(int i = 1; i<=N-1; i++) { // 정점 수 -1 
			for(int j=0;j<M; j++) { //간선수 M 
				int u =edge[j].src; //from
				int v=edge[j].dest; //to 
				int w=edge[j].weight; //가중치
				//dist[u]가 구해졌음 && dist[v]가 dist[u] + v의 가중치보다 크면 
				if(dist[u]!=INF && dist[v] > dist[u]+w) {
					dist[v] = dist[u]+w; //갱신 
				}
			 }
			} 
		
		for(int j=0; j<M; j++) { //음수사이클 다시한번 확인 	
			int u = edge[j].src; //from
			int v= edge[j].dest; //to 
			int w = edge[j].weight; //가중치 
			if( dist[u]<INF && dist[v] > dist[u]+w) {
				return false; //값이 또 적게 변할 수 있다면 계속 변하는 음수사이클이 있다는 것. 따라서 false 리턴 
			}
		}
		return true; //음수사이클 없음 
		
	}
}
