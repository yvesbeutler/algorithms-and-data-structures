package collections;

import collections.interfaces.Locator;
import collections.interfaces.OrderedDictionary;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * @author yvesbeutler
 * Implementation of a Skip List
 */
public class MySkipList<K extends Comparable<? super K>, E> implements OrderedDictionary<K, E> {

    private static final double INDEX_PROBABILITY = 0.5;

    private K minKey;
    private K maxKey;

    private int height = 2;
    private int size;

    private Node topLeft;
    private Node topRight;
    private Node bottomLeft;
    private Node bottomRight;

    private class Node implements Locator<K,E> {
        Node prev,next,above,below; // neighbours
        Object owner = MySkipList.this;
        K key;
        E element;

        @Override
        public E getElement() {
            return element;
        }

        @Override
        public K key() {
            return key;
        }
    }

    private MySkipList(K min, K max){
        topLeft = new Node();
        topLeft.key = min;

        topRight = new Node();
        topRight.key = max;

        bottomLeft = new Node();
        bottomLeft.key = min;

        bottomRight = new Node();
        bottomRight.key = max;

        // connect edges
        topLeft.next = topRight;
        topRight.prev = topLeft;
        bottomLeft.next = bottomRight;
        bottomRight.prev = bottomLeft;

        topLeft.below = bottomLeft;
        topRight.below = bottomRight;
        bottomLeft.above = topLeft;
        bottomRight.above = topRight;

        minKey = min;
        maxKey = max;
    }

    public static void main(String[] args) {

        int n = 100;
        MySkipList<Integer, String> skipList = new MySkipList<>(Integer.MIN_VALUE,Integer.MAX_VALUE);
        Locator<Integer,String>[] locators = new Locator[n];

        Random r = new Random();


        for (int i=0; i < n; i++) {
            locators[i]=skipList.insert(r.nextInt(n),""+i);
        }

        Locator<Integer,String>[] ll = skipList.findAll(33);
        for (int i=0;i<ll.length;i++)System.out.println(ll[i].key());
//		System.out.println("height of index: "+sl.height);
//		Iterator<Locator<Integer,String>> it = sl.sortedLocators();
//		while (it.hasNext()){
//			Locator<Integer, String> loc = it.next();
//			System.out.println(loc.key()+" element: "+loc.element());
//		}
        skipList.print();
//		sl.remove(locators[15]);
//		sl.print();
//		Locator<Integer,String> loc = sl.closestBefore(83);
//		if (loc!= null)System.out.println(loc.key()+":"+loc.element());
    }




    @Override
    public int size() {
        return size;
    }

    @Override
    public Locator<K, E> find(K key) {
        if (key.compareTo(minKey)<=0) throw new RuntimeException("key not bigger than minKey!");
        if (key.compareTo(maxKey)>=0) throw new RuntimeException("key not smaller than maxKey!");
        Node pos = search(key);
        if (pos.key.compareTo(key)!=0) return null; // we found nothing
        else {
            // we take the leftmost Locator with valid key
            while (pos.prev.key.compareTo(key)== 0) pos=pos.prev;
            return pos;
        }
    }

    private Node search(K key){
        // returns a node with key 'key'.
        // if no such element is found the
        // node previous to the insertion point.
        Node n = topLeft;
        //......
        return n;
    }



    @Override
    public Locator<K, E>[] findAll(K key) {
        Node n = (Node) find(key); // returns the leftmost occurrence
        if (n==null) return new Locator[0];
        ArrayList<Locator<K,E>> al = new ArrayList<>();
        while(n.key.compareTo(key)==0){
            al.add(n);
            n=n.next;
        }
        return al.toArray(new Locator[0]);
    }



    @Override
    public Locator<K, E> insert(K key, E o) {
        if (key.compareTo(minKey)<=0) throw new RuntimeException("key not bigger than minKey!");
        if (key.compareTo(maxKey)>=0) throw new RuntimeException("key not smaller than maxKey!");
        Node pos = search(key);
        // we take the rightmost Locator with valid key
        while (pos.next.key.compareTo(key)== 0) pos=pos.next;
        // now we want to insert a node at the position pos.next:
        Node nNew = new Node();
        //........
        return nNew;
    }

    private void expand(){
        // adds one index level
        // System.out.println("expanding..");
        Node n1 = new Node();
        n1.key = minKey;
        Node n2 = new Node();
        n2.key = maxKey;
        n1.next = n2;
        n2.prev = n1;

        n1.below = topLeft;
        n2.below = topRight;

        topLeft.above = n1;
        topRight.above = n2;

        topLeft = n1;
        topRight = n2;
        height++;
    }


