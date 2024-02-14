package ma.enset.hopital.security.repositories;

import ma.enset.hopital.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface AppUserRepository extends JpaRepository<AppUser,String> {
    AppUser findByUserName(String username);
}
