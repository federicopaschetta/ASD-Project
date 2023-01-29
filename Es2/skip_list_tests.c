#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include "unity.h"
#include "skip_list.h"

static int compareInteger(void* val1,void* val2){

  if(val1 == NULL){
    fprintf(stderr,"precedes_record_int_field: the first parameter is a null pointer");
    exit(EXIT_FAILURE);
  }
  if(val2 == NULL){
    fprintf(stderr,"precedes_record_int_field: the second parameter is a null pointer");
    exit(EXIT_FAILURE);
  }
  if(val1 > val2)
        return(1);
    
    else if(val1 == val2)
        return(0);
    else 
        return(-1);
    }




int a=1,b=2,c=3;
char* s1,s2,s3;
int *i1=&a,*i2=&b,*i3=&c;
SkipList list;

void setUp(void){
    skiplist_init(&list,compareInteger);
}

void TearDown(void){
    deleteList(&list);
}

static void test_skip_list_is_empty(void){
    TEST_ASSERT_TRUE(isEmpty(&list));
}

static void test_skip_list_size_zero_el(void){
    TEST_ASSERT_EQUAL_INT(0,size(&list));
}

static void test_skip_list_size_one_el(void){
    insertSkipList(&list,(void*)i1);
    TEST_ASSERT_EQUAL_INT(1,size(&list));
}

static void test_skip_list_size_three_el(void){
    insertSkipList(&list,(void*)i1);
    insertSkipList(&list,(void*)i2);
    insertSkipList(&list,(void*)i3);
    TEST_ASSERT_EQUAL_INT(3,size(&list));
}

static void test_skip_list_add_search_one_el(void){
    insertSkipList(&list,(void*)i1);
    TEST_ASSERT_TRUE(searchSkipList(&list,(void*)i1));
}

static void test_skip_list_add_search_three_el(void){
    insertSkipList(&list,(void*)i1);
    insertSkipList(&list,(void*)i2);
    insertSkipList(&list,(void*)i3);
    TEST_ASSERT_TRUE(searchSkipList(&list,(void*)i2));
}


int main(void) {
    
    UNITY_BEGIN();
    RUN_TEST(test_skip_list_is_empty);
    RUN_TEST(test_skip_list_size_zero_el);
    RUN_TEST(test_skip_list_size_one_el);
    RUN_TEST(test_skip_list_size_three_el);
    RUN_TEST(test_skip_list_add_search_one_el);
    RUN_TEST(test_skip_list_add_search_three_el);

    return UNITY_END();
}