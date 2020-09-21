import java.util.Iterator;
import java.util.Set;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.HashSet;

public class MyHashMap<K, V> implements Map61B<K, V> {
    private int initSize;
    private double factor;
    private LinkedList<Node>[] bins;
    private HashSet<K> keys;
    private int size;

    private class Node<K, V> {
        K key;
        V value;

        private Node(K k, V val) {
            key = k;
            value = val;
        }

    }

    public MyHashMap() {
        this(16, 0.75);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, 0.75);
    }

    public MyHashMap(int initialSize, double loadFactor) {
        this.initSize = initialSize;
        factor = loadFactor;
        bins = (LinkedList<Node>[]) new LinkedList[initialSize];
        for (int i = 0; i < initialSize; i++) {
            bins[i] = new LinkedList<>();
        }
        keys = new HashSet<>();
    }


    @Override
    public void clear() {
        this.bins = (LinkedList<Node>[]) new LinkedList[initSize];
        for (int i = 0; i < initSize; i++) {
            this.bins[i] = new LinkedList<>();
        }
        this.size = 0;
        this.keys = new HashSet<>();
    }

    private int hashToIndex(int hash) {
        return Math.floorMod(hash, this.initSize);
    }

    private Node findNode(K k) {
        int index = this.hashToIndex(k.hashCode());
        LinkedList<Node> chain = this.bins[index];
        for (Node n: chain) {
            if (n.key.equals(k)) {
                return n;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K k) {
        return (findNode(k) != null);
    }

    @Override
    public V get(K k) {
        Node node = this.findNode(k);
        if (node == null) {
            return null;
        }
        return (V) node.value;
    }

    @Override
    public int size() {
        return size;
    }

    private void resize() {
        MyHashMap<K, V> newHash = new MyHashMap<>(this.initSize * 2, this.factor);
        for (K key: keySet()) {
            newHash.put(key, this.get(key));
        }
        this.bins = newHash.bins;
        this.initSize = this.initSize * 2;
    }

    @Override
    public void put(K k, V v) {
        Node n = this.findNode(k);
        if (n != null) {
            n.value = v;
        } else {
            if (((double) initSize) * factor <= size) {
                resize();
            }
            int index = this.hashToIndex(k.hashCode());
            bins[index].add(new Node(k, v));
            size += 1;
            this.keys.add(k);
        }
    }

    @Override
    public Set<K> keySet() {
        return keys;
    }

    @Override
    public V remove(K k) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K k, V v) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return keys.iterator();
    }
}
