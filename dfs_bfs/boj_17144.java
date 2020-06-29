package boj17144;
import java.io.*;
import java.util.*;

/* boj 17144 �̼����� �ȳ�! 
 * 1) bfs�� ������ 4���� Ȯ�Ž����ش�. �� �� ť�� �ִ������� visited�ȵǾ��ְ� 0 �ΰ��,
 *    Ȯ���� ������������ ������ �����ش�.
 *    Ȯ������ ���� map�� ����
 *2) Ȯ������ �������ش�.
 *3) ����û����� �̼������� �Ű��ش�. 
 * for github test
 * */
class pair{
	int x;
	int y;
	pair(int x, int y){
		this.x=x;
		this.y=y;
	}
}
public class Main {
	private static int R;
	private static int C;
	private static int T;
	private static int X1=-1;
	private static int X2;
	private static int[][]dustMap;
	private static int[][]MapCopy;
	private static boolean[][]visited;
	static int[]dx = {-1,1,0,0};
	static int[]dy= {0,0,-1,1};
	private static Queue<pair>queue = new LinkedList<pair>();
	private static void bfs(int sx, int sy) { //Ȯ��
		// �װ��� �������� Ȯ��.
		// Ȯ�� ��, Ȯ���Ų ���� �̼������� �������ش�.
		// �ٸ� ���� �̼������� MapCopy�� �ش� ��ġ�� �����صд�.
		visited[sx][sy]=true;
		queue.add(new pair(sx, sy)); 
		while(!queue.isEmpty()) {
			pair polled= queue.poll();
			int x = polled.x;
			int y = polled.y;
			int cnt = 0; 
			int dust = dustMap[x][y]/5;
			for(int i =0 ; i<4; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(nx<0 || ny<0 || nx>=R || ny>=C) continue;
				if(dustMap[nx][ny]==-1) continue;
				cnt++; //Ȯ�� ��Ų ĭ�� ��
				if(!visited[x][y]&&dustMap[nx][ny]!=0) {
					queue.add(new pair(nx,ny));
					visited[nx][ny]=true;
				}
				MapCopy[nx][ny]+=dust; // Ȯ��
			}
			dustMap[x][y]=dustMap[x][y]-(dust*cnt); // Ȯ�� �� ��ȭ 
		}
	}
	
	private static void spread() { // MapCopy�� �ִ� ���� �ű�� �޼���
		for(int i =0 ; i<R; i++) {
			for(int j=0; j<C; j++) {
				dustMap[i][j]+=MapCopy[i][j];
			}
		}
	}
	
	private static void move() { //����û���� ���� 
		// ù��° ����û���� ��ġ X1 0
		// �ι�° ����û���� ��ġ X2 0
		
		//ù��° ����û����
		// map[x1-1][0]= 0 ���� �����.. �� �� �ϳ��� �����ֱ�
		dustMap[X1-1][0]=0;
		for(int i = X1-2 ; i>=0; i--) {
			dustMap[i+1][0]=dustMap[i][0];
		} 
		dustMap[0][0]=dustMap[0][1]; 
		for(int i = 1; i<C ; i++) {
			dustMap[0][i-1]=dustMap[0][i];
		} 
		dustMap[0][C-1]= dustMap[1][C-1];
		for(int i = 2; i<=X1; i++) {
			dustMap[i-1][C-1]=dustMap[i][C-1];
		}
		dustMap[X1][C-1]=dustMap[X1][C-2];
		for(int i= C-2; i > 1 ; i--) {
			dustMap[X1][i]=dustMap[X1][i-1];
		}
		dustMap[X1][1]=0;
		
		//�ι�° ����û����
		dustMap[X2+1][0]=dustMap[X2+2][0];
		for(int i = X2+2 ; i+1<R; i++) {
			dustMap[i][0]=dustMap[i+1][0];
		} // ok 
		dustMap[R-1][0]=dustMap[R-1][1]; 
		for(int i = 1; i<C ; i++) {
			dustMap[R-1][i-1]=dustMap[R-1][i];
		} //ok
	
		dustMap[R-1][C-1]=dustMap[R-2][C-1];
		for(int i = R-3; i>=X2; i--) {
			dustMap[i+1][C-1]=dustMap[i][C-1];
		}
		dustMap[X2][C-1]=dustMap[X2][C-2];
		for(int i= C-2; i > 1 ; i--) {
			dustMap[X2][i]=dustMap[X2][i-1];
		}
		dustMap[X2][1]=0;
	}
	
	private static int getSum() { // ��ü �̼����� �� ��ȯ 
		int sum = 0;
		for(int i =0 ; i< R; i ++) {
			for(int j=0; j<C; j++) {
				if(dustMap[i][j]==-1) continue;
				sum+=dustMap[i][j];
			}
		}
		return sum;
	}
	private static int solve() {
		for(int t =0 ; t<T; t++) {
			// Ȯ��
			for(int i = 0 ; i <R; i++) {
				for(int j =0; j<C; j++) {
					if(dustMap[i][j]!=-1 && dustMap[i][j]!=0 && visited[i][j]!=true) {
						bfs(i,j);
					}
				}
			}
			spread(); 			
			move();
			//visited, copy ����
			for(int i=0; i< R; i++) {
				for(int j=0; j<C; j++) {
					MapCopy[i][j]=0;
					visited[i][j]=false;
				}
			}
		}
		return getSum();
	}
	
	public static void main(String[]args)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());	
		T = Integer.parseInt(st.nextToken());
		
		dustMap = new int[R][C];
		MapCopy = new int[R][C];
		visited = new boolean[R][C];
		String []s;
		for(int i = 0 ; i < R; i++) {
			s = br.readLine().split(" ");
			for(int j =0 ; j < C; j ++) {
				dustMap[i][j]=Integer.parseInt(s[j]);
				if(dustMap[i][j]==-1) {
					if(X1==-1) {
						X1= i; 
					}
					else {
						X2 = i;
					}
				}
			}
		}
		System.out.println(solve());
	}
}
