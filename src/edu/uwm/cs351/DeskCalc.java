package edu.uwm.cs351;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Stack;

public class DeskCalc {

	private final StreamTokenizer tokens;
	private final Stack<Double> stack;
	
	public DeskCalc(StreamTokenizer st) {
		tokens = st;
		stack = new Stack<>();
	}
	
	public void run() throws IOException {
		while (tokens.nextToken() != StreamTokenizer.TT_EOF) {
			switch (tokens.ttype) {
			case StreamTokenizer.TT_NUMBER:
				stack.push(tokens.nval);
				break;
			case '+': {
				double n2 = stack.pop();
				double n1 = stack.pop();
				stack.push(n1+n2);
			}
			break;	
			case '=': {
				double n = stack.peek();
				System.out.println(n);
			}
			break;
			default:
				if (tokens.ttype < 0) {
					System.out.println("Cannot handle " + tokens.sval);
				} else {
					System.out.println("Cannot handle '" + (char)tokens.ttype + "'");
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		StreamTokenizer st = new StreamTokenizer(new InputStreamReader(System.in));
		new DeskCalc(st).run();
	}
}
