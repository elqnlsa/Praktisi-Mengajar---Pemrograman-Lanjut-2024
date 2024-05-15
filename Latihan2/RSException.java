package Latihan2;

import java.util.*;

public class RSException {
    public static class NamaPasien {
        private String name;

        public NamaPasien(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {
        Collection<String> antri = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan nama antrian: ");
        String nama = input.nextLine();

        try {
            if (nama == null || nama.isEmpty()) {
                throw new IllegalArgumentException("Nama antrian tidak boleh kosong");
            }
            System.out.println("Nama antrian: " + nama);
            antri.add(nama);
            antri.add("Tzuyu");
            antri.add("Hanni");
            antri.addAll(Arrays.asList("Mario", "Bros"));

            System.out.println("Antrian Pasien:");
            int i = 1;
            for (String name : antri) {
                System.out.println(i + ". " + name);
                i++;
            }

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}

