package apresentacao;

import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



public class ListarMusicasFavoritas extends javax.swing.JFrame {

	private JPanel contentPane;

	
	public ListarMusicasFavoritas() throws ClassNotFoundException, SQLException {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		try {
			setContentPane(new JTableMusicasFavoritas());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		contentPane.setLayout(null);
		

			

			
			
		
		
		
		
		
	}
}
