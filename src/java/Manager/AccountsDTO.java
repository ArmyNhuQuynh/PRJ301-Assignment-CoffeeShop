/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class AccountsDTO implements Serializable{
    private int AccId;
    private String Email;
    private String FullName;
    private String Password;
    private String Role;
    private boolean Status;

    public AccountsDTO() {
    }

    public AccountsDTO(int AccId, String Email, String FullName, String Password, String Role, boolean Status) {
        this.AccId = AccId;
        this.Email = Email;
        this.FullName = FullName;
        this.Password = Password;
        this.Role = Role;
        this.Status = Status;
    }

    public int getAccId() {
        return AccId;
    }

    public void setAccId(int AccId) {
        this.AccId = AccId;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }



    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

   
    
}
