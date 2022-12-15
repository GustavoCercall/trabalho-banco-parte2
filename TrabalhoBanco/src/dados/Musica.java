package dados;

public class Musica {
	
	private int id;
	private String nome;
	private int id_artista;
	
	public Musica() {
		
	}
	
	    
	public Musica(int id, String nome, int id_artista) {
		this.id = id;
		this.nome = nome;
		this.id_artista = id_artista;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getId_artista() {
		return id_artista;
	}
	
	public void setId_artista(int id_artista) {
		this.id_artista = id_artista;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Musica [id=" + id + ", nome=" + nome + ", artista=" + id_artista + "]";
	}
	public String soNome () {
		return nome;
	}



	
	
	
	
	

}
