/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Dessert;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PUTRI ANASTASYA
 */
public class DessertController {
    private List<Dessert> dessertList;

    public DessertController() {
        dessertList = new ArrayList<>(); // Corrected initialization
    }

    // Create
    public void addDessert(Dessert dessert) {
        dessertList.add(dessert);
        saveDessertToFile(dessert);
    }

    // Read
    public List<Dessert> getAllDessert() {
        return dessertList;
    }

    public Dessert getDessertById(String idDessert) {
        for (Dessert dessert : dessertList) {
            if (dessert.getIdDessert().equals(idDessert)) {
                return dessert;
            }
        }
        return null; // or throw exception
    }

    // Update
    public void updateDessert(String idDessert, String newMenu, int newQuantity) {
        Dessert dessert = getDessertById(idDessert);
        if (dessert != null) {
            dessert.setMenuDessert(newMenu);
            dessert.setQuantity(newQuantity);
        }
    }

    // Delete
    public void deleteDessert(String idDessert) {
        Dessert dessert = getDessertById(idDessert);
        if (dessert != null) {
            dessertList.remove(dessert);
        }
    }

    // Other methods
    public void displayAllDessert() {
        for (Dessert dessert : dessertList) {
            dessert.displayDessert();
        }
    }

    public void saveDessertToFile(Dessert dessert) {
        try (FileWriter writer = new FileWriter("data_dessert.txt", true);
             BufferedWriter bw = new BufferedWriter(writer)) {
            bw.write(dessert.writeDataDessert()); // Menggunakan metode writeDataDessert dari model Dessert
            bw.newLine(); // Menambahkan baris baru untuk setiap entri
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
