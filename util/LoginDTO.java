package util;

import java.io.Serializable;

public class LoginDTO implements Serializable {

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isCustomer(){
        
        return isCustomerOrRestaurant;
    }

    public void setIsCustomer(boolean isCustomerOrRestaurant){
        this.isCustomerOrRestaurant = isCustomerOrRestaurant;
    }


    private String userName;
    private String password;
    private boolean status;
    private boolean isCustomerOrRestaurant;
}
