class ListClient1{
	public static void main(String[] args){
		List A = new List();
		List B = new List();

		for(int i=1; i<=20; i++){
			A.prepend(i);
			B.append(i);
		}

		System.out.println("A == B? : " + A.equals(B));

		System.out.println(A);
		System.out.println(B+"\n");

		System.out.println(A.index());
		A.moveFront();
		System.out.println(A.index());
		A.movePrev();
		System.out.println(A.index());
		A.moveBack();
		System.out.println(A.index());
		System.out.println("A.get(): " + A.get());
		A.movePrev();
		System.out.println("A.get(): " + A.get());
		A.moveNext();
		System.out.println("A.get(): " + A.get()+"\n");

		A.movePrev();

		A.insertBefore(6);
		System.out.println(A);

		System.out.println(A.index());
		System.out.println("A.get(): " + A.get());

		A.insertAfter(100);
		System.out.println(A+"\n");

		A.deleteFront();
		System.out.println(A+"\n");	

		A.deleteBack();
		System.out.println(A+"\n");	

		A.movePrev();
		System.out.println("A.get(): " + A.get());
		A.delete();
		System.out.println(A+"\n");	

		System.out.println("A == B? : " + A.equals(B));


	}
}