public class Matrix{
	private class Entry{
		int column;
		double value;

		Entry(int col, double val){
			column = col;
			value = val;
		}

		// public boolean equals(Object x){

		// }
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
		boolean eq = true;
		int count = 0;
		Matrix m = (Matrix) x;
		if( nnz != m.nnz && size != m.size )
			eq = false;
		else{
			while(eq && count <= size){
				if( matrix[count].length() != m.matrix[count].length() )
					eq = false;
				if( !matrix[count].equals(m.matrix[count]) )
					eq = false;
				count++;
			}
		}
		return eq; 
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
		Matrix newMatrix = new Matrix(size);
		for(int i=1; i<=size; i++){
			matrix[i].moveFront();
			while( matrix[i].index() != -1 ){
				Entry temp = (Entry) matrix[i].get();
				newMatrix.changeEntry(i, temp.column, temp.value);
				matrix[i].moveNext();
			}
		}
		newMatrix.nnz = nnz;
		return newMatrix;
	}

	void changeEntry(int i, int j, double x){
		Entry holder = new Entry(j, x);
		if(i>size || i<1)
			throw new RuntimeException("Invalid i'th row");
		if(j>size || j<1)
			throw new RuntimeException("Invalid j'th column");
		if(x==0 && matrix[i].length() == 0)
			return; //do fnothing
		else if(x==0 && matrix[i].length() != 0){
			matrix[i].moveFront();
			while( matrix[i].index() >= 0 ){
				if( ((Entry) matrix[i].get()).column == j ){
					// System.out.println("row = " + i + "index = " + matrix[i].index() 
					// 	+ "value = " + ((Entry) matrix[i].get()).value);
					// System.out.println("List Length = " + matrix[i].length());
					matrix[i].delete();
					nnz--; return;
				}
				matrix[i].moveNext();
			}
			return;
		}
		else if(x!=0 && matrix[i].length() == 0){
			matrix[i].append(holder);
			nnz++;
			return;
		}
		else{ // x!=0 && matrix[i].length != 0
			matrix[i].moveFront();
			if( ((Entry) matrix[i].back()).column < j ){
				matrix[i].append(holder);
				nnz++; return;
			}
			else if( ((Entry) matrix[i].front()).column > j ){
				matrix[i].prepend(holder);
				nnz++; return;
			}

			while(matrix[i].index() != -1){
				Entry temp = (Entry) matrix[i].get();
				if(temp.column == j){
					if(temp.value > 0)
						temp.value = x;
					else{
						temp.value = x;
						nnz++;
					}
					return;
				}
				else
					matrix[i].moveNext();
			}
		}
	}

	Matrix scalarMult(double x){
		Matrix newMatrix = new Matrix(size);
		int columnPlace;
		for(int i=1; i<=size; i++){
			matrix[i].moveFront();
			columnPlace = 1;
			while(matrix[i].index() != -1){
				newMatrix.changeEntry(i, columnPlace, x * ((Entry)matrix[i].get()).value);
				columnPlace++;
				matrix[i].moveNext();
			}
		}
		return newMatrix;
	}

