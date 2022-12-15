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
import javax.swing.JComboBox;
import java.awt.SystemColor;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RemoverPlaylist extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RemoverPlaylist dialog = new RemoverPlaylist();
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
	public RemoverPlaylist() throws Exception {
		Sistema s = Sistema.getInstance();

		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Selecione uma playlist: ");
			lblNewLabel.setBounds(127, 82, 186, 14);
			contentPanel.add(lblNewLabel);
		}
		
			JComboBox comboBox = new JComboBox();
			comboBox.setBounds(127, 102, 186, 22);
			contentPanel.add(comboBox);
			
			for(int id_playlist : s.getPlaylists(s.getUsuarioLogado())) {
				for(Playlist p : s.getPlaylists()) {
					if(id_playlist == p.getId()) {
						comboBox.addItem(p);
					}
				}
			}
		
		{
			JButton btnNewButton = new JButton("Remover");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Playlist p = (Playlist) comboBox.getSelectedItem();
					try {
						s.removerPlaylist(p);
						JOptionPane.showMessageDialog(null, "Playlist removida com sucesso!");
						dispose();
					} catch (Exception e) {
						
						e.printStackTrace();
					}

					
				}
			});
			btnNewButton.setBounds(176, 135, 89, 23);
			contentPanel.add(btnNewButton);
		}
		{
			JPanel panel = new JPanel();
			panel.setBackground(SystemColor.inactiveCaptionBorder);
			panel.setBounds(90, 54, 256, 131);
			contentPanel.add(panel);
		}
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
