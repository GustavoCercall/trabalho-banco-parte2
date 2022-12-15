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

public class EditarArtista extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EditarArtista dialog = new EditarArtista();
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
	public EditarArtista() throws Exception {
		Sistema s = Sistema.getInstance();

		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblSelecioneUmaMsica = new JLabel("Selecione um artista");
			lblSelecioneUmaMsica.setBounds(150, 41, 170, 14);
			contentPanel.add(lblSelecioneUmaMsica);
		}
		
			JComboBox comboBox = new JComboBox();
			comboBox.setBounds(150, 59, 170, 22);
			contentPanel.add(comboBox);
			
			List<Artista> artistas =  new ArrayList<Artista>();
			artistas = s.getArtistas();
			
			for(Artista a : artistas) {
				comboBox.addItem(a.soNome());
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
			JButton okButton = new JButton("Editar");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String a = (String) comboBox.getSelectedItem();
					Artista a2 = new Artista();
					
					try {
						for(Artista a1 : s.getArtistas()) {
							if(a1.getNome().equals(a)) {
								a2 = a1;
							}
						}
						a2.setNome(textField.getText());
						a2.setGeneroMusical(textField_1.getText());
						s.alterarArtista(s.getUsuarioLogado(), a2);
						JOptionPane.showMessageDialog(null, "Artista editado com sucesso!");
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
			JLabel lblDigiteSeuGnero = new JLabel("Digite seu g\u00EAnero musical:");
			lblDigiteSeuGnero.setBounds(150, 141, 170, 14);
			contentPanel.add(lblDigiteSeuGnero);
		}
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(150, 162, 170, 20);
		contentPanel.add(textField_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaptionBorder);
		panel.setBounds(121, 24, 225, 204);
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
