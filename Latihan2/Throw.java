package Latihan2;

public class Throw {
    public static void main(String[] args) {
        try {
            double angka = 20/0;
            System.out.println(angka);
        } catch (ArithmeticException a) {
            System.out.println("terjadi kesalahan" + a);
        }
    }
}