package boj1931;
import java.util.*;
import java.io.*;
/*
백준 1931 회의실배정
그리디 알고리즘 
끝나는 시간 별로 정렬
끝나는 시간이 같다면 시작시간으로 정렬
*/
class pair implements Comparable<pair>{
	pair(int start, int end){
		this.start=start;
		this.end=end;
	}
	pair(){}
	
	
	int start;
	int end;
	@Override //end를 기준으로해서 정렬한후 start를 기준으로 정렬 한번 더 해주어야하 한다.. .. .. .. .....
	public int compareTo(pair arg) {
		if(this.end < arg.end ) return -1;
		else if(this.end>arg.end ) return 1;
		else if (this.end == arg.end) {
			if(this.start < arg.start) return -1;
			else if(this.start > arg.start) return 1;
		}
		return 0;
	}
}

public class Main {	
	static pair[] info; 
	static int maxnum=1;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(stk.nextToken());
		info = new pair[n]; 
		for(int i=0;i<n; i++) {
			stk = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(stk.nextToken());
			int end = Integer.parseInt(stk.nextToken());
			info[i]=new pair(start,end); 
		}
		
		Arrays.parallelSort(info); //끝나는 시간 별로 소팅
		solve(n);
		System.out.println(maxnum);
	}
	
	private static void solve(int n) {
		pair now = info[0]; //일단저장 
		for(int i=1; i<n; i++) {
			if(info[i].start >= now.end){
				now = info[i];
				maxnum++;
			}
		}
		
	}

}
