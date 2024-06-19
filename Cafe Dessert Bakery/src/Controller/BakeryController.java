/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Bakery;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BakeryController {
    private List<Bakery> bakeryList;

    public BakeryController() {
        bakeryList = new ArrayList<>();
    }

    // Create
    public void addBakery(Bakery bakery) {
        bakeryList.add(bakery);
        // Menulis data ke file setelah menambahkan bakery
        saveBakeryToFile(bakery);
    }

    // Read
    public List<Bakery> getAllBakery() {
        return bakeryList;
    }

    public Bakery getBakeryById(String idBakery) {
        for (Bakery bakery : bakeryList) {
            if (bakery.getIdBakery().equals(idBakery)) {
                return bakery;
            }
        }
        return null; // or throw exception
    }

    // Update
    public void updateBakery(String idBakery, String newMenu, int newQuantity) {
        Bakery bakery = getBakeryById(idBakery);
        if (bakery != null) {
            bakery.setMenuBakery(newMenu);
            bakery.setQuantity(newQuantity);
        }
    }

    // Delete
    public void deleteBakery(String idBakery) {
        Bakery bakery = getBakeryById(idBakery);
        if (bakery != null) {
            bakeryList.remove(bakery);
        }
    }

    // Other methods
    public void displayAllBakery() {
        for (Bakery bakery : bakeryList) {
            bakery.displayBakery();
        }
    }

    public void saveBakeryToFile(Bakery bakery) {
        try (FileWriter writer = new FileWriter("data_bakery.txt", true);
             BufferedWriter bw = new BufferedWriter(writer)) {
            bw.write(bakery.writeDataBakery()); // Menggunakan metode writeDataBakery dari model Bakery
            bw.newLine(); // Menambahkan baris baru untuk setiap entri
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
