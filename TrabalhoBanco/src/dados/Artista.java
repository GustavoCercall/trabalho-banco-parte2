package dados;

import java.util.LinkedList;
import java.util.List;

public class Artista {
	
	private int id;
	private String nome;
	private String generoMusical;
    private List<Musica> musicas = new LinkedList<Musica>();
   
    
    public Artista() {
    }
    
    public Artista(int id, String nome, String generoMusical, List<Musica> musicas) {
    	this.id = id;
    	this.nome = nome;
    	this.generoMusical = generoMusical;
    	this.musicas = musicas;
    }

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getGeneroMusical() {
		return generoMusical;
	}
	public void setGeneroMusical(String generoMusical) {
		this.generoMusical = generoMusical;
	}
	public List<Musica> getMusicas() {
		return musicas;
	}
	public void setMusicas(List<Musica> musicas) {
		this.musicas = musicas;
	}
	
	
	
	
	@Override
	public String toString() {
		return "Artista [id=" + id + ", nome=" + nome + ", generoMusical=" + generoMusical + ", musicas=" + musicas
				+ "]";
	}
	public String soNome() {
		return nome;
	}
	


	
	
	

}
