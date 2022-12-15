package apresentacao;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import dados.Musica;
import dados.Playlist;
import negocio.Sistema;

public class TabelaListarPlaylists extends AbstractTableModel {
	
	
	
	
	private List<Playlist> playlists = new LinkedList<Playlist>();

	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		return playlists.size();
	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		

		switch(coluna) {
		case 0:
			return playlists.get(linha).getId();
		case 1:
			return playlists.get(linha).getNome();
		case 2:
			try {
			Sistema s = Sistema.getInstance();
			List<String> nome_musicas = new LinkedList<String>();
			List<Musica> musicas = new LinkedList<Musica>();
			List<Integer> id_musicas = new LinkedList<Integer>();

			for(Playlist p : s.getPlaylists()) {
					if(p.getId() == playlists.get(linha).getId()) {
						id_musicas = s.selectMusicasPlaylist(p);
						for(Musica m : s.getMusicas()) {
							for(int i=0; i<id_musicas.size(); i++) {
								if(m.getId() == id_musicas.get(i)) {
									nome_musicas.add(m.getNome());
								}
							}
						}
					}
				}
			return nome_musicas;
				
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
			return "Músicas";
		
		default:
			throw new IllegalArgumentException();

			
		}
	}
	
	public void adicionarPlaylist(Playlist a) {
		playlists.add(a);
		this.fireTableStructureChanged();
	}
	
	
	

}
