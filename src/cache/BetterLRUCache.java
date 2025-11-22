package cache;


import java.util.HashMap;

class BetterLRUCache<K, V> {
    private class Node {
        K key;
        V value;
        Node prev, next;
        Node(K k, V v) { key = k; value = v; }
        Node() {}  // For dummy nodes
    }

    private final int capacity;
    private final HashMap<K, Node> map = new HashMap<>();
    private final Node head = new Node();  // Dummy head
    private final Node tail = new Node();  // Dummy tail

    public BetterLRUCache(int capacity) {
        this.capacity = capacity;
        head.next = tail;  // Connect dummies
        tail.prev = head;
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
                Node lru = tail.prev;  // Last real node
                remove(lru);
                map.remove(lru.key);
            }
        }
    }

    private void moveToHead(Node node) {
        remove(node);
        addToHead(node);
    }

    private void addToHead(Node node) {
        // Insert after dummy head
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void remove(Node node) {
        // No null checks needed!
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public void printCache() {
        Node current = head.next;
        while (current != tail) {
            System.out.print(current.key + "=" + current.value + " ");
            current = current.next;
        }
        System.out.println();
    }
}
