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

// void freeList(List* pL){
// 	if(pL!=NULL && *pL!=NULL){
// 		while( !isEmpty(*pL) ){
// 			delete(*pL);
// 		}
// 		free(*pL);
// 		*pL = NULL;
// 	}
// }


//ACCESS FUNCTIONS 
int length(List L){
	return L->length;
}

//changed function name from index -> getIndex to avoid warning error
int getIndex(List L){
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
	//must use freeNode and freeList
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
	if(L->length == 0){
		L->front = L->back = temp;
	}
	else{
		L->back->next = temp;
		temp->prev = L->back;
		L->back = temp;
	}
	L->length++;
}

void insertBefore(List L, int data){
	Node temp = newNode(data);
	if(L->length<=0 || L->index<0){
		printf("Cannot insertBefore() on empty list or off index.");
		exit(1);
	}
	if(L->index == 0)
		prepend(L, data);
	else{
		temp->next = L->cursor;
		temp->prev = L->cursor->prev;
		L->cursor->prev->next = temp;
		L->cursor->prev = temp;
		L->index++;
		L->length++;
	}
}

void insertAfter(List L, int data){
	Node temp = newNode(data);
	if(L->length<=0 || L->index<0){
		printf("Cannot insertAfter() on empty or null list.");
		exit(1);
	}
	if(L->index == L->length-1)
		append(L, data);
	else if(L->index >= L->length){
		printf("Cannot insertAfter() on index greater than length.");
		exit(1);
	}
	else{
		temp->prev = L->cursor;
		temp->next = L->cursor->next;
		L->cursor->next = temp;
		L->cursor->prev = temp;
		L->length++;
	}
}

void deleteFront(List L){
	if(L->length<=0){
		printf("Cannot deleteFront() on empty list.\n");
		exit(1);
	}
	Node temp;
	temp = L->front->next;
	temp->prev = NULL;
	L->front = temp;
	L->length--;
}

void deleteBack(List L){
	if(L->length <= 0){
		printf("Cannot deleteBack() on empty list.\n");
		exit(1);
	}
	Node temp;
	temp = L->back->prev;
	temp->next = NULL;
	L->back = temp;
	L->length--;
}

void delete(List L){
	if(L->length <= 0 || L->index < 0){
		printf("Cannot delete() on empty or NULL index list.");
		exit(1);
	}
	// if(L->index == 0)
	// 	// need to use clear or freeNode or freeList
	// else{

	// }
}

//must use freeNode to free nodeTemp
List copy(List L){
	List listTemp = newList();
	Node nodeTemp = L->front;
	while(nodeTemp != NULL){
		append(listTemp, nodeTemp->data);
		nodeTemp = nodeTemp->next;
	}
	freeNode(nodeTemp);
	return listTemp;
}

void printList(FILE* out, List L){
	Node N = NULL;

	if(L == NULL){
		printf("Cannot printList() on empty list.\n");
		exit(1);
	}

	for(N = L->front; N!=NULL; N = N->next){
		fprintf(out, "%d ", N->data);
		if(N->next == NULL)
			fprintf(out, "\n");
	}
}




