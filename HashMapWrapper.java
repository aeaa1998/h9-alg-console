
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HashMapWrapper<K extends Comparable<K>, V> implements Mappeable<K,V> {
    private HashMap<K, V> map;

    public HashMapWrapper() {
        this.map = new HashMap<>();
    }

    @Override
    public V get(K key) {
        return map.get(key);
    }

    @Override
    public void put(K key, V value) {
        map.put(key, value);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public Set<Association<K, V>> entrySet() {
        Set<Association<K, V>> set = new HashSet<>();
        map.forEach((key, value) -> set.add(new Association<>(key, value)));
        return set;
    }


}
