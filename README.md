# 430.-Flatten-a-Multilevel-Doubly-Linked-List
You are given a doubly linked list, which contains nodes that have a next pointer, a previous pointer, and an additional child pointer. This child pointer may or may not point to a separate doubly linked list, also containing these special nodes. These child lists may have one or more children of their own, and so on, to produce a multilevel data structure as shown in the example below.

Given the head of the first level of the list, flatten the list so that all the nodes appear in a single-level, doubly linked list. Let curr be a node with a child list. The nodes in the child list should appear after curr and before curr.next in the flattened list.

Return the head of the flattened list. The nodes in the list must have all of their child pointers set to null.

 

## Example 1:
![image](https://github.com/user-attachments/assets/6c34a5a5-d256-4366-b833-1f9bda031e50)

```
Input: head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
Output: [1,2,3,7,8,11,12,9,10,4,5,6]
Explanation: The multilevel linked list in the input is shown.
After flattening the multilevel linked list it becomes:

```
![image](https://github.com/user-attachments/assets/01a2f34f-a5c9-4874-9c0a-f39d1be13dc1)
-----------------------------------------------------------------------------------------
## Example 2:
![image](https://github.com/user-attachments/assets/62e7be86-caeb-4b4e-8415-dc01864222d8)

```
Input: head = [1,2,null,3]
Output: [1,3,2]
Explanation: The multilevel linked list in the input is shown.
After flattening the multilevel linked list it becomes:
```
![image](https://github.com/user-attachments/assets/45434560-3659-4bc0-ae86-ee2d261420db)
---------------------------------------------------------------------------------------
## Example 3:

```

Input: head = []
Output: []
Explanation: There could be empty list in the input.
 ```

Constraints:

The number of Nodes will not exceed 1000.
1 <= Node.val <= 105
 

How the multilevel linked list is represented in test cases:

We use the multilevel linked list from Example 1 above:
```
 1---2---3---4---5---6--NULL
         |
         7---8---9---10--NULL
             |
             11--12--NULL
```
The serialization of each level is as follows:
```
[1,2,3,4,5,6,null]
[7,8,9,10,null]
[11,12,null]
```
To serialize all levels together, we will add nulls in each level to signify no node connects to the upper node of the previous level. The serialization becomes:
```
[1,    2,    3, 4, 5, 6, null]
             |
[null, null, 7,    8, 9, 10, null]
                   |
[            null, 11, 12, null]
```
Merging the serialization of each level and removing trailing nulls we obtain:
```
[1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
```

### Code Recap
The code uses a **stack** to handle child nodes. The key steps are:
1. Traverse the main list using a pointer (`cur`).
2. When encountering a node with a `child`, push the `next` node (if it exists) onto the stack.
3. Link the `child` list into the main list by updating the `next` and `prev` pointers.
4. After traversing the main list, process the nodes in the stack to append any remaining parts of the list.

---

### Time Complexity Analysis

#### Key Observations:
1. Each node in the multilevel doubly linked list is visited exactly **once** during the traversal.
   - When processing a node, we either:
     - Move to its `next` node, or
     - Process its `child` list and push the `next` node onto the stack.
2. Nodes pushed onto the stack are processed later, but they are still visited only once when popped from the stack.

#### Total Work Done:
- Each node is visited once during the traversal.
- Pushing and popping from the stack are **O(1)** operations.

Thus, the total time complexity is proportional to the total number of nodes in the multilevel doubly linked list.

#### Final Time Complexity:
$$
\boxed{O(N)}
$$
Where $N$ is the total number of nodes in the multilevel doubly linked list.

---

### Space Complexity Analysis

#### Key Observations:
1. The space usage is determined by the **stack** used to store `next` nodes when encountering a `child`.
2. In the worst case, the depth of the stack corresponds to the maximum depth of the multilevel structure.
   - For example, if every node has a `child`, the stack will grow linearly with the depth of the hierarchy.

#### Worst Case:
- If the multilevel structure is deeply nested (e.g., each node has a `child`), the stack can grow up to $O(D)$, where $D$ is the maximum depth of the hierarchy.
- In the worst case, $D = N$, where $N$ is the total number of nodes.

#### Final Space Complexity:
$$
\boxed{O(D)}
$$
Where $D$ is the maximum depth of the multilevel structure. In the worst case, $D = O(N)$.

---

### Summary of Complexities

| **Metric**         | **Complexity**       |
|---------------------|----------------------|
| **Time Complexity** | $O(N)$             |
| **Space Complexity**| $O(D)$, where $D$ is the maximum depth of the multilevel structure. |

---

### Example Walkthrough

#### Input:
```
1 <-> 2 <-> 3 <-> 4 <-> 5 <-> 6
         |
         7 <-> 8 <-> 9 <-> 10
              |
              11 <-> 12
```

#### Steps:
1. Traverse the main list:  
   - At `3`, push `4` onto the stack and link the `child` list (`7 <-> 8 <-> 9 <-> 10`) into the main list.
   - At `8`, push `9` onto the stack and link the `child` list (`11 <-> 12`) into the main list.
2. After reaching the end of the main list, process the stack:
   - Pop `9` and append it to the list.
   - Pop `4` and append it to the list.

#### Output:
```
1 <-> 2 <-> 3 <-> 7 <-> 8 <-> 11 <-> 12 <-> 9 <-> 10 <-> 4 <-> 5 <-> 6
```

---

### Comparison with Recursive DFS

| Feature                     | Iterative Approach (Stack) | Recursive DFS Approach       |
|-----------------------------|----------------------------|------------------------------|
| **Time Complexity**         | $O(N)$                   | $O(N)$                      |
| **Space Complexity**        | $O(D)$                   | $O(D)$                      |
| **Code Simplicity**         | Slightly more verbose     | More intuitive and cleaner   |

Both approaches have the same asymptotic complexities, but the iterative approach avoids the risk of stack overflow for very deep hierarchies.

---

### Final Answer
The **time complexity** of the provided implementation is:
$$
\boxed{O(N)}
$$
The **space complexity** is:
$$
\boxed{O(D)}
$$
Where $N$ is the total number of nodes, and $D$ is the maximum depth of the multilevel structure.
