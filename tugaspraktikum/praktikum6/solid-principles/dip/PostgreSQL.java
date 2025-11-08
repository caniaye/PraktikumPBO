package dip;

public class PostgreSQL implements Database {
    
    @Override
    public void insert(String data) {
        System.out.println("Insert data ke PostgreSQL: " + data);
    }
}
