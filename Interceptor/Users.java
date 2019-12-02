package Interceptor;

import java.io.Serializable;
import java.util.ArrayList;

public class Users implements Serializable {
    private ArrayList<User>users=new ArrayList<>();

    public Users(ArrayList<User> users) {
        this.users = users;
    }

    public Users() {

    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
}
