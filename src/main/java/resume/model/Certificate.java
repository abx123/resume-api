package resume.model;

import java.util.Date;
import java.net.URL;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Certificate {
    private String id;
    private String personID;
    private String name;
    private String issuedBy;
    private Date issueDate;
    private Date expireDate;
    private String credentialID;
    private URL credentialURL;
    private URL thumbnailURL;

    public Certificate(@JsonProperty("id") String id, @JsonProperty("personID") String personID,
            @JsonProperty("name") String name, @JsonProperty("issuedBy") String issuedBy,
            @JsonProperty("issueDate") Date issueDate, @JsonProperty("expireDate") Date expireDate,
            @JsonProperty("credentialID") String credentialID, @JsonProperty("credentialURL") URL credentialURL,
            @JsonProperty("thumbnailURL") URL thumbnailURL) {
        this.id = id;
        this.personID = personID;
        this.name = name;
        this.issuedBy = issuedBy;
        this.issueDate = issueDate;
        this.expireDate = expireDate;
        this.credentialID = credentialID;
        this.credentialURL = credentialURL;
        this.thumbnailURL = thumbnailURL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIssuedBy() {
        return issuedBy;
    }

    public void setIssuedBy(String issuedBy) {
        this.issuedBy = issuedBy;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public String getCredentialID() {
        return credentialID;
    }

    public void setCredentialID(String credentialID) {
        this.credentialID = credentialID;
    }

    public URL getCredentialURL() {
        return credentialURL;
    }

    public void setCredentialURL(URL credentialURL) {
        this.credentialURL = credentialURL;
    }

    public URL getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(URL thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

}
