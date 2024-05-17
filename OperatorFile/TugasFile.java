package OperatorFile;

import java.io.*;
import java.util.*;

public class TugasFile {
    public static class Antrian {
        private String nama;
        private int nomorAntrian;
        private Date tanggal;

        public Antrian(String nama, int nomorAntrian, Date tanggal) {
            this.nama = nama;
            this.nomorAntrian = nomorAntrian;
            this.tanggal = tanggal;
        }

        public String getNama() {
            return nama;
        }

        public int getNomorAntrian() {
            return nomorAntrian;
        }

        public Date getTanggal() {
            return tanggal;
        }
    }

    public static void main(String[] args) {
        Collection<Antrian> daftarAntrian = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan nama antrian: ");
        String nama = input.nextLine();

        int nomorAntrian = 1;

        Date tanggalAntrian = new Date();

        File file1 = new File("Operatorpraktisi.txt");
        System.out.println(file1.getAbsolutePath());
        System.out.println(file1.exists());

        try {
            if (nama == null || nama.isEmpty()) {
                throw new IllegalArgumentException("Nama antrian tidak boleh kosong");
            }
            System.out.println("Nama antrian: " + nama);

            daftarAntrian.add(new Antrian(nama, nomorAntrian, tanggalAntrian));
            daftarAntrian.add(new Antrian("Tzuyu", 2, new Date()));
            daftarAntrian.add(new Antrian("Hanni", 3, new Date()));
            daftarAntrian.add(new Antrian("Mario", 4, new Date()));
            daftarAntrian.add(new Antrian("Bros", 5, new Date()));

            System.out.println("Antrian Pasien:");
            int i = 1;
            for (Antrian antrian : daftarAntrian) {
                System.out.println(i + ". Nama: " + antrian.getNama() + ", Nomor Antrian: " + antrian.getNomorAntrian() + ", Tanggal: " + antrian.getTanggal());
                i++;
            }

            saveToFile(daftarAntrian, file1);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Gagal menyimpan daftar antrian ke file.");
            System.out.println("Lokasi file: " + file1.getAbsolutePath());
        }
    }

    private static void saveToFile(Collection<Antrian> daftarAntrian, File file) throws IOException {
        FileWriter writer = new FileWriter(file);
        for (Antrian antrian : daftarAntrian) {
            writer.write("Nama: " + antrian.getNama() + ", Nomor Antrian: " + antrian.getNomorAntrian() + ", Tanggal: " + antrian.getTanggal() + "\n");
        }
        writer.close();
        System.out.println("Daftar antrian berhasil disimpan ke file.");
        System.out.println("Lokasi file: " + file.getAbsolutePath());
    }
}
