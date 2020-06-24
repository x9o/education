#include <stdio.h>

int main() {

    int i, b;
    int a = 2;
    unsigned int zahl;

    scanf("%d", &zahl);

    if(zahl >= 2){
        for(i=2; i<=zahl/2; i++){
            if(zahl%i == 0){
                b = 1;
                break;
            }
        }

        if (b == 1){               
            while(zahl > 1){
                if(zahl % a == 0){
                    zahl = zahl / a;
                    printf("%d \n", a);

                } else {
                    a = a + 1;
                }
            }
        } else if (b != 1){
                printf("%d ist prim.\n", zahl);
            }

    } else {
        printf("%d kann nicht zerlegt werden.\n", zahl);
    }

	return 0;
}
