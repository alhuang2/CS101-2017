// Alston Huang
// 1471706
// CMPS101 PA3
// Performs matrix operations using only the Matrix and List class
// Sparse.java

import java.io.*;
import java.util.Scanner;

class Sparse{
	public static void main(String[] args) throws IOException{
		if(args.length != 2)
			throw new RuntimeException("Usage: Sparse <infile> <outfile>");

		Scanner in;
		Matrix A;
		Matrix B;
		PrintWriter out;
		int count = 0;

		in = new Scanner(new File(args[0]));
		out = new PrintWriter(new File(args[1]));

		//System.out.println(in.next() + in.next() + in.next());

		int n = (int)Integer.parseInt(in.next());
		int a = (int)Integer.parseInt(in.next());
		int b = (int)Integer.parseInt(in.next());
		A = new Matrix(n);
		B = new Matrix(n);

		// in.nextLine();

		while(count<a){ 
			A.changeEntry((int)Integer.parseInt(in.next())
			,(int)Integer.parseInt(in.next())
			,(double)Double.parseDouble(in.next()));
			in.nextLine();
			count++;
		}

		//System.out.println(in.next() + in.next() + in.next());

		count = 0;
		while(count<b){
			B.changeEntry((int)Integer.parseInt(in.next())
			,(int)Integer.parseInt(in.next())
			,(double)Double.parseDouble(in.next()));
			in.nextLine();
			count++;
		}

		// while(count < a){
		// 	System.out.println(in.next() + " " + in.next() + " " +in.next());
		// 	in.nextLine();
		// 	count++;
		// }		
		// System.out.println();
		// count = 0;
		// while(count < b){
		// 	System.out.println(in.next() + " " + in.next() + " " +in.next());
		// 	in.nextLine();
		// 	count++;			
		// }
		out.println("A has " + A.getNNZ() + " non-zero entries: \n" + A);
		out.println("B has " + B.getNNZ() + " non-zero entries: \n" + B);
		out.println("(1.5)*A = \n" +A.scalarMult(1.5));
		out.println("A+B = \n" + A.add(B));
		out.println("A+A =\n" + A.add(A));
		out.println("B-A =\n" + B.sub(A));
		out.println("A-A = \n" + A.sub(A));
		out.println("Transpose(A) = \n" + A.transpose());
		out.println("A*B =\n" + A.mult(B));
		out.println("B*B = \n" + B.mult(B));

		in.close();
		out.close();
	}
}