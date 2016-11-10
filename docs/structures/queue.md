# Queue

**Link:** https://www.tutorialspoint.com/data_structures_algorithms/dsa_queue.htm
___

### Basic structure

The Queue is an abstract data structure which can be implemented by the use of an Array or a Linked List. Unlike
Stacks, a Queue is open at both its ends. One end is always used to insert data with _enqueue()_, the other end
to remove data is called _dequeue()_. This principle is called FIFO (First-in, First-out).

![alt text](https://www.tutorialspoint.com/data_structures_algorithms/images/queue_diagram.jpg "Queue structure")

### Basic operations

You can add data with the _enqueue()_ operation at the end of the Queue. To get (and remove) the first element,
there is the _dequeue()_ operation. To improve the Queue management, it's often useful to implement _isFull()_,
_isEmpty()_ and the _peek()_ operation. _peek()_ returns you get the first element without removing it.

**_enqueue()_ operation example**

```java
    public void enqueue(E o) {
        if(size == store.length) {
            expand();
        }
        
        store[pointerIn++] = o;
        size++;
    }
```

**_dequeue()_ operation example**

```java
    public E dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("The queue is empty!");
        }
        
        size--;
        return store[pointerOut++];
    }
```
