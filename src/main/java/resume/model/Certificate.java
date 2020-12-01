package resume.model;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Certificate {
    private final UUID id;
    private final String name;
    private final String issuedBy;
    private final Date issueDate;
    private final Date expireDate;
    private final String credentialID;
    private final String credentialURL;

    public Certificate(
        @JsonProperty("id") UUID id, 
        @JsonProperty("name") String name, 
        @JsonProperty("issuedBy") String issuedBy, 
        @JsonProperty("issueDate") Date issueDate, 
        @JsonProperty("expireDate") Date expireDate, 
        @JsonProperty("credentialID") String credentialID, 
        @JsonProperty("credentialURL") String credentialURL
    ){
        this.id = id;
        this.name = name;
        this.issuedBy = issuedBy;
        this.issueDate = issueDate;
        this.expireDate = expireDate;
        this.credentialID = credentialID;
        this.credentialURL = credentialURL;
    }

    public UUID getId(){
        return id;
    }
    
    public String getName(){
        return name;
    }

    public String getIssuedBy(){
        return issuedBy;
    }

    public Date getIssueDate(){
        return issueDate;
    }

    public Date getExpireDate(){
        return expireDate;
    }

    public String getCredentialID(){
        return credentialID;
    }

    public String gerCredentialURL(){
        return credentialURL;
    }

}
