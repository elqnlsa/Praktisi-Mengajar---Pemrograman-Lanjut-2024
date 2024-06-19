/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class Bakery {
    private String menuBakery;
    private int quantity;
    public boolean isTersedia = true;

    public Bakery(String menuBakery, int quantity) {
        this.menuBakery = menuBakery;
        this.quantity = quantity;
    }

    public Bakery(String menuBakery, int quantity, boolean isTersedia) {
        this.menuBakery = menuBakery;
        this.quantity = quantity;
        this.isTersedia = isTersedia;
    }

    public Bakery() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getMenuBakery() {
        return menuBakery;
    }

    public void setMenuBakery(String menuBakery) {
        this.menuBakery = menuBakery;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    private int umur;

    public int getUmur() {
        return umur;
    }

    // public void setUmur(int umur) {
    //     this.umur = umur;
    // }

    // Behaviour

    public void ubahMenuBakery(String menuBaru) {
        this.menuBakery = menuBaru;
    }

    public void cekUmurBakery() {
        umur = 2024 - this.quantity;
        System.out.println("Bakery ini berumur " + umur + " tahun");
    }

    public void displayBakery() {
        System.out.printf("%-20s: %s%n", "Menu Bakery", this.getMenuBakery());
        System.out.printf("%-20s: %d%n", "Quantity", this.getQuantity());
        System.out.printf("%-20s: %s%n", "Status", (isTersedia) ? "Tersedia" : "Tidak Tersedia");
    }

    public String writeDataBakery() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.menuBakery).append(", ")
          .append(this.quantity).append(", ")
          .append(this.isTersedia).append("\n");
        return sb.toString();
    }

    public boolean isTersedia() {
        return isTersedia;
    }

    public void setTersedia(boolean isTersedia) {
        this.isTersedia = isTersedia;
    }

    public void setUmur(int umur) {
        this.umur = umur;
    }

    public Object getIdBakery() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
