# Heap

**Link:** https://www.tutorialspoint.com/data_structures_algorithms/heap_data_structure.htm
___

### Min- and Max-Heap

In a Heap the value of the root node is either greater or less than any of its children. If the
root node has the highest value, it's called a Max-Heap. Vice versa the Min-Heap has got a root
node less than or equal to any of its children.

### Construction Algorithm

Heaps are constructed by its order of arrival which means that every node is inserted separately.
After any insertion you have to check if the Heap is still valid and holds the Heap property. If
it's not valid, you have to swap the new node with its parent. You repeat this step until the Heap
property is again valid. Then you can add the next node.

### Deletion Algorithm

If you want to delete from a Heap you can only delete the root node which is either the greatest or
smallest node value in your Heap depending if it's a Max- or Min-Heap. After that you move the last
node of the last level to the root position. Now you compare the new root with its children and swap
positions if necessary. Repeat these steps until the Heap property is valid again.


**Example deletion of a Max-Heap**

Step 1 − Remove root node.

Step 2 − Move the last element of last level to root.

Step 3 − Compare the value of this child node with its parent.

Step 4 − If value of parent is less than child, then swap them.

Step 5 − Repeat step 3 & 4 until Heap property holds.
