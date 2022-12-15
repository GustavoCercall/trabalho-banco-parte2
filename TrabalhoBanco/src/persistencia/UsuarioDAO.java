package persistencia;
import static com.mongodb.client.model.Filters.eq;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import dados.Playlist;
import dados.Usuario;
import dados.Artista;
import dados.Musica;
import java.util.stream.Collectors;
import javax.print.Doc;
import org.bson.Document;
import com.google.gson.Gson;
import org.bson.conversions.Bson;

public class UsuarioDAO implements DAO<Usuario> {
	
	
	private static UsuarioDAO instance = null;
	private static MongoCollection<Document> usuarioCollection = null;

	private PreparedStatement selectNewId;
	private PreparedStatement select;
	private PreparedStatement selectAll;
	private PreparedStatement insert;
	private PreparedStatement insertUsuarioArtista;
	private PreparedStatement insertUsuarioMusica;
	private PreparedStatement insertUsuarioPlaylist;
	private PreparedStatement delete;
	private PreparedStatement deleteUsuarioMusica;
	private PreparedStatement deleteUsuarioPlaylist;
	private PreparedStatement deleteUsuarioArtista;
	private PreparedStatement update;
	private PreparedStatement selectAllUsuarioArtista;
	private PreparedStatement selectAllUsuarioMusica;

	private PreparedStatement selectAllUsuarioPlaylist;




	private UsuarioDAO() throws ClassNotFoundException, SQLException{

		MongoDatabase conexao = Conexao.getConexao();
		MongoCollection<Document> usuario = conexao.getCollection("usuario");
		usuarioCollection = usuario;
//		selectNewId = conexao.prepareStatement("select nextval('id_usuario_seq')");
//		select = conexao.prepareStatement("select * from usuario where email = ? and senha = ?");
//		selectAll = conexao.prepareStatement("select * from usuario");
//		insert = conexao.prepareStatement("insert into usuario values (?,?,?,?,?,?)");
//		insertUsuarioArtista = conexao.prepareStatement("insert into usuario_artista values ((select nextval('id_usuario_artista_seq')),?,?) ");
//		insertUsuarioMusica = conexao.prepareStatement("insert into usuario_musica values ((select nextval('id_usuario_musica_seq')),?,?) ");
//		insertUsuarioPlaylist = conexao.prepareStatement("insert into usuario_playlist values ((select nextval('id_usuario_playlist_seq')),?,?) ");
//		delete = conexao.prepareStatement("delete from usuario where id_usuario = ?");
//		deleteUsuarioMusica = conexao.prepareStatement("delete from usuario_musica where id_usuario = ?");
//		deleteUsuarioPlaylist = conexao.prepareStatement("delete from usuario_playlist where id_usuario = ?");
//		deleteUsuarioArtista = conexao.prepareStatement("delete from usuario_artista where id_usuario = ?");
//
//		selectAllUsuarioArtista = conexao.prepareStatement("select * from usuario_artista");
//		selectAllUsuarioMusica = conexao.prepareStatement("select * from usuario_musica");
//		selectAllUsuarioPlaylist = conexao.prepareStatement("select * from usuario_playlist");
//
//
//
//		update = conexao.prepareStatement("update usuario set email = ?, nome = ?, cpf = ?, senha = ?, moderador = ?  where id_usuario = ?");


	}

