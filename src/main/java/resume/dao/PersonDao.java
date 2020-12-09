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

import resume.model.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDao extends AbstractMongoDBDao {

    public static String PERSON_COLLECTION = "person";

    private MongoCollection<Document> personCollection;

    private static Logger log = LoggerFactory.getLogger(PersonDao.class.getName());

    @Autowired
    public PersonDao(MongoClient mongoClient, @Value("${spring.mongodb.database}") String databaseName) {
        super(mongoClient, databaseName);
        personCollection = db.getCollection(PERSON_COLLECTION);
    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    public List<Document> getPersons() {
        List<Document> persons = new ArrayList<>();
        personCollection.find().iterator().forEachRemaining(persons::add);
        return persons;
    }

    public Document getPerson(String id) {
        if (!ObjectId.isValid(id)) {
            return null;
        }
        Document person = personCollection.find(eq("_id", new ObjectId(id))).first();
        return person;
    }

    public boolean deletePerson(String personID) {
        DeleteResult dr = null;

        try {
            dr = personCollection.deleteOne(eq("_id", new ObjectId(personID)));
        } catch (MongoException e) {
            log.error("An error ocurred while trying to delete a person." + e.getMessage());
            return false;
        }
        return dr.getDeletedCount() > 0;
    }

    public boolean upsertPerson(Person person) {
        UpdateResult res = null;
        ObjectId id = person.getId() == null ? new ObjectId() : new ObjectId(person.getId());
        Document personDoc = new Document("_id", id);
        personDoc.put("name", person.getName());
        personDoc.put("title", person.getTitle());
        personDoc.put("phoneNumber", person.getPhoneNumber());
        personDoc.put("address", person.getAddress());
        personDoc.put("summary", person.getSummary());
        personDoc.put("skills", person.getSkills());
        
        UpdateOptions options = new UpdateOptions();
        options.upsert(true);
        log.error(id.toHexString());
        try {
            res = personCollection.updateOne(new Document("_id", id), new Document("$set", personDoc), options);
        } catch (MongoException e) {
            log.error("An error ocurred while trying to upsert a person. " + e.getMessage());
            return false;
        }
         return res.getModifiedCount() > 0 || res.getUpsertedId() != null || res.getMatchedCount() > 0;
    }
}
