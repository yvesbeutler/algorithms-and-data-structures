# Linked List

**Link:** https://www.tutorialspoint.com/data_structures_algorithms/linked_list_algorithms.htm
___

### Basic structure

A Linked List is a sequence of data structures which are connected together via links. Each of these
items called Nodes store an object and a link to the next Node in the List. There are three types of
Linked Lists: The Simple-, Doubly- and the Circular Linked List. You can only navigate top-down on the
Simple Linked List and both ways on the Doubly Linked List. The Doubly Linked List contains a link to
the previous Node in addition to the next Node link. The Circular Linked List doesn't end so the last
Node's next link is pointing to the first Node again.

![alt text](https://www.tutorialspoint.com/data_structures_algorithms/images/linked_list.jpg "Max-Heap creation example")

### Basic operations

If you insert a Node B between Node A and Node C, then you have to remove A.next and set the pointer to
the inserted Node B. Repeat the same for C.prev and at the end you have to set both pointers of Node B.

If you remove a Node, then you have to revert the procedure of inserting a Node. If your Node is at the
beginning or at the end of the Linked List, then you have to take care of this.