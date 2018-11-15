// Alston Huang
// 1471706
// CMPS101 PA2
// Performs the lexicographically organization through insertionsort
// Lex.c

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"List.h"
#define MAX_LENGTH 250

int main(int argc, char* argv[]){

	int lineNumber = 0;
	List list = newList();
	char* stringStorage[MAX_LENGTH];
	char line[MAX_LENGTH]; 
	int count = 0;
	FILE *in, *out;

	//setup and command line checks
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

	//have to get line number first
	while( fgets(line, MAX_LENGTH, in)!=NULL ){
		//strcpy(stringStorage[lineNumber], line);
		lineNumber++;
	}

	FILE *in1;
	in1 = fopen(argv[1], "r");

	for(int i=0; i<lineNumber; i++){
		stringStorage[i] = malloc(sizeof(char* [MAX_LENGTH]));
	}

	while( fgets(line, MAX_LENGTH, in1)!=NULL ){
		strcpy(stringStorage[count], line);
		count++;
	}

	append(list, 0);
	for(int i=lineNumber-1; i>=1; i--){
		char* current = stringStorage[i];
		moveBack(list);
		//printList(out, list);
		//printf("Current = %s Current i-Value = %d INDEX = %d\n" , current, i, index(list));
		if(strcmp(current, stringStorage[front(list)]) < 0){
			prepend(list, i);
			//printList(out, list);
			//printf("Current = %s pCurrent i-Value = %d INDEX = %d\n", current, i, index(list));
		}
		else if(strcmp(current, stringStorage[back(list)]) > 0){
			append(list, i);
			//printList(out, list);
			//printf("Current = %s aCurrent i-Value = %d INDEX = %d\n", current, i, index(list));
		}
		else{
			//printf("Comparing %s to %s\n", current, stringStorage[get(list)]);
			while(index(list) != -1 && strcmp(current, stringStorage[get(list)]) < 0){
				//printList(out, list);
				//printf("Comparing %s to %s\n", current, stringStorage[get(list)]);
				movePrev(list);
				//printf("I moved to index %d\n", index(list));
			}
			insertAfter(list, i);
			//printList(out, list);
			//printf("Current = %s afterCurrent i-Value = %d INDEX = %d\n", current, i, index(list));
		}
	}

	moveFront(list);
	for(int j=0; j<lineNumber; j++){
		fprintf(out, "%s", stringStorage[get(list)]);
		printf("%s", stringStorage[get(list)]);
		moveNext(list);
	}

	for(int i=0; i<lineNumber; i++){
		free(stringStorage[i]);
	}
	freeList(&list);
	fclose(in1);
	fclose(in);
	fclose(out);
}
