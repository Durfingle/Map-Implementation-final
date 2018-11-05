package edu.sdsu.cs;

import java.util.LinkedList;

/**
 * Michael Kemper
 * cssc0833
 *
 * Juan Pina-Sanz
 * cssc0835
 *
 * @param <K>
 * @param <V>
 */

public class UnbalancedMap <K extends Comparable<K>,V> implements IMap<K,V>
{
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

    UnbalancedMap(IMap<K,V> iMap) {
        Iterable<K> iterable = iMap.keyset();
        for (K keyInIterable: iterable) {
            add(keyInIterable,iMap.getValue(keyInIterable));
        }
    }

    @Override
    public boolean contains(K key) {
        return contains(key, root);
    }

    public boolean contains(K key, Node node) {
        if(node==null)
            return false;
        else if (key.compareTo(node.key) < 0)
            return contains(key, node.left);
        else if (key.compareTo(node.key) > 0)
            return contains(key, node.right);
        else
            return true;
    }

    @Override
    public boolean add(K key, V value) {
        return insert(key, value, this.root);
    }

    private boolean insert(K key, V value, Node node) {
        if (this.root == null) {
            this.root = new Node(key, value);
            return true;
        } else if (key.compareTo(node.key) < 0) {
            if (node.left != null) {
                insert(key, value, node.left);
            } else {
                node.left = new Node(key, value);
                return true;
            }
        } else if (key.compareTo(node.key) > 0) {
            if (node.right != null) {
                insert(key, value, node.right);
            } else {
                node.right = new Node(key, value);
                return true;
            }
        }
        return false;
    }

    @Override
    public V delete(K key) {
        root = deleteNode(root, key);
        return lastDelete;
    }

    private V lastDelete = null;
    private Node deleteNode(Node root, K key)
    {
        if (root == null)
            return root;

        if (key.compareTo(root.key) < 0)
            root.left = deleteNode(root.left, key);
        else if (key.compareTo(root.key) > 0)
            root.right = deleteNode(root.right, key);
        else
        {
            if (root.left == null) {
                if (lastDelete == null)
                    lastDelete = root.value;
                return root.right;
            }
            else if (root.right == null) {
                if (lastDelete == null)
                    lastDelete = root.value;
                return root.left;
            }
            if (lastDelete == null)
                lastDelete = root.value;
            root.key = findSmallerNodeKey(root.right);
            root.right = deleteNode(root.right, root.key);
        }
        return root;
    }

    private K findSmallerNodeKey(Node root)
    {
        K smallerKey = root.key;
        while (root.left != null)
        {
            smallerKey = root.left.key;
            root = root.left;
        }
        return smallerKey;
    }

    @Override
    public V getValue(K key) {
        if(root == null)
            return null;
        Node currentNode = root;
        while(currentNode.key.compareTo(key) != 0){
            if(key.compareTo(currentNode.key)< 0)
                currentNode = currentNode.left;
            else
                currentNode = currentNode.right;
            if (currentNode == null)
                return null;
        }
        return currentNode.value;
    }

    @Override
    public K getKey(V value) {
        LinkedList<Node> nodeList = new LinkedList<>();
        nodeList = findAscendingOrder(root, nodeList);
        for (Node node: nodeList) {
            if (node.value.equals(value)) {
                return node.key;
            }
        }
        return null;
    }

    @Override
    public Iterable<K> getKeys(V value) {
        LinkedList keysList = new LinkedList();
        LinkedList<Node> nodeList = new LinkedList<>();
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
        return size(root, 0);
    }

    private int size(Node node, int counter){
        if (node !=null) {
            counter++;
            counter = size(node.left, counter);
            counter = size(node.right, counter);
        }
        return counter;
    }

    @Override
    public boolean isEmpty() {
        return root==null;
    }

    @Override
    public void clear() {
        this.root = null;
    }

    @Override
    public Iterable<K> keyset() {
        LinkedList<Node> nodeList = new LinkedList<>();
        nodeList = findAscendingOrder(root, nodeList);
        LinkedList<V> valuesList = new LinkedList<>();
        LinkedList<K> keyList = new LinkedList<>();
        for(Node node: nodeList){
            keyList.add(node.key);
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
        LinkedList<Node> nodeList = new LinkedList<>();
        nodeList = findAscendingOrder(root, nodeList);
        LinkedList<V> valuesList = new LinkedList<>();
        LinkedList<K> keyList = new LinkedList<>();
        for(Node node: nodeList){
            keyList.add(node.key);
        }

        for(Node node: nodeList){
            valuesList.add(node.value);
        }
        return valuesList;
    }

}




