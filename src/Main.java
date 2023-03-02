
public class Main {

	public static void believeString(Object x) {
		System.out.println("Length of string = " + ((String)x).length());
	}
	
	public static void main(String[] args) {
		believeString("Hello");
		believeString("");
		believeString(42);
	}
}
