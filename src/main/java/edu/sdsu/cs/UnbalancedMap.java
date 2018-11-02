package edu.sdsu.cs;

import java.util.LinkedList;
import java.util.Objects;

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

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equals(key, node.key) &&
                    Objects.equals(value, node.value);
        }

        @Override
        public int hashCode() {

            return Objects.hash(key, value);
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
        if (root != null) {
            if(root.key.compareTo(key) == 0)
                return true;
            else
                return check(root,key);
        }
        return false;
    }

    public boolean check(Node node, K key) {
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

        return null;
    }

    public V deleteWithNode(K key, V value, Node n) {
        if (this.root == null) {
            this.root = new Node(key, value);
            return null;
        } else if ((Integer)value < (Integer)n.value) {
            if (n.left != null) {
                insert(key, value, n.left);
            } else {
                n.left = new Node(key, value);
                return null;
            }
        } else if ((Integer)value > (Integer)n.value) {
            if (n.right != null) {
                insert(key, value, n.right);
            } else {
                n.right = new Node(key, value);
                return null;
            }
        }
        return null;
    }

    @Override
    public V getValue(K key) {
        return null;
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
        return size;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
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
        return null;
    }
}