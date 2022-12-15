package apresentacao;
import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import dados.Artista;
import negocio.Sistema;

public class TabelaListarArtistasFavoritos extends AbstractTableModel {

	
	private List<Artista> artistasFavoritos = new LinkedList<Artista>();

	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		return artistasFavoritos.size();
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		switch(coluna) {
		case 0:
			return artistasFavoritos.get(linha).getId();
		case 1:
			return artistasFavoritos.get(linha).getNome();
		case 2:
			return artistasFavoritos.get(linha).getGeneroMusical();
			default:
				throw new IllegalArgumentException();
		}
	}
	
	public String getColumnName(int coluna) {
		switch (coluna) {
		case 0:
			return "Id";
		case 1:
			return "Nome";
		case 2:
			return "Gênero Musical";
		default:
			throw new IllegalArgumentException();

			
		}
	}
	
	public void adicionarArtista(Artista a) {
		artistasFavoritos.add(a);
		this.fireTableStructureChanged();
	}
	
	
	

}
