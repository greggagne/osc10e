/**
 * Simple program demonstrating Win32 anonymous pipes.
 *
 * Figure 3.25
 *
 * @author Silberschatz, Gagne, and Galvin.
 * Operating System Concepts  - Tenth Edition
 * Copyright John Wiley & Sons - 2018
 */

#include <stdio.h>
#include <windows.h>

#define BUFFER_SIZE 25

int main(VOID)
{
    HANDLE ReadHandle, WriteHandle; 
    CHAR buffer[BUFFER_SIZE];
    DWORD read;
    
    ReadHandle = GetStdHandle(STD_INPUT_HANDLE);
    WriteHandle= GetStdHandle(STD_OUTPUT_HANDLE);
    
    /* have the child read from the pipe */
    if (ReadFile(ReadHandle, buffer, BUFFER_SIZE, &read, NULL))
       printf("child: >%s<",buffer);
    else
        fprintf(stderr, "Child: Error reading from pipe\n");
    
    return 0;
}
