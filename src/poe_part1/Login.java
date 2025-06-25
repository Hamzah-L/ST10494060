
package poe_part1;

public class Login {
    private boolean checkUserName;
    private boolean checkPasswordComplexity;
    private boolean checkCellPhoneNumber;
    private String registerUser;
    private boolean loginUser;
    private String returnLoginStatus;

    public Login(boolean checkUserName, boolean checkPasswordComplexity, boolean checkCellPhoneNumber, String registerUser, boolean loginUser, String returnLoginStatus) {
        this.checkUserName = checkUserName;
        this.checkPasswordComplexity = checkPasswordComplexity;
        this.checkCellPhoneNumber = checkCellPhoneNumber;
        this.registerUser = registerUser;
        this.loginUser = loginUser;
        this.returnLoginStatus = returnLoginStatus;
    }
    
//Method to validate username
public boolean checkUserName(String username) {
    return username.contains("_") && username.length() < 5;
}
//Method to validate passwors
public boolean checkPasswordComplexity(String password) {
    return password.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!()_{}\\[\\]:;\"'<>,.?/~`|\\\\-]).{8,}$");
}
//Method to validate phone number
  public boolean checkCellPhoneNumber(String phone) {
        return phone.matches("^\\+27\\d{1,3}\\d{1,10}$");
    }
  //Method to validate user register
   public String registerUser() {
        if (!checkUserName(registerUser)) {
           }
   return "Username is incorrectly formatted. Please include an underscore and keep it under 5 characters.";
}
   //Method to verify the user login
   public boolean loginUser(String enteredUsername) {
        return registerUser.equals(enteredUsername);
    }
   //Method to validate login status
   public String returnLoginStatus(boolean loginSuccessful) {
        return loginSuccessful ? "Login successful!" : "Login failed. Username or password incorrect.";
    }
}
