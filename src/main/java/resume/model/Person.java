package resume.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Person {
    // private String id;
    private String name;
    private String title;
    private String phoneNumber;
    private String address;
    private String summary;
    private List<String> skills;

    public Person(
        // @JsonProperty("id") String id, 
        @JsonProperty("name") String name, 
        @JsonProperty("title") String title, 
        @JsonProperty("phoneNumber") String phoneNumber, 
        @JsonProperty("address") String address, 
        @JsonProperty("summary") String summary,
        @JsonProperty("skills") List<String> skills
    ){
        // this.id = id;
        this.name = name;
        this.title = title;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.summary = summary;
        this.skills = skills;
    }
    
    // public String getId(){
    //     return id;
    // }

    // public void setId(String id){
    //     this.id = id;
    // }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getSummary(){
        return summary;
    }

    public void setSummary(String summary){
        this.summary = summary;
    }

    public List<String> getSkills(){
        return skills;
    }

    public void setSkills(List<String> skills){
        this.skills = skills;
    }
}
