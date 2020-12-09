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

import resume.model.Education;

import java.util.ArrayList;
import java.util.List;

@Component
public class EducationsDao extends AbstractMongoDBDao {

    public static String EDUCATION_COLLECTION = "educations";

    private MongoCollection<Document> educationsCollection;

    private static Logger log = LoggerFactory.getLogger(EducationsDao.class.getName());

    @Autowired
    public EducationsDao(MongoClient mongoClient, @Value("${spring.mongodb.database}") String databaseName) {
        super(mongoClient, databaseName);
        educationsCollection = db.getCollection(EDUCATION_COLLECTION);
    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    public List<Document> getEducations(String id) {
        if (!ObjectId.isValid(id)) {
            return null;
        }
        List<Document> educations = new ArrayList<>();
        educationsCollection.find(eq("personID", new ObjectId(id))).iterator().forEachRemaining(educations::add);
        return educations;
    }

    public Document getEducation(String eduID) {
        if (!ObjectId.isValid(eduID)) {
            return null;
        }
        return educationsCollection.find(eq("_id", new ObjectId(eduID))).first();
    }

    public boolean deleteEducation(String eduID) {
        DeleteResult dr = null;

        try {
            dr = educationsCollection.deleteOne(eq("_id", new ObjectId(eduID)));
        } catch (MongoException e) {
            log.error("An error ocurred while trying to delete a education." + e.getMessage());
            return false;
        }
        return dr.getDeletedCount() > 0;
    }

    public boolean upsertEducation(Education edu) {
        UpdateResult res = null;
        ObjectId id = edu.getId() == null ? new ObjectId() : new ObjectId(edu.getId());
        Document eduDoc = new Document("_id", id);
        eduDoc.put("personID", new ObjectId(edu.getPersonID()));
        eduDoc.put("type", edu.getType());
        eduDoc.put("instituteName", edu.getInstituteName());
        eduDoc.put("location", edu.getLocation());
        eduDoc.put("grade", edu.getGrade());
        eduDoc.put("endDate", edu.getEndDate());
        eduDoc.put("startDate", edu.getStartDate());

        UpdateOptions options = new UpdateOptions();
        options.upsert(true);
        log.error(id.toHexString());
        try {
            res = educationsCollection.updateOne(new Document("_id", id), new Document("$set", eduDoc), options);
        } catch (MongoException e) {
            log.error("An error ocurred while trying to upsert a education. " + e.getMessage());
            return false;
        }
         return res.getModifiedCount() > 0 || res.getUpsertedId() != null || res.getMatchedCount() > 0;
    }
    
}