	Matrix add(Matrix M){
		if( size != M.getSize() )
			throw new RuntimeException("Cannot add matrices of different size.");
		Matrix newMatrix = new Matrix(size);
		if( matrix.equals(M) )
			newMatrix = M.scalarMult(2);
		else{
			for(int i=1; i<=size; i++){
				matrix[i].moveFront(); M.matrix[i].moveFront();

				while( matrix[i].index() != -1 || M.matrix[i].index() != -1 ){
					if( matrix[i].index() != -1 && M.matrix[i].index() != -1 ){
						Entry left = (Entry)matrix[i].get();
						Entry right = (Entry)M.matrix[i].get();
						if(left.column > right.column){
							newMatrix.changeEntry(i, right.column, right.value);
							M.matrix[i].moveNext();
						}
						else if(left.column < right.column){
							newMatrix.changeEntry(i, left.column, left.value);
							matrix[i].moveNext();
						}
						else /*if(left.column == right.column)*/{
							newMatrix.changeEntry(i, left.column, left.value + right.value);
							matrix[i].moveNext();
							M.matrix[i].moveNext();
						}
					}
					else if( matrix[i].index() != -1){
						Entry left = (Entry)matrix[i].get();
						newMatrix.changeEntry(i, left.column, left.value);
						matrix[i].moveNext();
					}
					else /*if( M.matrix[i].index() != -1)*/{
						Entry right = (Entry)M.matrix[i].get();
						newMatrix.changeEntry(i, right.column, right.value);
						M.matrix[i].moveNext();
					}
				}
			}

			if( nnz >= M.nnz )
				newMatrix.nnz = nnz;
			else
				newMatrix.nnz = M.nnz;
		}
		return newMatrix;
	}

	Matrix sub(Matrix M){
		if(size != M.getSize())
			throw new RuntimeException("Cannot sub matrices of different sizes.");
		Matrix newMatrix = new Matrix(size);

		if( nnz >= M.nnz )
				newMatrix.nnz = nnz;
			else
				newMatrix.nnz = M.nnz;

		if( matrix.equals(M) )
			return newMatrix;
		else{
			for(int i=1; i<=size; i++){
				matrix[i].moveFront(); M.matrix[i].moveFront();
				while( matrix[i].index() != -1 || M.matrix[i].index() != -1 ){
					if( matrix[i].index() != -1 && M.matrix[i].index() != -1 ){
						Entry left = (Entry)matrix[i].get();
						Entry right = (Entry)M.matrix[i].get();
						if(left.column > right.column){
							newMatrix.changeEntry(i, right.column, -1 * right.value);
							M.matrix[i].moveNext();
						}
						else if(left.column < right.column){
							newMatrix.changeEntry(i, left.column, left.value);
							matrix[i].moveNext();
						}
						else{
							if(left.value == right.value){
								matrix[i].moveNext();
								M.matrix[i].moveNext();
								newMatrix.nnz--;
							}
							else{
								newMatrix.changeEntry(i, left.column, left.value - right.value);
								matrix[i].moveNext();
								M.matrix[i].moveNext();
							}
						}
					}
					else if( matrix[i].index() != -1 ){
						Entry left = (Entry)matrix[i].get();
						newMatrix.changeEntry(i, left.column, left.value);
						matrix[i].moveNext();
					}
					else{
						Entry right = (Entry)M.matrix[i].get();
						newMatrix.changeEntry(i, right.column, -1 * right.value);
						M.matrix[i].moveNext();
					}
				}
			}
		}
		return newMatrix;
	}
	Matrix transpose(){
		Matrix newMatrix = new Matrix(size);
		for(int i=1; i<=size; i++){
			matrix[i].moveFront();
			while(matrix[i].index() != -1){
				Entry temp = (Entry)matrix[i].get();
				newMatrix.changeEntry(temp.column, i, temp.value);
				matrix[i].moveNext();
			}
		}
		newMatrix.nnz = nnz;
		return newMatrix;
	}

	// Matrix mult(Matrix B){
	// 	Matrix newMatrix = new Matrix(size);
	// 	return newMatrix;
	// }

	private static double dot(List P, List Q){
		double answer = 0; 
		P.moveFront(); Q.moveFront();
		while( P.index() != -1 && Q.index() != -1){
			Entry left = (Entry)P.get();
			Entry right = (Entry)Q.get();

			if( left.column == right.column ){
				answer = answer + (left.value*right.value);
				P.moveNext();
				Q.moveNext();
			}
			else if(left.column > right.column){
				while(left.column != right.column){
					Q.moveNext();
					right = (Entry)Q.get();
				}
			}
			else /*if(left.column < right.column)*/{
				while(left.column != right.column){
					P.moveNext();
					left = (Entry)P.get();
				}
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