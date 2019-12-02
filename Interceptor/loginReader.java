package Interceptor;

import Maze.Persistance.MazeParts;

import java.io.*;
import java.util.ArrayList;

//takes username and password, reads acceptable ones from file, returns if they exist together in file
class loginReader {
    public User u=null;
    boolean checkCredentials(String username, String password) throws IOException {
        boolean credentialsAccepted = false;


        /*ArrayList<String> credentialList = new ArrayList<>();

        File file = new File(getClass().getResource("/text/logins.txt").getFile());
        BufferedReader br = new BufferedReader(new FileReader(file));

        String inRead;
        while ((inRead = br.readLine()) != null) {
            credentialList.add(inRead.trim());
        //    System.out.println("Just read: "+inRead);
        }
        System.out.println("username given:" + username + " password given: " + password);

        for(int i = 0; i < (credentialList.size() - 1); i++) {
            if (credentialList.get(i).equals(username) && credentialList.get(i + 1).equals(password)) {
                credentialsAccepted = true;
                break;
            }
        }*/
        MazeParts.CURRENTUSER=FileManagement.getUser(new User(username, password));
        if(MazeParts.CURRENTUSER!=null){
            credentialsAccepted=true;
        }
        return credentialsAccepted;
    }
}
