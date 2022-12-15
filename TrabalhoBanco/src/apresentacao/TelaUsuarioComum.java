package apresentacao;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import negocio.Sistema;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.JFormattedTextField;

public class TelaUsuarioComum extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaUsuarioComum frame = new TelaUsuarioComum();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaUsuarioComum() throws ClassNotFoundException, SQLException {
		Sistema s = Sistema.getInstance();

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 333);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Playlist");
		lblNewLabel.setBounds(58, 53, 68, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					CadastroPlaylist c = new CadastroPlaylist();
					c.setVisible(true);

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(23, 78, 115, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Adicionar M\u00FAsica");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(s.getPlaylists(s.getUsuarioLogado()).isEmpty()) {
						JOptionPane.showMessageDialog(null, "nenhuma playlist cadastrada");
					}else {
						AdicionarMusicaPlaylist t = new AdicionarMusicaPlaylist();
						t.setVisible(true);
					}
					

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnNewButton_1.setBounds(24, 112, 115, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Remover Musica");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					RemoverMusicaPlaylist t = new RemoverMusicaPlaylist();
					t.setVisible(true);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnNewButton_2.setBounds(24, 144, 115, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Listar");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ListarPlaylists t = new ListarPlaylists();
					t.setVisible(true);

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton_3.setBounds(23, 178, 115, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_2_1 = new JButton("Remover Playlist");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					RemoverPlaylist p = new RemoverPlaylist();
					p.setVisible(true);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
		btnNewButton_2_1.setBounds(23, 211, 115, 23);
		contentPane.add(btnNewButton_2_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaptionBorder);
		panel.setBounds(10, 46, 143, 204);
		contentPane.add(panel);
		
		JLabel lblNewLabel_1 = new JLabel("Artistas");
		lblNewLabel_1.setBounds(224, 53, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton_4 = new JButton("Favoritar");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					favoritarArtistas t = new favoritarArtistas();
					t.setVisible(true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton_4.setBounds(191, 78, 107, 23);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_4_1 = new JButton("Listar Favoritos");
		btnNewButton_4_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ListarArtistasFavoritos t = new ListarArtistasFavoritos();
					t.setVisible(true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton_4_1.setBounds(191, 101, 107, 23);
		contentPane.add(btnNewButton_4_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.inactiveCaptionBorder);
		panel_1.setBounds(174, 46, 143, 100);
		contentPane.add(panel_1);
		
		JButton btnNewButton_5 = new JButton("Favoritar");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					FavoritarMusicas t = new FavoritarMusicas();
					t.setVisible(true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton_5.setBounds(191, 189, 107, 23);
		contentPane.add(btnNewButton_5);
		
		JButton btnNewButton_4_1_1 = new JButton("Listar Favoritas");
		btnNewButton_4_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ListarMusicasFavoritas t = new ListarMusicasFavoritas();
					t.setVisible(true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton_4_1_1.setBounds(191, 211, 107, 23);
		contentPane.add(btnNewButton_4_1_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.inactiveCaptionBorder);
		panel_2.setBounds(174, 157, 143, 93);
		contentPane.add(panel_2);
		
		JLabel lblNewLabel_2 = new JLabel("M\u00FAsicas");
		panel_2.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Minha Conta");
		lblNewLabel_3.setBounds(372, 53, 81, 14);
		contentPane.add(lblNewLabel_3);
		
		JButton btnNewButton_7 = new JButton("Editar");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					EditarConta c = new EditarConta();
					c.setVisible(true);

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton_7.setBounds(359, 78, 89, 23);
		contentPane.add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("Excluir");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 
				try {
					ExcluirConta c = new ExcluirConta();
					c.setVisible(true);

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton_8.setBounds(359, 101, 89, 23);
		contentPane.add(btnNewButton_8);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.inactiveCaptionBorder);
		panel_3.setBounds(343, 46, 131, 100);
		contentPane.add(panel_3);
		
		JButton btnNewButton_6 = new JButton("Sair");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					TelaPrincipal t = new TelaPrincipal();
					t.setVisible(true);
					dispose();
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		});
		btnNewButton_6.setBackground(SystemColor.inactiveCaptionBorder);
		btnNewButton_6.setBounds(364, 157, 89, 23);
		contentPane.add(btnNewButton_6);
	}
}
