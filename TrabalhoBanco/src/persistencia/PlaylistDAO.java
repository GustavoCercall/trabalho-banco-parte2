package persistencia;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import dados.Playlist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import dados.Musica;
import java.util.Random;
import org.bson.Document;

public class PlaylistDAO {
	
	private static PlaylistDAO instance = null;
	private static MusicaDAO musicaDAO = null;
	private static MongoCollection<Document> playlistCollection = null;

	
	
	private PreparedStatement selectNewId;
	private PreparedStatement insert; //cria a playlist
	private PreparedStatement selectNewIdMusicaPlaylist;
	private PreparedStatement insertMusicaPlaylist; //insere uma musica em uma playlist
	private PreparedStatement delete; //exclui a playlist
	private PreparedStatement deleteMusicasPlaylist; //remove todas as musicas de uma playlist que vai ser excluida 
	private PreparedStatement deletePlaylistUsuario;
	private PreparedStatement deleteMusicaPlaylist; //remove uma musica de uma playlist
	private PreparedStatement selectAll; //busca todas as playlists
	private PreparedStatement selectAllPlaylists; //busca todas as playlists

	private PreparedStatement update; //edita nome da playlist 
	
	private PlaylistDAO() throws ClassNotFoundException, SQLException {

		MongoDatabase conexao = Conexao.getConexao();
		MongoCollection<Document> playlist = conexao.getCollection("playlist");

//		selectNewId = conexao.prepareStatement("select nextval('id_playlist_seq')");
//		insert = conexao.prepareStatement("insert into playlist values (?,?)");
//		selectNewIdMusicaPlaylist = conexao.prepareStatement("select nextval('id_musica_playlist_seq')");
//		insertMusicaPlaylist = conexao.prepareStatement("insert into musica_playlist values (?,?,?)");
//		delete = conexao.prepareStatement("delete from playlist where id_playlist = ?");
//		deleteMusicasPlaylist = conexao.prepareStatement("delete from musica_playlist where id_playlist = ?");
//		deletePlaylistUsuario = conexao.prepareStatement("delete from usuario_playlist where id_playlist = ?");
//		deleteMusicaPlaylist = conexao.prepareStatement("delete from musica_playlist where id_musica = ? and id_playlist = ?");
//		selectAll = conexao.prepareStatement("select * from playlist");
//		selectAllPlaylists = conexao.prepareStatement("select * from playlist");
//
//
//		update = conexao.prepareStatement("update playlist set nome = ? where id_playlist = ?");


		
	}

	public static PlaylistDAO getInstance() throws ClassNotFoundException, SQLException {
		if (instance == null) {
			instance = new PlaylistDAO();
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
	
	public int selectNewIdMusicaPlaylist() throws Exception {

		ResultSet rs = selectNewIdMusicaPlaylist.executeQuery();
		if (rs.next()) {
			return rs.getInt(1);
		
	}

	return 0;
}
	
	

	
	
	public List<Playlist> selectAllPlaylists() throws Exception {
		List<Playlist> playlists = new LinkedList<Playlist>();
			ResultSet rs = selectAll.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String nome = rs.getString(2);
				System.out.println(nome);
				
				
				
				Playlist aux = new Playlist();
				aux.setId(id);
				aux.setNome(nome);
				
				playlists.add(aux);
			}
			
		
		return playlists;
	}


	
	public void insert(Playlist p) throws Exception {
		Document playlistclasse = new Document();
		playlistclasse.append("id",  new Random().nextInt(100000));
		playlistclasse.append("nome", p.getNome());

		playlistCollection.insertOne(playlistclasse);
//		p.setId(selectNewId());
//		insert.setInt(1, p.getId());
//		insert.setString(2, p.getNome());
//		insert.executeUpdate();
		
	} 
	
	public void insertMusicaPlaylist(Playlist p, Musica m) throws Exception {
		insertMusicaPlaylist.setInt(1, selectNewIdMusicaPlaylist());
		insertMusicaPlaylist.setInt(2, m.getId());
		insertMusicaPlaylist.setInt(3, p.getId());
		insertMusicaPlaylist.executeUpdate();
	}
	
	public void deleteMusicaPlaylist(Playlist p, Musica m) throws Exception {
		deleteMusicaPlaylist.setInt(1, m.getId());
		deleteMusicaPlaylist.setInt(2, p.getId());
		deleteMusicaPlaylist.executeUpdate();
	
	}


	
	public void delete(Playlist p) throws Exception {
		delete.setInt(1, p.getId());
		deleteMusicasPlaylist.setInt(1, p.getId());
		deletePlaylistUsuario.setInt(1, p.getId());
		deletePlaylistUsuario.executeUpdate();
		deleteMusicasPlaylist.executeUpdate();
		delete.executeUpdate();
	}

	public void update(Playlist p) throws Exception {
		update.setString(1, p.getNome());
		update.setInt(2, p.getId());
		update.executeUpdate();
		
	}
	

}
