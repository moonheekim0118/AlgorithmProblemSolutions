package boj1931;
import java.util.*;
import java.io.*;
/*
���� 1931 ȸ�ǽǹ���
�׸��� �˰��� 
������ �ð� ���� ����
������ �ð��� ���ٸ� ���۽ð����� ����
*/
class pair implements Comparable<pair>{
	pair(int start, int end){
		this.start=start;
		this.end=end;
	}
	pair(){}
	
	
	int start;
	int end;
	@Override //end�� ���������ؼ� �������� start�� �������� ���� �ѹ� �� ���־���� �Ѵ�.. .. .. .. .....
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
		
		Arrays.parallelSort(info); //������ �ð� ���� ����
		solve(n);
		System.out.println(maxnum);
	}
	
	private static void solve(int n) {
		pair now = info[0]; //�ϴ����� 
		for(int i=1; i<n; i++) {
			if(info[i].start >= now.end){
				now = info[i];
				maxnum++;
			}
		}
		
	}

}
