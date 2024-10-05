package org.koerber.consultlogger;

import org.koerber.consultlogger.model.Consult;
import org.koerber.consultlogger.model.Doctor;
import org.koerber.consultlogger.model.Specialty;
import org.koerber.consultlogger.model.Symptom;
import org.koerber.consultlogger.repository.ConsultRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ConsultLoggerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsultLoggerApplication.class, args);
    }

    public CommandLineRunner demoData(ConsultRepository consultRepository) {
        return args ->{
            var symptoms = generateSymptoms();
            Specialty derma = new Specialty("Dermatology");
            Doctor antonio = new Doctor("Antonio", derma);


            var consults = List.of(new Consult())

            consultRepository.saveAll()
        };
    }

    private List<Symptom> generateSymptoms(){
        var symptoms = new ArrayList<Symptom>();
        for (int i = 1; i <= 15 ; i++) {
            symptoms.add(new Symptom("Symptom "+ i+" Description"));
        }
        return symptoms;
    }
}
