package org.koerber.consultlogger;

import org.koerber.consultlogger.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ConsultLoggerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsultLoggerApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadDemoData(ConsultRepository consultRepository,
                                          DoctorRepository doctorRepository,
                                          PatientRepository patientRepository,
                                          SpecialtyRepository specialtyRepository,
                                          PathologyRepository pathologyRepository,
                                          SymptomRepository symptomRepository) {
        return args ->
                new Bootstrapper().load(consultRepository, doctorRepository, patientRepository,
                        specialtyRepository, pathologyRepository, symptomRepository);
    }


}
