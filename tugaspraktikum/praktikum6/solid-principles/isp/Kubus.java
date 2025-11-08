package isp;

public class Kubus implements Shape3Dimension, Shape2Dimension {
    @Override
    public void calculateArea() {
        System.out.println("Menghitung luas permukaan kubus...");
    }

    @Override
    public void calculateVolume() {
        System.out.println("Menghitung volume kubus...");
    }
}
