/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class Dessert {
    private String menu;
    private int quantity;
    private String idDessert;

    public Dessert(String menu, int quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    public Dessert(String menu, int quantity, String idDessert) {
        this.menu = menu;
        this.quantity = quantity;
        this.idDessert = idDessert;
    }

    public String getMenuDessert() {
        return menu;
    }

    public void setMenuDessert(String menu) {
        this.menu = menu;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getIdDessert() {
        return idDessert;
    }

    public void setIdDessert(String idDessert) {
        this.idDessert = idDessert;
    }

    public void displayDessert() {
        System.out.printf("%-20s: %s%n", "Menu", this.getMenuDessert());
        System.out.printf("%-20s: %s%n", "Kategori", this.getIdDessert());
        System.out.printf("%-20s: %d%n", "Quantity", this.quantity);
    }

    public String writeDataDessert() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.menu).append(", ")
          .append(this.idDessert).append(", ")
          .append(this.quantity).append("\n");
        return sb.toString();
    }

    public void ubahNamaDessert(String menuBaru) {
        this.menu = menuBaru;
    }
}

