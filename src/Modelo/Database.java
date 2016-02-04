package Modelo;

import com.mongodb.MongoClient;
import java.net.UnknownHostException;

public class Database {
    
    public static MongoClient crearConexion() {
	MongoClient mongo = null;
	try {
            mongo = new MongoClient("localhost", 27017);
	} catch (UnknownHostException e) {
            e.printStackTrace();
	}
	return mongo;
    }
}