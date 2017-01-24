#include<stdio.h>
#include<stdlib.h>

//structs


//private NodeObj type
typedef struct NodeObj{
	int data;
	struct NodeObj* next;
	struct NodeObj* prev;
} NodeObj;

//private Node type
typedef NodeObj* Node;

//private ListObj type
typedef struct ListObj{
	Node front;
	Node back;
	int length;
	int index;
	Node cursor;
} ListObj;

typedef ListObj* List;
//constructos - destructors

//newNode()
Node newNode(int data){
	Node N = malloc(sizeof(NodeObj));
	N->data = data;
	N->next = NULL;
	N->prev = NULL;
	return(N);
}

//freeNode()
void freeNode(Node* pN){
	if(pN!=NULL && *pN!=NULL){
		free(*pN);
		*pN=NULL;
	}
}

//newList()
List newList(){
	List L;
	L = malloc(sizeof(ListObj));
	L->front = L->back = NULL;
	L->cursor = NULL;
	L->length = 0;
	L->index = -1;
	return(L);
}

void freeList(List* pL){
	if(pL!=NULL && *pL!=NULL){
		while( !isEmpty(*pQ) ){
			delete(*pL);
		}
		free(*pL);
		*pL = NULL;
	}
}


//ACCESS FUNCTIONS 
int length(List L){
	return L->length;
}

int index(List L){
	if(L->index > L->length - 1)
		L->index = -1;
	return L->index;
}

int front(List L){
	if(L->length>0)
		return L->front->data;
	else{
		printf("Cannot front() on empty list.");
		exit(1);
	}
}

int back(List L){
	if(L->length>0)
		return L->back->data;
	else{
		printf("Cannot back() on empty list.");
		exit(1);
	}
}

int get(List L){
	if(L->index >= 0 && L->length > 0)
		return L->cursor->data;
	else{
		printf("Cannot get() on empty list or invalid index.");
	}
}

//equals returns 1 if true 0 if false 
int equals(List L1, List L2){
	int eq = 1;
	Node a = L1->front;
	Node b = L2->front;
	if( length(L1) == length(L2) ){
		while( eq == 1 && a != NULL){
			if( a->data != b->data )
				eq = 0;
			a->next;
			b->next;
		}
	}
	return eq;
}

//Manipulation procedures
void clear(List L){
	L->front = L->back = NULL;
	L->index = -1;
	L->length = 0;
}

void moveFront(List L){
	if(L->length > 0){
		L->cursor = L->front;
		L->index = 0;
	}
}

void moveBack(List L){
	if(L->length > 0){
		L->cursor = L->back;
		L->index = L->length-1;
	}
}

void movePrev(List L){
	if( L->index <= L->length-1 && L->index > 0){
		L->cursor = L->cursor->prev;
		L->index--;
	}
	else{
		L->index = -1;
		L->cursor = NULL;
	}
}

void moveNext(List L){
	if(L->cursor != NULL && L->cursor != L->back){
		L->cursor = L->cursor->next;
		L->index++;
	}
	else if( L->cursor == L->back ){
		L->cursor = NULL;
		L->index = -1;
	}
}

void prepend(List L, int data){
	Node temp = newNode(data);
	if( L->length == 0 ){
		L->front = L->back = temp;
	}
	else{
		temp->next = L->front;
		L->front->prev = temp;
		L->front = temp;
		L->index++;
	}

	L->length++;
}

void append(List L, int data){
	Node temp = newNode(data);
	if()
}

