package resume.api;

import resume.model.*;

// import java.util.List;
// import java.util.UUID;

// import resume.service.MovieService;
// import resume.service.ResumeService;
import resume.service.ResumeService;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// import io.micrometer.core.lang.NonNull;

// import org.apache.commons.lang3.builder.ReflectionToStringBuilder;


// import resume.model.Mooc;
// import resume.model.Certificate;

@RestController
public class ResumeController {
    // private final ResumeService resumeService;
    private final ResumeService resumeService;
    
    @Autowired
    public ResumeController(ResumeService resumeService){
        this.resumeService = resumeService;
    }

    // @Autowired
    // public ResumeController(ResumeService resumeService){
    //     this.resumeService = resumeService;
    // }

    @GetMapping(value="/{personID}")
    public Resume getResume(@PathVariable("personID") String id){
        return this.resumeService.getResume(id);
    }

    @GetMapping(value="/person/{id}")
    public Person getPerson(@PathVariable("id") String id){
        return this.resumeService.getPerson(id);
    }

    @GetMapping(value="/experiences/{id}")
    public List<Experience> getExperiences(@PathVariable("id") String id){
        return this.resumeService.getExperiences(id);
    }
    // @GetMapping(value="/")
    // public String getResume(){
    //     return resumeService.getResume();
    // }
   
    @GetMapping("/certificates/{id}")
    public List<Certificate> getCerts(@PathVariable("id") String id){
        // System.out.println(ReflectionToStringBuilder.toString(cert));
        return resumeService.getCertifications(id);
    }

    @GetMapping("/moocs/{id}")
    public List<Mooc> getMoocs(@PathVariable("id") String id){
        // System.out.println(ReflectionToStringBuilder.toString(cert));
        return resumeService.getMoocs(id);
    }


    // @GetMapping("/Certificate/{id}")
    // public Certificate getCert(@PathVariable("id") UUID id){
    //     System.out.println(id);
    //     return resumeService.getCert(id).orElse(null);
    // }

    // @PostMapping("/Certificate")
    // public int upsertCertificate(@NonNull @RequestBody Certificate cert){
    //     System.out.println(ReflectionToStringBuilder.toString(cert));
    //     return resumeService.upsertCert(cert);
    // }

    // @DeleteMapping("/Certificate/{id}")
    // public int deleteCert(@PathVariable("id") UUID id){
    //     return resumeService.deleteCert(id);
    // }

    // @PostMapping("/Mooc")
    // public void addMooc(@NonNull @RequestBody Mooc mooc){
    //     System.out.println(ReflectionToStringBuilder.toString(mooc));

    //     resumeService.addMOOC(mooc);
    // }

}
