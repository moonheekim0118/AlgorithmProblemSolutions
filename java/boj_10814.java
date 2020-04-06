import java.util.*;
/* BOJ 10814 나이순 정렬 
 * Stable-sort
 * 객체배열 -> comparable 구현 / 오버라이드로 정렬 
 * 객체배열 정렬할 때는 Arrays.sort(참조변수)  
 *  */
public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n= in.nextInt();
		fair[] list = new fair[n]; //객체배열생성 
		for(int i=0; i<n;i++) {
			int age= in.nextInt();
			String Name=in.nextLine();
			list[i] =new fair(age,Name); //초기화 
		}
		
		Arrays.sort(list); //정렬
		for(int i=0; i<n; i++) {
			System.out.println(list[i].toString());
		}
		in.close();
	}
}

class fair implements Comparable<fair>
{
	int age;
	String Name;
	
	fair(int age, String Name) {
		this.age=age;
		this.Name=Name;
	}
	
	@Override 
	public int compareTo(fair f) {
		return this.age-f.age; 
	} //객체 특정 요소끼리 비교하기 

	
	@Override
	public String toString() {
		return age+""+Name;
	}
}
