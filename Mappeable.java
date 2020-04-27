
import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface Mappeable<K, T> {
    public T get(K key);
    public void put(K key, T value);
    public int size();
    public Set<Association<K, T>> entrySet();

}
