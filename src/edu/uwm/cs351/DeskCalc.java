package edu.uwm.cs351;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class DeskCalc {

	final StreamTokenizer tokens;
	
	public DeskCalc(StreamTokenizer st) {
		tokens = st;
	}
	
	public void run() throws IOException {
		while (tokens.nextToken() != StreamTokenizer.TT_EOF) {
			switch (tokens.ttype) {
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
