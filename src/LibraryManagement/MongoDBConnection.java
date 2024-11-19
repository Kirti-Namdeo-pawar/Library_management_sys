package LibraryManagement;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDBConnection {
    private static final String HOST = "localhost";
    private static final int PORT = 27017;
    private static final String DATABASE_NAME = "library";
    
    private static MongoClient mongoClient;
    private static MongoDatabase database;
    
    // Initialize connection only once
    public static void connectToMongoDB() {
        if (mongoClient == null) {
            String connectionString = "mongodb://" + HOST + ":" + PORT;
            mongoClient = MongoClients.create(connectionString);
            database = mongoClient.getDatabase(DATABASE_NAME);
        }
    }
    
    // Get collections
    public static MongoCollection<Document> getBooksCollection() {
        connectToMongoDB();
        return database.getCollection("books");
    }

    public static MongoCollection<Document> getStudentsCollection() {
        connectToMongoDB();
        return database.getCollection("students");
    }

    public static MongoCollection<Document> getBorrowedBooksCollection() {
        connectToMongoDB();
        return database.getCollection("borrowed_books");
    }
    
    public static MongoCollection<Document> getIssuedBooksCollection() {
        connectToMongoDB();
        return database.getCollection("issued_books");
    }
    
    public static void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}
