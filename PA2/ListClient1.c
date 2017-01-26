#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"List.h"

int main(int argc, char* argv[]){

	List A = newList();
	List B = newList();
	int n=0;
	int count=0;
	FILE *in, *out;

	if(argc != 3){
		printf("Usage: Lex <input> <output>\n");
		exit(1);
	}

	in = fopen(argv[1], "r");
	out = fopen(argv[2], "w");

	if( in == NULL ){
		printf("Unable to open the infile.");
		exit(1);
	}
	if( out == NULL ){
		printf("Unable to open the outfile.");
		exit(1);
	}

	for(int i=0; i<20; i++){
		append(A, i);
		prepend(B, i);
	}

	printList(out, A);

	fclose(in);
	fclose(out);
}