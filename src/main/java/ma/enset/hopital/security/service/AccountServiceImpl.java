package ma.enset.hopital.security.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import ma.enset.hopital.security.entities.AppRole;
import ma.enset.hopital.security.entities.AppUser;
import ma.enset.hopital.security.repositories.AppRoleRepository;
import ma.enset.hopital.security.repositories.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public AppUser addNewUser(String userName, String password, String confirmPassword,String email) {
        AppUser user=appUserRepository.findByUserName(userName);
        if(user!=null) throw new RuntimeException("USER NAME ALREADY EXIST");
        if(!(password.equals(confirmPassword))) throw new RuntimeException("password dosen't match");
        String hashedPwd= passwordEncoder.encode(password);
        AppUser newUser=AppUser.builder()
                .userID(UUID.randomUUID().toString())
                .userName(userName)
                .password(hashedPwd)
                .email(email)
        .build();

        appUserRepository.save(newUser);
        return newUser;
    }

    @Override
    public AppRole addNewRole(String roleName) {
        AppRole appRole=appRoleRepository.findByRoleName(roleName);
        if(appRole!=null) throw new RuntimeException("ROLE ALREADY EXIST");
        AppRole newRole=new AppRole();
        newRole.setRoleName(roleName);
        appRoleRepository.save(newRole);
        return newRole;
    }


    @Override
    public void addRoleToUser(String userName, String roleName) {
        AppUser appUser=appUserRepository.findByUserName(userName);
        if(appUser==null) throw new RuntimeException("user not found");
        AppRole appRole=appRoleRepository.findByRoleName(roleName);
        if(appRole==null) throw new RuntimeException("role not found");
        appUser.getAppRoles().add(appRole);
    }

    @Override
    public void removeRoleFromUser(String userName, String roleName) {
        AppUser appUser=appUserRepository.findByUserName(userName);
        if(appUser==null) throw new RuntimeException("user not found");
        AppRole appRole=appRoleRepository.findByRoleName(roleName);
        if(appRole==null) throw new RuntimeException("role not found");
        appUser.getAppRoles().remove(appRole);
    }

    @Override
    public AppUser LoadUserByUserName(String userName) {
        return appUserRepository.findByUserName(userName);

    }
}
