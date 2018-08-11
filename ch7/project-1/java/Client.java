public class Client
{
    public static void main(String[] args) {
        ThreadPool pool = new ThreadPool();

        pool.add(new Task(5,10));
        pool.add(new Task(1,2));
        pool.add(new Task(5,1));
        pool.add(new Task(15,0));
        pool.add(new Task(75,7));
        pool.add(new Task(12,-10));

        pool.shutdown();
    }
}
