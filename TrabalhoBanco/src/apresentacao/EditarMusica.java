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

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditarMusica extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EditarMusica dialog = new EditarMusica();
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
	public EditarMusica() throws Exception {
		Sistema s = Sistema.getInstance();

		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblSelecioneUmaMsica = new JLabel("Selecione uma m\u00FAsica");
			lblSelecioneUmaMsica.setBounds(150, 41, 170, 14);
			contentPanel.add(lblSelecioneUmaMsica);
		}
		
			JComboBox comboBox = new JComboBox();
			comboBox.setBounds(150, 59, 170, 22);
			contentPanel.add(comboBox);
			
			List<Musica> musicas =  new ArrayList<Musica>();
			musicas = s.getMusicas();
			
			for(Musica m : musicas) {
				comboBox.addItem(m);
			}
		
		
		{
			JLabel lblNewLabel = new JLabel("Digite seu nome:");
			lblNewLabel.setBounds(150, 92, 170, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			textField = new JTextField();
			textField.setColumns(10);
			textField.setBounds(150, 110, 170, 20);
			contentPanel.add(textField);
		}
		{
			JLabel lblSelecioneUmArtista = new JLabel("Selecione um artista");
			lblSelecioneUmArtista.setBounds(150, 141, 170, 14);
			contentPanel.add(lblSelecioneUmArtista);
		}
		
			JComboBox comboBox_1 = new JComboBox();
			comboBox_1.setBounds(150, 160, 170, 22);
			contentPanel.add(comboBox_1);
			
			List<Artista> artistas =  new ArrayList<Artista>();
			artistas = s.getArtistas();
			
			for(Artista a : artistas) {
				comboBox_1.addItem(a);
			}
		
		{
			JButton okButton = new JButton("Editar");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Musica m = (Musica) comboBox.getSelectedItem();
					m.setNome(textField.getText());
					Artista a = (Artista) comboBox_1.getSelectedItem();
					m.setId_artista(a.getId());
					try {
						s.alterarMusica(s.getUsuarioLogado(), m);
						JOptionPane.showMessageDialog(null, "Música Editada com sucesso!");
						dispose();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			});
			okButton.setBounds(196, 193, 69, 23);
			contentPanel.add(okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			JPanel panel = new JPanel();
			panel.setBackground(SystemColor.inactiveCaptionBorder);
			panel.setBounds(114, 24, 234, 204);
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
