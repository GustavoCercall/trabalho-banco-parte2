package persistencia;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

public class Conexao
{

    private static MongoDatabase conexao = null;

    public static MongoDatabase getConexao() throws ClassNotFoundException, SQLException
    {

        if (conexao == null)
        {
             MongoClient mongoClient = MongoClients.create("mongodb+srv://gustavo:123@cluster0.u1g0xeb.mongodb.net/Trabalhobancoparte2?retryWrites=true&w=majority");
            
                MongoDatabase database = mongoClient.getDatabase("Trabalhobancoparte2");
                conexao = database;
            
//            String url = "jdbc:postgresql://localhost:5432/Trabalho_POO"; // conexao Jason
//            String usuario = "postgres";
//            Class.forName("org.postgresql.Driver");
//            conexao = DriverManager.getConnection(url, usuario, senha);
//        }

        }
        return conexao;
    }
}
