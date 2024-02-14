package ma.enset.hopital;

import ma.enset.hopital.entities.Patient;
import ma.enset.hopital.repository.PatientRepository;
import ma.enset.hopital.security.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.awt.geom.PathIterator;
import java.util.Date;

@SpringBootApplication
public class HopitalApplication implements CommandLineRunner {
    @Autowired
    private PatientRepository patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(HopitalApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {

    }
    //@Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository){
        return args -> {
            patientRepository.save(new Patient(null,"Mohamed",new Date(),false,4000));
            patientRepository.save(new Patient(null,"Hanane",new Date(),false,432));
            patientRepository.save(new Patient(null,"Imane",new Date(),true,340));
            patientRepository.save(new Patient(null,"Hassen",new Date(),false,6592));
            patientRepository.save(new Patient(null,"Houda",new Date(),true,120));
            patientRepository.save(new Patient(null,"Wided",new Date(),true,650));
            patientRepository.save(new Patient(null,"Mourad",new Date(),false,850));
            patientRepository.save(new Patient(null,"Adel",new Date(),false,360));
            patientRepository.save(new Patient(null,"Belgacem",new Date(),true,340));
            patientRepository.save(new Patient(null,"Zohra",new Date(),true,656));
            patientRepository.save(new Patient(null,"Kamel",new Date(),false,783));
            patientRepository.save(new Patient(null,"Ihsen",new Date(),true,498));
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //@Bean
    CommandLineRunner start (AccountService accountService){
        return args ->{
            accountService.addNewUser("hbiba","456","456","haboub@gmail.com");
            accountService.addNewUser("samira","456","456","samira@gmail.com");
            accountService.addNewUser("joud","456","456","joud@gmail.com");

            accountService.addNewRole("ADMIN");
            accountService.addNewRole("USER");

            accountService.addRoleToUser("hbiba","ADMIN");
            accountService.addRoleToUser("hbiba","USER");
            accountService.addRoleToUser("samira","USER");
            accountService.addRoleToUser("joud","USER");

        };
    }

}
