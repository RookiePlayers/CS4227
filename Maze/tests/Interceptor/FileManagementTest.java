package Interceptor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileManagementTest {

    @Test
    void loadUserFile() {
        Users users=null;
        users=FileManagement.loadUserFile();
        assertNotNull(users);
    }

    @Test
    void saveAllToFile() {
        Users users=new Users();
        User u=new User("tester","xxxxx");
        users.getUsers().add(u);
        FileManagement.saveAllToFile(users);
        users=FileManagement.loadUserFile();
        boolean succes=false;
        for(User user:users.getUsers()){
            if(u.getUsername().equals(user.getUsername())) succes=true;
        }

        assertTrue(succes);

    }

    @Test
    void saveAToFile() {
        Users users=new Users();
        User u=new User("tester","xxxxx");

        FileManagement.saveAToFile(u);
        users=FileManagement.loadUserFile();
        boolean succes=false;
        for(User user:users.getUsers()){
            if(u.getUsername().equals(user.getUsername())) succes=true;
        }

        assertTrue(succes);
    }

    @Test
    void getUser() {
        User u=new User("tester","xxxxx");

        FileManagement.saveAToFile(u);
        u=FileManagement.getUser(new User("tester","xxxxx"));
        assertNotNull(u);

    }
}