#include <stdio.h>

int primfaktor(int);

int main(void) {

	int pf;
	while((pf=primfaktor(12))>1){
		printf("%d\n",pf);
	}
	while((pf=primfaktor(1))>1){
		printf("%d\n",pf);
	}
	printf("%d\n",primfaktor(1));
	return 0;
}


int primfaktor(int n)
{
	int a = 2;
	int static m, s;

	if(m == n){
		n = s;
	} else {
		m = n;
		s = 0;
	}

	while(n > 1){
		if(n%a == 0){
			s = n/a;
			return a;
		} else {
			a += 1;
		}
	}

	if(n < 2){
		return -2;
	}
	
	if(n == s){
		return -1;
	} else {
		return -2;
	}
	
}