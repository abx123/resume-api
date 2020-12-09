package resume.dao;

import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.MongoException;
import com.mongodb.client.model.*;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import resume.model.Certificate;

import java.util.ArrayList;
import java.util.List;

@Component
public class CertificatesDao extends AbstractMongoDBDao {

    public static String CERTIFICATE_COLLECTION = "certificates";

    private MongoCollection<Document> certificatesCollection;

    private static Logger log = LoggerFactory.getLogger(CertificatesDao.class.getName());

    @Autowired
    public CertificatesDao(MongoClient mongoClient, @Value("${spring.mongodb.database}") String databaseName) {
        super(mongoClient, databaseName);
        certificatesCollection = db.getCollection(CERTIFICATE_COLLECTION);
    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    public List<Document> getCertifications(String id) {
        if (!ObjectId.isValid(id)) {
            return null;
        }
        List<Document> experiences = new ArrayList<>();
        certificatesCollection.find(eq("personID", new ObjectId(id))).iterator().forEachRemaining(experiences::add);
        return experiences;
    }

    public Document getCertificate(String certificateID) {
        if (!ObjectId.isValid(certificateID)) {
            return null;
        }
        return certificatesCollection.find(eq("_id", new ObjectId(certificateID))).first();
    }

    public boolean deleteCertificate(String certificateID) {
        DeleteResult dr = null;

        try {
            dr = certificatesCollection.deleteOne(eq("_id", new ObjectId(certificateID)));
        } catch (MongoException e) {
            log.error("An error ocurred while trying to delete a certificate." + e.getMessage());
            return false;
        }
        return dr.getDeletedCount() > 0;
    }

    public boolean upsertCertificate(Certificate cert) {
        UpdateResult res = null;
        ObjectId id = cert.getId() == null ? new ObjectId() : new ObjectId(cert.getId());
        Document certDoc = new Document("_id", id);
        certDoc.put("credentialID", cert.getCredentialID());
        certDoc.put("credentialURL", cert.getCredentialURL().toString());
        certDoc.put("expireDate", cert.getExpireDate());
        certDoc.put("issueDate", cert.getIssueDate());
        certDoc.put("issuedBy", cert.getIssuedBy());
        certDoc.put("name", cert.getName());
        certDoc.put("personID", new ObjectId(cert.getPersonID()));
        certDoc.put("thumbnailURL", cert.getThumbnailURL().toString());

        UpdateOptions options = new UpdateOptions();
        options.upsert(true);
        log.error(id.toHexString());
        try {
            res = certificatesCollection.updateOne(new Document("_id", id), new Document("$set", certDoc), options);
        } catch (MongoException e) {
            log.error("An error ocurred while trying to upsert a certificate. " + e.getMessage());
            return false;
        }

        return res.getModifiedCount() > 0 || res.getUpsertedId() != null || res.getMatchedCount() > 0;
    }
}
