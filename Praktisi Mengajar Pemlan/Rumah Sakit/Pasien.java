package JavaCollection;

import java.util.*;

public class Pasien extends NamaPasien {
    
    public Pasien(String name) {
        super(name);
        //TODO Auto-generated constructor stub
    }

    public static void main(String[] args) {
        Collection<String> antri = new ArrayList<>();
    
        antri.add("Karina");
        antri.add("Ningning");
        antri.addAll(Arrays.asList("Mario", "Bros"));
    
        System.out.println("Antrian Pasien:");
        int i = 1;
        for (String name : antri){
            System.out.println(i + ". " + name);
            i++;
        }

    }
}
