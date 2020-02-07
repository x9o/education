#include <stdio.h>
#include <string.h>

int main(int argc, char* argv[]){

    int i;
    int count = 0;
    char temp[9999];
    FILE * f = fopen("testat-8-namen.txt", "r");
    
    /*
    if(argc <= 1){ //Wenn kein Kommdandobefehl mitgegeben wird, 0 anzeigen!
        printf("%d Treffer\n", count);
    } else */
    
    //if(argc > 1){
        if(f == NULL){ //Überprüft ob die Datei geöffnet werden kann
            fprintf(stderr, "Dateifehler\n");
            return 0;
        } else {
            while(fscanf(f, "%s", temp) != EOF){ //Geht bis zum EOF
                for(i=1; i<argc; i++){
                    //printf("%s\n", temp);
                    if(strcmp(temp, argv[i]) == 0){ //Überprüfe ob es das gleiche Wort ist
                        count += 1; //Gleiches Wort = count +1
                    }
                }
            }
            fclose(f);
        }
   //} 
   //Probleme: Stern wird nicht gefunden
    printf("%d Treffer\n", count);
    return 0;
}