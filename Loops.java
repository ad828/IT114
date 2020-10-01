package IT114;

public class Loops {

	public static int sum(int num) {
		int temp = 0;
		for(int i = 0; i <= num; i++) {
			temp = temp + i;
		}
		return temp;
	}
	public static void main(String[] args) {
		System.out.println(sum(10));
	}
}
