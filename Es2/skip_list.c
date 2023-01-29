#include <stdlib.h>
#include <stdio.h>
#include "skip_list.h"

#define MAX_HEIGHT 15

/*
Randomizes the number of levels for each node.
The randomly generated number is then returned.
*/
int randomLevel() {
    int lvl = 1;
    float k;
    while (( k=((float)random())/RAND_MAX)< 0.5 && (lvl < MAX_HEIGHT)) {
        lvl++;
    }
    return lvl;
}

/*
Creates and returns a new node for the skiplist.
*/
Node* createNode(void* item, unsigned int size) {
    Node* nptr = (Node*)malloc(sizeof(Node));
    nptr->item = item;
    nptr->size = size;
    nptr->next = (Node **) malloc(sizeof(Node*) * (size +1 ));
    for(int i=0;i<size;i++){
        nptr->next[i] = NULL;
    }
    return nptr;
}

/*
Creates and returns a new and empty skiplist.
*/
SkipList *skiplist_init(SkipList *list, int (*comparator)(void*, void*)) {
    int i;
    Node *header = (Node *) malloc(sizeof(Node));
    list->head = header;
    list->compare = comparator;
    header->size = MAX_HEIGHT;
    header->next = (Node **) malloc(sizeof(Node*) * (MAX_HEIGHT +1));
    for (i = 0; i <= MAX_HEIGHT; i++) {
        header->next[i] = NULL;
    }
    list->max_level = 0;
    
    return list;
}

/*
Inserts and sorts a generic item I in the skiplist.
*/
void insertSkipList (SkipList* list, void* I) {
    int k;
    Node* new = createNode(I, randomLevel());
    if(new->size > list->max_level){
        list->max_level = new->size;
    }
    
    Node* x = list->head;
    for( k = list->max_level; k >= 0; k--){
       
        if (x->next[k] == NULL ||  list->compare(I, x->next[k]->item) < 0) {
           
            if (k < new->size) {
                
                new->next[k] = x->next[k];
                x->next[k] = new;
                
            }
            
        } else{
            
            x = x->next[k];
            k++;

        }
        
            
    }
}

/*
Searches the generic item searchItem in the skiplist.
Returns 1 if found, -1 otherwise.
*/
int searchSkipList(SkipList* list, void* searchItem) {
    
        Node* x = list->head;
        int i;
        for (i = list->max_level-1; i>=0; i--) {
            while (x->next[i]!=NULL && list->compare(x->next[i]->item, searchItem)<0) {
                x = x->next[i];
            }
        }
        x = x->next[0];
        if(list->compare(x->item,searchItem)==0){
            return 1 ;
        }else {
            return -1;
        }
    
}

/*
Returns the size of the given skiplist.
*/
int size(SkipList* list){
    if (list->head==NULL)
        return 0;
    Node* x=list->head;
    int count=0;
    while(x->next[0]!=NULL){
        x=x->next[0];
        count++;
    }
    return count;
}

/*
Returns 1 if the given skiplist list is empty, 0 otherwise.
*/
int isEmpty(SkipList* list){
    if(size(list)==0)
        return 1;
    else 
        return 0;
}

/*
Deallocates the given skiplist.
*/
void deleteList(SkipList* list){
    Node* prev=NULL;
    Node* x=list->head;
    while(x->next[0]!=NULL){
        prev=x;
        x=x->next[0];
        free(prev);
    }
    
    
}