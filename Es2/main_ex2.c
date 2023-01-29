#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include "skip_list.h"
#include "time.h"
//clock_t tstart, tend, tstart2, tend2;

/*
The commented code was used to calculate the execution time
of the algorithm.
*/

/*
void duration() {
    printf("L'ALGORITMO HA IMPIEGATO %f SECONDI PER INDIVIDUARE GLI ERRORI\n", ((double) tend-tstart)/CLOCKS_PER_SEC);
  }
*/

/*
Compares the given strings. 
Returns 1 if r1_p>r2_p, returns 0 if r1_p==r2_p, -1 otherwise.
*/
static int compareString(void* r1_p,void* r2_p){
  if(r1_p == NULL){
    fprintf(stderr,"precedes_string: the first parameter is a null pointer");
    exit(EXIT_FAILURE);
  }
  if(r2_p == NULL){
    fprintf(stderr,"precedes_string: the second parameter is a null pointer");
    exit(EXIT_FAILURE);
  }
  if(strcmp(r1_p, r2_p)>0){
    return(1);
  }
  else if(strcmp(r1_p, r2_p)==0)
   return(0);
else 
   return(-1);
}

/*
Reads from a given input file and puts each value in the given skiplist.
*/
void read(SkipList *list){
    char *row;
    char buffer[1024];
    int buf_size = 1024;
    int i=0;
    char name[50];
    FILE *fp;
    printf("Inserire il nome del file dizionario\n");
    scanf("%s",name);
    fp= fopen(name,"r");
    while(fgets(buffer,buf_size,fp) != NULL){
        row = malloc((strlen(buffer)+1)*sizeof(char));
        strcpy(row,buffer);
        char *lettura=strtok(row,"\n");
        char *value = malloc((strlen(lettura)+1)*sizeof(char));
        strcpy(value,lettura);
       // printf("%s\n",lettura);
        insertSkipList(list,(void*)value);
    }
    fclose(fp);
   }


/*
Prints the given list.
*/
   void printList(SkipList* list) {
     Node* testa = list->head;
     int i=0;
     Node* toPrint = testa->next[0];
     while (toPrint!= NULL) {
       printf(" %d  %s \n",i,(char*)toPrint->item);
       toPrint = toPrint->next[0];
       i++;
     }
   }
   


/*
Creates the empty list, reads the input file that needs to be corrected
and tells apart correct words from wrong ones based on the file read in the read() function.
*/
int main(){
    char *row;
    char buffer[1024];
    int buf_size = 1024;
    char name[50];
    FILE *fp;
    int i=0,k=0,j=0;
    SkipList list;
    skiplist_init(&list, compareString);
    //printf("Inizio lettura..\n");
    //tstart2 = clock();
    read(&list);
    //tend2 = clock();
    //printf("Termine lettura\n");
    //printf("lettura %f\n", ((double) tend2-tstart2)/CLOCKS_PER_SEC);
    //printList(&list);
    //printf("Inizio esecuzione algoritmo...\n");
    printf("Inserire il nome del file da correggere\n");
    scanf("%s",name);
    fp= fopen(name,"r");
    fgets(buffer,buf_size,fp);
    //tstart = clock();
    while(i==0){
      char *lettura;
      row = malloc((strlen(buffer)+1)*sizeof(char));
      strcpy(row,buffer);
      if(j==0)
        lettura=strtok(row, " ");
      else
        lettura=strtok(NULL," ");      
      if (lettura!=NULL){ 
        char *value = malloc((strlen(lettura)+1)*sizeof(char));
        strcpy(value,lettura); 
        k=0;
      while(value[k]!='\0'){
        if (value[k]==','|| value[k]=='.'|| value[k]==':')
          value[k]='\0';
        else
          k++;
      }
       // printf("%s\n",lettura);
      for (size_t i = 0; i < strlen(value); ++i) {
        value[i]= tolower((unsigned char) value[i]);
      }

      int result=searchSkipList(&list, (void*) value);
      if(result!=1){
        printf("%s è sbagliato! \n",value);
      }
      else
      {
        printf("%s è giusto! \n",value);
      } 
      }
      else{
        i=1;}
      j++;
      }
      //tend = clock();
      //duration();
    fclose(fp);
    deleteList(&list);
    return 0;   
   }
