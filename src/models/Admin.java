package models;

public class Admin extends User {
    public Admin(String username, String password){
        super(username, password, "Admin");
    }

    @Override
    public boolean login() {
        return false;
    }

    @Override
    public boolean logout() {
        return false;
    }

    public void addProduct(){

    }
    public void removeProduct(){

    }
    public void findProduct(){

    }
    public void editProduct(){

    }

    public void showStatistics(){

    }
}
