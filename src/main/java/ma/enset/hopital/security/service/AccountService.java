package ma.enset.hopital.security.service;

import ma.enset.hopital.security.entities.AppRole;
import ma.enset.hopital.security.entities.AppUser;

public interface AccountService {

    AppUser addNewUser(String userName, String password, String confirmPassword,String email);
    AppRole addNewRole(String roleName);
    void addRoleToUser(String userName, String roleName);
    void removeRoleFromUser (String userName, String roleName);
    AppUser LoadUserByUserName(String userName);
}
