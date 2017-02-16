// Alston Huang
// 1471706
// CMPS101 PA3
// Matrix ADT using List ADT's
// Matrix.java

public class Matrix{
	private class Entry{
		int column;
		double value;

		Entry(int col, double val){
			column = col;
			value = val;
		}

		public boolean equals(Object x){
			Entry temp = (Entry) x;
			if(column == temp.column && value == temp.value)
				return true;
			else
				return false;
		}
		public String toString(){
			return ( "(" + column + ", " + value + ") ");
		}
	}

	List[] matrix;
	int nnz;
	int size;

	Matrix(int n){
		if(n < 1){
			System.out.println("Matrix requires n >= 1");
			System.exit(1);
		}
		size = n;
		nnz = 0;
		matrix = new List[n+1]; //n+1 because not using column 0
		for(int i=1; i<n+1; i++)
			matrix[i] = new List();
	}
	//Acces Functions---------------------------------------------------------------

	int getSize(){
		return size;
	}

	int getNNZ(){
		return nnz;
	}

	public boolean equals(Object x){
		Matrix m = (Matrix) x;
		if(m.nnz != nnz || size != m.getSize())
			return false;
		for(int i=1; i<=size; i++){
			if(m.matrix[i].length()!=matrix[i].length()) 
				return false;
			if( !(m.matrix[i].equals(matrix[i])) ) //Calls List's equals then calls Entry's equals
				return false;
		}
		return true; 
	}
	//Manipulation Procedures ------------------------------------------------------

	void makeZero(){
		for(int i=1; i<size+1; i++){
			matrix[i].clear();
		}
		nnz = 0;
		size = 0;
	}
	Matrix copy(){
		Matrix returnMatrix = new Matrix(size);
		for(int i=1; i<=size; i++){
			matrix[i].moveFront();
			while( matrix[i].index() != -1 ){
				Entry temp = (Entry) matrix[i].get();
				returnMatrix.changeEntry(i, temp.column, temp.value);
				matrix[i].moveNext();
			}
		}
		returnMatrix.nnz = nnz;
		return returnMatrix;
	}

	void changeEntry(int i, int j, double x){
		if(i > size || i < 1)
			throw new RuntimeException("Invalid i'th row");
		if(j > size || j < 1)
			throw new RuntimeException("Invalid j'th row");
		Entry iEntry = new Entry(j, x);
		//4 possible conditions
		if( x!=0 && matrix[i].length() == 0){ //new item, empty list. just  append 
			matrix[i].append(iEntry);
			nnz++; return;
		}
		else if( x==0 && matrix[i].length() != 0 ){ //delete existing value or do nothing 
			matrix[i].moveFront();
			while( matrix[i].index() != -1 ){
				Entry temp = (Entry)matrix[i].get();
				if(temp.column == j){
					// System.out.println("row = " + i + "index = " + matrix[i].index() + "value = " + ((Entry) matrix[i].get()).value);
					// System.out.println("List Length = " + matrix[i].length());
					matrix[i].delete();
					nnz--; return;
				}
				matrix[i].moveNext();
			}
			return; //if temp.column never == to j
		}
		else if( x==0 && matrix[i].length() == 0 ) //do nothing but still need the condition
			return; 
		else/* if( x!=0 && matrix[i].length() != 0 )*/{

			//conditions: j < lowest column. j > greatest column. 
			//if j is a new column. if j already exists

			matrix[i].moveFront();
			if( ((Entry)matrix[i].front()).column > j ){
				matrix[i].prepend(iEntry);
				nnz++; return;
			}
			else if( ((Entry)matrix[i].back()).column < j ){
				matrix[i].append(iEntry);
				nnz++; return;
			}
			else{ //j is a new column or j already exists.
				while( matrix[i].index() != -1 ){
					if( ((Entry)matrix[i].get()).column == j ){
						// System.out.println("row = " + i + "index = " + matrix[i].index() + "value = " + ((Entry) matrix[i].get()).value);
						// System.out.println("List Length = " + matrix[i].length());
						((Entry)matrix[i].get()).value = x;
						//nnz++;  
						return; 
					}
					else /*j is a new column, find correct place to insert*/{
						if( ((Entry)matrix[i].get()).column > j ){
							// System.out.println("row = " + i + "index = " + matrix[i].index() + "value = " + ((Entry) matrix[i].get()).value);
							// System.out.println("List Length = " + matrix[i].length());
							matrix[i].insertBefore(iEntry);
							nnz++; return;
						}
					}
					matrix[i].moveNext();
				}
			}
		}
	}

	Matrix scalarMult(double x){
		Matrix returnMatrix = new Matrix(size);
		int columnPlace;
		for(int i=1; i<=size; i++){
			matrix[i].moveFront();
			columnPlace = 1;
			while(matrix[i].index() != -1){
				returnMatrix.changeEntry(i, columnPlace, x * ((Entry)matrix[i].get()).value);
				columnPlace++;
				matrix[i].moveNext();
			}
		}
		return returnMatrix;
	}

