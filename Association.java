
import java.util.Map;


public class Association<K,V> implements Map.Entry<K,V>
{
    /**
     * The immutable key.  An arbitrary object.
     */
    private K theKey; // the key of the key-value pair
    /**
     * The mutable value.  An arbitrary object.
     */
    private V theValue; // the value of the key-value pair

    /*
      for example:
      Association<String,Integer> personAttribute =
         new Assocation<String,Integer>("Age",34);
     */
    /**
     * Constructs a pair from a key and value.
     *
     * @pre key is non-null
     * @post constructs a key-value pair
     * @param key A non-null object.
     * @param value A (possibly null) object.
     */
    public Association(K key, V value)
    {

        theKey = key;
        theValue = value;
    }


    public Association(K key)
    {
        this(key,null);
    }


    public boolean equals(Object other)
    {
        Association otherAssoc = (Association)other;
        return getKey().equals(otherAssoc.getKey());
    }


    public int hashCode()
    {
        return getKey().hashCode();
    }


    public V getValue()
    {
        return theValue;
    }

    public K getKey()
    {
        return theKey;
    }


    public V setValue(V value)
    {
        V oldValue = theValue;
        theValue = value;
        return oldValue;
    }
    public void setKey(K key)
    {
        theKey = key;
    }


    public String toString()
    {
        StringBuffer s = new StringBuffer();
        s.append("<Association: "+getKey()+"="+getValue()+">");
        return s.toString();
    }
}
