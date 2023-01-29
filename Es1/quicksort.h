/*
Sorts the given generic array of pointers a, the integers primo and ultimo represent the interval that needs to be sorted.
A comparator is also required.
The sorting is executed via a pivot value, which is used to divide the array into two segments,
where the elements in one segment are less than the pivot and in the other are greater than the pivot.
The algorithm executes recursively until primo==ultimo
*/
void quicksort(int primo,int ultimo, void** a,int (*comparatore)(void*,void*));