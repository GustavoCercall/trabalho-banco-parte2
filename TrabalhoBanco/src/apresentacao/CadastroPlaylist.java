package apresentacao;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dados.Playlist;
import negocio.Sistema;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.ActionEvent;

public class CadastroPlaylist extends JDialog {
	private JTextField textField;

	/**
	 * Create the dialog.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public CadastroPlaylist() throws ClassNotFoundException, SQLException {
		Sistema s = Sistema.getInstance();

		System.out.println(s.getUsuarioLogado());
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 228, 434, 33);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("Cadastrar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Playlist p = new Playlist();
						p.setNome(textField.getText());
						int cont=0;
						try {
								for(Integer id_playlist : s.getPlaylists(s.getUsuarioLogado())) {
									for(Playlist p1 : s.getPlaylists()) {
										if(id_playlist == p1.getId()) {
											if(p1.getNome().equals(p.getNome())) {
												cont=1;
											}
										}
								}
							}
								if(cont!=1) {
									s.criarPlaylist(p, s.getUsuarioLogado());
									JOptionPane.showMessageDialog(null, "Playlist cadastrada com sucesso");
									dispose();
								}else {
									JOptionPane.showMessageDialog(null, "Playlist já cadastrada, escolha outro nome!");

								}
							
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
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
		{
			JLabel lblNewLabel = new JLabel("Digite o nome da sua Playlist:");
			lblNewLabel.setBounds(137, 96, 249, 14);
			getContentPane().add(lblNewLabel);
		}
		
		textField = new JTextField();
		textField.setBounds(137, 114, 164, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaptionBorder);
		panel.setBounds(70, 71, 284, 103);
		getContentPane().add(panel);
	}
}