	Matrix add(Matrix M){
		if( size != M.getSize() )
			throw new RuntimeException("Cannot add matrices of different size.");
		Matrix returnMatrix = new Matrix(size);
		if( matrix.equals(M) )
			returnMatrix = M.scalarMult(2);
		else{
			for(int i=1; i<=size; i++){
				matrix[i].moveFront(); M.matrix[i].moveFront();

				while( matrix[i].index() != -1 || M.matrix[i].index() != -1 ){
					if( matrix[i].index() != -1 && M.matrix[i].index() != -1 ){

						Entry left = (Entry)matrix[i].get();
						Entry right = (Entry)M.matrix[i].get();

						if(left.column > right.column){
							returnMatrix.changeEntry(i, right.column, right.value);
							M.matrix[i].moveNext();
						}
						else if(left.column < right.column){
							returnMatrix.changeEntry(i, left.column, left.value);
							matrix[i].moveNext();
						}
						else /*if(left.column == right.column)*/{
							returnMatrix.changeEntry(i, left.column, left.value + right.value);
							matrix[i].moveNext();
							M.matrix[i].moveNext();
						}
					}
					else if( matrix[i].index() != -1){
						Entry left = (Entry)matrix[i].get();
						returnMatrix.changeEntry(i, left.column, left.value);
						matrix[i].moveNext();
					}
					else /*if( M.matrix[i].index() != -1)*/{
						Entry right = (Entry)M.matrix[i].get();
						returnMatrix.changeEntry(i, right.column, right.value);
						M.matrix[i].moveNext();
					}
				}
			}
			if( nnz >= M.nnz )
				returnMatrix.nnz = nnz;
			else
				returnMatrix.nnz = M.nnz;
		}
		return returnMatrix;
	}

	Matrix sub(Matrix M){
		if(size != M.getSize())
			throw new RuntimeException("Cannot sub matrices of different sizes.");
		Matrix returnMatrix = new Matrix(size);

		if( this.equals(M) )
			return returnMatrix;
		// if( nnz >= M.nnz )
		// 	returnMatrix.nnz = nnz;
		// else
		// 	returnMatrix.nnz = M.nnz;
		for(int i=1; i<=size; i++){
			matrix[i].moveFront();	M.matrix[i].moveFront();

			while( matrix[i].index() != -1 || M.matrix[i].index() != -1 ){
				if( matrix[i].index() != -1 && M.matrix[i].index() != -1 ){ 

					Entry left = (Entry)matrix[i].get();
					Entry right = (Entry)M.matrix[i].get();

					if(left.column > right.column){	
						returnMatrix.changeEntry(i, right.column, -1 * right.value);
						M.matrix[i].moveNext();
					}
					else if(left.column < right.column){
						returnMatrix.changeEntry(i, left.column, left.value);
						matrix[i].moveNext();
					}
					else{
						if(left.value == right.value){
							matrix[i].moveNext();
							M.matrix[i].moveNext();
							//returnMatrix.nnz--;
						}
						else{
							returnMatrix.changeEntry(i, left.column, left.value - right.value);
							matrix[i].moveNext();
							M.matrix[i].moveNext();
						}
					}
				}
				else if( matrix[i].index() != -1 ){
					Entry left = (Entry)matrix[i].get();
					returnMatrix.changeEntry(i, left.column, left.value);
					matrix[i].moveNext();
				}
				else{
					Entry right = (Entry)M.matrix[i].get();
					returnMatrix.changeEntry(i, right.column, -1 * right.value);
					M.matrix[i].moveNext();
				}
			}
		}

		return returnMatrix;
	}
	Matrix transpose(){
		Matrix returnMatrix = new Matrix(size);
		for(int i=1; i<=size; i++){
			matrix[i].moveFront();
			while(matrix[i].index() != -1){
				Entry temp = (Entry)matrix[i].get();
				returnMatrix.changeEntry(temp.column, i, temp.value);
				matrix[i].moveNext();
			}
		}
		returnMatrix.nnz = nnz;
		return returnMatrix;
	}

	Matrix mult(Matrix B){
		if(size != B.getSize())
			throw new RuntimeException("Cannot multiply matrices of different size.");
		Matrix returnMatrix = new Matrix(size);
		Matrix tposed = B.transpose();
		for(int i=1; i<=size; i++){
			if(matrix[i].length() != 0){
				for(int j=1; j<=size; j++){
				if(tposed.matrix[j].length() != 0)
					returnMatrix.changeEntry(i, j, dot(matrix[i], tposed.matrix[j]));
				}
			}
		}
		return returnMatrix;
	}

	private static double dot(List P, List Q){
		double answer = 0; 
		P.moveFront(); Q.moveFront();
		if(P.length() == 0 || Q.length() == 0)
			return answer;
		while( P.index() != -1 && Q.index() != -1){
			Entry left = (Entry)P.get();
			Entry right = (Entry)Q.get();

			if( left.column == right.column ){
				answer = answer + (left.value*right.value);
				P.moveNext();
				Q.moveNext();
			}
			else if(left.column > right.column){
				//while(left.column != right.column){
					Q.moveNext();
					//right = (Entry)Q.get();
				//}
			}
			else /*if(left.column < right.column)*/{
				//while(left.column != right.column){
					P.moveNext();
					//left = (Entry)P.get();
				//}
			}
		}
		return answer;
	}

	public String toString(){
		Entry temp;
		String str = "";
		for(int i=1; i<=size; i++){
			matrix[i].moveFront();
			if(matrix[i].length() > 0){
				str = str + i + ": ";
			}
			while(matrix[i].length() > 0 && matrix[i].index() >= 0){
				temp = (Entry) matrix[i].get();
				str = str + temp.toString();
				matrix[i].moveNext();
			}
			if(matrix[i].length() > 0)
				str = str + "\n";
		}
		return str;
	}
}