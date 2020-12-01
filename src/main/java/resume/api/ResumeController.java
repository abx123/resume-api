package resume.api;

import java.util.List;
import java.util.UUID;

import resume.service.ResumeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.lang.NonNull;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;


import resume.model.Mooc;
import resume.model.Certificate;

@RestController
public class ResumeController {
    private final ResumeService resumeService;

    @Autowired
    public ResumeController(ResumeService resumeService){
        this.resumeService = resumeService;
    }

    @GetMapping(value="/")
    public String getResume(){
        return resumeService.getResume();
    }
   
    @GetMapping("/Certificates")
    public List<Certificate> getCerts(){
        // System.out.println(ReflectionToStringBuilder.toString(cert));
        return resumeService.getCerts();
    }

    @GetMapping("/Certificate/{id}")
    public Certificate getCert(@PathVariable("id") UUID id){
        System.out.println(id);
        return resumeService.getCert(id).orElse(null);
    }

    @PostMapping("/Certificate")
    public int upsertCertificate(@NonNull @RequestBody Certificate cert){
        System.out.println(ReflectionToStringBuilder.toString(cert));
        return resumeService.upsertCert(cert);
    }

    @DeleteMapping("/Certificate/{id}")
    public int deleteCert(@PathVariable("id") UUID id){
        return resumeService.deleteCert(id);
    }

    @PostMapping("/Mooc")
    public void addMooc(@NonNull @RequestBody Mooc mooc){
        System.out.println(ReflectionToStringBuilder.toString(mooc));

        resumeService.addMOOC(mooc);
    }

}
