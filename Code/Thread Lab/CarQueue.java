import java.util.PriorityQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CarQueue{
    PriorityQueue<Integer> dirs;
    private final Lock removeQueueLock;
    public CarQueue(){
        dirs = new PriorityQueue<>();
        for(int i = 0; i<6; i++){
            dirs.add((int) (Math.random() * 4));
        }
        removeQueueLock = new ReentrantLock();
    }

    public void addToQueue() {
        class QueueRunnable implements Runnable{
            @Override
            public void run() {
                try {
                    for(int i = 0; i<30; i++){
                        dirs.add((int) (Math.random() * 4));
                        Thread.sleep(100);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        Runnable r = new QueueRunnable();
        Thread t = new Thread(r);
        t.start();
    }

    public int deleteQueue(){
//        removeQueueLock.lock();
        if(dirs.peek() == null){
            return -1;
        }
        int top = dirs.poll();
//        removeQueueLock.unlock();
        System.out.println(dirs.size());
        return top;
    }
}
