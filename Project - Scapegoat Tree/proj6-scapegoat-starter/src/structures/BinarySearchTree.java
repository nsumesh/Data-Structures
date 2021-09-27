package structures;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;





public class BinarySearchTree<T extends Comparable<T>> implements BSTInterface<T> {
  protected BSTNode<T> root;

  public boolean isEmpty() {
    return root == null;
  }

  public int size() {
    return subtreeSize(root);
  }

  protected int subtreeSize(BSTNode<T> node) {
    if (node == null) {
      return 0;
    } else {
      return 1 + subtreeSize(node.getLeft()) + subtreeSize(node.getRight());
    }
  }

  public boolean contains(T t) {
    // TODO: Implement the contains() method
    boolean check = false;
    if(get(t)!=null)
      check = true;
    return check;
  }

  public boolean remove(T t) {
    if (t == null) {
      throw new NullPointerException();
    }
    boolean result = contains(t);
    if (result) {
      root = removeFromSubtree(root, t);
    }
    return result;
  }

  protected BSTNode<T> removeFromSubtree(BSTNode<T> node, T t) {
    // node must not be null
    int result = t.compareTo(node.getData());
    if (result < 0) {
      node.setLeft(removeFromSubtree(node.getLeft(), t));
      return node;
    } else if (result > 0) {
      node.setRight(removeFromSubtree(node.getRight(), t));
      return node;
    } else { // result == 0
      if (node.getLeft() == null) {
        return node.getRight();
      } else if (node.getRight() == null) {
        return node.getLeft();
      } else { // neither child is null
        T predecessorValue = getHighestValue(node.getLeft());
        node.setLeft(removeRightmost(node.getLeft()));
        node.setData(predecessorValue);
        return node;
      }
    }
  }

  private T getHighestValue(BSTNode<T> node) {
    // node must not be null
    if (node.getRight() == null) {
      return node.getData();
    } else {
      return getHighestValue(node.getRight());
    }
  }

  private BSTNode<T> removeRightmost(BSTNode<T> node) {
    // node must not be null
    if (node.getRight() == null) {
      return node.getLeft();
    } else {
      node.setRight(removeRightmost(node.getRight()));
      return node;
    }
  }

  public T get(T t) {
    // TODO: Implement the get() method
    if(t==null)
      throw new NullPointerException();
    return getHelper(root, t);
  }

  private T getHelper(BSTNode<T> curNode, T value)
  {
    if(curNode==null)
      return null;
    int res = value.compareTo(curNode.getData());
    if(res==0)
      return curNode.getData();
    else if(res>0)
      return getHelper(curNode.getRight(),value);
    else
      return getHelper(curNode.getLeft(),value);
  }


  public void add(T t) {
    if (t == null) {
      throw new NullPointerException();
    }
    root = addToSubtree(root, new BSTNode<T>(t, null, null));
  }

  protected BSTNode<T> addToSubtree(BSTNode<T> node, BSTNode<T> toAdd) {
    if (node == null) {
      return toAdd;
    }
    int result = toAdd.getData().compareTo(node.getData());
    if (result <= 0) {
      node.setLeft(addToSubtree(node.getLeft(), toAdd));
    } else {
      node.setRight(addToSubtree(node.getRight(), toAdd));
    }
    return node;
  }

  @Override
  public T getMinimum() {
    // TODO: Implement the getMinimum() method
    if(root==null)
      return null;
    return getMinimumHelper(root);
  }

  private T getMinimumHelper(BSTNode<T> curNode)
  {
    if(curNode.getLeft()==null)
      return curNode.getData();
    else
      return getMinimumHelper(curNode.getLeft());
  }
  @Override
  public T getMaximum() {
    // TODO: Implement the getMaximum() method
    if(root==null)
      return null;
    return getMaximumHelper(root);
  }

  private T getMaximumHelper(BSTNode<T> curNode)
  {
    if(curNode.getRight()==null)
      return curNode.getData();
    else 
      return getMaximumHelper(curNode.getRight()); 
  }


  @Override
  public int height() {
    // TODO: Implement the height() method
    return heightHelper(root);
  }

  private int heightHelper(BSTNode<T> curNode)
  {
    if(curNode==null)
      return -1;
    else
    {
      int leftVal = heightHelper(curNode.getLeft());
      int rightVal = heightHelper(curNode.getRight());
      return 1 + Math.max(leftVal, rightVal);
    }
    
  }

  public Iterator<T> preorderIterator() {
    // TODO: Implement the preorderIterator() method
    Queue <T> queue = new LinkedList<T>();
    preOrderTraverse(queue,root);
    return queue.iterator();
  }

  private void preOrderTraverse(Queue<T> queue, BSTNode<T>node)
  {
    if(node!=null)
    {
      queue.add(node.getData());
      preOrderTraverse(queue, node.getLeft());
      preOrderTraverse(queue, node.getRight());
    }
  }


  public Iterator<T> inorderIterator() {
    Queue<T> queue = new LinkedList<T>();
    inorderTraverse(queue, root);
    return queue.iterator();
  }


  private void inorderTraverse(Queue<T> queue, BSTNode<T> node) {
    if (node != null) {
      inorderTraverse(queue, node.getLeft());
      queue.add(node.getData());
      inorderTraverse(queue, node.getRight());
    }
  }

  public Iterator<T> postorderIterator() {
    // TODO: Implement the postorderIterator() method
    Queue <T> queue = new LinkedList<T>();
    postorderTraverse(queue,root);
    return queue.iterator();
  }


