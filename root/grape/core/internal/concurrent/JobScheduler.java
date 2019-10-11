package grape.core.internal.concurrent;

import java.util.PriorityQueue;
import java.util.Queue;

public class JobScheduler extends PriorityQueue<Job>
{   
    public final static JobScheduler main = new JobScheduler();

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