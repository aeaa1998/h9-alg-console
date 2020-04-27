
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class Controller {
    private Mappeable<String, String> dictionary;
    private ArrayList<String> menu = new ArrayList<>(){{add("Traducir .txt"); add("Traducir texto en consola");}};
    private MappeableFactory<String, String> factory;
    public Controller() {
        this.factory = new MappeableFactory<String, String>();
        this.dictionary = factory.getMap();
    }
    public Controller(Mappeable<String, String> dic) {
        this.dictionary = dic;
    }

    public void init() throws URISyntaxException, IOException {
        fillDictionary("Spanish.txt");
        boolean running = true;
        while (running){
            /* Funcion que retorna el valor de el indice de la viarbale menu*/
            switch (View.getView().selectOptions(menu, "Ingrese una de las opciones.", "Ingrese un valor valido.")){
                case 0:
                    /* Codigo para traducir nuestro archivo txt*/
                    var mainPath = Controller.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
                    if (getOsName().startsWith("Windows")){
                        if(String.valueOf(mainPath.charAt(0)).equals("/")) { mainPath = mainPath.substring(1, mainPath.length());}
                    }
                    List<String> strings = Files.readAllLines(Path.of(mainPath + "texto.txt"));
                    strings.forEach(string -> View.getView().print(traduce(string)));
                    break;
                case 1:
                    String textToTranslate = View.getView().input("Ingrese el texto que desea traducir:");
                    View.getView().print(traduce(textToTranslate));
                    break;
                default:
                    /* Decidio salir*/
                    View.getView().print("Gracias por usar nuestro traductor");
                    running = false;
                    break;
            }

        }
    }

    public void init(int d) throws URISyntaxException, IOException {
        fillDictionary("Spanish.txt");
        boolean running = true;
            /* Funcion que retorna el valor de el indice de la viarbale menu*/
            switch (d){
                case 0:
                    /* Codigo para traducir nuestro archivo txt*/
                    var mainPath = Controller.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
                    if (getOsName().startsWith("Windows")){
                        if(String.valueOf(mainPath.charAt(0)).equals("/")) { mainPath = mainPath.substring(1, mainPath.length());}
                    }
                    List<String> strings = Files.readAllLines(Path.of(mainPath + "texto.txt"));
                    strings.forEach(string -> View.getView().print(traduce(string)));
                    break;
                case 1:
                    String textToTranslate = View.getView().input("Ingrese el texto que desea traducir:");
                    View.getView().print(traduce(textToTranslate));
                    break;
                default:
                    /* Decidio salir*/
                    View.getView().print("Gracias por usar nuestro traductor");
                    running = false;
                    break;
            }


    }

    public String traduce(String textToTranslate){
        var list = textToTranslate.split(" ");
        StringBuilder finalResult =  new StringBuilder();
        for (String s : list) {
            if (hasKey(dictionary, s)) finalResult.append(dictionary.get(s.toLowerCase()));
            else finalResult.append("*").append(s).append("*");
            finalResult.append(" ");
        }
        return finalResult.toString();

    }

    public void fillDictionary(String fileName){
        try {
            var mainPath = Controller.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            if (getOsName().startsWith("Windows")){
                if(String.valueOf(mainPath.charAt(0)).equals("/")) { mainPath = mainPath.substring(1, mainPath.length());}
            }
            List<String> strings = Files.readAllLines(Path.of(mainPath + fileName));
            for (String line:
                    strings) {
                var holder = new ArrayList<String>(){{ addAll(List.of(line.split("\t")));}};
                var english = holder.get(0);
                var spanish = holder.get(1);
                if (spanish.contains(", ")){
                    spanish = spanish.split(", ")[0];
                }else if(spanish.contains(",")){
                    spanish = spanish.split(",")[0];
                }
                if (spanish.contains("[")){
                    spanish = spanish.substring(0, spanish.indexOf("["));
                }
                dictionary.put(english.toLowerCase(), spanish);
            }
        } catch(URISyntaxException | IOException e){
            System.out.println("Revise bien que su archivo txt exista");
        }
    }
    private boolean hasKey(Mappeable<String, String> map, String key){
        AtomicBoolean has = new AtomicBoolean(false);
        map.entrySet().forEach(keyValue -> {
            if(key.equalsIgnoreCase(keyValue.getKey()))
                has.set(true);
        });
        return has.getPlain();
    }
    public Mappeable<String, String> getDictionary() {
        return dictionary;
    }

    private  String getOsName()
    {
        return System.getProperty("os.name");
    }
}
