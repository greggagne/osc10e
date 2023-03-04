import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List ;
public class FCFS implements Algorithm {
    private int index;
    private int currentTime=0;
    private int waitingTime =0;
    private int turnAroundTime = 0;



    List<Task> queue = new ArrayList<Task>();

    public FCFS(List queue){
        System.out.println("FCFS Scheduling");
        int tempSize = queue.size();
        this.queue=queue;
        schedule();
        System.out.println("Waiting itme = " + (double)waitingTime / tempSize );
        System.out.println("Waiting itme = " + (double)turnAroundTime/ tempSize );
    }
//trying the push
    @Override
    public void schedule() {

        while(this.queue.size() !=0 ) {
           System.out.println("Will run:" );
           System.out.println(pickNextTask().toString());
           System.out.println("Task " + pickNextTask().getName() + " finished");
           //burst += pickNextTask().getBurst();
            int wait_temp;
            if(queue.size()!=1){
           wait_temp = pickNextTask().getBurst() + currentTime;
           currentTime += pickNextTask().getBurst();
           waitingTime += wait_temp;

            }

            turnAroundTime += pickNextTask().getBurst();
           queue.remove(index);

        }
    }

    @Override
    public Task pickNextTask() {
        return queue.get(index);

    }

}
