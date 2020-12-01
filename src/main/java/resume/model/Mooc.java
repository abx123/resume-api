package resume.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Mooc {
    private final String name;
    private final String issuedBy;
    private final Date issueDate;
    private final String credentialID;
    private final String credentialURL;

    public Mooc(
        @JsonProperty("name") String name, 
        @JsonProperty("issuedBy") String issuedBy, 
        @JsonProperty("issueDate") Date issueDate, 
        @JsonProperty("credentialID") String credentialID, 
        @JsonProperty("credentialURL") String credentialURL
    ){
        this.name = name;
        this.issuedBy = issuedBy;
        this.issueDate = issueDate;
        this.credentialID = credentialID;
        this.credentialURL = credentialURL;
    }
    
    public String getName(){
        return name;
    }

    public String getIssuedBy(){
        return issuedBy;
    }

    public Date getissueDate(){
        return issueDate;
    }

    public String getCredentialID(){
        return credentialID;
    }

    public String gerCredentialURL(){
        return credentialURL;
    }

}
