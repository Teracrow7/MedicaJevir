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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import textfield.TextField;

public class Actualizar {

	JLabel idLabel = new JLabel("ID Paciente");
	JLabel newValueLabel = new JLabel("Nuevo Valor");
	
	Conexion conexion = new Conexion();
	TextField nuevoDatoTextField = new TextField();
	JPanel nuevoDatoPanel = new JPanel(new BorderLayout());
	
	TextField idTextField = new TextField();
	JPanel idPanel = new JPanel(new BorderLayout());
	String[] tablasLista= {"Paciente","HistorialClinico","Tratamientos","Citas","Diagnostico","TratamientoHasDiagnostico"};
	JComboBox<String> TablasComboBox= new JComboBox<String>(tablasLista) ;
	
	String[] variablesLista= {"NombreP","Edad","Ocupacion","Domicilio","Telefono","Sexo","Numerodeemergencia","Fechadenacimiento"
			,"Medicado","Alergias","Enfermedades_Cronicas","ASMA","Transfucionesdesangre","Embarazada","preciodeltratamiento","Horadecitas"
			,"motivosdecita","padecimientos","subtotal","cantidad"};
	JComboBox<String> variablesComboBox= new JComboBox<String>(variablesLista) ;
	
	JFrame actualizarFrame = new JFrame();
	JPanel panelbg = new JPanel();
	
	public void init() {
		
	
		actualizarFrame.setSize(500,400);
		
		newValueLabel.setBounds(220, 150, 180, 40);
		idLabel.setBounds(20, 150, 180, 40);
		nuevoDatoPanel.setBounds(220, 100, 180, 40);
		nuevoDatoPanel.add(nuevoDatoTextField,BorderLayout.CENTER);
		
		idPanel.setBounds(20, 100, 180, 40);
		idPanel.add(idTextField,BorderLayout.CENTER);
		
		variablesComboBox.setBounds(220, 20, 180, 40);
		TablasComboBox.setBounds(20, 20, 180, 40);
		
		actualizarFrame.add(newValueLabel);
		actualizarFrame.add(idLabel);
		actualizarFrame.add(idPanel);
		actualizarFrame.add(nuevoDatoPanel);
		actualizarFrame.add(variablesComboBox);
		actualizarFrame.add(TablasComboBox);
		actualizarFrame.add(new ActualizarButton());
		actualizarFrame.add(panelbg);
		actualizarFrame.setResizable(false);
		actualizarFrame.setLocationRelativeTo(null);
		actualizarFrame.setVisible(true);
		
		
	}
	
	
	@SuppressWarnings("serial")
	public class ActualizarButton extends JButton implements ActionListener {
		private Color color1 = new Color(196, 235, 188);
		private Color color2 = new Color(196, 235, 188);
		public ActualizarButton() {
			
			setBounds(110, 250, 140, 30);
			this.addActionListener(this);	
			this.setText("Actualizar");
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
				String id=idTextField.getText();
				String newValue = nuevoDatoTextField.getText();
				String table = tablasLista[TablasComboBox.getSelectedIndex()];
				System.out.println(table);
				String column = variablesLista[variablesComboBox.getSelectedIndex()];
				System.out.println(column);
				
				if(table=="Paciente") {
					conexion.updatePaciente(column, newValue, id);
				}else if(table=="HistorialClinico") {
					conexion.updateHistorialClinico(column, newValue, id);
				}else if(table=="Tratamientos") {
					conexion.updateTratamientos(column, newValue, id);
				}else if(table=="Citas") {
					conexion.updateCitas(column, newValue, id);
				}else if(table=="Diagnostico") {
					conexion.updateDiagnostico(column, newValue, id);
				}else if(table=="TratamientoHasDiagnostico") {
					conexion.updateTratamientoHasDiagnostico(column, newValue, id);
				}
				
			}
			
		}	
	}
	
}
