package apresentacao;

import java.util.LinkedList;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import dados.Musica;
import negocio.Sistema;

public class JTableMusicasFavoritas extends JScrollPane {
	Sistema s = Sistema.getInstance();


	
	public  JTableMusicasFavoritas() throws Exception {


		TabelaListarMusicasFavoritas t = new TabelaListarMusicasFavoritas();
		JTable table = new JTable(t);
		setViewportView(table);
		if(musicasFavoritas() != null) {
			for(int i=0; i<musicasFavoritas().size(); i++) {
				t.adicionarMusica(musicasFavoritas().get(i)); //chama o método de adicionarMusica passando a musica da lista de musicas favoritas

			}
		}
	}
	
	public List<Musica> musicasFavoritas() throws Exception {
		
		List<Musica> musicas = new LinkedList<Musica>();

		for(Integer id_musica : s.getMusicasFavoritas(s.getUsuarioLogado())) { //percorre os ids das musicas favoritas daquele usuario
				for(Musica musica : s.getMusicas()) { //percorre as musicas do sistema
					if(id_musica == musica.getId()) { //se o id da musica favorita for igual ao da musica no sistema
						musicas.add(musica); //adiciona na lista de musicas
					}
				}
			}
		
		return musicas; //retorna uma lista de musicas favoritas
		
	}
	
	
	
	
	

}
