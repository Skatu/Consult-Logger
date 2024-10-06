package org.koerber.consultlogger;

import org.koerber.consultlogger.model.*;
import org.koerber.consultlogger.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class ConsultLoggerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsultLoggerApplication.class, args);
    }

    @Bean
    public CommandLineRunner demoData(ConsultRepository consultRepository,
                                      DoctorRepository doctorRepository,
                                      PatientRepository patientRepository,
                                      SpecialtyRepository specialtyRepository,
                                      PathologyRepository pathologyRepository,
                                      SymptomRepository symptomRepository) {
        return args -> {
            var specialties = generateSpecialties();
            var symptoms = generateSymptoms();
            var pathologies = generatePathologies(symptoms);

            var antonio = new Doctor("António", specialties.get("Dermatology"));
            var maria = new Doctor("Maria", specialties.get("Ophthalmology"));
            var carlos = new Doctor("Carlos", specialties.get("Radiology"));
            var gabriela = new Doctor("Gabriela", specialties.get("Family Medicine"));
            var paulo = new Doctor("Paulo", specialties.get("Pediatrics"));


            var patients = generatePatients(pathologies);
            //FIXME ?we could get the specialty from the doctor, but what can happen is the doctor can change specialty
            var consult1 = new Consult(1L, antonio, patients.get("Manuel"), pathologies.get(0));
            var consult2 = new Consult(2L, antonio, patients.get("Manuel"), pathologies.get(1));
            var consult3 = new Consult(3L, antonio, patients.get("Manuel"), pathologies.get(2));
            var consult4 = new Consult(4L, maria, patients.get("Joana"), pathologies.get(2));
            var consult5 = new Consult(5L, carlos, patients.get("Ana"), pathologies.get(3));
            var consult6 = new Consult(6L, gabriela, patients.get("Diogo"), pathologies.get(4));
            var consult7 = new Consult(7L, paulo, patients.get("Catarina"), pathologies.get(5));
            var consult8 = new Consult(8L, maria, patients.get("Miguel"), pathologies.get(6));

            specialtyRepository.saveAll(specialties.values());
            symptomRepository.saveAll(symptoms);
            pathologyRepository.saveAll(pathologies);
            doctorRepository.saveAll(List.of(antonio, maria, carlos, gabriela, paulo));
            patientRepository.saveAll(patients.values());
            consultRepository.saveAll(List.of(consult1, consult2, consult3, consult4, consult5, consult6, consult7, consult8));

        };
    }

    private Map<String, Specialty> generateSpecialties() {
        Map<String, Specialty> map = new LinkedHashMap<>();
        map.put("Dermatology", new Specialty("Dermatology"));
        map.put("Ophthalmology", new Specialty("Ophthalmology"));
        map.put("Radiology", new Specialty("Radiology"));
        map.put("Family Medicine", new Specialty("Family Medicine"));
        map.put("Pediatrics", new Specialty("Pediatrics"));
        return map;
    }

    private Map<String, Patient> generatePatients(List<Pathology> pathologies) {
        Map<String, Patient> map = new LinkedHashMap<>();
        map.put("Manuel", new Patient("Manuel", 53, pathologies.subList(0, 3)));
        map.put("Joana", new Patient("Joana", 32, pathologies.subList(2, 3)));
        map.put("Ana", new Patient("Ana", 25, pathologies.subList(3, 4)));
        map.put("Diogo", new Patient("Diogo", 33, pathologies.subList(4, 5)));
        map.put("Catarina", new Patient("Catarina", 33, pathologies.subList(5, 6)));
        map.put("Miguel", new Patient("Miguel", 40, pathologies.subList(6, 7)));
        return map;
    }

    private List<Pathology> generatePathologies(List<Symptom> symptoms) {
        var pathologies = new ArrayList<Pathology>();
        for (int i = 1; i <= 7; i++) {
            pathologies.add(new Pathology("Pathology " + i));
        }
        //TODO refactor to subList
        pathologies.get(0).addSymptom(symptoms.get(0));
        pathologies.get(0).addSymptom(symptoms.get(1));

        pathologies.get(1).addSymptom(symptoms.get(2));
        pathologies.get(1).addSymptom(symptoms.get(3));
        pathologies.get(1).addSymptom(symptoms.get(4));

        pathologies.get(2).addSymptom(symptoms.get(5));
        pathologies.get(2).addSymptom(symptoms.get(6));

        pathologies.get(3).addSymptom(symptoms.get(7));

        pathologies.get(4).addSymptom(symptoms.get(8));
        pathologies.get(4).addSymptom(symptoms.get(9));
        pathologies.get(4).addSymptom(symptoms.get(10));

        pathologies.get(5).addSymptom(symptoms.get(11));
        pathologies.get(5).addSymptom(symptoms.get(12));

        pathologies.get(6).addSymptom(symptoms.get(13));
        pathologies.get(6).addSymptom(symptoms.get(14));

        return pathologies;
    }

    private List<Symptom> generateSymptoms() {
        var symptoms = new ArrayList<Symptom>();
        for (int i = 1; i <= 15; i++) {
            symptoms.add(new Symptom("Symptom " + i + " Description"));
        }
        return symptoms;
    }
}
