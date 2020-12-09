package resume.api;

import resume.model.*;
import resume.service.ResumeService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.lang.NonNull;

@RestController
public class ResumeController {
    private final ResumeService resumeService;
    
    @Autowired
    public ResumeController(ResumeService resumeService){
        this.resumeService = resumeService;
    }

    @GetMapping(value="/resume/{personID}")
    public Resume getResume(@PathVariable("personID") String id){
        return this.resumeService.getResume(id);
    }

    @GetMapping(value="/persons")
    public List<Person> getPersons(){
        return this.resumeService.getPersons();
    }

    @GetMapping("/person/{personID}")
    public Person getPerson(@PathVariable("personID") String personID){
        return resumeService.getPerson(personID);
    }

    @PostMapping("/person")
    public ResponseEntity upsertPerson(@NonNull @RequestBody Person person){
        Map<String, Object> results = new HashMap<>();
        if (!resumeService.upsertPerson(person, results)) {
            return ResponseEntity.badRequest().body(results);
        }
        results.put("upsert", "ok");
        return ResponseEntity.ok(results);
    }

    @DeleteMapping("/person/{personID}")
    public ResponseEntity deletePerson(@PathVariable("personID") String personID){
        Map<String, Object> results = new HashMap<>();
        if (!resumeService.deletePerson(personID, results)) {
            return ResponseEntity.badRequest().body(results);
        }
        results.put("delete", "ok");
        return ResponseEntity.ok(results);
    }

    @GetMapping(value="/educations")
    public List<Education> getEducations(@PathVariable("id") String id){
        return this.resumeService.getEducations(id);
    }

    @GetMapping("/education/{eduID}")
    public Education getEducation(@PathVariable("eduID") String eduID){
        return resumeService.getEducation(eduID);
    }

    @PostMapping("/education")
    public ResponseEntity upsertEducation(@NonNull @RequestBody Education edu){
        Map<String, Object> results = new HashMap<>();
        if (!resumeService.upsertEducation(edu, results)) {
            return ResponseEntity.badRequest().body(results);
        }
        results.put("upsert", "ok");
        return ResponseEntity.ok(results);
    }

    @DeleteMapping("/education/{eduID}")
    public ResponseEntity deleteEducation(@PathVariable("eduID") String eduID){
        Map<String, Object> results = new HashMap<>();
        if (!resumeService.deleteEducation(eduID, results)) {
            return ResponseEntity.badRequest().body(results);
        }
        results.put("delete", "ok");
        return ResponseEntity.ok(results);
    }

    @GetMapping(value="/experiences/{id}")
    public List<Experience> getExperiences(@PathVariable("id") String id){
        return this.resumeService.getExperiences(id);
    }

    @GetMapping("/experience/{expID}")
    public Experience getExperience(@PathVariable("expID") String expID){
        return resumeService.getExperience(expID);
    }

    @PostMapping("/experience")
    public ResponseEntity upsertExperience(@NonNull @RequestBody Experience exp){
        Map<String, Object> results = new HashMap<>();
        if (!resumeService.upsertExperience(exp, results)) {
            return ResponseEntity.badRequest().body(results);
        }
        results.put("upsert", "ok");
        return ResponseEntity.ok(results);
    }

    @DeleteMapping("/experience/{expID}")
    public ResponseEntity deleteExperience(@PathVariable("expID") String expID){
        Map<String, Object> results = new HashMap<>();
        if (!resumeService.deleteExperience(expID, results)) {
            return ResponseEntity.badRequest().body(results);
        }
        results.put("delete", "ok");
        return ResponseEntity.ok(results);
    }

    @GetMapping("/moocs/{id}")
    public List<Mooc> getMoocs(@PathVariable("id") String id){
        return resumeService.getMoocs(id);
    }

    @GetMapping("/mooc/{moocID}")
    public Mooc getMooc(@PathVariable("moocID") String moocID){
        return resumeService.getMooc(moocID);
    }

    @PostMapping("/mooc")
    public ResponseEntity upsertMooc(@NonNull @RequestBody Mooc mooc){
        Map<String, Object> results = new HashMap<>();
        if (!resumeService.upsertMooc(mooc, results)) {
            return ResponseEntity.badRequest().body(results);
        }
        results.put("upsert", "ok");
        return ResponseEntity.ok(results);
    }

    @DeleteMapping("/mooc/{moocID}")
    public ResponseEntity deleteMooc(@PathVariable("moocID") String moocID){
        Map<String, Object> results = new HashMap<>();
        if (!resumeService.deleteMooc(moocID, results)) {
            return ResponseEntity.badRequest().body(results);
        }
        results.put("delete", "ok");
        return ResponseEntity.ok(results);
    }

    @GetMapping("/certificates/{id}")
    public List<Certificate> getCerts(@PathVariable("id") String id){
        return resumeService.getCertifications(id);
    }

    @GetMapping("/certificate/{certID}")
    public Certificate getCert(@PathVariable("certID") String certID){
        return resumeService.getCertificate(certID);
    }

    @PostMapping("/certificate")
    public ResponseEntity upsertCertificate(@NonNull @RequestBody Certificate cert){
        Map<String, Object> results = new HashMap<>();
        if (!resumeService.upsertCertificate(cert, results)) {
            return ResponseEntity.badRequest().body(results);
        }
        results.put("upsert", "ok");
        return ResponseEntity.ok(results);
    }

    @DeleteMapping("/certificate/{certID}")
    public ResponseEntity deleteCert(@PathVariable("certID") String certID){
        Map<String, Object> results = new HashMap<>();
        if (!resumeService.deleteCertificate(certID, results)) {
            return ResponseEntity.badRequest().body(results);
        }
        results.put("delete", "ok");
        return ResponseEntity.ok(results);
    }

    @GetMapping("/")
    public ResponseEntity addMooc(){
        Map<String, Object> results = new HashMap<>();
        results.put("status", "ok");
        return ResponseEntity.ok(results);
    }
}
