// Alston Huang
// 1471706
// CMPS101 PA3
// test file to test each function as it is created
// MatrixTest.java

class MatrixTest{
	public static void main(String[] args){
		Matrix A = new Matrix(1000);
		Matrix B = new Matrix(1000);
		Matrix C = new Matrix(1000);
      A.changeEntry(1,1,1.0); B.changeEntry(1,1,1.0); //System.out.println(A); System.out.println(B);
      A.changeEntry(1,2,2.0); //B.changeEntry(1,2,0); //System.out.println(A); System.out.println(B);
      A.changeEntry(1,3,3.0); B.changeEntry(1,3,1.0); //System.out.println(A); System.out.println(B);
      A.changeEntry(2,1,4.0); B.changeEntry(2,1,0); //System.out.println(A); System.out.println(B);
      A.changeEntry(2,2,5.0); //B.changeEntry(2,2,1); //System.out.println(A); System.out.println(B);
      A.changeEntry(2,3,6.0); B.changeEntry(2,3,0); //System.out.println(A); System.out.println(B);
      A.changeEntry(3,1,7.0); //B.changeEntry(3,1,1.0); //System.out.println(A); System.out.println(B);
      A.changeEntry(3,2,8.0); B.changeEntry(3,2,1.0); //System.out.println(A); System.out.println(B);
      A.changeEntry(3,3,9.0); B.changeEntry(3,3,1.0); //System.out.println(A); System.out.println(B);
      //A.changeEntry(1,1,0);

      C.changeEntry(1,1,2);
      C.changeEntry(1,2,3);
      C.changeEntry(1,3,4);
      C.changeEntry(2,1,5);
      //C.changeEntry(2,2,6);
      C.changeEntry(2,3,7);
      C.changeEntry(3,1,8);
      C.changeEntry(3,2,9);
      C.changeEntry(3,3,10);

	   C.changeEntry(1,1,0);
      C.changeEntry(1,2,0);
      C.changeEntry(2,1,0);
      C.changeEntry(2,2,0);
      //C.changeEntry(2,3,0);
      C.changeEntry(3,1,0);
      C.changeEntry(3,2,0);
      C.changeEntry(3,3,0);

      A.changeEntry(3,2,0);

      Matrix H = A.copy();

      System.out.println(A.getNNZ());
      System.out.println(A);

      System.out.println(B.getNNZ());
      System.out.println(B);

      System.out.println(H);

      System.out.println(A.scalarMult(5));

      A.changeEntry(3,3,0);

      Matrix sum = A.add(C);
      Matrix sum1 = A.add(B);
      System.out.println("A+C: \n" + sum);
      System.out.println("A+B: \n" + sum1);
      System.out.println(sum.getNNZ());
      System.out.println(sum1.getNNZ());

      Matrix subSum = A.sub(B);
      Matrix subSum1 = A.sub(C);
      Matrix subSum2 = B.sub(A);
      Matrix subSum3 = C.sub(A);

      System.out.println(subSum.getNNZ());
      System.out.println("A-B: \n" + subSum);
      System.out.println(subSum1.getNNZ());
      System.out.println("A-C: \n" + subSum1);
      System.out.println(subSum2.getNNZ());
      System.out.println("B-A: \n" + subSum2);
      System.out.println(subSum3.getNNZ());
      System.out.println("C-A: \n" + subSum3);

      // double d = A.dot(A.matrix[1], C.matrix[1]);
      // System.out.println("A dot C = " + d);

      Matrix transposed = A.transpose();
      System.out.println("Transpose of A: \n" + transposed);

      Matrix multiplied = A.mult(B);
      System.out.println("A*B = \n" + multiplied);

      Matrix multiplied1 = A.mult(C);
      System.out.println("A*C = \n" + multiplied1); 

      Matrix multiplied2 = B.mult(B);
      System.out.println("A*A = \n" + multiplied2);

      System.out.println("A has " + A.getNNZ() + " non-zero entries: \n" + A);
		System.out.println("B has " + B.getNNZ() + " non-zero entries: \n" + B);
		System.out.println("(1.5)*A = \n" +A.scalarMult(1.5));
		System.out.println("A+B = \n" + A.add(B));
		System.out.println("A+A =\n" + A.add(A));
		System.out.println("B-A =\n" + B.sub(A));
		System.out.println("A-A = \n" + A.sub(A));
		System.out.println("Transpose(A) = \n" + A.transpose());
		System.out.println("A*B =\n" + A.mult(B));
		System.out.println("B*B = \n" + B.mult(B));

	}
}