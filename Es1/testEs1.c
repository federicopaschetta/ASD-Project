#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "quicksort.h"
#include "binary_insertion_sort.h"

 struct record{
    int id;
    char* field1;
    int field2;
    double field3;
};

static int compareInteger(void* r1_p,void* r2_p){
  if(r1_p == NULL){
    fprintf(stderr,"precedes_record_int_field: the first parameter is a null pointer");
    exit(EXIT_FAILURE);
  }
  if(r2_p == NULL){
    fprintf(stderr,"precedes_record_int_field: the second parameter is a null pointer");
    exit(EXIT_FAILURE);
  }
  struct record *rec1_p = (struct record*)r1_p;
  struct record *rec2_p = (struct record*)r2_p;
  if(rec1_p->field2 > rec2_p->field2){
    return(1);
  }
else if(rec1_p->field2 == rec2_p->field2)
   return(0);
else 
   return(-1);
}
static int compareDouble(void* r1_p,void* r2_p){
  if(r1_p == NULL){
    fprintf(stderr,"precedes_record_int_field: the first parameter is a null pointer");
    exit(EXIT_FAILURE);
  }
  if(r2_p == NULL){
    fprintf(stderr,"precedes_record_int_field: the second parameter is a null pointer");
    exit(EXIT_FAILURE);
  }
  struct record *rec1_p = (struct record*)r1_p;
  struct record *rec2_p = (struct record*)r2_p;
  if(rec1_p->field3 > rec2_p->field3){
    return(1);
  }else if(rec1_p->field3 == rec2_p->field3)
   return(0);
else 
   return(-1);
}


static int compareString(void* r1_p,void* r2_p){
  if(r1_p == NULL){
    fprintf(stderr,"precedes_string: the first parameter is a null pointer");
    exit(EXIT_FAILURE);
  }
  if(r2_p == NULL){
    fprintf(stderr,"precedes_string: the second parameter is a null pointer");
    exit(EXIT_FAILURE);
  }
  struct record *rec1_p = (struct record*)r1_p;
  struct record *rec2_p = (struct record*)r2_p;
  if(strcmp(rec1_p->field1,rec2_p->field1)>0){
    return(1);
  }
  else if(strcmp(rec1_p->field1 , rec2_p->field1)==0 )
   return(0);
else 
   return(-1);
}



static struct record **a;
static struct record **b;


void create_data(){
    int i=0;
        struct record *temp = malloc(sizeof(struct record));
        temp->id = 2;
        temp->field1 = "ciao";
        temp->field2 = 3;
        temp->field3 = 3.2;
       b = realloc(b,sizeof(struct record)*i);
       b[i] = temp;
       i++;
             struct record *temp1 = malloc(sizeof(struct record));
        temp1->id = 1;
        temp1->field1 = "balena";
        temp1->field2 = 2;
        temp1->field3 = 2.2;
       b = realloc(b,sizeof(struct record)*i);
       b[i] = temp1;
       i++;
    struct record *temp2 = malloc(sizeof(struct record));
        temp2->id = 0;
        temp2->field1 = "anna";
        temp2->field2 = 1;
        temp2->field3 = 1.2;
       b = realloc(b,sizeof(struct record)*i);
       b[i] = temp2;
       i++;
   

         struct record *temp3 = malloc(sizeof(struct record));
        temp3->id = 0;
        temp3->field1 = "ciao";
        temp3->field2 = 1;
        temp3->field3 = 1.2;
       b = realloc(b,sizeof(struct record)*i);
       b[i] = temp3;

       
}
void create_sample_data(){
    int i=0;
    struct record *temp = malloc(sizeof(struct record));
        temp->id = 0;
        temp->field1 = "anna";
        temp->field2 = 1;
        temp->field3 = 1.1;
       a = realloc(a,sizeof(struct record)*i);
       a[i] = temp;
       i++;
    struct record *temp1 = malloc(sizeof(struct record));
        temp1->id = 0;
        temp1->field1 = "anna";
        temp1->field2 = 1;
        temp1->field3 = 1.1;
       a = realloc(a,sizeof(struct record)*i);
       a[i] = temp1;
       i++;
         struct record *temp2 = malloc(sizeof(struct record));
        temp2->id = 1;
        temp2->field1 = "balena";
        temp2->field2 = 2;
        temp2->field3 = 2.2;
       a = realloc(a,sizeof(struct record)*i);
       a[i] = temp2;
       i++;
         struct record *temp3 = malloc(sizeof(struct record));
        temp3->id = 2;
        temp3->field1 = "ciao";
        temp3->field2 = 3;
        temp3->field3 = 3.2;
       a = realloc(a,sizeof(struct record)*i);
       a[i] = temp3;
       


       
}


void test_sort_null_array() {
    void**bn=NULL;
    quicksort(0,-1,bn,NULL);
  if(NULL==bn){
      printf("test_sort_null_array PASSED\n");
  }
  
}
void test_sort_empty_array() {
quicksort(0,-1,(void**)b,compareInteger);
  if(a==b){
      printf("test_sort_empty_array PASSED\n");
  }
  
}

void test_sort_three_records(int (*comparatore)(void*,void*)) {
  quicksort(0,3,(void**)b,compareInteger);
  int i=0;
  for(;i<4;i++){
      if(comparatore(a[i],b[i])!=0){
         i=5;
      }
  }
  if(i!=5){
      printf("test_sort_three_records PASSED\n");
  }else{
      printf("test_sort_three_records FAILED\n");
  }
   
}
 



int main(int argc, char** argv) {

  test_sort_empty_array();
  test_sort_null_array();

  create_data();
  create_sample_data();
  printf("Compare_integer_");
  test_sort_three_records(compareInteger);
    printf("Compare_string_");
  test_sort_three_records(compareString);
    printf("Compare_double_");
  test_sort_three_records(compareDouble);
  
}
