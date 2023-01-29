#include <stdlib.h>
#include <stdio.h>
#ifndef SKIPLIST
#define SKIPLIST 
typedef struct _SkipList SkipList;
typedef struct _Node Node;

typedef struct _SkipList {
Node *head;
unsigned int max_level;
int (*compare)(void*, void*);
}SkipList;

typedef struct _Node{
Node **next;
unsigned int size;
void *item;
}Node;

/*
Randomizes the number of levels for each node.
The randomly generated number is then returned.
*/
int randomLevel();

/*
Creates and returns a new node for the skiplist.
*/
Node* createNode(void*, unsigned int );

/*
Creates and returns a new and empty skiplist.
*/
SkipList *skiplist_init(SkipList *, int (*comparator)(void*, void*));

/*
Inserts and sorts a generic item I in the skiplist.
*/
void insertSkipList (SkipList* , void* );

/*
Searches the generic item searchItem in the skiplist.
Returns 1 if found, -1 otherwise.
*/
int searchSkipList(SkipList*, void* );

/*
Deallocates the given skiplist.
*/
void deleteList(SkipList* );

/*
Returns the size of the given skiplist.
*/
int size(SkipList* );

/*
Returns 1 if the given skiplist list is empty, 0 otherwise.
*/
int isEmpty(SkipList* );
#endif




