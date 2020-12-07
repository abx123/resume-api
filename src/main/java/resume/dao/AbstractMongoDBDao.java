package resume.dao;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Configuration
public abstract class AbstractMongoDBDao {

    protected final String DATABASE;
    protected MongoDatabase db;
    protected MongoClient mongoClient;
    @Value("${spring.mongodb.uri}")
    private String connectionString;

    protected AbstractMongoDBDao(MongoClient mongoClient222, String databaseName) {
        // this.mongoClient = MongoClients.create(new ConnectionString(connectionString));

        this.mongoClient = mongoClient222;
        DATABASE = databaseName;
        this.db = this.mongoClient.getDatabase(DATABASE);
    }

    public ObjectId generateObjectId() {
        return new ObjectId();
    }

    public Map<String, Object> getConfiguration() {
        ConnectionString connString = new ConnectionString(connectionString);
        Bson command = new Document("connectionStatus", 1);
        Document connectionStatus = this.mongoClient.getDatabase(DATABASE).runCommand(command);

        List authUserRoles =
                ((Document) connectionStatus.get("authInfo")).get("authenticatedUserRoles", List.class);

        Map<String, Object> configuration = new HashMap<>();

        if (!authUserRoles.isEmpty()) {
            configuration.put("role", ((Document) authUserRoles.get(0)).getString(
                    "role"));
            configuration.put("pool_size", connString.getMaxConnectionPoolSize());
            configuration.put(
                    "wtimeout",
                    this.mongoClient
                            .getDatabase(DATABASE)
                            .getWriteConcern()
                            .getWTimeout(TimeUnit.MILLISECONDS));
        }
        return configuration;
    }
}
