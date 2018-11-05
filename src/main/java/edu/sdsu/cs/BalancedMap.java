package edu.sdsu.cs;
import java.util.LinkedList;
import java.util.TreeMap;

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

public class BalancedMap <K extends Comparable<K>,V> implements IMap<K,V>
{
    TreeMap treeMap;

    public BalancedMap() {
        treeMap = new TreeMap();
    }

    public BalancedMap(IMap<K,V> iMap) {
        for (Object o : iMap.keyset()) {
            treeMap.put(o, iMap.getValue((K) o));
        }
    }
    @Override
    public boolean contains(Comparable key) {
        for(Object o:treeMap.keySet()) {
            if (key.compareTo(o)==0)
                return true;
        }
        return false;
    }

    @Override
    public boolean add(Comparable key, Object value) {
        treeMap.put(key,value);
        return treeMap.containsKey(key);
    }

    @Override
    public Object delete(Comparable key) {
        return treeMap.remove(key);
    }

    @Override
    public Object getValue(Comparable key) {
        return treeMap.get(key);
    }

    @Override
    public K getKey(Object value) {
        for(Object o:treeMap.keySet()) {
            if (treeMap.get(o).equals(value))
                return (K)o;
        }
        return null;
    }

    @Override
    public Iterable<K> getKeys(Object value) {
        LinkedList<K> linkedList = new LinkedList<K>();
        for(Object o:treeMap.keySet()) {
            if (treeMap.get(o).equals(value))
                linkedList.add((K)o);
        }
        return linkedList;
    }

    @Override
    public int size() {
        return treeMap.size();
    }

    @Override
    public boolean isEmpty() {
        return treeMap.isEmpty();
    }

    @Override
    public void clear() {
        treeMap.clear();
    }

    @Override
    public Iterable<K> keyset() {
        LinkedList<K> linkedList = new LinkedList<K>();
        for(Object o:treeMap.keySet()) {
            linkedList.add((K)o);
        }
        return linkedList;
    }

    @Override
    public Iterable values() {
        LinkedList<K> linkedList = new LinkedList<K>();
        for(Object o:treeMap.keySet()) {
            linkedList.add((K)treeMap.get((K)o));
        }
        return linkedList;
    }
}
