# Data Structures
 
 
This is a collection of all the projects done in the Spring 2021 semester. Java was the main medium of Instruction for this Course

Project - RLE Encoding 
- For this project, you will implement Run Length Encoding, or RLE, an algorithm that encodes
  ascii data in a compressed format. Given a text file containing an image constructed with ascii
  characters, RLE will encode the file in compressed form. Given a file with data in RLE
  compressed form, you will be able to decompress the file and recover the exact image that was
  originally compressed. You will use arrays as the basic data structures to implement the RLE
  compression and decompression algorithms.
- Grade : 60/60

Project - Bucket Sort
- Most sorting algorithms you encounter are designed to be “general purpose” sorts, where any
		data type can be sorted as long as a method for comparing them is defined. In Java, this
		method is called “ compareTo ” in the Comparable interface. Any class that implements
		Comparable can be sorted because they can be compared.
		There are other sorting algorithms that do not rely on comparing data directly. These are not
		general purpose in the sense that they have to be customized for different types of data. In this
		project you will implement one of these sorting algorithms called Bucket Sort. The version of
		Bucket Sort will be customized to work with integers, but it could work with any symbolic
		language.
- Grade : 95/95

Project - Recursive Linked List
- Imagine you are an engineer of DroneDrop, a company that deploys drones to deliver life
		essentials in a post-apocalypse world, where everyone stays home because of biohazards	
		outside. The drones are assigned to different districts, each with a pre-calculated route to visit
		all households. In order to provide fast and responsive customer services, the drone will update
		the route to skip those with no active order. Your goal is to provide a linear data structure that
		represents the route, and allows fast update, so we can beat our competitor. Luckily, some
		insider told us they use inefficient ArrayLists for the same job.
		In this project, you will write a linked-based implementation for the List abstract data type, as
		specified in ListInterface.java. You’ll be building a singly linked list that can act as a
		drone route. The requirement is all methods you implement must use recursion and you are
		NOT allowed to use any loop (such as for, while, do-while). You must also stay within
		Big-O runtime bounds so that the drones are efficient in delivering life essentials.
- Grade : 150/150

Project - Postfix Evaluator
- For this assignment, you will implement an evaluator for postfix expressions. Your evaluator
		will be stack-based, and capable of evaluating correctly formed but otherwise arbitrary
		arithmetic expressions on integers. You will implement the stack using a linked list.
- Grade : 50/50

Project - Scapegoat Tree
- In this course, you’ll learn about one or more of the self-balancing BSTs that are used in
		practice, such as AVL and red-black trees trees. In this project, we’ll focus on a simpler, but still
		reasonably effective form of self-balancing tree: the scapegoat tree.
		In this assignment, you’ll start with a codebase for binary search trees similar to that presented
		in lecture. You’ll need to implement several additional methods for binary search trees. Then,
		you’ll subclass the binary search tree to create a “scapegoat tree” — a simple form of a
		self-balancing binary search tree.
- Grade : 109/115

Project - Priority Queue (Triage Data)
- For this project, you will implement a futuristic automated emergency room (ER) triage system.
		Your system will receive data in the form of patient and injury pairs over time and automatically
		assign patients to doctors as the resources become available depending on the severity of the
		patient’s injury. To accomplish this, you will implement a data structure called a priority queue –
		more specifically, a heap-based priority queue. A priority queue is similar to a stack or a queue
		in that it stores and serves data in a specific order. It differs from these two data structures in
		that the order of data insertion does not affect the order of data serving (though the data clearly
		must be added to the structure before it can be served). Instead, the order of data serving is
		based on a priority of each datum – in this scenario, the priority of each patient is
		commensurate with the severity of their injury or age of the patient (depending on the
		comparator being used). Importantly, a priority queue accomplishes this task in an efficient way.
		Despite this specific setting, your implementation will be generic enough to work with all types of
		data and could be applied to other real world problems.
- Grade : 65/65

Project - Graph Coloring
- Many real-world problems can be represented by graphs and solved by applying various
		algorithms to the graph (GPS navigation, utility networks, social networks are just some of the
		systems that rely on graphs). In this assignment, you will implement a graph structure and a
		graph coloring algorithm. You will then use the graph and graph-coloring algorithm to solve
		various problems involving conflict resolution and finding an optimal assignment of resources to
		accomplish a task or goal.
- Grade : 48/48
