package Interceptor;

import java.io.IOException;

public interface loginCheck {
    boolean checkInput(String userName, String password) throws IOException;
    boolean validInput(String text,String regEx);
}
