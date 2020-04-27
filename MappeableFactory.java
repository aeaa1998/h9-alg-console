
import java.util.ArrayList;

public class MappeableFactory<K extends Comparable<K>, V> {
    private ArrayList<String> options = new ArrayList<>(){{add("HashMap"); add("RedBlackTree");}};
    private int type = -1;

    public Mappeable<K, V> getMap(){
        if (type == -1) type = View.getView().selectOptions(options, "Escoja el numero de una de las siguientes opciones", "Escoja una opcion valida");
        if (type == 0){
            return new HashMapWrapper<>();
        }else {
            return new RedBlackTree<>();
        }
    }
    public Mappeable<K, V> getMapHash(){
            return new HashMapWrapper<>();
    }

    public Mappeable<K, V> getTree(){
        return new RedBlackTree<>();
    }
}
