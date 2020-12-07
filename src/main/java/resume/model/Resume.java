package resume.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Resume {

    private Person person;
    private List<Experience> experiences;
    private List<Education> educations;
    private List<Certificate> certifications;
    private List<Mooc> moocs;

    public Resume(
    @JsonProperty("person") Person person, 
    @JsonProperty("experience") List<Experience> experiences, 
    @JsonProperty("educations") List<Education> educations, 
    @JsonProperty("certifications") List<Certificate> certifications, 
    @JsonProperty("mooc") List<Mooc> moocs

){
    this.person = person;
    this.experiences = experiences;
    this.educations = educations;
    this.certifications = certifications;
    this.moocs = moocs;
    }
    
    public Person getPerson(){
        return person;
    }

    public void setPerson(Person person){
        this.person = person;
    }

    public List<Experience> getExperiences(){
        return experiences;
    }

    public void setExperiences(List<Experience> experiences){
        this.experiences = experiences;
    }

    public List<Education> getEducations(){
        return educations;
    }

    public void setEducations(List<Education> educations){
        this.educations = educations;
    }

    public List<Certificate> getCetrifications(){
        return certifications;
    }

    public void setCertifications(List<Certificate> certifications){
        this.certifications = certifications;
    }

    public List<Mooc> getMoocs(){
        return moocs;
    }

    public void setMoocs(List<Mooc> moocs){
        this.moocs = moocs;
    }
}
