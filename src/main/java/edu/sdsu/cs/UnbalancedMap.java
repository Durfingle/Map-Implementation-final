package edu.sdsu.cs;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

/**
 * Micheal Kemper
 *
 *
 * Juan Pina-Sanz
 * cssc0835
 *
 * @param <K>
 * @param <V>
 */

public class UnbalancedMap <K extends Comparable<K>,V> implements IMap<K,V>
{
    int size = 0;
    private class Node {
        K key;
        V value;
        Node left;
        Node right;

        public Node (K key, V value){
            this.key = key;
            this.value = value;
        }
    }

    Node root;

    UnbalancedMap() {

    }

    UnbalancedMap(IMap iMap) {

    }

    public static void main( String[] args )
    {
        UnbalancedMap unbalancedMap = new UnbalancedMap();
        unbalancedMap.add(23,1);
        unbalancedMap.add(17,1);
        unbalancedMap.add(13,1);
        unbalancedMap.add(25,1);
        unbalancedMap.add(5,1);
        unbalancedMap.add(3,1);
        unbalancedMap.add(2,1);
        unbalancedMap.add(27,1);
        unbalancedMap.add(24,1);
        unbalancedMap.add(18,1);
        unbalancedMap.add(15,1);
        unbalancedMap.add(14,1);
        unbalancedMap.add(16,1);
        unbalancedMap.add(28,1);
        unbalancedMap.add(29,1);
        unbalancedMap.add(30,1);
        Iterable<Integer> it = unbalancedMap.keyset();
        for (Integer integer: it) {
            System.out.println(integer);
        }
    }

    @Override
    public boolean contains(K key) {
        return false;
    }

    @Override
    public boolean add(K key, V value) {
        size++;
        return insert(key, value, this.root);
    }

    private boolean insert(K key, V value, Node n) {
        if (this.root == null) {
            this.root = new Node(key, value);
            return true;
        } else if ((Integer)key < (Integer)n.key) {
            if (n.left != null) {
                insert(key, value, n.left);
            } else {
                n.left = new Node(key, value);
                return true;
            }
        } else if ((Integer)key > (Integer)n.key) {
            if (n.right != null) {
                insert(key, value, n.right);
            } else {
                n.right = new Node(key, value);
                return true;
            }
        }
        return false;
    }

    @Override
    public V delete(K key) {
        if ( root == null){
            return null;
        }
        Node curr = root;
        while(curr.key.compareTo(key) != 0){
            if(curr.key.compareTo(key) < 0){
                curr = curr.left;

            }
            else{
                curr = curr.right;
            }
        }

        return null;
    }

    @Override
    public V getValue(K key) {
        if(root == null){
            return null;
        }

        Node curr = root;

        while(curr.key.compareTo(key) != 0){
            if(key.compareTo(curr.key)< 0){
                curr = curr.left;
            }else{
                curr = curr.right;
            }
            if (curr == null){
                return null;
            }
        }
        return curr.value;
    }

    @Override
    public K getKey(V value) {
        return null;
    }

    @Override
    public Iterable<K> getKeys(V value) {
        return null;
    }

    @Override
    public int size() {

        Node curr = root;
        if( curr == null){
            return 0;
        }else{
            root = curr.left;
            int leftSize = size();

            root = curr.right;
            int rightSize = size();

            root = curr;
            return leftSize + rightSize + 1;
        }
    }

    @Override
    public boolean isEmpty() {
        return root==null;
    }

    @Override
    public void clear() {

    }

    @Override
    public Iterable<K> keyset() {
        LinkedList<K> linkedList = new LinkedList<K>();
        linkedList = findAscendingOrder(root, linkedList);
        return linkedList;
    }

    private LinkedList<K> findAscendingOrder(Node node, LinkedList<K> linkedList) {

        if (this.root==null) {
            return linkedList;
        }
        if (node.right != null) {
            linkedList = findAscendingOrder(node.right, linkedList);
            linkedList.addFirst(node.key);
        }
        if (node.left != null) {
            if (node.right == null) {
                linkedList.addFirst(node.key);
            }
            linkedList = findAscendingOrder(node.left, linkedList);
        } else {
            if (!linkedList.contains(node.key))
                linkedList.addFirst(node.key);
        }
        //linkedList.addFirst(node.key);
        return linkedList;
    }

    @Override
    public Iterable<V> values() {
        LinkedList<V> valuesList = new LinkedList<V>();
        LinkedList<K> keyList = new LinkedList<>();
        for(K key: keyset()){
            keyList.add(key);
        }

        for(Object o: keyList){
            valuesList.add(keyList.indexOf(o),(V)o);
        }
        return valuesList;
    }

}




