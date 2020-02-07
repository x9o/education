//Testat von Baeuml Dominik (80043), Baeuml Julian (79708) und Waldsauer Andreas (79874)

#include <stdio.h>
#include <string.h>

void skytale (char*, int ,char*);

int main() {
	char s1[1000] = "DIESERKLARTEXTISTJETZTZUVERSCHLUESSELN12",
	s2[1000]="***bereits*verwendete*zeichenkette*mit*inhalt***", // Verschlüsseln hier hinein
	s3[1000]; // Entschlüsseln in diese Variable
    int n = 20, l = strlen(s1);
	printf("%s\n", s1);
    printf("Laenge: %d\n", l);
	skytale(s1, n, s2); // Chiffriere s1 zu s2 mit 8 Buchstaben pro Umdrehung
	printf("%s\n", s2);
    if (l % n == 0) {
        skytale(s2, l / n , s3); // Chiffriere zurueck
        printf("%s\n", s3);
        if (!strcmp(s1, s3))
            printf("In diesem Fall OK\n");
        else
            printf("Problem!\n");
    }
	return 0;
}

void skytale (char * in, int n , char * out)
{
	
	int s=0, k=0, j=0, l = strlen(in);
	
	while(k != n)
	{
		while (l > j)
		{
			out[s] = in[j];
			s++;
			j=j+n;
		}
		
		j=1+k;
		k++;
	}
	
	out[l]= '\0';
}
