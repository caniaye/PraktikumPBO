package dip;

public class MySQL implements Database {
    
    @Override
    public void insert(String data) {
        System.out.println("Insert data ke MySQL: " + data);
    }
}
