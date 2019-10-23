package grape.core.events;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EventHub
{
    private Map<String, List<Task>> out = new HashMap<>();

    protected final EventHub clear()
    {
        out.clear();
        return this;
    }

    public final EventHub disconnect(String event, Task task)
    {
        out.get(event).remove(task);
        return this;
    }

    public final EventHub disconnect(String event)
    {
        out.remove(event);
        //out.put(event, new ArrayList<>());
        return this;
    }
    
    public final EventHub on(String event, Task executable)
    {
        List<Task> list;
        if (out.get(event) != null)
        {
            list = out.get(event);
        } else
        {
            list = new ArrayList<>();
            out.put(event, list);
        }
        list.add(executable);
        return this;
    }

    public final EventHub fire(String event)
    {
        if (out.get(event) == null) return this;
        out.get(event).forEach(EventHub::run_task_async);
        return this;
    }

    public final EventHub fire(String event, Map<String, Object> argv)
    {
        if (out.get(event) == null) return this;
        out.get(event).forEach(e -> 
        {
            run_task_async_argv(e, argv);
        });
        return this;
    }

    private final static void run_task_async(Task r)
    {
        new Thread(() ->
        {
            r.run(null);
        }).start();
    } 

    private final static void run_task_async_argv(Task r, Map<String, Object> args)
    {
        new Thread(() ->
        {
            r.run(args);
        }).start();
    }
}