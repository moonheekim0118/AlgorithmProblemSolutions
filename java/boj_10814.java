import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n= in.nextInt();
		fair[] list = new fair[n];
		for(int i=0; i<n;i++) {
			int age= in.nextInt();
			String Name=in.nextLine();
			list[i] =new fair(age,Name);
		}
		
		Arrays.sort(list);
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
	}
	
	@Override
	public String toString() {
		return age+""+Name;
	}
}
