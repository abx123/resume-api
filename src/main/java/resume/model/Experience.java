package resume.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Experience {
    private String id;
    private String personID;
    private String title;
    private String companyName;
    private String location;
    private Date startDate;
    private Date endDate;
    private List<String> descriptions;


    public Experience(
        @JsonProperty("id") String id, 
        @JsonProperty("personID") String personID, 
        @JsonProperty("title") String title, 
        @JsonProperty("companyName") String companyName, 
        @JsonProperty("location") String location, 
        @JsonProperty("startDate") Date startDate, 
        @JsonProperty("endDate") Date endDate,
        @JsonProperty("description") List<String> descriptions

    ){
        this.id = id;
        this.personID = personID;
        this.title = title;
        this.companyName = companyName;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.descriptions = descriptions;
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

        public String getTitle(){
            return title;
        }
    
        public void setTitle(String title){
            this.title = title;
        }

        public String getCompanyName(){
            return companyName;
        }
    
        public void setCompanyName(String companyName){
            this.companyName = companyName;
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

        public List<String> getDescriptions(){
            return descriptions;
        }
    
        public void setDescription(List<String> descriptions){
            this.descriptions = descriptions;
        }
    }
    

