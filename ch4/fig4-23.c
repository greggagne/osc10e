/**
 * A pthread program illustrating how to
 * create a simple thread and some of the pthread API
 * This program implements the summation function where
 * the summation operation is run as a separate thread.
 *
 * Most Unix/Linux/OS X users
 * gcc fig4-23.c -lpthread
 *
 * Figure 4.23
 *
 * @author Gagne, Galvin, Silberschatz
 * Operating System Concepts  - Tenth Edition
 * Copyright John Wiley & Sons - 2018
 */

#include <pthread.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>

int value = 0;
void *runner(void *param); /* the thread */

int main(int argc, char *argv[])
{
	pid_t pid;
	pthread_t tid; pthread_attr_t attr;
	pid = fork();

	if (pid == 0) { /* child process */ 
		pthread_attr_init(&attr);
		pthread_create(&tid,&attr,runner,NULL); 
		pthread_join(tid,NULL);
    		printf("CHILD: value = %d",value); /* LINE C */
	}
	else if (pid > 0) { /* parent process */
		wait(NULL);
    		printf("PARENT: value = %d",value); /* LINE P */
	} 
}

void *runner(void *param) { 
	value = 5;
	pthread_exit(0);
}
