/**
 * Illustrate memory-mapping files in Win32
 *
 * Producer code.
 *
 * Figure 13.15
 *
 * @author Gagne, Galvin, Silberschatz
 * Operating System Concepts  - Tenth Edition
 * Copyright John Wiley & Sons - 2018.
 */

#include <windows.h>
#include <stdio.h>

int main(int argc, char *argv[])
{
HANDLE hFile, hMapFile;
LPVOID mapAddress;

	// first create/open the file
	hFile = CreateFile("temp.txt",
						GENERIC_READ | GENERIC_WRITE,
						0,
						NULL,
						OPEN_ALWAYS,
						FILE_ATTRIBUTE_NORMAL,
						NULL);

	if (hFile == INVALID_HANDLE_VALUE) {
   		fprintf(stderr,"Could not open file temp.txt (%d).\n",GetLastError());
   		return -1;
	}

	// now obtain a mapping for it

	hMapFile = CreateFileMapping(hFile,
									NULL,
									PAGE_READWRITE,
									0,
									0,
									TEXT("SharedObject"));

	if (hMapFile == NULL) {
		fprintf(stderr,"Could not create mapping (%d).\n", GetLastError());
   		return -1;
	}

	// now establish a mapped viewing of the file

	mapAddress = MapViewOfFile(hMapFile,FILE_MAP_ALL_ACCESS,0,0,0);

	if(mapAddress == NULL) {
		printf("Could not map view of file (%d).\n", GetLastError());
		return -1;
	}

	// write to shared memory

	sprintf((char *)mapAddress,"%s","Shared memory message");

	while (1);
	// remove the file mapping
	UnmapViewOfFile(mapAddress);

	// close all handles
	CloseHandle(hMapFile);
	CloseHandle(hFile);
}

