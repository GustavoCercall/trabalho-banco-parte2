package apresentacao;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dados.Usuario;
import negocio.Sistema;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class TelaCadastro extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastro frame = new TelaCadastro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public TelaCadastro() throws ClassNotFoundException, SQLException {
		Sistema s = Sistema.getInstance();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 571, 341);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Sim");
		rdbtnNewRadioButton.setBounds(163, 178, 48, 23);
		contentPane.add(rdbtnNewRadioButton);
		
		textField = new JTextField();
		textField.setBounds(163, 75, 201, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(163, 31, 202, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(163, 119, 201, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(163, 229, 201, 20);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.setBackground(SystemColor.inactiveCaption);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Usuario u = new Usuario();
				u.setEmail(textField_1.getText());
				u.setNome(textField.getText());
				u.setCpf((Integer.parseInt(textField_2.getText())));
				u.setModerador(rdbtnNewRadioButton.isSelected());
				u.setSenha(passwordField.getText());
				int cont=0;
				try {
					
					for(Usuario usuario : s.listarUsuarios()) {
						if(u.getEmail().equals(usuario.getEmail()) || u.getCpf() == usuario.getCpf()) {
							cont=1;
						}
					}
					
					if(cont==1) {
						JOptionPane.showMessageDialog(null, "Dados invalidos! Digite um novo email e cpf");
						textField_1.setText("");
						textField_2.setText("");
						textField_1.requestFocus();
						textField_2.requestFocus();
					}else {
						if(u.getEmail() != null && u.getNome() != null &&  u.getCpf() != 0 && u.getSenha() != null){
							s.cadastrarUsuario(u);
							JOptionPane.showMessageDialog(null, "Usuario cadastrado com sucesso!");
							dispose();
							TelaPrincipal t = new TelaPrincipal();
							t.setVisible(true);
						}else {
							JOptionPane.showMessageDialog(null, "Preencha todos os campos");
							textField_1.setText("");
							textField.setText("");
							textField_2.setText("");
							passwordField.setText("");
							textField_1.requestFocus();
							textField.requestFocus();
							textField_2.requestFocus();
							passwordField.requestFocus();

						}
						
					}
							
							
					} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "N�o foi poss�vel realizar cadastro");
					e.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(203, 260, 111, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Digite seu email:");
		lblNewLabel.setBounds(163, 11, 119, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Digite seu nome:");
		lblNewLabel_1.setBounds(163, 56, 119, 20);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblDigiteSuaSenha = new JLabel("Digite seu cpf:");
		lblDigiteSuaSenha.setBounds(163, 100, 119, 20);
		contentPane.add(lblDigiteSuaSenha);
		
		JLabel lblUmAdministrador = new JLabel("\u00C9 um administrador?");
		lblUmAdministrador.setBounds(163, 157, 142, 14);
		contentPane.add(lblUmAdministrador);
		
		JLabel lblDigiteSuaSenha_1 = new JLabel("Digite sua senha:");
		lblDigiteSuaSenha_1.setBounds(163, 208, 119, 14);
		contentPane.add(lblDigiteSuaSenha_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaptionBorder);
		panel.setBounds(131, 0, 266, 302);
		contentPane.add(panel);
	}
}
