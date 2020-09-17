import java.util.Scanner;
public class HW {
public static void main(String[] args) {
	Scanner data = new Scanner(System.in);
			// 1. Create a variable that holds a name
System.out.println("Enter a Name");
String n = data.nextLine();
// 2. Output the name to the console

System.out.println("Hey there, " + n + "!");
System.out.println("How old are you?");
			// 3. Create a variable that holds an age (as a whole number)
	int a = data.nextInt();
			// 4. Output the age to the console
System.out.println(n + " is "+ a + " years old!");
			// 5. Create a variable that holds a decimal
System.out.println("How cool is this program expressed as a decimal out of ten?");
float dec = data.nextFloat();
System.out.println("It is " + dec + " out of ten.");
			// 6. Output the variable
}
}