	public static UsuarioDAO getInstance() throws ClassNotFoundException, SQLException {
		if (instance == null) {
			instance = new UsuarioDAO();
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
	
	
	
	
	
	@Override
	public List<Usuario> selectAll() throws Exception {
		List<Usuario> usuarios = new LinkedList<Usuario>();
		FindIterable<Document> usuariolista =  usuarioCollection.find();
		for(Document usuario:usuariolista){
			Usuario usuario1 = new Gson().fromJson(usuario.toJson(), Usuario.class);
			usuarios.add(new Usuario(usuario1.getId(), usuario1.getNome(), usuario1.getEmail(),usuario1.getSenha(), usuario1.getCpf(),usuario1.isModerador()));
		}

//		while (rs.next()) {
//			int id = rs.getInt(1);
//			String email = rs.getString(2);
//			System.out.println(email);
//			String nome = rs.getString(3);
//			System.out.println(nome);
//			Long cpf = rs.getLong(4);
//			System.out.println(cpf);u
//			String senha = rs.getString(5);
//			System.out.println(senha);
//			Boolean moderador = rs.getBoolean(6);
//			System.out.println(moderador);

			
//			usuarios.add(new Usuario(id, nome, email,senha, cpf,moderador));
//		}
	
		return usuarios;
	}
	
	
	public Usuario select(String email, String senha) throws Exception {
		
		Usuario u = new Usuario();
		ResultSet rs = select.executeQuery();
		int id = rs.getInt(1);
		String email1 = rs.getString(2);
		System.out.println(email1);
		String nome = rs.getString(3);
		System.out.println(nome);
		int cpf = rs.getInt(4);
		System.out.println(cpf);
		String senha1 = rs.getString(5);
		System.out.println(senha1);
		boolean moderador = rs.getBoolean(6);
		System.out.println(moderador);
		u.setId(id);
		u.setEmail(email1);
		u.setNome(nome);
		u.setCpf(cpf);
		u.setSenha(senha1);
		u.setModerador(moderador);
		return u;
	
	}

	@Override
	public void insert(Usuario usuario) throws Exception {
		Document usuarioclasse = new Document();
		usuarioclasse.append("id",  new Random().nextInt(100000));
		usuarioclasse.append("email", usuario.getEmail());
		usuarioclasse.append("nome", usuario.getNome());
		usuarioclasse.append("cpf", usuario.getCpf());
		usuarioclasse.append("senha", usuario.getSenha());
		usuarioclasse.append("moderador", usuario.isModerador());

		usuarioCollection.insertOne(usuarioclasse);
//		insert.setInt(1, usuario.getId());
//		insert.setString(2, usuario.getEmail());
//		insert.setString(3, usuario.getNome());
//		insert.setLong(4, usuario.getCpf());
//		insert.setString(5, usuario.getSenha());
//		insert.setBoolean(6, usuario.isModerador());
//		insert.executeUpdate();


	}

	@Override
	public void delete(Usuario usuario) throws Exception {
		usuarioCollection.deleteOne(new Document("id", usuario.getId()));

//		deleteUsuarioMusica.setInt(1,usuario.getId());
//		deleteUsuarioMusica.executeUpdate();
//
//		deleteUsuarioPlaylist.setInt(1,usuario.getId());
//		deleteUsuarioPlaylist.executeUpdate();
//
//		deleteUsuarioArtista.setInt(1,usuario.getId());
//		deleteUsuarioArtista.executeUpdate();
//
//		delete.setInt(1, usuario.getId());
//		delete.executeUpdate();
	}

	@Override
	public void update(Usuario usuario) throws Exception {
		Document usuarioupdate = new Document();
		usuarioupdate.append("email", usuario.getEmail());
		usuarioupdate.append("nome", usuario.getNome());
		usuarioupdate.append("cpf", usuario.getCpf());
		usuarioupdate.append("senha", usuario.getSenha());
		usuarioupdate.append("moderador", usuario.isModerador());

		if(!usuario.getArtistasFavoritos().isEmpty())
		{
			ArrayList<Document> teste = new ArrayList<>();
			for(Artista artista : usuario.getArtistasFavoritos()){
				Document artistaclasse = new Document();
				artistaclasse.append("id",  artista.getId());
				artistaclasse.append("nome", artista.getNome());
				artistaclasse.append("generoMusical", artista.getGeneroMusical());
				teste.add(artistaclasse);
			};
			usuarioupdate.append("artistasFavoritos", teste);
		}
		if(!usuario.getMusicasFavoritas().isEmpty())
		{
			ArrayList<Document> teste = new ArrayList<>();
			for(Musica musica : usuario.getMusicasFavoritas()){
				Document musicaDoc = new Document();
				musicaDoc.append("id",  musica.getId());
				musicaDoc.append("nome", musica.getNome());
				musicaDoc.append("id_artista", musica.getId_artista());
				teste.add(musicaDoc);
			};

			usuarioupdate.append("musicasFavoritos",teste);
		}
		if(!usuario.getPlaylists().isEmpty())
		{
			usuarioupdate.append("playlistFavoritas", usuario.getPlaylists());
		}
		Bson filtro = eq("id", usuario.getId());
		usuarioCollection.updateOne(filtro, new Document("$set", usuarioupdate));


//		update.setString(1, usuario.getEmail());
//		update.setString(2, usuario.getNome());
//		update.setLong(3, usuario.getCpf());
//		update.setString(4, usuario.getSenha());
//		update.setBoolean(5, usuario.isModerador());
//		update.setInt(6, usuario.getId());
//		update.executeUpdate();
		
	}
	
	public void favoritarArtista(Usuario u, Artista a) throws Exception{
		List<Artista> artistasFavoritosUsuario =  u.getArtistasFavoritos();
		artistasFavoritosUsuario.add(a);
		update(u);

//		insertUsuarioArtista.setInt(1,u.getId());
//		insertUsuarioArtista.setInt(2, a.getId());
//		insertUsuarioArtista.executeUpdate();
		
		
	}
	
	public List<Integer> selectAllUsuarioArtista(Usuario u){
		List<Integer> ids = new LinkedList<Integer>();
//		Bson filtro = Filters.eq("id", u.getId());
//		Document docUsuario =  usuarioCollection.find(filtro).first();
//		Usuario usuario = new Gson().fromJson(docUsuario.toJson(), Usuario.class);
		for(Artista artista: u.getArtistasFavoritos()){
			ids.add(artista.getId());
		}
			
//				try {
//					ResultSet rs = selectAllUsuarioArtista.executeQuery();
//					while(rs.next()) {
//					int idArtista = rs.getInt(3);
//					if(u.getId() == rs.getInt(2)) {
//						ids.add(idArtista);
//					}
//
//				}
//					}catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				return ids;
				
	}
	
	public List<Integer> selectAllUsuarioMusica(Usuario u){
		List<Integer> ids = new LinkedList<Integer>();
		for(Musica musica: u.getMusicasFavoritas()){
			ids.add(musica.getId());
		}
//				try {
//					ResultSet rs = selectAllUsuarioMusica.executeQuery();
//						while(rs.next()) {
//							int idMusica = rs.getInt(3);
//							if(u.getId() == rs.getInt(2)) {
//								ids.add(idMusica);
//							}
//
//						}
//					}catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				return ids;
	}
	
	public List<Integer> selectAllUsuarioPlaylist(Usuario u){
		List<Integer> ids = new LinkedList<Integer>();
			
				try {
						ResultSet rs = selectAllUsuarioPlaylist.executeQuery();
						while(rs.next()) {
							int idPlaylist = rs.getInt(3);
							if(u.getId() == rs.getInt(2)) {
								ids.add(idPlaylist);
							}
						 
						}
					}catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return ids;
	}
	
	
	
	public void favoritarMusica(Usuario u, Musica m) throws Exception{
		List<Musica> musicasFavoritosUsuario =  u.getMusicasFavoritas();
		musicasFavoritosUsuario.add(m);
		update(u);
		
//		insertUsuarioMusica.setInt(1,u.getId());
//		insertUsuarioMusica.setInt(2, m.getId());
//		insertUsuarioMusica.executeUpdate();
		
		
	}
	
	public void insertUsuarioPlaylist(Usuario u, Playlist p) throws Exception{
		
		insertUsuarioPlaylist.setInt(1,u.getId());
		insertUsuarioPlaylist.setInt(2, p.getId());
		insertUsuarioPlaylist.executeUpdate();
		
		
	}

	public void deleteMusicaLista (Integer id) throws Exception {
		List<Usuario> usuarios = this.selectAll();
		for(Usuario usuario: usuarios)
		{
			List<Musica> musicasNaoRemovidas = usuario.getMusicasFavoritas().stream().filter(musica -> musica.getId() != id).collect(Collectors.toList());
			usuario.setMusicasFavoritas(musicasNaoRemovidas);
			this.update(usuario);
		}
	}
	
	
	
	
	
	
	
	
	
	

}
