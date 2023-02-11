/**
 * Illustrate memory-mapping files in Windows
 *
 * Consumer code.
 *
 * Figure 13.16
 *
 * @author Gagne, Galvin, Silberschatz
 * Operating System Concepts  - Tenth Edition
 * Copyright John Wiley & Sons - 2018.
 */

#include <stdio.h>
#include <windows.h>

int main(int argc, char *argv[]) {
HANDLE hMapFile;
LPVOID lpMapAddress;

hMapFile = OpenFileMapping(FILE_MAP_ALL_ACCESS,          // read/write permission
                           FALSE,                        // Do not inherit the name
                           TEXT("SharedObject")); // of the mapping object.

if (hMapFile == NULL)
{
   printf("Could not open file mapping object (%d).\n", GetLastError());
   return -1;
}

lpMapAddress = MapViewOfFile(hMapFile,            // handle to mapping object
                             FILE_MAP_ALL_ACCESS, // read/write permission
                             0,                   // max. object size
                             0,                   // size of hFile
                             0);                  // map entire file

if (lpMapAddress == NULL)
{
   printf("Could not map view of file (%d).\n", GetLastError());
   return -1;
}

printf("%s\n",lpMapAddress);

UnmapViewOfFile(lpMapAddress);
CloseHandle(hMapFile);

}
