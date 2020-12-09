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

import resume.model.Experience;
import java.util.ArrayList;
import java.util.List;

@Component
public class ExperiencesDao extends AbstractMongoDBDao {

    public static String EDUCATION_COLLECTION = "experiences";

    private MongoCollection<Document> experiencesCollection;

    private static Logger log = LoggerFactory.getLogger(ExperiencesDao.class.getName());

    @Autowired
    public ExperiencesDao(MongoClient mongoClient, @Value("${spring.mongodb.database}") String databaseName) {
        super(mongoClient, databaseName);
        experiencesCollection = db.getCollection(EDUCATION_COLLECTION);
    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    public List<Document> getExperiences(String id) {
        if (!ObjectId.isValid(id)) {
            return null;
        }
        List<Document> experiences = new ArrayList<>();
        experiencesCollection.find(eq("personID", new ObjectId(id))).iterator().forEachRemaining(experiences::add);
        return experiences;
    }

    public Document getExperience(String expID) {
        if (!ObjectId.isValid(expID)) {
            return null;
        }
        return experiencesCollection.find(eq("_id", new ObjectId(expID))).first();
    }

    public boolean deleteExperience(String expID) {
        DeleteResult dr = null;

        try {
            dr = experiencesCollection.deleteOne(eq("_id", new ObjectId(expID)));
        } catch (MongoException e) {
            log.error("An error ocurred while trying to delete a experience." + e.getMessage());
            return false;
        }
        return dr.getDeletedCount() > 0;
    }

    public boolean upsertExperience(Experience exp) {
        UpdateResult res = null;
        ObjectId id = exp.getId() == null ? new ObjectId() : new ObjectId(exp.getId());
        Document expDoc = new Document("_id", id);
        expDoc.put("personID", new ObjectId(exp.getPersonID()));
        expDoc.put("title", exp.getTitle());
        expDoc.put("companyName", exp.getCompanyName());
        expDoc.put("location", exp.getLocation());
        expDoc.put("description", exp.getDescriptions());
        expDoc.put("endDate", exp.getEndDate());
        expDoc.put("startDate", exp.getStartDate());

        UpdateOptions options = new UpdateOptions();
        options.upsert(true);
        log.error(id.toHexString());
        try {
            res = experiencesCollection.updateOne(new Document("_id", id), new Document("$set", expDoc), options);
        } catch (MongoException e) {
            log.error("An error ocurred while trying to upsert a experience. " + e.getMessage());
            return false;
        }
         return res.getModifiedCount() > 0 || res.getUpsertedId() != null || res.getMatchedCount() > 0;
    }

}
