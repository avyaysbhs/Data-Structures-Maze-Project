package grape.core.internal.concurrent;

public class Job<E>
{
    boolean clean = true;
    Runnable exe;
    Job prejob;
    Job postjob;
    E result;
    
    private Thread cached_thread;
    
    public Job(Runnable runnable)
    {
        exe = runnable;
    }

    public Job(Runnable runnable, boolean clean)
    {
        this(runnable);
        this.clean = clean;
    }

    public void setClean(boolean clean)
    {
        this.clean = clean;
    }

    public void setPreJob(Job pre)
    {
        prejob = pre;
    }

    public void setPostJob(Job post)
    {
        postjob = post;
    }

    public void passResult(E result) { this.result = result; }

    public Thread constructThread()
    {
        if (cached_thread != null) {return cached_thread; }
        cached_thread = new Thread(() ->
        {
            if (prejob != null) {
                prejob.exe.run();
                if (clean)
                {
                    prejob.recycle();
                    prejob.empty();
                }
            }
            exe.run();
            if (postjob != null) {
                postjob.exe.run();
                if (clean) {
                    postjob.recycle();
                    postjob.empty();
                }
            }
            if (clean) {
                recycle();
                empty();
            }
        });
        return cached_thread;
    }

    public Thread constructThreadTree()
    {
        if (cached_thread != null) {return cached_thread; }
        cached_thread = new Thread(() ->
        {
            if (prejob != null)
            {
                prejob.constructThreadTree().start();
                try
                {
                    prejob.constructThreadTree().join();
                } catch (InterruptedException e) { }
            }
            exe.run();
            if (postjob != null)
            {
                postjob.constructThreadTree().start();
                try
                {
                    postjob.constructThreadTree().join();
                } catch (InterruptedException e) { }
            }
        });
        return cached_thread;
    }

    public void recycle()
    {
        if (cached_thread.getState() != Thread.State.TERMINATED) return;
        cached_thread = null;
        result = null;
    }

    public void empty()
    {
        prejob = null;
        postjob = null;
        exe = null;
    }
}