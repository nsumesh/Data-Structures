package structures;



public class ScapegoatTree<T extends Comparable<T>> extends BinarySearchTree<T> {
  private int upperBound;


  @Override
  public void add(T t) {
    // TODO: Implement the add() method
    if(t==null)
      throw new NullPointerException();
    upperBound = upperBound + 1;
    BSTNode<T> newNode = new BSTNode<T>(t, null, null);
    root = addToSubtree(root, newNode);
    if(height()>Math.log(upperBound) / Math.log(3/2))
    {
      BSTNode<T> child = newNode;
      BSTNode<T> w = newNode.parent;
      while((subtreeSize(child)/subtreeSize(w))<=((double)(2/3)))
      {
        w = w.parent;
        child = child.parent;
      }
      ScapegoatTree<T> subTree = new ScapegoatTree<T>();
      subTree.root = w;
      BSTNode<T> Parent = w.parent;
      subTree.balance();
      if(Parent.getLeft()==w)
        Parent.setLeft(w);
      else
        Parent.setRight(w);
    }
  }

  @Override
  public boolean remove(T element) {
    // TODO: Implement the remove() method
    if(element==null)
      throw new NullPointerException();
    boolean check = contains(element);
    if(check==true)
      root = removeFromSubtree(root,element);
    if(upperBound>(size()*2))
    {
      balance();
      upperBound = size();
    }
    return check;
  }

  public static void main(String[] args) {
    BSTInterface<String> tree = new ScapegoatTree<String>();
  
    for (String r : new String[] {"0", "1", "2", "3", "4"}) {
      tree.add(r);
      System.out.println(toDotFormat(tree.getRoot()));
    }
    
  }
}
