package apresentacao;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import dados.Artista;
import dados.Musica;
import negocio.Sistema;

public class TabelaListarMusicasFavoritas extends AbstractTableModel {
	
	
	
	
	private List<Musica> musicasFavoritas = new LinkedList<Musica>();

	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		return musicasFavoritas.size();
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		


		switch(coluna) {
		case 0:
			return musicasFavoritas.get(linha).getId();
		case 1:
			return musicasFavoritas.get(linha).getNome();
		case 2:
			try {
				Sistema s = Sistema.getInstance();
				String nome_a;
				for(Artista a: s.getArtistas()) {
					if(a.getId() == musicasFavoritas.get(linha).getId_artista()) {
						nome_a = a.getNome();
						return nome_a;

					}
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
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
			return "Artista";
		default:
			throw new IllegalArgumentException();

			
		}
	}
	
	public void adicionarMusica(Musica a) {
		musicasFavoritas.add(a);
		this.fireTableStructureChanged();
	}
	
	
	

}
