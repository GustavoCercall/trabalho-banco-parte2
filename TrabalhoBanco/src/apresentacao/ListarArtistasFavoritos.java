package apresentacao;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import negocio.Sistema;

import javax.swing.JTable;

public class ListarArtistasFavoritos extends javax.swing.JFrame {

	private JPanel contentPane;
	private JTable table;

	
	public ListarArtistasFavoritos() throws ClassNotFoundException, SQLException {
		Sistema s = Sistema.getInstance();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		try {
			setContentPane(new JTableArtistasFavoritos());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		contentPane.setLayout(null);
		

			

			
			
		
		
		
		
		
	}
}
