package apresentacao;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import negocio.Sistema;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class TelaUsuarioModerador extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaUsuarioModerador frame = new TelaUsuarioModerador();
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
	public TelaUsuarioModerador() throws ClassNotFoundException, SQLException {
		Sistema s = Sistema.getInstance();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 610, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Playlist");
		lblNewLabel.setBounds(45, 26, 80, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("M\u00FAsicas");
		lblNewLabel_2.setBounds(359, 26, 80, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Minha Conta");
		lblNewLabel_3.setBounds(491, 26, 81, 14);
		contentPane.add(lblNewLabel_3);
		
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
		btnNewButton.setBounds(10, 51, 111, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Adicionar M\u00FAsica");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					if(s.getPlaylists(s.getUsuarioLogado()) != null) {
						AdicionarMusicaPlaylist t = new AdicionarMusicaPlaylist();
						t.setVisible(true);
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(10, 85, 111, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Remover M\u00FAsica");
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
		btnNewButton_2.setBounds(10, 119, 111, 23);
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
		btnNewButton_3.setBounds(10, 153, 111, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Remover Playlist");
		btnNewButton_4.addActionListener(new ActionListener() {
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
		btnNewButton_4.setBounds(10, 187, 111, 23);
		contentPane.add(btnNewButton_4);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaptionBorder);
		panel.setBounds(0, 11, 132, 220);
		contentPane.add(panel);
		
		JButton btnNewButton_5 = new JButton("Cadastrar");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					CadastroArtista c = new CadastroArtista();
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
		btnNewButton_5.setBounds(167, 51, 111, 23);
		contentPane.add(btnNewButton_5);
		
		JButton btnNewButton_5_1 = new JButton("Editar");
		btnNewButton_5_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					EditarArtista a = new EditarArtista();
					a.setVisible(true);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton_5_1.setBounds(167, 85, 111, 23);
		contentPane.add(btnNewButton_5_1);
		
		JButton btnNewButton_5_2 = new JButton("Remover");
		btnNewButton_5_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					RemoverArtista r = new RemoverArtista();
					r.setVisible(true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
		btnNewButton_5_2.setBounds(167, 119, 111, 23);
		contentPane.add(btnNewButton_5_2);
		
		JButton btnNewButton_4_1 = new JButton("Favoritar");
		btnNewButton_4_1.addActionListener(new ActionListener() {
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
		btnNewButton_4_1.setBounds(167, 153, 111, 23);
		contentPane.add(btnNewButton_4_1);
		
		JButton btnNewButton_4_1_1 = new JButton("Listar Favoritos");
		btnNewButton_4_1_1.addActionListener(new ActionListener() {
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
		btnNewButton_4_1_1.setBounds(167, 187, 111, 23);
		contentPane.add(btnNewButton_4_1_1);
		
		JLabel lblNewLabel_1 = new JLabel("Artistas");
		lblNewLabel_1.setBounds(202, 26, 80, 14);
		contentPane.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.inactiveCaptionBorder);
		panel_1.setBounds(154, 11, 138, 220);
		contentPane.add(panel_1);
		
		JButton btnNewButton_5_3 = new JButton("Cadastrar");
		btnNewButton_5_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 
				try {
					CadastroMusica c = new CadastroMusica();
					c.setVisible(true);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton_5_3.setBounds(322, 51, 111, 23);
		contentPane.add(btnNewButton_5_3);
		
		JButton btnNewButton_5_1_1 = new JButton("Editar");
		btnNewButton_5_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					EditarMusica m = new EditarMusica();
					m.setVisible(true);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton_5_1_1.setBounds(322, 85, 111, 23);
		contentPane.add(btnNewButton_5_1_1);
		
		JButton btnNewButton_5_2_1 = new JButton("Remover");
		btnNewButton_5_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					RemoverMusica r = new RemoverMusica();
					r.setVisible(true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnNewButton_5_2_1.setBounds(322, 119, 111, 23);
		contentPane.add(btnNewButton_5_2_1);
		
		JButton btnNewButton_4_1_2 = new JButton("Favoritar");
		btnNewButton_4_1_2.addActionListener(new ActionListener() {
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
		btnNewButton_4_1_2.setBounds(322, 153, 111, 23);
		contentPane.add(btnNewButton_4_1_2);
		
		JButton btnNewButton_4_1_1_1 = new JButton("Listar Favoritas");
		btnNewButton_4_1_1_1.addActionListener(new ActionListener() {
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
		btnNewButton_4_1_1_1.setBounds(322, 187, 111, 23);
		contentPane.add(btnNewButton_4_1_1_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.inactiveCaptionBorder);
		panel_2.setBounds(311, 11, 132, 220);
		contentPane.add(panel_2);
		
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
		btnNewButton_7.setBounds(483, 51, 89, 23);
		contentPane.add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("Excluir");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ExcluirConta c = new ExcluirConta();
					c.setVisible(true);
					dispose();

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton_8.setBounds(483, 85, 89, 23);
		contentPane.add(btnNewButton_8);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.inactiveCaptionBorder);
		panel_3.setBounds(464, 11, 120, 131);
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
		btnNewButton_6.setBounds(483, 153, 89, 23);
		contentPane.add(btnNewButton_6);
	}
}
