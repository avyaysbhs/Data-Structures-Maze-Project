package grape.core.events;

import java.util.Map;

public interface Task
{
    public void run(Map<String, Object> parameters);
}