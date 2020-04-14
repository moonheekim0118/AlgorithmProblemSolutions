package shortestPath;
import java.util.*;

/* boj 1753 최단경로 
 * 다익스트라 
 * priority queue 사용 
 * adj list 사용
 * 
 * */

class distance implements Comparable<distance> //pq 저장용 -> element로 저장하면 시간초과남!! 
{
	public distance(int key, int u) {
		this.key=key;
		this.u=u;
	}
	public distance() {}
	int key;
	int u;
	
	public int compareTo(distance dis) {
		if(this.key >=dis.key) return 1;
		else if(this.key < dis.key) return -1;
		return 0;
	}
}

class element  // ArrayList 저장용 
{
	public element (int u, int v, int key){

		this.u=u;
		this.v=v;
		this.key=key;
	}
	int key;
	int u;
	int v;

}

public class Main {
	static ArrayList<ArrayList<element>> graph;
	static PriorityQueue<distance> pq = new PriorityQueue<distance>();
	static boolean[] visited; 
	static int[] dist; //저장용  
	static int V;
	static int E;
	static int K;
	public static void main(String[] args) 
	{ 
		 Scanner in = new Scanner(System.in);
		 V=in.nextInt();
		 E=in.nextInt();
		 K=in.nextInt();
		 graph = new ArrayList<ArrayList<element>>();
		 for(int i=0; i<V+1 ; i++) {
			 graph.add(new ArrayList<element>());
		 }
		 visited = new boolean[V+1];
		 dist = new int[V+1];
		 Arrays.fill(dist, Integer.MAX_VALUE);
		 
		 int u,v,w;
		 for(int i=0 ; i < E ; i++) 
		 {
			 u=in.nextInt(); //정점   //유향그래프 
			 v=in.nextInt(); //정점2
			 w=in.nextInt(); //가중치 
			 element set=new element(u,v,w);
			 graph.get(u).add(set);
		 }
		 dijkstra(K);
		 printDist();
		 in.close();
	}
	
	private static void printDist() { //출력 
		for(int i=1; i<dist.length;i++){
			if(dist[i]==Integer.MAX_VALUE) {
				System.out.println("INF");
			}
			else {
				System.out.println(dist[i]);
			}
		}
	}
	
	private static distance getMinDist() {
		
		int minKey = Integer.MAX_VALUE;
		distance minVal=new distance();
		while(!pq.isEmpty()) { //pq가 빌 때 까지 -> 처음에는 V 만큼 해줬었다.
			                   //하지만 pq에는 최소 E 만큼의 요소가 들어가고,같은 값으로 채워진 요소들이 V개가 넘는 경우 
			                   // visited가 안된 요소는 더 늦게 나올 수 있음
			                   // 따라서  무조건 pq.isEmpty 가 조건이어야 한다. 
			if(pq.peek().key <= minKey && visited[pq.peek().u]==false) { 
				minVal=pq.peek();
				pq.poll(); 
				break; 
			}
			else { //visited된 경우 그냥 poll
				minVal=pq.poll();
			}
		}
		return minVal;
	}
	
	private static void dijkstra(int src) {
		int base = Integer.MAX_VALUE;
		for(int i = 1; i< V+1; i ++) {
			distance tmp= new distance(base,i);
			pq.offer(tmp);
		}
		
		distance tmp2 = new distance(0,src); 
		pq.offer(tmp2);
		int u;
		for(int i =0; i< V; i++) {
			distance min=getMinDist();
			if(min.key == Integer.MAX_VALUE) break;
			u = min.u;
			dist[u]=min.key;
			visited[u]=true;
			for(element j : graph.get(u)) {
				if(visited[j.v]==false) {
					distance dis=new distance(j.key+dist[u],j.v); //가중치 갱신 
					//따로 값을 덮어쓰지 않아도 pq에서 알아서 가장 작은 가중치부터 poll 해준다. 
					pq.offer(dis);
				}
			}
		}
	}
}