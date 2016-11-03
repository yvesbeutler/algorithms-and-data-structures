# AVL Tree

**Link:** https://www.tutorialspoint.com/data_structures_algorithms/avl_tree_algorithm.htm
___

### Performance problem of Binary Search Trees

The Binary Search Tree has great performance for mostly balanced tree structures. Balanced means that
the left side of the root node has approximately the same amount of nodes as the right side. But if
the input order is already sorted, the Binary Search Tree will grow only on one side. An unbalanced
Tree is the worst-case scenario for traversing through the tree.

### Balancing

Adelson, Velski & Landis (AVL) created a Binary Tree which is self-balancing. It changes parent nodes
if the height difference of the parents left sub-tree in comparison of its right sub-tree is more than 1.
The height difference is called the Balance Factor.

![alt text](https://www.tutorialspoint.com/data_structures_algorithms/images/unbalanced_avl_trees.jpg "Balance Factors")

### Rotations

To balance itself, the AVL Tree can use one of four rotations. Two are single rotations and the other two are
double rotations which change the Tree two times.

#### Left rotation

![alt text](https://www.tutorialspoint.com/data_structures_algorithms/images/avl_left_rotation.jpg "Left rotation")

#### Right rotation

![alt text](https://www.tutorialspoint.com/data_structures_algorithms/images/avl_right_rotation.jpg "Right rotation")


to be continued..
