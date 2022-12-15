package dados;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Usuario {
	
	private int id;
	private String email;
	private String nome;
	private int cpf;
	private String senha;
	private boolean moderador = false;
	
	

		public Usuario(){
	        
	    }
		
		public Usuario(int id, String nome, String email, String senha, int cpf, boolean moderador){
			this.id = id;
			this.nome = nome;
			this.email = email;
			this.senha = senha;
			this.cpf = cpf;
			this.moderador = moderador;
			
	   }

	private List<Playlist> playlists = new LinkedList<Playlist>();
    private List<Artista> artistasFavoritos = new LinkedList<Artista>();
    private List<Musica> musicasFavoritas = new LinkedList<Musica>();
    
    
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getCpf() {
		return cpf;
	}
	public void setCpf(int cpf) {
		this.cpf = cpf;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public boolean isModerador() {
		return moderador;
	}
	public void setModerador(boolean moderador) {
		this.moderador = moderador;
	}
	
	
	
	public List<Playlist> getPlaylists() {
		return playlists;
	}
	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}
	
	@Override
	public String toString() {
		return "Usuario [email=" + email + ", nome=" + nome + ", cpf=" + cpf + ", senha=" + senha + ", moderador="
				+ moderador + ", playlists=" + playlists + ", artistasFavoritos=" + artistasFavoritos
				+ ", musicasFavoritas=" + musicasFavoritas + "]";
	}
	
	

	public List<Artista> getArtistasFavoritos() {
		return artistasFavoritos;
	}

	public void setArtistasFavoritos(List<Artista> artistasFavoritos) {
		this.artistasFavoritos = artistasFavoritos;
	}

	public List<Musica> getMusicasFavoritas() {
		return musicasFavoritas;
	}

	public void setMusicasFavoritas(List<Musica> musicasFavoritas) {
		this.musicasFavoritas = musicasFavoritas;
	}
	
	
	
	
	
	
	
	
	

	
	
	

}
