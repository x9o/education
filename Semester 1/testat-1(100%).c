#include <stdio.h>

int main() {
	
    int a, b, c, d, e, f, j, k, M, N, p, q, Ostern;

	scanf("%d", &j);

    a = j % 19;
    b = j % 4;
    c = j % 7;
    k = j / 100;
    p = k / 3;
    q = k / 4;
    M = (15 + k - p - q) % 30;
    d = ((19*a) + M) % 30;
    N = (4 + k - q) % 7;
    e = ((2*b) + (4*c) + (6*d) + N) % 7;
    Ostern = (22 + d + e);
    f = 3 + (Ostern / 32);
    Ostern -= 1;
    Ostern = 1 + Ostern % 31;


	printf("%02d.%02d.%04d\n", Ostern ,f, j);
	
	return 0;
}
