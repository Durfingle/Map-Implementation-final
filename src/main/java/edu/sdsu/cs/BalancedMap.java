package edu.sdsu.cs;
import java.util.TreeMap;

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

public class BalancedMap <K extends Comparable<K>,V> implements IMap<K,V>
{
    public static void main( String[] args )
    {

    }

    @Override
    public boolean contains(Comparable key) {
        return false;
    }

    @Override
    public boolean add(Comparable key, Object value) {
        return false;
    }

    @Override
    public Object delete(Comparable key) {
        return null;
    }

    @Override
    public Object getValue(Comparable key) {
        return null;
    }

    @Override
    public K getKey(Object value) {
        return null;
    }

    @Override
    public Iterable<K> getKeys(Object value) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public Iterable<K> keyset() {
        return null;
    }

    @Override
    public Iterable values() {
        return null;
    }
}
