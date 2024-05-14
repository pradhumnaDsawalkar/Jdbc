import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;

public class MongoDBExample {

    public static void main(String[] args) {
        // Connect to MongoDB
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            // Get database
            MongoDatabase database = mongoClient.getDatabase("your_database_name");

            // Get collection
            MongoCollection<Document> collection = database.getCollection("Teacher_info");

            // Display all data
            System.out.println("All records in Teacher_info collection:");
            displayData(collection);

            // Delete record of one teacher specifying the condition (Example: delete record with Teacher_id = "Pic001")
            deleteRecord(collection, "Teacher_id", "Pic001");

            // Display data after deletion
            System.out.println("\nData after deletion:");
            displayData(collection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to display all data in the collection
    public static void displayData(MongoCollection<Document> collection) {
        FindIterable<Document> documents = collection.find();
        for (Document document : documents) {
            System.out.println(document.toJson());
        }
    }

    // Method to delete record based on condition
    public static void deleteRecord(MongoCollection<Document> collection, String fieldName, String value) {
        collection.deleteOne(Filters.eq(fieldName, value));
        System.out.println("Record with " + fieldName + " = " + value + " deleted successfully.");
    }
}
