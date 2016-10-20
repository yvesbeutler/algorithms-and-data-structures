# Stack

**Link:** https://www.tutorialspoint.com/data_structures_algorithms/stack_algorithm.htm
___

### Basic structure

A Stack represents a simple data structure which can be implemented by the use of Arrays or Linked Lists.
There are only two operations on the Stack which are called _push()_ and _pop()_. At any time we can only access
the top element of the Stack and no element below. This is called a LIFO (Last-in, First-out) data structure.
A Stack can have a fix size or can adjust its size dynamically.

![alt text](https://www.tutorialspoint.com/data_structures_algorithms/images/stack_representation.jpg "Max-Heap creation example")

### Basic operations

With the _push()_ operation you add a new element to the Stack which can be removed with the _pop()_ operation. It's
often useful if you implement several other operations to simplify the Stack management. There's a _peek()_ operation
which allows you to get the top element without removing it from the Stack. It's useful to check the state of the Stack
with _isFull()_ or _isEmpty()_ functions to prevent errors when inserting or deleting elements.

**_pop()_ operation example**

```java
public int pop() {
    if (!isEmpty()) {
        int data = stack[top--];
        return data;
    }
    System.out.println("Couldn't retrieve data because the stack is empty");
}
```
