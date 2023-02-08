package tpo.algorithm;

import java.util.ArrayList;
import java.util.Objects;

// This is a Class to represent the hash table.
class MyMap<K, V> {
    // A bucketArray is used to store the array of chains.
    private ArrayList<HashNode<K, V>> bucketArray;
    // The current capacity of the Array List.
    private int numbucks;
    // The current size_of_array of the ArrayList.
    private int size_of_array;

    // Constructor (Initializes capacity, size_of_array and
    // empty chains.
    public MyMap() {
        bucketArray = new ArrayList<>();
        numbucks = 12;
        size_of_array = 0;

        // Create a set of empty chains for implementing Open Hashing.
        for (int i = 0; i < numbucks; i++)
            bucketArray.add(null);
    }

    public int size_of_array() {
        return size_of_array;
    }

    public boolean isEmpty() {
        return size_of_array() == 0;
    }

    private final int hashCode(K key) {
        return Objects.hashCode(key);
    }

    // This implements Hash Function to find the index for a key
    private int getBucketIndex(K key) {
        int hashCode = hashCode(key);
        int index = hashCode % numbucks;
        // key.hashCode() may result negative.
        index = index < 0 ? index * -1 : index;
        return index;
    }

    public V remove(K key) {
        // Apply Hash Function to find index for any given key
        int bucketIndex = getBucketIndex(key);
        int hashCode = hashCode(key);
        // Get head of the chain
        HashNode<K, V> head = bucketArray.get(bucketIndex);

        // To search for any key in its chain
        HashNode<K, V> prev = null;
        while (head != null) {
            // If Key found
            if (head.key.equals(key) && hashCode == head.hashCode)
                break;

            // Else keep moving in chain
            prev = head;
            head = head.next;
        }
        // If the key is not present.
        if (head == null)
            return null;
        // size of the array is decremented.
        size_of_array--;
        if (prev != null)
            prev.next = head.next;
        else
            bucketArray.set(bucketIndex, head.next);
        return head.value;
    }

    // This returns the value for any key.
    public V get(K key) {
        // Find head of chain for a given key.
        int bucketIndex = getBucketIndex(key);
        int hashCode = hashCode(key);
        HashNode<K, V> head = bucketArray.get(bucketIndex);
        // To search for key in the chain.
        while (head != null) {
            if (head.key.equals(key) && head.hashCode == hashCode)
                return head.value;
            head = head.next;
        }
        // If the requried key is not found, then
        return null;
    }

    // Adds a key value pair to the hash
    public void add(K key, V value) {
        // Find head of the chain for any given key.
        int bucketIndex = getBucketIndex(key);
        int hashCode = hashCode(key);
        HashNode<K, V> head = bucketArray.get(bucketIndex);
        // Check if the key already exists.
        while (head != null) {
            if (head.key.equals(key) && head.hashCode == hashCode) {
                head.value = value;
                return;
            }
            head = head.next;
        }
        // Increment the size of array.
        size_of_array++;
        head = bucketArray.get(bucketIndex);
        HashNode<K, V> newNode
                = new HashNode<K, V>(key, value, hashCode);
        newNode.next = head;
        bucketArray.set(bucketIndex, newNode);
        /*  If the load factor goes beyond threshold, then
         double hash table size_of_array */
        if ((1.0 * size_of_array) / numbucks >= 0.7) {
            ArrayList<HashNode<K, V>> temp = bucketArray;
            bucketArray = new ArrayList<>();
            numbucks = 2 * numbucks;
            size_of_array = 0;
            for (int i = 0; i < numbucks; i++)
                bucketArray.add(null);
            for (HashNode<K, V> headNode : temp) {
                while (headNode != null) {
                    add(headNode.key, headNode.value);
                    headNode = headNode.next;
                }
            }
        }
    }
}
