package clinica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import textfield.TextField;

public class Eliminar {

	PreparedStatement ps;
	ResultSet rs;
	
	Conexion conexion = new Conexion();
	JFrame mainEliminar = new JFrame();
	
	JLabel IDPacienteLabel = new JLabel("ID Paciente");
	JPanel IDPanel = new JPanel(new BorderLayout());
	JPanel panelbg = new JPanel();
	
	TextField IDTextField = new TextField();
	
	
	public void init() {
		
		IDPacienteLabel.setBounds(10, 15,  150, 60);
		IDPanel.setBounds(110, 20, 140, 40);
		IDPanel.add(IDTextField,BorderLayout.CENTER);
		
		
		mainEliminar.add(IDPacienteLabel);
		mainEliminar.add(IDPanel);
		mainEliminar.add(new EliminarButton());
		mainEliminar.add(panelbg);
		mainEliminar.setSize(400,300);
		mainEliminar.setResizable(false);
		mainEliminar.setLocationRelativeTo(null);
		mainEliminar.setVisible(true);
		
	}
	
	
	
	
	
	
	@SuppressWarnings("serial")
	public class EliminarButton extends JButton implements ActionListener {
		private Color color1 = new Color(196, 235, 188);
		private Color color2 = new Color(196, 235, 188);
		public EliminarButton() {
			
			setBounds(110, 150, 140, 30);
			this.addActionListener(this);	
			this.setText("Eliminar");
			this.setFocusable(false);
			this.setContentAreaFilled(false);
			this.setForeground(Color.black);
			this.setCursor(new Cursor(Cursor.HAND_CURSOR));
			this.setBorder(new EmptyBorder(10,20,10,20));
		}
		
		public void paint(Graphics g) {
			int w = getWidth();
			int h = getHeight();
			BufferedImage img = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2D = img.createGraphics();
			g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			//g2D.setColor(Color.red);
			GradientPaint gp = new GradientPaint(0,0,color1,w,0,color2);
			g2D.setPaint(gp);
			g2D.fillRoundRect(0, 0, w, h, h, h);		
			g.drawImage(img, 0, 0, null);
			
			super.paintComponent(g);
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==this) {
			
				conexion.createConnection();
				String id= IDTextField.getText();
				conexion.deleteTratamientos(id);
				//conexion.deleteTratamientoHasDiagnostico( id);
				conexion.deleteDiagnostico(  id);
				conexion.deleteCitas(  id);
				conexion.deleteHistorialClinico(  id);
				conexion.deletePaciente(  id);
				

			}
			
		}	
	}
	

}
