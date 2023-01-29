#include <stdio.h>
#include <stdlib.h>
#include "quicksort.h"

/*
Sorts the given generic array of pointers a, the integers primo and ultimo represent the interval that needs to be sorted.
A comparator is also required.
The sorting is executed via a pivot value, which is used to divide the array into two segments,
where the elements in one segment are less than the pivot and in the other are greater than the pivot.
The algorithm executes recursively until primo==ultimo
*/

void quicksort(int primo,int ultimo, void** a,int (*comparatore)(void*,void*)){ 
   int i, j, pivot;
   void *temp;

   if(primo<ultimo){
      pivot=primo;
      i=primo;
      j=ultimo;
      while(i<j){
         while(comparatore(a[i],a[pivot])<1 && i<ultimo)
            i++;
         while(comparatore(a[j],a[pivot])==1)
            j--;
         if(i<j){
            temp=a[i];
            a[i]=a[j];
            a[j]=temp;
         }
      }
      temp=a[pivot];
      a[pivot]=a[j];
      a[j]=temp;

      quicksort(primo,j-1,a,comparatore);
      quicksort(j+1,ultimo,a,comparatore);
   }
}