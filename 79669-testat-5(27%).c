//Abdullah Yildiz 79669, Nikolas Banea 79359, Muhammed Demir 70727
#include <stdio.h>
#include <string.h>

void skytale(char * in, int n, char * out);

void skytale(char * in, int n, char * out) {
//in = s1 der zu schiffrierende string
//n = n Anzahl der Buchstaben auf dem Skytale
//out = s2 der verschlüsselte text

int i, j, x, y, z;
x = strlen(in);
y = 0;
z = x/n;

    for(i = 0; i<n; i++){ //Buchstaben auf dem Skytale
        for(j=0; j<z; j++){
            if(i == 0){
                out[y] = in[j*n];
            } else {
                out[y] = in[j*n+i];
            }
            y += 1;
            out[y] = 0;
            //printf("Ergebnis: %s\n", out);
        }
    }
}

int main() {
	char s1[1000] = "DIESERKLARTEXTISTJETZTZUVERSCHLUESSELN12",
	s2[1000]="***bereits*verwendete*zeichenkette*mit*inhalt***", // Verschl�sseln hier hinein
	s3[1000]; // Entschl�sseln in diese Variable
    int n = 20, l = strlen(s1); 
	printf("%s\n", s1);
    printf("Laenge: %d\n", l);
	skytale(s1, n, s2); // Chiffriere s1 zu s2 mit 8 Buchstaben pro Umdrehung
	printf("%s\n", s2);
    if (l % n == 0) {
        skytale(s2, l / n , s3); // Chiffriere zurueck
        if (!strcmp(s1, s3))
            printf("In diesem Fall OK\n");
        else
            printf("Problem!\n");
    }
	return 0;
}