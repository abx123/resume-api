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

import resume.model.Mooc;

import java.util.ArrayList;
import java.util.List;

@Component
public class MoocsDao extends AbstractMongoDBDao {

    public static String MOOC_COLLECTION = "moocs";

    private MongoCollection<Document> moocsCollection;

    private static Logger log = LoggerFactory.getLogger(MoocsDao.class.getName());

    @Autowired
    public MoocsDao(MongoClient mongoClient, @Value("${spring.mongodb.database}") String databaseName) {
        super(mongoClient, databaseName);
        moocsCollection = db.getCollection(MOOC_COLLECTION);
    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    public List<Document> getMoocs(String id) {
        if (!ObjectId.isValid(id)) {
            return null;
        }  
        List<Document> moocs = new ArrayList<>();
        moocsCollection.find(eq("personID", new ObjectId(id))).iterator().forEachRemaining(moocs::add);
        return moocs;
    }

    public Document getMooc(String moocID) {
        if (!ObjectId.isValid(moocID)) {
            return null;
        }
        return moocsCollection.find(eq("_id", new ObjectId(moocID))).first();
    }

    public boolean deleteMooc(String moocID) {
        DeleteResult dr = null;

        try {
            dr = moocsCollection.deleteOne(eq("_id", new ObjectId(moocID)));
        } catch (MongoException e) {
            log.error("An error ocurred while trying to delete a mooc." + e.getMessage());
            return false;
        }
        return dr.getDeletedCount() > 0;
    }

    public boolean upsertMooc(Mooc mooc) {
        UpdateResult res = null;
        ObjectId id = mooc.getId() == null ? new ObjectId() : new ObjectId(mooc.getId());
        Document moocDoc = new Document("_id", id);
        moocDoc.put("credentialID", mooc.getCredentialID());
        moocDoc.put("credentialURL", mooc.getCredentialURL().toString());
        moocDoc.put("issueDate", mooc.getIssueDate());
        moocDoc.put("issuedBy", mooc.getIssuedBy());
        moocDoc.put("name", mooc.getName());
        moocDoc.put("personID", new ObjectId(mooc.getPersonID()));
        moocDoc.put("thumbnailURL", mooc.getThumbnailURL().toString());

        UpdateOptions options = new UpdateOptions();
        options.upsert(true);
        log.error(id.toHexString());
        try {
            res = moocsCollection.updateOne(new Document("_id", id), new Document("$set", moocDoc), options);
        } catch (MongoException e) {
            log.error("An error ocurred while trying to upsert a mooc. " + e.getMessage());
            return false;
        }
         return res.getModifiedCount() > 0 || res.getUpsertedId() != null || res.getMatchedCount() > 0;
    }
}
