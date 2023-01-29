#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "quicksort.h"
#include "binary_insertion_sort.h"
#include "time.h"

//Commented code was used for calculating execution time or prints.

struct record{
    int id;
    char* field1;
    int field2;
    double field3;
};
int length;

struct record **a;

//clock_t tstart, tend;
/*
  void duration() {
     printf("L'ALGORITMO HA IMPIEGATO %f SECONDI PER ORDINARE I RECORD\n", ((double) tend-tstart)/CLOCKS_PER_SEC);
  }

  void stampe(){
      for(int i =0;i<length;i++){
      printf("\t %d \t %s \t %d \t %f \t \n", a[i]->id, a[i]->field1, a[i]->field2, a[i]->field3);
   }
}
*/
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


/*
Reads from an input csv file and loads the records
*/

   void csv_read(){
    char *row;
    char buffer[1024];
    int buf_size = 1024;
    int i=0;
    char *name;
    FILE *fp;
    printf("Inserire il nome del file da utilizzare\n");
    scanf("%s",name);
    printf("Inserire il numero di records da ordinare\n");
    scanf("%d",&length);
    fp= fopen(name,"r");
    if (!fp){
       printf("Nome del file non valido.\n");
       exit(1);
    }
    while(fgets(buffer,buf_size,fp) != NULL && i<length){
        row = malloc((strlen(buffer)+1)*sizeof(char));
        strcpy(row,buffer);

        int id = atoi(strtok(row,","));
        char *field1_lettura = strtok(NULL,",");
        char *field1 = malloc((strlen(field1_lettura)+1)*sizeof(char));
        strcpy(field1,field1_lettura);
        int field2 = atoi(strtok(NULL,","));
        double field3 = atof(strtok(NULL,","));
        struct record *temp = malloc(sizeof(struct record));


        temp->id = id;
        temp->field1 = field1;
        temp->field2 = field2;
        temp->field3 = field3;
       a = realloc(a,sizeof(struct record)*i);
       a[i] = temp;
       i++;

        free(row);
    }
    fclose(fp);

   }

/*
Generates a simple selection menu and calls the requested methods based 
on the user's choice.
*/

int main(){
int selection=0;
int algoritmo=0;
    csv_read();
    //printf("LETTURA CSV\n");
    //stampe();
    while(selection>=0){
      printf("Selezionare l'algoritmo di ordinamento: 1 quicksort, 2 binary_insertion_sort\n");
      scanf("%d",&algoritmo);
      printf("Selezionare il campo desiderato per l'ordinamento: 0, 1, 2, altrimenti -1 per uscire\n");
      scanf("%d",&selection);
      if(algoritmo==1){
       switch(selection){
            case 0:
               //tstart = clock();
               quicksort(0,length-1,(void**)a,compareString);
               //tend = clock();
               printf("\n------ Eseguo QUICKSORT SULLE STRINGHE DEL RECORD ------ \n");
               break;
            case 1:
               //tstart = clock();
               quicksort(0,length-1,(void**)a,compareInteger);
               //tend = clock();
               printf("\n------ Eseguo QUICKSORT SUGLI INTERI DEL RECORD------ \n");
               break;
            case 2:
               //tstart = clock();
               quicksort(0,length-1,(void**)a,compareDouble);
               //tend = clock();
               printf("\n------ Eseguo QUICKSORT SUI DOUBLE DEL RECORD------ \n");
               break;
         }
      }else{
          switch(selection){
            case 0:
               //tstart = clock();
               binary_insertion_sort((void**)a,length-1,compareString);
               //tend = clock();
               printf("\n------ Eseguo BINARY_INSERTION_SORT SULLE STRINGHE DEL RECORD------ \n");
               break;
            case 1:
               //tstart = clock();
               binary_insertion_sort((void**)a,length-1,compareInteger);
               //tend = clock();
               printf("\n------ Eseguo BINARY_INSERTION_SORT SUGLI INTERI DEL RECORD ------ \n");
               break;
            case 2:
               //tstart = clock();
               binary_insertion_sort((void**)a,length-1,compareDouble);
               //tend = clock();
               printf("\n------ Eseguo BINARY_INSERTION_SORT SUI DECIMALI DEL RECORD------ \n");
               break;
         }


      }
      if(selection<3 && selection>=0 ){
         //printf("STAMPE AGGIORNATE\n");
         //stampe();
         //duration();
         
      }
      else if (selection!=-1)
         printf("Selezione non valida\n");
   }
   free(a);
}
