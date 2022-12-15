package apresentacao;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dados.Artista;
import dados.Musica;
import negocio.Sistema;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class FavoritarMusicas extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FavoritarMusicas dialog = new FavoritarMusicas();
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
	public FavoritarMusicas() throws Exception {
		Sistema s = Sistema.getInstance();

		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.control);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(118, 115, 199, 22);
		contentPanel.add(comboBox);
		
		List<Musica> musicas =  new ArrayList<Musica>();
		musicas = s.getMusicas();
		
		for(Musica m : musicas) {
			comboBox.addItem(m);
		}
		
		JLabel lblNewLabel = new JLabel("Selecione uma m\u00FAsica");
		lblNewLabel.setBounds(166, 94, 170, 14);
		contentPanel.add(lblNewLabel);
		{
			JButton okButton = new JButton("Favoritar");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Musica m = (Musica) comboBox.getSelectedItem();
					Integer id_m = m.getId();
					int cont=0;
					try {
						for(Integer id_musica : s.getMusicasFavoritas(s.getUsuarioLogado())) {
							if(id_musica == id_m) {
								cont = 1;
							}
						}
							if(cont == 1) {
								JOptionPane.showMessageDialog(null, "Música já está favoritada, escolha outra música!");
							}else {
								s.favoritarMusica(m, s.getUsuarioLogado());
								JOptionPane.showMessageDialog(null, "Música favoritada com sucesso!");
								dispose();
							}
						
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				
			});
			okButton.setBounds(170, 142, 102, 23);
			contentPanel.add(okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaptionBorder);
		panel.setBounds(77, 68, 286, 126);
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
