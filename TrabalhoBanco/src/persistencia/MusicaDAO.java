package persistencia;

import static com.mongodb.client.model.Filters.eq;

import com.google.gson.Gson;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import dados.Artista;
import dados.Musica;
import dados.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import org.bson.Document;
import org.bson.conversions.Bson;


public class MusicaDAO implements DAO<Musica>  {

	private static MusicaDAO instance = null;
	private static MongoCollection<Document> musicaCollection = null;
	private static UsuarioDAO instanciaUsuario = null;

	private PreparedStatement selectNewId;
	private PreparedStatement select;
	private PreparedStatement selectAll;
	private PreparedStatement selectMusicasPlaylist;
	private PreparedStatement insert;
	private PreparedStatement delete;
	private PreparedStatement deleteMusicaUsuario;
	private PreparedStatement deleteMusicaPlaylist;
	private PreparedStatement update;

	private MusicaDAO() throws ClassNotFoundException, SQLException{

		MongoDatabase conexao = Conexao.getConexao();
		MongoCollection<Document> musica = conexao.getCollection("musica");
		musicaCollection = musica;
		instanciaUsuario = UsuarioDAO.getInstance();

//		selectNewId = conexao.prepareStatement("select nextval('id_musica_seq')");
//		select = conexao.prepareStatement("select * from musica where id_artista = ?");
//		selectAll = conexao.prepareStatement("select * from musica");
//		selectMusicasPlaylist = conexao.prepareStatement("select * from musica_playlist where id_playlist = ? ");
//		insert = conexao.prepareStatement("insert into musica values (?,?,?)");
//		delete = conexao.prepareStatement("delete from musica where id_musica = ?");
//		deleteMusicaUsuario = conexao.prepareStatement("delete from usuario_musica where id_musica = ?");
//		deleteMusicaPlaylist = conexao.prepareStatement("delete from musica_playlist where id_musica = ?");
//
//		update = conexao.prepareStatement("update musica set nome = ?, id_artista = ? where id_musica = ?");

	}

	public static MusicaDAO getInstance() throws ClassNotFoundException, SQLException {
		if (instance == null) {
			instance = new MusicaDAO();
		}
		return instance;
	}

	private int selectNewId() throws Exception {
			ResultSet rs = selectNewId.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		
		return 0;
	}

	public void insert(Musica musica) throws Exception {
		Document musicaclasse = new Document();
		musicaclasse.append("id",  new Random().nextInt(100000));
		musicaclasse.append("nome", musica.getNome());
		musicaclasse.append("id_artista", musica.getId_artista());

		musicaCollection.insertOne(musicaclasse);
		
//			insert.setInt(1, selectNewId());
//			insert.setString(2, musica.getNome());
//			insert.setInt(3, musica.getId_artista());
//			insert.executeUpdate();
		
	}

	public List<Musica> selectMusicasArtista(int artista) throws Exception {
		
//		select.setInt(1, artista);
//		ResultSet rs = select.executeQuery();
	    List<Musica> musicas = new LinkedList<Musica>();
		Bson filtro = Filters.eq("id_artista", artista);
		FindIterable<Document>listamusicas = musicaCollection.find(filtro);

		for(Document musica1:listamusicas){
			Musica musica2 = new Gson().fromJson(musica1.toJson(), Musica.class);
			musicas.add(new Musica(musica2.getId(), musica2.getNome(), musica2.getId_artista()));
		}



//			while(rs.next()) {
//
//				int id = rs.getInt(1);
//				String nome = rs.getString(2);
//
//				Musica m = new Musica(id, nome, artista);
//				musicas.add(m);
//
//			}
//
    	return musicas;
//
	}
	
	public List<Integer> selectMusicasPlaylist(int playlist) throws Exception{ //retorna uma lista de ids das músicas da playlist passada como parâmetro
		selectMusicasPlaylist.setInt(1, playlist);
		ResultSet rs = selectMusicasPlaylist.executeQuery();
	    List<Integer> ids_musicas = new LinkedList<Integer>();
	    while(rs.next()) {

			int id_musica = rs.getInt(2);
			ids_musicas.add(id_musica);

		}

	    return ids_musicas;
	    
	}

	public void delete(Musica musica) throws Exception {
			Bson filtro = Filters.eq("id", musica);
			musicaCollection.deleteOne(new Document("id", musica.getId()));
			instanciaUsuario.deleteMusicaLista(musica.getId());

//
//			deleteMusicaUsuario.setInt(1, musica.getId());
//			deleteMusicaUsuario.executeUpdate();
//			deleteMusicaPlaylist.setInt(1, musica.getId());
//			deleteMusicaPlaylist.executeUpdate();
//			delete.setInt(1, musica.getId());
//			delete.executeUpdate();
		

	}

	public void update(Musica musica) throws Exception {
		Document musicaupdate = new Document();
		musicaupdate.append("id", musica.getId());
		musicaupdate.append("nome", musica.getNome());
		musicaupdate.append("id_artista", musica.getId_artista());


		Bson filtro = eq("id", musica.getId());
		musicaCollection.updateOne(filtro, new Document("$set", musicaupdate));

		
//			update.setString(1, musica.getNome());
//			update.setInt(2, musica.getId_artista());
//			update.setInt(3, musica.getId());
//			update.executeUpdate();
		
	}
	
	public List<Musica> selectAll() throws Exception {
		List<Musica> musicas = new LinkedList<Musica>();
		FindIterable<Document> musicalista =  musicaCollection.find();
		for(Document musica:musicalista){
			Musica musicas1 = new Gson().fromJson(musica.toJson(), Musica.class);
			musicas.add(new Musica(musicas1.getId(), musicas1.getNome(), musicas1.getId_artista()));
		}
//			ResultSet rs = selectAll.executeQuery();
//			while (rs.next()) {
//				int id = rs.getInt(1);
//				String nome = rs.getString(2);
//				System.out.println(nome);
//				int id_artista = rs.getInt(3);
				
	//			musicas.add(new Musica(id, nome, id_artista));
	//		}
		
		return musicas;
	}

}
