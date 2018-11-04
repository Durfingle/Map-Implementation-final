package edu.sdsu.cs;

import sun.awt.image.ImageWatched;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

/**
 * Michael Kemper
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
        Iterable<K> iterable = iMap.keyset();
        for (K keyInIterable: iterable) {
            add(keyInIterable,(V)iMap.getValue(keyInIterable));
        }
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
        Iterable<Integer> val = unbalancedMap.values();
        for (Integer integer: val) {
            System.out.println(integer);
        }
    }

    @Override
    public boolean contains(K key) {
        Iterable<K> iterable = keyset();
        for (K keyInIterable: iterable) {
            if (keyInIterable.compareTo(key) == 0) {
                return true;
            }
        }
        return false;
//        Node curr = root;
//        if (curr == null)
//            return false;
//        while((K)curr.key != key){
//            if(curr != null){
//                if(((K) curr.key).compareTo(key) > 0){
//                    curr = curr.left;
//                    if(((K) curr.key).compareTo(key) == 0){
//                        return true;
//                    }
//                }else{
//                    curr = curr.right;
//                    if (curr == null){
//                        continue;
//                    }
//                    if(((K) curr.key).compareTo(key) == 0){
//                        return true;
//                    }
//                }
//            }
//            continue;
//        }
//        return false;
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
        } else if (key.compareTo(n.key) < 0) {
            if (n.left != null) {
                insert(key, value, n.left);
            } else {
                n.left = new Node(key, value);
                return true;
            }
        } else if (key.compareTo(n.key) > 0) {
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
        Node deletedNode = delete(key, root);
        if (deletedNode==null)
            return null;
        return deletedNode.value;
    }

    public Node delete(K key, Node node) {
        if ( root == null){
            return node;
        }

        if(key.compareTo(node.key) < 0) {
            node.left = delete(key, node.left);
        } else if(key.compareTo(node.key) > 0) {
            node.right = delete(key, node.right);
        } else if(node.left != null && node.right != null) {
            node.key = findSmallerNode(node.right);
            delete(node.key, node.right);
        } else {
//            if (node.left != null) {
//                node = node.left;
//            } else {
//                node = node.right;
//            }
            node  = (node.left != null) ? node.left : node.right;
        }
        return node;
    }

    private K findSmallerNode(Node node) {
        if (node.left == null) {
            return node.key;
        }
        else {
            return findSmallerNode(node.left);
        }
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
//        LinkedList<K> linkedList = new LinkedList<>();
//        linkedList = findAscendingOrder(root,linkedList);
//        return linkedList.contains(key);
        return null;
    }

    @Override
    public Iterable<K> getKeys(V value) {
        LinkedList keysList = new LinkedList();
        LinkedList<Node> nodeList = new LinkedList<Node>();
        nodeList = findAscendingOrder(root, nodeList);
        for (Node node: nodeList) {
            if (node.value.equals(value)) {
                keysList.add(node.key);
            }
        }
        return keysList;
    }

    @Override
    public int size() {
//        LinkedList<Node> nodeList = new LinkedList<Node>();
//        nodeList = findAscendingOrder(root, nodeList);
        return size(root);
    }

    private int size(Node node){
        if (node == null)
            return 0;
        if(node.left != null && node.right != null){
            return 1 + size(node.left) + size(node.right);
        }else if(node.left != null){
            return 1 + size(node.left);
        }else if(node.right != null){
            return 1 + size(node.right);
        }else{
            return 1;
        }
    }

    @Override
    public boolean isEmpty() {
        return root==null;
    }

    @Override
    public void clear() {
        this.root.left = null;
        this.root.right = null;
        this.root = null;
    }

    private K getMinKey(Node node){
        if(root == null){
            return null;
        }

        if(node.left == null){
            return node.key;
        } else {
            return getMinKey(node.left);
        }
    }

    private K getMaxKey(Node node){
        if (root == null){
            return null;
        }

        if(node.right == null){
            return node.key;
        }else{
            return getMaxKey(node);
        }
    }

    @Override
    public Iterable<K> keyset() {
        LinkedList<Node> nodeList = new LinkedList<Node>();
        nodeList = findAscendingOrder(root, nodeList);
        LinkedList<V> valuesList = new LinkedList<V>();
        LinkedList<K> keyList = new LinkedList<>();
        for(Node node: nodeList){
            keyList.add((K)node.key);
        }

        for(Object o: keyList){
            valuesList.add(keyList.indexOf(o),(V)o);
        }
        return keyList;
    }

    private LinkedList<Node> findAscendingOrder(Node node, LinkedList<Node> linkedList) {

        if (this.root==null) {
            return linkedList;
        }
        if (node.right != null) {
            linkedList = findAscendingOrder(node.right, linkedList);
            linkedList.addFirst(node);
        }
        if (node.left != null) {
            if (node.right == null) {
                linkedList.addFirst(node);
            }
            linkedList = findAscendingOrder(node.left, linkedList);
        } else {
            if (!linkedList.contains(node))
                linkedList.addFirst(node);
        }
        return linkedList;
    }

    @Override
    public Iterable<V> values() {
        LinkedList<Node> nodeList = new LinkedList<Node>();
        nodeList = findAscendingOrder(root, nodeList);
        LinkedList<V> valuesList = new LinkedList<V>();
        LinkedList<K> keyList = new LinkedList<>();
        for(Node node: nodeList){
            keyList.add((K)node.key);
        }

        for(Node node: nodeList){
            valuesList.add((V)node.value);
        }
        return valuesList;
    }

}




