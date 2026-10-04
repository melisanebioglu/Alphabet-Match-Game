package alphabet_match;

public class Queue {
	
    private Object[] elements; 
    private int front, rear, size;

    
    Queue(int capacity) 
    {
        elements = new Object[capacity];
        rear = -1;
        front = 0;
        size = 0;
    }

    
    boolean isFull() 
    { 
    	return (size == elements.length);
    }
    
    boolean isEmpty() 
    { 
    	return (size == 0);
    }
    
    int size() 
    { 
    	return size;
    } 
    
    
    void enqueue(Object data) 
    {
        if (!isFull()) 
        {
        	rear = (rear + 1) % elements.length;
            elements[rear] = data;
            size++; 
        }
    }

    
    Object dequeue() 
    {
        if (isEmpty()) 
        {
        	return null;
        }
        else
        {
        	Object retData = elements[front];
        	elements[front] = null;
            front = (front + 1) % elements.length;
            size--;
            return retData;
        }
    }

    
    public Object peek() 
    {
    	if (isEmpty()) 
        {
        	return null;
        }
        else
        {
            return elements[front];
        }
    }
    
    
}