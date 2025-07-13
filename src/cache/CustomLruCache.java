package cache;

//Custom LRU Cache with O(1) Operations (For Interviews)
import java.util.HashMap;

class CustomLRUCache<K, V> {
    private class Node {
        K key;
        V value;
        Node prev, next;
        Node(K k, V v) { key = k; value = v; }
    }

    private final int capacity;
    private final HashMap<K, Node> map = new HashMap<>();
    private Node head, tail;

    public CustomLRUCache(int capacity) {
        this.capacity = capacity;
    }

    public V get(K key) {
        if (!map.containsKey(key)) return null;
        Node node = map.get(key);
        moveToHead(node);
        return node.value;
    }

    public void put(K key, V value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            moveToHead(node);
        } else {
            Node node = new Node(key, value);
            map.put(key, node);
            addToHead(node);
            if (map.size() > capacity) {
                map.remove(tail.key);
                removeTail();
            }
        }
    }

    private void moveToHead(Node node) {
        remove(node);
        addToHead(node);
    }

    private void addToHead(Node node) {
        node.prev = null;
        node.next = head;
        if (head != null) head.prev = node;
        head = node;
        if (tail == null) tail = head;
    }

    private void remove(Node node) {
        if (node.prev != null)
            node.prev.next = node.next;
        else head = node.next;
        if (node.next != null) node.next.prev = node.prev;
        else tail = node.prev;
    }

    private void removeTail() {
        if (tail != null) remove(tail);
    }

    public void printCache() {
        Node current = head;
        while (current != null) {
            System.out.print(current.key + "=" + current.value + " ");
            current = current.next;
        }
        System.out.println();
    }
}


