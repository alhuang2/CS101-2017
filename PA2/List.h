#ifndef _LIST_H_INCLUDE_
#define _LIST_H_INCLUDE_

typedef struct ListObj* List;

List newList(void);

void freeList(List* pL);

//access functions
int length(List L);

//changed function name from index -> getIndex to avoid warning error
int getIndex(List L);

int front(List L);

int back(List L);

int get(List L);

int equals(List L1, List L2);

int isEmpty(List L);

//manipulation procedures

void clear(List L);

void moveFront(List L);

void moveBack(List L);

void movePrev(List L);

void moveNext(List L);

void prepend(List L, int data);

void append(List L, int data);

void insertBefore(List L, int data);

void insertAfter(List L, int data);

void deleteFront(List L);

void deleteBack(List L);

void delete(List L);

List copy(List  L);

void printList(FILE* out, List L);

#endif

