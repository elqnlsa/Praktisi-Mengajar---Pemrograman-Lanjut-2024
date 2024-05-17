package OperatorFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class TugasFile {
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
        
        File file1 = new File("Operatorpraktisi.txt");
        System.out.println(file1.getAbsolutePath());
        System.out.println(file1.exists());
        
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

            saveToFile(antri, file1);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Gagal menyimpan daftar antrian ke file.");
            System.out.println("Lokasi file: " + file1.getAbsolutePath());
        }
    }

    private static void saveToFile(Collection<String> antri, File file) throws IOException {
        FileWriter writer = new FileWriter(file);
        for (String name : antri) {
            writer.write(name + "\n");
        }
        writer.close();
        System.out.println("Daftar antrian berhasil disimpan ke file.");
        System.out.println("Lokasi file: " + file.getAbsolutePath());
    }
}
