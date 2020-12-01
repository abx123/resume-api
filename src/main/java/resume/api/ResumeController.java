package resume.api;

import resume.service.ResumeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;


import resume.model.Mooc;

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

    @PostMapping("/Mooc")
    public void addMooc(@RequestBody Mooc mooc){
        System.out.println(ReflectionToStringBuilder.toString(mooc));

        resumeService.addMOOC(mooc);
    }
}
