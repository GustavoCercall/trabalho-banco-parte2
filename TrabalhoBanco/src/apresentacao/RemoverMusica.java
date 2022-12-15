package apresentacao;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dados.Musica;
import negocio.Sistema;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RemoverMusica extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RemoverMusica dialog = new RemoverMusica();
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
	public RemoverMusica() throws Exception {
		Sistema s = Sistema.getInstance();

		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
			JComboBox comboBox = new JComboBox();
			comboBox.setBounds(146, 118, 151, 22);
			contentPanel.add(comboBox);
			
			List<Musica> musicas =  new ArrayList<Musica>();
			musicas = s.getMusicas();
			
			for(Musica m : musicas) {
				comboBox.addItem(m.soNome());
			}
			
			
		
		{
			JLabel lblNewLabel = new JLabel("Escolha uma m\u00FAsica");
			lblNewLabel.setBounds(175, 93, 151, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			JButton okButton = new JButton("Excluir");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String m = (String) comboBox.getSelectedItem();
					try {
						for(Musica m1 : s.getMusicas()) {
							if(m1.getNome().equals(m)) {
								s.excluirMusica(m1);
								JOptionPane.showMessageDialog(null, "Música excluída com sucesso!");
								dispose();
							}
						}
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			okButton.setBounds(186, 151, 81, 23);
			contentPanel.add(okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			JPanel panel = new JPanel();
			panel.setBackground(SystemColor.inactiveCaptionBorder);
			panel.setBounds(117, 69, 209, 125);
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
