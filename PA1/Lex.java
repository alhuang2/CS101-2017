// Alston Huang
// 1471706
// CMPS101 PA1
// Sorts words in lexographic order using List.java's ADT functinos
// Lex.java

import java.io.*;
import java.util.Scanner;

class Lex{
	public static void main(String[] args) throws IOException{
		int lineNumber = 0;
		String[] storage;
		List list = new List();
		int count = 0;

		if(args.length != 2){
			System.out.println("Usage: Lex -infile- -outfile-");
			System.exit(1);
		}

		//Setup
		Scanner in = new Scanner(new File(args[0]));
		PrintWriter out = new PrintWriter(new File(args[1]));

		//get linecount
		while(in.hasNextLine()){
			lineNumber++;
			in.nextLine();
		}
		in.close();

		//Scan in words into array
		Scanner sc = new Scanner(new File(args[0]));
		storage = new String[lineNumber];
		while(sc.hasNextLine()){
			storage[count] = sc.nextLine();
			count++;
		}
		sc.close();

		//sorting algorithm begins here
		//The system out prints is my debugging process
		list.append(0);
		for(int i=storage.length-1; i>=1; i--){ //starts from back
			String current = storage[i];
			//System.out.println(current);
			
			list.moveBack(); 
			//first 2 if statement checks for front and back.
			if(current.compareTo(storage[list.front()]) < 0){
				list.prepend(i);
				//System.out.println(list + " pCURRENT i = " + i + " INDEX = " + list.index());
			}
			else if(current.compareTo(storage[list.back()]) > 0){
				list.append(i);
				//System.out.println(list + " aCURRENT i = " + i +" INDEX = " + list.index());
			}
			//if not front or back, find where in the middle it belongs.
			else{
				while(list.index() != -1 && current.compareTo( storage[list.get()] ) < 0){
					//System.out.println("Comparing " + current + " to " + storage[list.get()]);
					list.movePrev();
					//System.out.println("I moved to index: " + list.index());
				}
				list.insertAfter(i);
				//System.out.println(list + " afterCURRENT i = " + i +" INDEX = " + list.index());
			}
		}

		//create new array and store the sorted list into array
		String[] sortedArray = new String[storage.length];
		list.moveFront(); 
		for(int i=0; i<storage.length; i++){
			sortedArray[i] = storage[list.get()];
			list.moveNext();
		}
		//System.out.println(list);

		//write to file out
		for(int j=0; j<storage.length; j++){
			out.println(sortedArray[j]);
		}
		out.close();
	}

		//PREVIOUS/BROKEN ALGORITHM -------------------------------------
		// list.moveFront();
		// list.insertBefore(1);

		// for(int i=1; i<storage.length; i++){
		// 	String current = storage[i];
		// 	list.moveBack();

		// 	if(current.compareTo(storage[list.front()]) < 0){
		// 		list.prepend(i);
		// 		System.out.println(list + " pCURRENT i = " + i);
		// 	}
		// 	else if(current.compareTo(storage[list.back()]) > 0){
		// 		list.append(i);
		// 		System.out.println(list + " aCURRENT i = " + i);
		// 	}
		// 	else{ 
		// 		while(list.index() != -1){
		// 			if(current.compareTo( storage[list.get()] ) > 0){
		// 				list.insertAfter(i);
		// 				System.out.println(list + " insertCURRENT i = " + i);
		// 				break;
		// 			}

		// 			else{
		// 				list.movePrev();
		// 				System.out.println("index= " + list.index() + " i= " + i);
		// 			}
		// 		}
		// 	}
		// }
}