  private void postorderTraverse(Queue <T> queue, BSTNode<T> node)
  {
    if(node!=null)
    {
      postorderTraverse(queue, node.getLeft());
      postorderTraverse(queue, node.getRight());
      queue.add(node.getData());
    }
  }

  @Override
  public boolean equals(BSTInterface<T> other) {
    // TODO: Implement the equals() method
    boolean check = false;
    if(other==null)
      throw new NullPointerException();
    if(equalsHelper(root,other.getRoot())==true)
      check = true;
    return check;
  }

  private boolean equalsHelper(BSTNode<T> curNode1, BSTNode<T> curNode2)
  {
    boolean check = false;
    if(curNode1==null && curNode2==null)
      check = true;
    else if(curNode1==null || curNode2==null)
      check = false;
    else
    {
      if(curNode1.getData().compareTo(curNode2.getData())!=0)
        check = false;
      return equalsHelper(curNode1.getLeft(), curNode2.getLeft()) && equalsHelper(curNode1.getRight(), curNode2.getRight());
    }
    return check;
    
  }

  @Override
  public boolean sameValues(BSTInterface<T> other) {
    // TODO: Implement the sameValues() method
    Iterator<T> one = this.inorderIterator();
    Iterator<T> two = other.inorderIterator();
    while(one.hasNext()==true && two.hasNext()==true)
    {
      if(one.next().equals(two.next())==false)
        return false;
    }
    return one.hasNext()==false && two.hasNext()==false;
  }

  @Override
  public boolean isBalanced() {
    // TODO: Implement the isBalanced() method
    boolean check = false;
    if((Math.pow(2,height())<=size()) && (size()<Math.pow(2, height()+1)))
      check = true;
    return check;
  }

  @Override
  @SuppressWarnings("unchecked")
  public void balance() {
    // TODO: Implement the balanceHelper() method
    Iterator<T> iter1 = this.inorderIterator();
    T[] arr = (T[]) new Comparable[size()];
    int a = 0;
    while(iter1.hasNext()==true)
    {
      arr[a] = iter1.next();
      a++;
    }
      
    root = arrayToBinarySearchTree(0, arr.length-1, arr);
  }

  private BSTNode<T> arrayToBinarySearchTree(int lowerLimit, int upperLimit, T[] array)
  {
    if(lowerLimit>upperLimit)
      return null;
    else
    {
      int midpoint = (lowerLimit + upperLimit)/2;
      BSTNode<T> newNode = new BSTNode<T>(array[midpoint], arrayToBinarySearchTree(lowerLimit, midpoint-1, array), arrayToBinarySearchTree(midpoint+1, upperLimit, array));
      return newNode;
    }
  }

  @Override
  public BSTNode<T> getRoot() {
    // DO NOT MODIFY
    return root;
  }

  public static <T extends Comparable<T>> String toDotFormat(BSTNode<T> root) {
    // header
    int count = 0;
    String dot = "digraph G { \n";
    dot += "graph [ordering=\"out\"]; \n";
    // iterative traversal
    Queue<BSTNode<T>> queue = new LinkedList<BSTNode<T>>();
    queue.add(root);
    BSTNode<T> cursor;
    while (!queue.isEmpty()) {
      cursor = queue.remove();
      if (cursor.getLeft() != null) {
        // add edge from cursor to left child
        dot += cursor.getData().toString() + " -> " + cursor.getLeft().getData().toString() + ";\n";
        queue.add(cursor.getLeft());
      } else {
        // add dummy node
        dot += "node" + count + " [shape=point];\n";
        dot += cursor.getData().toString() + " -> " + "node" + count + ";\n";
        count++;
      }
      if (cursor.getRight() != null) {
        // add edge from cursor to right child
        dot +=
            cursor.getData().toString() + " -> " + cursor.getRight().getData().toString() + ";\n";
        queue.add(cursor.getRight());
      } else {
        // add dummy node
        dot += "node" + count + " [shape=point];\n";
        dot += cursor.getData().toString() + " -> " + "node" + count + ";\n";
        count++;
      }
    }
    dot += "};";
    return dot;
  }

  public static void main(String[] args) {
    for (String r : new String[] {"a", "b", "c", "d", "e", "f", "g"}) {
      BSTInterface<String> tree = new BinarySearchTree<String>();
      for (String s : new String[] {"d", "b", "a", "c", "f", "e", "g"}) {
        tree.add(s);
      }
      Iterator<String> iterator = tree.inorderIterator();
      while (iterator.hasNext()) {
        System.out.print(iterator.next());
      }
      System.out.println();
      iterator = tree.preorderIterator();
      while (iterator.hasNext()) {
        System.out.print(iterator.next());
      }
      System.out.println();
      iterator = tree.postorderIterator();
      while (iterator.hasNext()) {
        System.out.print(iterator.next());
      }
      System.out.println();

      System.out.println(tree.remove(r));

      iterator = tree.inorderIterator();
      while (iterator.hasNext()) {
        System.out.print(iterator.next());
      }
      System.out.println();
    }

    BSTInterface<String> tree = new BinarySearchTree<String>();
    for (String r : new String[] {"a", "b", "c", "d", "e", "f", "g"}) {
      tree.add(r);
    }
    System.out.println(toDotFormat(tree.getRoot()));
    System.out.println(tree.size());
    System.out.println(tree.height());
    System.out.println(tree.isBalanced());
    tree.balance();
    System.out.println(tree.size());
    System.out.println(tree.height());
    System.out.println(tree.isBalanced());
  }
}
