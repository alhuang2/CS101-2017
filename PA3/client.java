class client{
	public static void main(String[] args){
		Matrix A = new Matrix(1000);
		Matrix B = new Matrix(1000);
		Matrix C = new Matrix(1000);
      A.changeEntry(1,1,1); B.changeEntry(1,1,1); //System.out.println(A); System.out.println(B);
      A.changeEntry(1,2,2); B.changeEntry(1,2,0); //System.out.println(A); System.out.println(B);
      A.changeEntry(1,3,3); B.changeEntry(1,3,1); //System.out.println(A); System.out.println(B);
      A.changeEntry(2,1,4); B.changeEntry(2,1,0); //System.out.println(A); System.out.println(B);
      A.changeEntry(2,2,5); B.changeEntry(2,2,1); //System.out.println(A); System.out.println(B);
      A.changeEntry(2,3,6); B.changeEntry(2,3,0); //System.out.println(A); System.out.println(B);
      A.changeEntry(3,1,7); B.changeEntry(3,1,1); //System.out.println(A); System.out.println(B);
      A.changeEntry(3,2,8); B.changeEntry(3,2,1); //System.out.println(A); System.out.println(B);
      A.changeEntry(3,3,9); B.changeEntry(3,3,1); //System.out.println(A); System.out.println(B);
     // A.changeEntry(1,1,0);

      C.changeEntry(1,1,2);
      C.changeEntry(1,2,3);
      C.changeEntry(1,3,4);
      C.changeEntry(2,1,5);
      C.changeEntry(2,2,6);
      C.changeEntry(2,3,7);
      C.changeEntry(3,1,8);
      C.changeEntry(3,2,9);
      C.changeEntry(3,3,10);

	  // // C.changeEntry(1,1,0);
   // //    C.changeEntry(1,2,0);
   // //    C.changeEntry(1,3,0);
   // //    C.changeEntry(2,1,0);
   //    C.changeEntry(2,2,0);
   //    C.changeEntry(2,3,0);
   // //    C.changeEntry(3,1,0);
   // //    C.changeEntry(3,2,0);
   //    C.changeEntry(3,3,0);
   //    //A.changeEntry(3,2,0);
      //Matrix H = A.copy();
      // System.out.println(A.getNNZ());
      // System.out.println(A);
      // System.out.println(B.getNNZ());
      // System.out.println(B);
      // System.out.println(H);
     // System.out.println(A.scalarMult(5));
      //A.changeEntry(3,3,0);
      //Matrix sum = A.add(C);
      //Matrix sum1 = A.add(B);
      //System.out.println("A+C: \n" + sum);
      //System.out.println("A+B: \n" + sum1);
      //System.out.println(sum.getNNZ());
      //System.out.println(sum1.getNNZ());

      Matrix subSum = A.sub(B);
      Matrix subSum1 = A.sub(C);
      Matrix subSum2 = B.sub(A);
      Matrix subSum3 = C.sub(A);

      System.out.println("A-B: \n" + subSum);
      System.out.println("A-C: \n" + subSum1);
      System.out.println("B-A: \n" + subSum2);
      System.out.println("C-A: \n" + subSum3);

      double d = A.dot(A.matrix[1], C.matrix[1]);
      System.out.println("A dot C = " + d);

      Matrix transposed = A.transpose();
      System.out.println("Transpose of A: \n" + transposed);

	}
}