/**
 * Example of openmp parallel region
 * 
 * To compile, enter:
 *
 *	gcc -fopenmp openmp.c
 *
 * You should see the message "I am a parallel region" for each
 * processing core on your system.
 *
 * For those using a virtual machine, make sure you set the number of
 * processing cores > 1 to see parallel execution of the parallel region.
 */

#include <omp.h>
#include <stdio.h>

int main(int argc, char *argv[])
{
	/* sequential code */

	#pragma omp parallel
	{
		printf("I am a parallel region\n");
	}

	/* sequential code */

	return 0;
}
