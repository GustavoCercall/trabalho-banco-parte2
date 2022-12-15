package negocio;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dados.Artista;
import dados.Musica;
import dados.Playlist;
import dados.Usuario;
import persistencia.ArtistaDAO;
import persistencia.MusicaDAO;
import persistencia.PlaylistDAO;
import persistencia.UsuarioDAO;

public class Sistema {
	
	
	
	private ArtistaDAO artistaDAO;
	private MusicaDAO musicaDAO;
	private PlaylistDAO playlistDAO;
	private UsuarioDAO usuarioDAO;
	private Usuario usuarioLogado;
	
	
	private static Sistema instance = null;
	
	private Sistema() throws ClassNotFoundException, SQLException {
        artistaDAO = ArtistaDAO.getInstance();
        musicaDAO = MusicaDAO.getInstance();
        playlistDAO = PlaylistDAO.getInstance();
        usuarioDAO = UsuarioDAO.getInstance();
    }
	
	
	public static Sistema getInstance() throws ClassNotFoundException, SQLException {
		if (instance == null) {
			instance = new Sistema();
		}
		return instance;
		
	}
	
	
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	
	public Usuario loginESenha(String email, String senha) throws Exception{
        for(Usuario usuario : usuarioDAO.selectAll()){
            if(usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)){
                return usuario;
            }
        }  
        return null;
    }

	public void cadastrarUsuario(Usuario usuario) throws Exception {
		usuarioDAO.insert(usuario); 
	}
	
	public void editarUsuario(Usuario usuario) throws Exception{ 
		usuarioDAO.update(usuario);
	}
	
	public void excluirUsuario(Usuario u)throws Exception {
		usuarioDAO.delete(u);
		
	}
	
	public List<Usuario> listarUsuarios() throws Exception{
		return usuarioDAO.selectAll();
	}
	
	 
	public boolean inserirMusica(Usuario usuario, Musica musica, Artista a) throws Exception{
		
        if (!usuario.isModerador()){
            return false;
        } else {
            musicaDAO.insert(musica);
            return true; 
        }
    }
	
	public boolean alterarMusica(Usuario usuario, Musica musica) throws Exception{  

		if (!usuario.isModerador()) {
            return false;
        } else {
        	musicaDAO.update(musica);
            return true;
        }
	}
	
	public void excluirMusica(Musica m)throws Exception {
		musicaDAO.delete(m);
		
	}	
	
	public List<Musica> getMusicas() throws Exception{ //todas as musicas do sistema
		return musicaDAO.selectAll();
	}
	
	
	
	
	public List<Integer> selectMusicasPlaylist(Playlist p) throws Exception{ //retorna uma lista de ids das músicas da playlist passada como parametro
		return musicaDAO.selectMusicasPlaylist(p.getId());
	}
	
	public boolean inserirArtista(Usuario usuario, Artista artista) throws Exception {
		
        if (!usuario.isModerador()){
            return false;
        } else{
            artistaDAO.insert(artista);
            return true;
        }
    }
	
	public boolean alterarArtista(Usuario usuario, Artista artista) throws Exception{ 
		
		if (!usuario.isModerador()){
            return false;
        }else{
        	artistaDAO.update(artista);
            return true;
        }
	}
	public void excluirArtista(Artista a)throws Exception {
		artistaDAO.delete(a);
		
	}
	
	public List<Artista> getArtistas() throws Exception{
		return artistaDAO.selectAll();
	}
	
	public List<Integer> getArtistasFavoritos(Usuario u) throws Exception{ //retorna os ids dos artistas favoritos do usuario passado
		return usuarioDAO.selectAllUsuarioArtista(u);
	}
	
	
	
	public void favoritarMusica(Musica musica, Usuario usuario) throws Exception{
		
		usuarioDAO.favoritarMusica(usuario, musica);
	}
	
	public List<Integer> getMusicasFavoritas(Usuario u) throws Exception{ //retorna os ids dos artistas favoritos do usuario passado
		return usuarioDAO.selectAllUsuarioMusica(u);
	}
	
	public void favoritarArtista(Artista artista, Usuario usuario) throws Exception{
		
		usuarioDAO.favoritarArtista(usuario, artista);
	}
	
	public void criarPlaylist(Playlist p, Usuario usuario) throws Exception{
		playlistDAO.insert(p);
		usuarioDAO.insertUsuarioPlaylist(usuario, p);
	}
	
	public List<Playlist> getPlaylists() throws Exception{
		return playlistDAO.selectAllPlaylists();
	}
	
	public List<Integer> getPlaylists(Usuario u) throws Exception{
		return usuarioDAO.selectAllUsuarioPlaylist(u);
	}
	
	public void removerPlaylist(Playlist p) throws Exception{ 
		playlistDAO.delete(p);
	}
	
	public void adicionarMusicaPlaylist(Playlist p, Musica m) throws Exception{ 
		
		playlistDAO.insertMusicaPlaylist(p, m);
	}
	
	public void removerMusicaPlaylist(Playlist p, Musica m) throws Exception{
		
		playlistDAO.deleteMusicaPlaylist(p, m);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		
	
	
	
	
	
	
	
}
