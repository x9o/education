#include <stdio.h>

int main() {

int verheiratet, kinder;
double bruttogehalt, steuern, stn_kinder = 0.1, stn_verheiratet = 0.0;

const double zwoelf_prozent = 0.12;
const double fuenfzehn_prozent = 0.15;
const double zwanzig_prozent = 0.20;
const double fuenfundzwanig_prozent = 0.25;

scanf("%lf%d%d", &bruttogehalt, &verheiratet, &kinder);

if((bruttogehalt > 0) && (verheiratet == 0 || verheiratet == 1) && (kinder >= 0)){
    if(verheiratet == 1){
        stn_verheiratet += 0.2;
    }
    
    if(kinder >= 8){
        stn_kinder = 0;
        stn_verheiratet = 0;
    }

        if (bruttogehalt < 12000){
            steuern = (bruttogehalt * zwoelf_prozent) * (1 - (stn_verheiratet + (kinder * stn_kinder)));

        } else if(bruttogehalt > 12000){
            steuern = (bruttogehalt * fuenfzehn_prozent) * (1 - (stn_verheiratet + (kinder * stn_kinder)));

        } else if (bruttogehalt > 20000){
            steuern = (bruttogehalt * zwanzig_prozent) * (1 - (stn_verheiratet + kinder * stn_kinder));

        } else if (bruttogehalt > 30000){
            steuern = (bruttogehalt * fuenfundzwanig_prozent) * (1 - (stn_verheiratet + kinder * stn_kinder));   
        }
        printf("%.2lf\n", steuern);   

} else {
    printf("Eingabefehler\n");
}

return 0;
}
