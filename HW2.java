
public class HW2 {
	public static void main(String[] args) {
		//Create an array/collection of numbers
		int[] arr = {1,2,3,4,5,6,7};
		//Create a loop that loops over each number
		//Have the loop output only even numbers regardless of how long the array/collection is.
		for (int i = 0; i < arr.length; i++) {
			if(arr[i] % 2 == 0) {
				System.out.print(arr[i] + " ");
			}
		}
	}
}
