//Abdullah Yildiz 79669, Hüseyin Selcuk 79375, Muhammed Demir 70727
#include <stdio.h>
#include "testat-6.h"

int scheitelhoehe(struct parabel * p, double *y) {

    int rc = 0;
    double x_value, y_value;
    double a = p->a;
    double b = p->b;
    double c = p->c;

    if(a != 0){
        x_value = -b / (2*a);
        y_value = (a*(x_value*x_value)) + (b*x_value) + c;
        *y = y_value;
    } else {
        rc = 1;
    }
    return rc;
}

void sort_parabeln(struct parabel * p, int n) {

    int i, j;
    double tmp_a, tmp_b, tmp_c, tmp_h, x, height[n];

    for(i=0; i<n; i++){
        if(scheitelhoehe(p+i, &x) == 0){
            height[i] = x;
        } else {
            height[i] = 9999999999;
        }
    }

    for(i=0; i<2*n; i++){
        for(j=0; j<n-1; j++){

            tmp_h = height[j];
            tmp_a = p[j].a;
            tmp_b = p[j].b;
            tmp_c = p[j].c;

            if(height[j] > height[j+1]){
                p[j] = p[j+1];
                height[j] = height[j+1];
                p[j+1].a = tmp_a;
                p[j+1].b = tmp_b;
                p[j+1].c = tmp_c;
                height[j+1] = tmp_h;
            }
        }
    }
}

int main() {
    struct parabel p[] = {    
        {1,2,3},
        {2,5,-19},
        {0,0,0},
        {-1,0,0}
    };
    double y;
    printf("Index 0 ist eine echte Parabel: %d\n", scheitelhoehe(p, &y) == 0);
    sort_parabeln(p, sizeof(p) / sizeof(struct parabel));
    return 0;
}