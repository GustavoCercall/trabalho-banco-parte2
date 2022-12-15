package apresentacao;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dados.Artista;
import dados.Musica;
import negocio.Sistema;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class favoritarArtistas extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			favoritarArtistas dialog = new favoritarArtistas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws Exception 
	 */
	public favoritarArtistas() throws Exception {
		Sistema s = Sistema.getInstance();

		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblSelecioneUmArtista = new JLabel("Selecione um artista");
			lblSelecioneUmArtista.setBounds(150, 84, 170, 14);
			contentPanel.add(lblSelecioneUmArtista);
		}
		
			JComboBox comboBox = new JComboBox();
			comboBox.setBounds(122, 109, 170, 22);
			contentPanel.add(comboBox);
			List<Artista> artistas =  new ArrayList<Artista>();
			artistas = s.getArtistas();
			
			for(Artista a : artistas) {
				comboBox.addItem(a.soNome());
			}
		
		
		JButton okButton_1 = new JButton("Favoritar");
		okButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String a = (String) comboBox.getSelectedItem();
				Artista a2 = new Artista();
				
				try {
					for(Artista a1 : s.getArtistas()) {
						if(a1.getNome().equals(a)) {
							a2 = a1;
						}
					}
					Integer id_a2 = a2.getId();
					int cont=0;
					
					for(Integer id_artista : s.getArtistasFavoritos(s.getUsuarioLogado())) {
						if(id_artista == id_a2) {
							cont = 1;
						}
					}
						if(cont == 1) {
							JOptionPane.showMessageDialog(null, "Artista já está favoritado, escolha outro artista!");
						}else {
							s.favoritarArtista(a2, s.getUsuarioLogado());
							JOptionPane.showMessageDialog(null, "Artista favoritado com sucesso!");
							dispose();
						}
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		okButton_1.setActionCommand("OK");
		okButton_1.setBounds(160, 142, 102, 23);
		contentPanel.add(okButton_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaptionBorder);
		panel.setBounds(97, 60, 221, 130);
		contentPanel.add(panel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
