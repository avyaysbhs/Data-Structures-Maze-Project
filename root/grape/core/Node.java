package grape.core;

import java.util.ArrayList;
import java.util.List;

public class Node
{
    public String name;
    public Node parent;
    public final List<Node> children = new ArrayList<>();
    
    public Node findFirstChild(String name)
    {
        for (Node node: children)
        {
            if (node.name == name)
                return node;
        }
        return null;
    }

    public Node appendChild(Node node)
    {
        if (node.parent != this && node.parent != null)
        {
            try
            {
                node.parent.removeChild(node);
            } catch (NullPointerException e)
            {
                throw (NullPointerException) e.fillInStackTrace();
            }
            node.parent = this;
        }
        if (!children.contains(node))
            children.add(node);
        return node;
    }

    public Node removeChild(Node node)
    {
        if (children.remove(node))
        {
            node.parent = node.parent == this ? null : node.parent;
        }
        return node;
    }

    public List<Node> removeAllChildren()
    {
        List<Node> out = new ArrayList<>();
        children.forEach(node ->
        {
            out.add(this.removeChild(node));
        });
        children.clear();
        return out;
    }
}