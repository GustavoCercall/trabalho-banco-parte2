package persistencia;

import static com.mongodb.client.model.Filters.eq;

import com.google.gson.Gson;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import dados.Artista;
import dados.Musica;
import dados.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import org.bson.Document;
import org.bson.conversions.Bson;

public class ArtistaDAO implements DAO<Artista> {

	private static ArtistaDAO instance = null;
	private static MusicaDAO musicaDAO = null;
	private static MongoCollection<Document> artistaCollection = null;

	private PreparedStatement selectNewId;
	private PreparedStatement insert;
	private PreparedStatement delete;
	private PreparedStatement deleteArtistaMusica;
	private PreparedStatement deleteArtistaUsuario;
	private PreparedStatement selectAll;
	private PreparedStatement update;

	private ArtistaDAO() throws ClassNotFoundException, SQLException {

		MongoDatabase conexao = Conexao.getConexao();
		MongoCollection<Document> artista = conexao.getCollection("artista");
		artistaCollection = artista;

//		selectNewId = conexao.prepareStatement("select nextval('id_artista_seq')");
//		insert = conexao.prepareStatement("insert into artista values (?,?,?)");
//		delete = conexao.prepareStatement("delete from artista where id_artista = ?"); //DELETA DA TABELA ARTISTA
//		deleteArtistaMusica = conexao.prepareStatement("delete from musica where id_artista = ?"); //DELETA DA TABELA MUSICA
//		deleteArtistaUsuario = conexao.prepareStatement("delete from usuario_artista where id_artista = ?"); //DELETA DA TABELA USUARIO_ARTISTA
//		selectAll = conexao.prepareStatement("select * from artista");
//		update = conexao.prepareStatement("update artista set nome = ?, genero_musical = ? where id_artista = ?");

		musicaDAO = MusicaDAO.getInstance();
	}

	public static ArtistaDAO getInstance() throws ClassNotFoundException, SQLException {
		if (instance == null) {
			instance = new ArtistaDAO();
		}
		return instance;
	}

	public int selectNewId() throws Exception {

			ResultSet rs = selectNewId.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			
		}

		return 0;
	}

	public void insert(Artista artista) throws Exception {
		Document artistaclasse = new Document();
		artistaclasse.append("id",  new Random().nextInt(100000));
		artistaclasse.append("nome", artista.getNome());
		artistaclasse.append("generoMusical", artista.getGeneroMusical());

		artistaCollection.insertOne(artistaclasse);
			//artista.setId(selectNewId());

//			insert.setInt(1, artista.getId());
//			insert.setString(2, artista.getNome());
//			insert.setString(3, artista.getGeneroMusical());
//			insert.executeUpdate();
//
			
		} 
	

	public void delete(Artista a) throws Exception {
		List<Musica> musicas = new LinkedList<Musica>();
		musicas = musicaDAO.selectMusicasArtista(a.getId());
		for(int i=0; i<musicas.size(); i++) {
			musicaDAO.delete(musicas.get(i));
		}
		artistaCollection.deleteOne(new Document("id", a.getId()));
		
//			deleteArtistaMusica.setInt(1, a.getId());
//			deleteArtistaMusica.executeUpdate();
//			deleteArtistaUsuario.setInt(1, a.getId());
//			deleteArtistaUsuario.executeUpdate();
//			delete.setInt(1, a.getId());
//			delete.executeUpdate()

	}

	public List<Artista> selectAll() throws Exception {
		List<Artista> artistas = new LinkedList<Artista>();
		List<Musica> musicas = new LinkedList<Musica>();
		FindIterable<Document> artistalista =  artistaCollection.find();
		for(Document artista:artistalista){
			Artista artista1 = new Gson().fromJson(artista.toJson(), Artista.class);
			musicas = musicaDAO.getInstance().selectMusicasArtista(artista1.getId());
			artistas.add(new Artista(artista1.getId(), artista1.getNome(), artista1.getGeneroMusical(), musicas));

		}


//			while (rs.next()) {
//				int id = rs.getInt(1);
//				String nome = rs.getString(2);
//				System.out.println(nome);
//				String genero_musical = rs.getString(3);
//				System.out.println(genero_musical);
//			musicas = musicaDAO.selectMusicasArtista(id);
//				Artista aux = new Artista();
//				aux.setId(id);
//				aux.setNome(nome);
//				aux.setGeneroMusical(genero_musical);
//				aux.setMusicas(musicas);

//				artistas.add(aux);
//			}
			
		
		return artistas;
	}

	public void update(Artista artista) throws Exception {

		Document artistaupdate = new Document();

		artistaupdate.append("nome", artista.getNome());
		artistaupdate.append("generoMusical", artista.getGeneroMusical());

		if(!artista.getMusicas().isEmpty())
		{
			ArrayList<Document> teste = new ArrayList<>();
			for (Musica musica : artista.getMusicas())
			{
				Document musicadoc = new Document();
				musicadoc.append("id", musica.getId());
				musicadoc.append("nome", musica.getNome());
				musicadoc.append("id_artista", musica.getId_artista());
				teste.add(musicadoc);
			}
			;

			artistaupdate.append("musicas", teste);
		}

		Bson filtro = eq("id", artista.getId());
		artistaCollection.updateOne(filtro, new Document("$set", artistaupdate));


//			update.setString(1, artista.getNome());
//			update.setString(2, artista.getGeneroMusical());
//			update.setInt(3, artista.getId());
//			update.executeUpdate();
		
	}

}
