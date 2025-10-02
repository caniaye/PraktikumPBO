import java.util.ArrayList; import java.util.HashMap;
public class CollectionExample {     
    public static void main(String[] args) {         
        // ArrayList         
        ArrayList<String> list = new ArrayList<>();         
        list.add("Java");         
        list.add("Python");         
        list.add("C++");         
        for (String bahasa : list) {             
            System.out.println(bahasa);
        }
        // HashMap
        HashMap<String, Integer> umur = new HashMap<>();         
        umur.put("Andi", 20);         
        umur.put("Budi", 22);         
        umur.put("Citra", 19);
        System.out.println("Umur Budi: " + umur.get("Budi"));
    }
}
