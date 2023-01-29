#include <stdio.h>
#include <stdlib.h>
#include "binary_insertion_sort.h"
/* Finds the index where data needs to be inserted in the given sorted array due to compare_to function
*/
int binary_search (void ** array, int min, int max, void * data, int(*compare_to)(void*, void*)) {

    if (max<=min) {
        if (compare_to(data, array[min])>0) {
            return min+1;
        } else {
            return min;
        }
    }
    int mid = (min+max)/2;
    if (compare_to(data, array[mid]) == 0) {
        return mid+1;
    }
    if (compare_to(data, array[mid])>0) {
        return binary_search(array, mid+1, max, data, compare_to);
    }
    return binary_search(array, min, mid-1, data, compare_to);
}
/* Sorts the array of generic pointers looping on the array. For each record finds the correct spot to 
insert in the already sorted part of the array, slides one place right the records between the index to insert
and the location where it needs to be inserted and then the current record is located right there.

*/
void binary_insertion_sort(void ** array, int size, int (*comparatore)(void*,void*)) {
  if (array != NULL && size >= 0) {
  	    int toInsert, loc, sorted;
    void *selected;
    for (toInsert = 1; toInsert <= size; ++toInsert) //<size
    {
        sorted = toInsert - 1;
        selected = array[toInsert];
        loc = binary_search(array, 0, sorted, selected, comparatore);
        while (sorted >= loc) {
           array[sorted+1] = array[sorted];
            sorted--;
        }
        array[sorted+1] = selected;
    }
  } else {
  exit(EXIT_FAILURE);
}
}
