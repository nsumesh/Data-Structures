package app;

import java.util.Iterator;


public class RecursiveList<T> implements ListInterface<T> {

  private int size;
  private Node<T> head = null;

  public RecursiveList() {
    this.head = null;
    this.size = 0;
  }

  public RecursiveList(Node<T> first) {
    this.head = first;
    this.size = 1;
  }

  @Override
  public int size() {
    return size;
  }

  
  public void insertFirst(T elem) {
    if(elem==null)
    {
      throw new NullPointerException();
    }
    Node<T> cNode = new Node<T>(elem,head);
    head = cNode;
    size++;

}

@Override
public void insertLast(T elem) {
  if(elem==null)
  {
    throw new NullPointerException();
  }
  if(head==null)
  {
      Node <T> current = new Node<T>(elem,null);
      head = current;
      size++;
  }
  else
    InsertLastHelper(head, elem);
}

private void InsertLastHelper(Node <T> temp, T elem)
{
    if(temp.getNext()==null)
    {
      Node <T> cNode = new Node<T>(elem,null);
      temp.setNext(cNode);
      size++;
    }
    else
    {
      InsertLastHelper(temp.getNext(),elem);
    }
}

@Override
public void insertAt(int index, T elem) {
  int a = 0;
    if(elem==null)
    {
      throw new NullPointerException();
    }

    if(index<0 || index>size())
    {
      throw new IndexOutOfBoundsException();
    }
    
   if(isEmpty())
   {
      Node <T> curNode = new Node<T>(elem,null);
      head = curNode;
      size++;
      return;
   }
   InsertAtHelper(index, head, elem, a);
}

private void InsertAtHelper(int ind, Node<T> temp, T elem, int a)
{
  if(ind==0)
  {
    Node <T> cNode = new Node<T>(elem, head);
    head = cNode;
    size++;
    return;
  }
  if((a+1)==ind)
  {
    Node<T> curNode = new Node<T>(elem,temp.getNext());
    temp.setNext(curNode);
    size++;
    return;
  }
  else
  {
    a++;
    InsertAtHelper(ind, temp.getNext(), elem, a);
  }
}

@Override
public T removeFirst() {
  T removedItem = null;
  if(isEmpty())
  {
    throw new IllegalStateException();
  }
  else
  {
    removedItem = head.getData();
    head = head.getNext();
    size--;
  }
  return removedItem;
}

@Override
public T removeLast() {
  T removedItem = null;
  if(isEmpty())
  {
    throw new IllegalStateException();
  }
  if(head.getNext()==null)
  {
    removedItem = head.getData();
    head = null;
    size = 0;
    return removedItem;
  }
  removedItem = removeLastHelper(head);

  return removedItem;
}

private T removeLastHelper(Node <T> cur)
{
  T elem = null;
  if(cur==null)
    throw new NullPointerException();

  if(cur.getNext().getNext()==null)
  {
    elem = cur.getNext().getData();
    cur.setNext(cur.getNext().getNext());
    size--;
    return elem;
  }
  else
  {
    return removeLastHelper(cur.getNext());
  }
}

@Override
public T removeAt(int i) {
  T removedItem = null;
  if(i<0 || i>=(size()))
  {
    throw new IndexOutOfBoundsException();
  }
  if(isEmpty())
  {
    throw new NullPointerException();
  }
  int a = 0; 
  if(i==0)
  {
    removedItem = head.getData();
    head = head.getNext();
    size--;
    return removedItem;
  }
  else
  {
    removedItem = removeAtHelper(head, a, i);
  }

return removedItem;
}

private T removeAtHelper(Node <T> cur, int a, int ind)
{
  T elem = null;
  if(cur==null)
  {
    throw new NullPointerException();
  }
  if((a+1)==ind)
  {
    elem = cur.getNext().getData();
    cur.setNext(cur.getNext().getNext());
    size--;
    return elem;
  }
  else
  {
    a++;
    return removeAtHelper(cur.getNext(), a, ind);
  }
}



@Override
public T getFirst() {
  T item = null;
  if(head==null)
  {
    throw new IllegalStateException();
  }
  else
  {
    item = head.getData();
  }

  return item;
}



@Override
public T getLast() {
  T item = null;
  if(head==null)
  {
    throw new IllegalStateException();
  }

  item = getLastHelper(head);
  
  return item;
}

private T getLastHelper(Node <T> cur)
{
  T elem = null;
  if(cur.getNext()==null)
  {
    elem = cur.getData();
    return elem;
  }
  else
  {
    return getLastHelper(cur.getNext());
  }

}

@Override
public T get(int i) {
  T item = null;
  int a = 0;
  if(i<0 || i>=(size()))
  {
    throw new IndexOutOfBoundsException();
  }
  if(isEmpty()){
    throw new NullPointerException();
  }
  if(i==0)
    item = head.getData();
  else
    item = getHelper(i, head, a);

  return item;
}

private T getHelper(int a, Node <T> Temp, int i)
{
  if(Temp==null)
    throw new IndexOutOfBoundsException();
  if(i==a)
  {
    return Temp.getData();
  }
  else
  {
    i++;
    return getHelper(a, Temp.getNext(), i);
  }
}

@Override
public void remove(T elem) {
  if(elem==null)
  {
    throw new NullPointerException();
  }
  if(isEmpty())
  {
    throw new NullPointerException();
  }
  
  removeHelper(head, elem);
}

private void removeHelper(Node <T> cur, T elem)
{
  if(cur==null)
  {
    throw new ItemNotFoundException();
  }
  if(cur.getData()==elem && cur==head)
  {
    head = head.getNext();
    size--;
    return;
  } 
  if(cur.getNext()!=null && cur.getNext().getData()==elem)
  {
    cur.setNext(cur.getNext().getNext());
    size--;
    return;
  }
  removeHelper(cur.getNext(), elem);
}


@Override
public int indexOf(T elem) {
  int index = -1;
  int a = 0;
  if(elem==null)
    throw new NullPointerException();

  if(head.getData()==null)
    index = -1;
  index = indexOfHelper(head, elem, a); 
  return index;
}

private int indexOfHelper(Node <T> Temp, T element, int ind)
{
  if(element==null)
    throw new NullPointerException();
  if(Temp.getData()==element)
  {
      return ind;
  }
  if(Temp.getNext()==null)
    return -1;
  else
  {
    ind++;
    return indexOfHelper(Temp.getNext(), element, ind);
  }
}

@Override
public boolean isEmpty() {
  boolean empty = false;
  if(head==null)
    empty = true;
  else
    empty = false;
  return empty;
}

public Iterator<T> iterator() {
  Iterator<T> iter = new LinkedNodeIterator<T>(head);
  return iter;
}
}

