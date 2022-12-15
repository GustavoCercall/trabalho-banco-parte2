package apresentacao;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import dados.Artista;
import negocio.Sistema;

public class JTableArtistasFavoritos extends JScrollPane {
	Sistema s = Sistema.getInstance();


	
	public  JTableArtistasFavoritos() throws Exception {


		TabelaListarArtistasFavoritos t = new TabelaListarArtistasFavoritos();
		JTable table = new JTable(t);
		setViewportView(table);
		if(artistasFavoritos() != null) {
			for(int i=0; i<artistasFavoritos().size(); i++) {
				t.adicionarArtista(artistasFavoritos().get(i));
				System.out.println("aquiiii"+artistasFavoritos().get(i));

			}
		}
	}
	
	public List<Artista> artistasFavoritos() throws Exception {
		
		List<Artista> artistas = new LinkedList<Artista>();

		for(Integer id_artista : s.getArtistasFavoritos(s.getUsuarioLogado())) {
				for(Artista artista : s.getArtistas()) {
					if(id_artista == artista.getId()) {
						artistas.add(artista);
					}
				}
			}
		
		return artistas;
		
	}
	
	
	
	
	

}
