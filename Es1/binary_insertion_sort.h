

/* Finds the index where data needs to be inserted in the given sorted array due to compare_to function
*/
int binary_search (void ** array, int min, int max, void * data, int(*compare_to)(void*, void*));
/* Sorts the array of generic pointers looping on the array. For each record finds the correct spot to 
insert in the already sorted part of the array, slides one place right the records between the index to insert
and the location where it needs to be inserted and then the current record is located right there.

*/
void binary_insertion_sort(void ** array, int size, int (*comparatore)(void*,void*)) ;
