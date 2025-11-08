package dip;

public class Main {
    public static void main(String[] args) {

        Database db = new MySQL();
        UserService userService = new UserService(db);
        userService.registerUser("cania@gmail.com");
    }
}
