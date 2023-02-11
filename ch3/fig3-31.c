/**
 * Figure 3.31
 */

#include <stdio.h>
#include <unistd.h>

int main()
{
	fork();

	fork();

	fork();
	
	return 0;
}
