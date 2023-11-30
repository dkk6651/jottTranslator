
#include <string.h>

char* concat(char* s1, char* s2){
    char *newStr = malloc(sizeof(char) * (strlen(s1) + strlen(s2)));
    strcat(newStr, s1);
    strcat(newStr, s2);
    return(newStr);
}