package shortestPath;
import java.util.*;

/* boj 1753 �ִܰ�� 
 * ���ͽ�Ʈ�� 
 * priority queue ��� 
 * adj list ���
 * 
 * */

class distance implements Comparable<distance> //pq ����� -> element�� �����ϸ� �ð��ʰ���!! 
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

class element  // ArrayList ����� 
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
	static int[] dist; //�����  
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
			 u=in.nextInt(); //����   //����׷��� 
			 v=in.nextInt(); //����2
			 w=in.nextInt(); //����ġ 
			 element set=new element(u,v,w);
			 graph.get(u).add(set);
		 }
		 dijkstra(K);
		 printDist();
		 in.close();
	}
	
	private static void printDist() { //��� 
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
		while(!pq.isEmpty()) { //pq�� �� �� ���� -> ó������ V ��ŭ �������.
			                   //������ pq���� �ּ� E ��ŭ�� ��Ұ� ����,���� ������ ä���� ��ҵ��� V���� �Ѵ� ��� 
			                   // visited�� �ȵ� ��Ҵ� �� �ʰ� ���� �� ����
			                   // ����  ������ pq.isEmpty �� �����̾�� �Ѵ�. 
			if(pq.peek().key <= minKey && visited[pq.peek().u]==false) { 
				minVal=pq.peek();
				pq.poll(); 
				break; 
			}
			else { //visited�� ��� �׳� poll
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
					distance dis=new distance(j.key+dist[u],j.v); //����ġ ���� 
					//���� ���� ����� �ʾƵ� pq���� �˾Ƽ� ���� ���� ����ġ���� poll ���ش�. 
					pq.offer(dis);
				}
			}
		}
	}
}