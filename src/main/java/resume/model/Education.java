package resume.model;

import java.util.Date;
import java.util.List;
import java.net.URL;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Education {
    private String id;
    private String personID;
    private String type;
    private String instituteName;
    private String location;
    private Date startDate;
    private Date endDate;
    private String grade;


public Education(
        @JsonProperty("id") String id, 
        @JsonProperty("personID") String personID, 
        @JsonProperty("type") String type, 
        @JsonProperty("instituteName") String instituteName, 
        @JsonProperty("location") String location, 
        @JsonProperty("startDate") Date startDate, 
        @JsonProperty("endDate") Date endDate,
        @JsonProperty("grade") String grade

    ){
        this.id = id;
        this.personID = personID;
        this.type = type;
        this.instituteName = instituteName;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.grade = grade;
    }
        public String getId(){
            return id;
        }
    
        public void setId(String id){
            this.id = id;
        }

        public String getPersonID(){
            return personID;
        }
    
        public void setPersonID(String personID){
            this.personID = personID;
        }

        public String getType(){
            return type;
        }
    
        public void setType(String type){
            this.type = type;
        }

        public String getInstituteName(){
            return instituteName;
        }
    
        public void setInstituteName(String instituteName){
            this.instituteName = instituteName;
        }

        public String getLocation(){
            return location;
        }
    
        public void setLocation(String location){
            this.location = location;
        }

        public Date getStartDate(){
            return startDate;
        }
    
        public void setStartDate(Date startDate){
            this.startDate = startDate;
        }

        public Date getEndDate(){
            return endDate;
        }
    
        public void setEndDate(Date endDate){
            this.endDate = endDate;
        }

        public String getGrade(){
            return grade;
        }
    
        public void setGrade(String grade){
            this.grade = grade;
        }
    }
    