    @Override
    public void remove(Locator<K, E> loc) {
        Node n = (Node) loc;
        if (n.owner != this) throw new RuntimeException("invalid locator "+loc.key());
        n.owner=null;
        int lev=0;
        while (n!=null){
            n.prev.next=n.next;
            n.next.prev=n.prev;
            n=n.above;
            lev++;
        }
        if (lev==height-1) shrink();
        size--;
    }



    private void shrink() {
        // System.out.println("shrink called");
        while (height>2 && topLeft.below.next==topRight.below){
            // System.out.println("shrinking...");
            topLeft = topLeft.below;
            topRight = topRight.below;
            topLeft.above = null;
            topRight.above = null;
            height--;
        }
    }



    @Override
    public Locator<K, E> closestBefore(K key) {
        if (key.compareTo(minKey)<=0) throw new RuntimeException("key not bigger than minKey!");
        if (key.compareTo(maxKey)>=0) throw new RuntimeException("key not smaller than maxKey!");
        Node pos = search(key);
        int comp = key.compareTo(pos.key);
        if (comp==0){
            pos = pos.prev;
            // still equal?
            if (pos == bottomLeft) return null;
            while (key.compareTo(pos.key)==0) pos=pos.prev;
        }
        else if (comp>0){
            // in case we have sevearal equal keys take the rightmost locator
            while (pos.key.compareTo(pos.next.key)==0) pos=pos.next;
            if (pos == bottomLeft) pos = null;

        }
        else
            throw new RuntimeException("should never happen!");
        return pos;

    }


    @Override
    public Locator<K, E> closestAfter(K key) {
        if (key.compareTo(minKey)<=0) throw new RuntimeException("key not bigger than minKey!");
        if (key.compareTo(maxKey)>=0) throw new RuntimeException("key not smaller than maxKey!");
        Node pos = search(key);
        int comp = key.compareTo(pos.key);
        if (comp==0){
            pos = pos.next;
            // still equal?
            while (key.compareTo(pos.key)==0) pos=pos.next;
            if (pos == bottomRight) pos = null;
        }
        else if (comp>0){
            // in case we have several equal keys take the rightmost locator
            while (pos.key.compareTo(pos.next.key)==0) pos=pos.next;
            // the next key is bigger than 'key'
            pos = pos.next;
            if (pos == bottomRight) pos = null;

        }
        else throw new RuntimeException("should never happen!");
        return pos;
    }



    @Override
    public Locator<K, E> next(Locator<K, E> loc) {
        Node n = (Node) loc;
        if (n.owner != this) throw new RuntimeException("invalid locator "+loc.key());
        n = n.next;
        if (n==bottomRight) n=null;
        return n;
    }



    @Override
    public Locator<K, E> previous(Locator<K, E> loc) {
        Node n = (Node) loc;
        if (n.owner != this) throw new RuntimeException("invalid locator "+loc.key());
        n = n.prev;
        if (n==bottomLeft) n=null;
        return n;
    }



    @Override
    public Locator<K, E> min() {
        if (size>0) return bottomLeft.next;
        else return null;
    }



    @Override
    public Locator<K, E> max() {
        if (size>0) return bottomRight.prev;
        else return null;
    }



    @Override
    public Iterator<Locator<K, E>> sortedLocators() {
        return new Iterator<Locator<K, E>>(){
            Node pos = bottomLeft.next;
            @Override
            public boolean hasNext() {
                return pos != bottomRight;
            }

            @Override
            public Locator<K, E> next() {
                Node ret = pos;
                pos = pos.next;
                return ret;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException(
                        "use remove method of MySkipList!");
            }

        };
    }

    private void print(){
        System.out.println("-------start------");
        Node n = bottomLeft;
        n=n.next;
        StringBuffer lev = new StringBuffer();
        while (n!=bottomRight){
            lev.delete(0,lev.length());
            Node m = n;
            int index = 0;
            while (m.above != null) {
                index++;
                m=m.above;
                lev.append("+");
            }
            while(index<height-2){
                index++;
                lev.append("|");
            }
            System.out.println(String.format("%11d", n.key())+lev.toString()+"    elem: "+n.element);
            n=n.next;
        }
        System.out.println("--------end-------");

    }
}