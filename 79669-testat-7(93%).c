#include <stdio.h>
#include <math.h>
#include "testat-7.h"
int huepfspiel(spielfeld * s, int nr);

int huepfspiel(spielfeld * s, int nr){
    int rc=0, value=0, value2=0;

    //Nicht loesbar
    if((s->feld[nr] == 0) && (nr > 0) && (nr < s->n)){ //Wenn eine 0 gefunden wird
        //printf("Es wurde eine Null gefunden\n");
        rc = -999999;
    }

    //Loesbar
    if(nr < s->n){ //Punkt positiv?
        if((nr + s->feld[nr]) <= (s->n)){  //Feld darf nicht über das groesste Feld hinaus + nach rechts laufen
            /*printf("Jetzt nach rechts ");
            printf("Feld: %d ", nr);
            printf("Wert: %d\n", s->feld[nr]);*/
            value = nr + s->feld[nr];
            s->feld[nr] = -(s->feld[nr]); //Ariadnefaden, Vorzeichen wechsel
            rc = huepfspiel(s, value);

        } else { //Wenn das naechste Feld groesser ist, als das max Feld
            /*printf("Jetzt nach links ");
            printf("Feld: %d ", nr);
            printf("Wert: %d\n", s->feld[nr]);*/
            value2 = nr - (s->feld[nr]/2);
            s->feld[nr] = -(s->feld[nr]); //Ariadnefaden, Vorzeichen wechsel
            rc = huepfspiel(s, value2);  
        }
        s->feld[nr] = -(s->feld[nr]);
        rc += 1; //1x springen
    } else {
        if(nr != s->n){ //Erkennt Loops, damit keine weitere negative Zahl versucht wird    
            rc = -999999;
        }   
    }
    return rc;
}

int main() {
    spielfeld s  = {20,{5,3,2,1,7,3,1,2,3,4,5,1,3,7,1,2,1,9,0,1}};
    spielfeld s2 = {4,{1,2,2,1}};
    spielfeld s3 = {4,{1,2,2,5}};
    spielfeld s4 = {7,{2,3,2,4,3,0}};
    spielfeld s5 = {4,{1,2,2,3}};
    //spielfeld s6 = {40,{5,3,2,1,7,4,1,13,3,4,5,1,3,5,1,6,1,0,5,1,5,0,4,0,0,6,3,13,0,1,7,12,0,0,6,0,0,6,0,0}};
    spielfeld s7 = {20,{5,0,4,0,0,6,3,13,0,1,7,12,0,0,6,0,0,6,0,0}};
    //spielfeld s8 = {30,{5,1,1,1,5,3,0,1,6,7,1,1,18,1,0,2,3,4,-2,4,1,3,7,7,4,1,1,1,32,1}};
    spielfeld s9 = {20,{5,3,2,1,7,5,1,0,3,12,5,1,3,7,1,5,1,9,0,1}};
    spielfeld s10 = {11,{4,0,2,0,4,5,0,0,6,0,2}};
    spielfeld s11 = {11,{4,8,2,0,4,5,0,0,6,0,1}};
    spielfeld s12 = {20,{5,3,2,1,7,4,1,0,3,4,5,1,3,5,1,6,1,9,5,1}};
    spielfeld s13 = {20,{6,0,0,16,0,1,6,0,0,0,9,0,4,0,0,1,2,1,2,1}};
    printf("LoesbarS: %d\n", huepfspiel(&s, 0)); //lösung 6
    printf("LoesbarS2: %d\n", huepfspiel(&s2, 0));//lösung 3
    printf("LoesbarS3: %d\n", huepfspiel(&s3, 0));//lösung-1
    printf("LoesbarS4: %d\n", huepfspiel(&s4, 0));//lösung 3
    printf("LoesbarS5: %d\n", huepfspiel(&s5, 0));//lösung 4
    //printf("LoesbarS6: %d\n", huepfspiel(&s6, 0));//lösung 11
    printf("LoesbarS7: %d\n", huepfspiel(&s7, 0));//lösung 7
    //printf("LoesbarS8: %d\n", huepfspiel(&s8, 0));//lösung 7
    printf("LoesbarS9: %d\n", huepfspiel(&s9, 0));//lösung 4
    printf("LoesbarS10: %d\n", huepfspiel(&s10, 0));//lösung-1
    printf("LoesbarS11: %d\n", huepfspiel(&s11, 0));//lösung 5
    printf("LoesbarS12: %d\n", huepfspiel(&s12, 0));//lösung-1
    printf("LoesbarS13: %d\n", huepfspiel(&s13, 0));//lösung 4
    printf("Loesbar: %d\n", huepfspiel(&s, 0));
    return 0;
}