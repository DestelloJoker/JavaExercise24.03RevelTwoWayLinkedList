/*Program Name: Exercise24_03.java
* called Exercise24_03 removes the hard coded element 2 addition
* and 3 removal and then this one that follows the purpose
 * Authors: Austin P
 * Date last Updated: 9/14/2024
 * Purpose: You have to use the following template to submit to Revel.
   Note: To test the code using the CheckExerciseTool, you will submit entire code.
   To submit your code to Revel, you must only submit the code enclosed between
     // BEGIN REVEL SUBMISSION

     // END REVEL SUBMISSION
*/ 
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.Collection;

public class Exercise24_03 {
    public static void main(String[] args) {
        new Exercise24_03();
    }

    public Exercise24_03() {
        TwoWayLinkedList<Double> list = new TwoWayLinkedList<>();
        System.out.print("Enter five numbers: ");
        Scanner input = new Scanner(System.in);

        for (int i = 0; i < 5; i++) {
            list.add(input.nextDouble());  // Add the inputs directly to the list
        }
        input.close();

        // Print the list in forward order
        ListIterator<Double> iterator1 = list.listIterator();
        System.out.print("The list in forward order: ");
        while (iterator1.hasNext()) {
            System.out.print(iterator1.next() + " ");
        }

        // Print the list in backward order
        ListIterator<Double> iterator2 = list.listIterator(list.size());
        System.out.print("\nThe list in backward order: ");
        while (iterator2.hasPrevious()) {
            System.out.print(iterator2.previous() + " ");
        }
    }
}
interface MyList<E> extends java.util.Collection<E> {
    public void add(int index, E e);
    public E get(int index);
    public int indexOf(Object e);
    public int lastIndexOf(E e);
    public E remove(int index);
    public E set(int index, E e);

    @Override
    public default boolean add(E e) {
        add(size(), e);
        return true;
    }

    @Override
    public default boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public default boolean remove(Object e) {
        if (indexOf(e) >= 0) {
            remove(indexOf(e));
            return true;
        } else
            return false;
    }

    @Override
    public default boolean containsAll(Collection<?> c) {
        return true;
    }

    @Override
    public default boolean addAll(Collection<? extends E> c) {
        return true;
    }

    @Override
    public default boolean removeAll(Collection<?> c) {
        return true;
    }

    @Override
    public default boolean retainAll(Collection<?> c) {
        return true;
    }

    @Override
    public default Object[] toArray() {
        return null;
    }

    @Override
    public default <T> T[] toArray(T[] array) {
        return null;
    }
}

class TwoWayLinkedList<E> implements MyList<E> {
    private Node head, tail;  // Updated: No <E> for inner Node class
    private int size = 0;

    public TwoWayLinkedList() {
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(Object e) {
        Node current = head;
        while (current != null) {
            if (current.element.equals(e)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);

        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.element;
    }

    @Override
    public int indexOf(Object e) {
        Node current = head;
        int index = 0;
        while (current != null) {
            if (current.element.equals(e)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(E e) {
        Node current = tail;
        int index = size - 1;
        while (current != null) {
            if (current.element.equals(e)) {
                return index;
            }
            current = current.previous;
            index--;
        }
        return -1;
    }

    @Override
    public E set(int index, E e) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);

        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        E oldElement = current.element;
        current.element = e;
        return oldElement;
    }

    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    public ListIterator<E> listIterator() {
        return new LinkedListIterator();
    }

    public ListIterator<E> listIterator(int index) {
        return new LinkedListIterator(index);
    }

    private class LinkedListIterator implements ListIterator<E> {
        private Node current;

        public LinkedListIterator() {
            current = head;
        }

        public LinkedListIterator(int index) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            E e = current.element;
            current = current.next;
            return e;
        }

        @Override
        public boolean hasPrevious() {
            return current != null && current.previous != null;
        }

        @Override
        public E previous() {
            E e = current.element;
            current = current.previous;
            return e;
        }

        @Override
        public int nextIndex() {
            return 0; // Left as an exercise
        }

        @Override
        public int previousIndex() {
            return 0; // Left as an exercise
        }

        @Override
        public void remove() {
            // Left as an exercise
        }

        @Override
        public void set(E e) {
            current.element = e;
        }

        @Override
        public void add(E e) {
            // Left as an exercise
        }
    }

    private class Node {
        E element;
        Node next;
        Node previous;

        public Node(E e) {
            element = e;
        }
    }

    // BEGIN REVEL SUBMISSION

// Removes the element at the specified index in the list and returns the removed element
 // This handles all removal operations for the beginning, middle, and or end of the list
 @Override
 public E remove(int index) {
     if (index < 0 || index >= size)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);

     if (index == 0) {
         return removeFirst();
     } else if (index == size - 1) {
         return removeLast();
     } else {
         Node current = head;
         for (int i = 0; i < index; i++) {
             current = current.next;
         }

         current.previous.next = current.next;
         current.next.previous = current.previous;
         size--;
         return current.element;
     }
 }

 /*
  * RemoveFirst and RemoveLast do the same thing but RemoveFirst is for the head and RemoveLast is for the tail
  * The code first stores the element of the current head/tail node, then checks if the list has only one element
  * If the list has more than one element, uopdate the head/tail node to the next node and then set the new head's/tail's 
  * previous reference to null then return the removed element after decrementing the size of the list.
  */
 public E removeFirst() {
     if (size == 0) return null;

     E element = head.element;
     head = head.next;
     if (head != null) {
         head.previous = null;
     } else {
         tail = null;
     }
     size--;
     return element;
 }

 public E removeLast() {
     if (size == 0) return null;

     E element = tail.element;
     tail = tail.previous;
     if (tail != null) {
         tail.next = null;
     } else {
         head = null;
     }
     size--;
     return element;
 }

 public void addFirst(E e) {
     // Create a new node with the given element e
     Node newNode = new Node(e);
     if (size == 0) {
         // Set both the head and tail to the new node
         head = tail = newNode;
     } else {
         // Point the new node to the current head
         // Then update the current head's reference to the new node
         // and update the head to the new node
         newNode.next = head;
         head.previous = newNode;
         head = newNode;
     }
     size++;
 }

 public void addLast(E e) {
     // This does the same thing as the above addFirst method but for the tail
     Node newNode = new Node(e);
     if (size == 0) {
         head = tail = newNode;
     } else {
         newNode.previous = tail;
         tail.next = newNode;
         tail = newNode;
     }
     size++;
 }

 @Override
 public void add(int index, E e) {
     if (index < 0 || index > size)
         // Checks if index is out of bounds and throws an exception
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);


     // If the index is equal to 0, add it to the beginning
     // If the index is equal to the size, add it to the end
     // Otherwise add the element at the specified index
     if (index == 0) {
         addFirst(e);
     } else if (index == size) {
         addLast(e);
     } else {
         Node current = head;
         for (int i = 0; i < index; i++) {
             current = current.next;
         }

         /* Create a new node with the given element e
          * New node is next to current node
          * New node is equal to the previous of the current node
          * Update the current node previous reference to the new node
          * Link the current node next to the new node
          */
         Node newNode = new Node(e);
         newNode.next = current;
         newNode.previous = current.previous;
         current.previous.next = newNode;
         current.previous = newNode;
         size++;
     }
 }
// END REVEL SUBMISSION

}
