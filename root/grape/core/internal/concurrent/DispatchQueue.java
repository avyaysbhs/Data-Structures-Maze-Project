package grape.core.internal.concurrent;

import java.util.PriorityQueue;
import java.util.Queue;

public class DispatchQueue extends PriorityQueue<Job>
{   
    public final static DispatchQueue main = new DispatchQueue();

    static 
    {
        new Thread(() -> 
        {
            while (true)
            {
                try
                {
                    Thread.sleep(1);
                    if (main.next())
                        main.sync(main.remove());
                } catch (InterruptedException e)
                {

                }
            }
        }).start();
    }

    public void async(Job job)
    {
        job.constructThread().start();
    }

    public synchronized void sync(Job job)
    {
        job.constructThread().start();
    }

    public boolean next()
    {
        if (poll() != null)
            return true;
        return false;
    }
}