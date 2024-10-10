package clinica;

import java.awt.BorderLayout;
import java.awt.Color; 
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import textfield.TextField;

public class PacienteNuevo {
	
	float[] precios = {600,400,1500,800,750,400};
	String[] tratamientosLista= {"Limpieza","Extraccion","Endodoncia","Blanqueamiento","Incrutaciones","Caries"};
	JComboBox<String> TratamientosComboBox= new JComboBox<String>(tratamientosLista) ;
	
	JComboBox<String> TratamientosComboBox2= new JComboBox<String>(tratamientosLista) ;
	
	String[] tratamientosNum = {"0","1","2","3","4","5","6"};
	JComboBox<String> numTratamientosComboBox = new JComboBox<String>(tratamientosNum);
	
	JComboBox<String> numTratamientos2ComboBox = new JComboBox<String>(tratamientosNum);
	
	String[] ASMALista= {"NO","SI"};
	JComboBox<String> AsmaComboBox= new JComboBox<String>(ASMALista) ;
	JComboBox<String> EmbarazoComboBox= new JComboBox<String>(ASMALista) ;
	
	
	Conexion conexion = new Conexion();
	JFrame mainFramePacienteNuevo = new JFrame();
	
	String fechaNacimiento;
	
	String nombre,ocupacion,domicilio,
		padecimientos,tratamientos,alergias,medicacion
		,transfusiones,sexo,id_Paciente,enfermedadesCronicas;
	
	String telefono,telefonoEmergencia;
	int edad,IDCita;
	
	float total,cantidad;
	
	int Asma,embarazo;
	
	JLabel enfermedadesCronicasLabel = new JLabel();
	JLabel numTratamientosLabel = new JLabel();
	JLabel numTratamientosLabel2 = new JLabel();
	JLabel idCitaLabel = new JLabel();
	JLabel asmaLabel = new JLabel();
	JLabel idPacienteLabel = new JLabel();
	JLabel nombreLabel = new JLabel();
	JLabel edadLabel = new JLabel();
	JLabel sexoLabel = new JLabel();
	JLabel ocupacionLabel = new JLabel();
	JLabel domicilioLabel = new JLabel();
	JLabel telefonoLabel = new JLabel();
	JLabel telefonoEmergenciaLabel = new JLabel();
	JLabel fechaNacimientoLabel = new JLabel();
	JLabel padecimientosLabel = new JLabel();
	JLabel tratamientos2Label = new JLabel();
	JLabel tratamientoLabel = new JLabel();
	JLabel alergiasLabel = new JLabel();
	JLabel medicacionLabel = new JLabel();
	JLabel embarazoLabel = new JLabel();
	JLabel transfusionesLabel = new JLabel();
	JLabel costoTotalLabel = new JLabel();
	
	TextField enfermedadesCronicasTextField = new TextField();
	TextField idCitaTextField = new TextField();
	TextField idPacienteTextField = new TextField();
	TextField nombreTextField = new TextField();
	TextField edadTextField = new TextField();
	TextField sexoTextField = new TextField();
	TextField ocupacionTextField = new TextField();
	TextField domicilioTextField = new TextField();
	TextField telefonoTextField = new TextField();
	TextField telefonoEmergenciaTextField = new TextField();
	TextField fechaNacimientoTextField = new TextField();
	TextField padecimientosTextField = new TextField();
	TextField tratamientoTextField = new TextField();
	TextField alergiasTextField = new TextField();
	TextField medicacionTextField = new TextField();
	TextField embarazoTextField = new TextField();
	TextField transfusionesTextField = new TextField();
	TextField costoTotalTextField = new TextField();
	
	
	JPanel panelenfermedadesCronicas = new JPanel(new BorderLayout());
	JPanel panelIdCita = new JPanel(new BorderLayout());
	JPanel panelIdPaciente = new JPanel(new BorderLayout());
	JPanel panelNombre = new JPanel(new BorderLayout());
	JPanel panelEdad = new JPanel(new BorderLayout());
	JPanel panelSexo = new JPanel(new BorderLayout());
	JPanel panelOcupacion = new JPanel(new BorderLayout());
	JPanel panelDomicilio = new JPanel(new BorderLayout());
	JPanel panelTelefono = new JPanel(new BorderLayout());
	JPanel panelTelefonoEmergencia = new JPanel(new BorderLayout());
	JPanel panelfechaNacimiento = new JPanel(new BorderLayout());
	JPanel panelPadecimientos = new JPanel(new BorderLayout());
	JPanel panelTratamiento = new JPanel(new BorderLayout());
	JPanel panelAlergias = new JPanel(new BorderLayout());
	JPanel panelMedicacion = new JPanel(new BorderLayout());
	JPanel panelEmbarazo = new JPanel(new BorderLayout());
	JPanel panelTransfusiones = new JPanel(new BorderLayout());
	JPanel panelCostoTotal = new JPanel(new BorderLayout());
	
	PacienteNuevo(){
		
	}
	
	public void Initialize() {
		
		
		/*
    
   
    
    
   
    preciodeltratamiento float
    
   
   
    
  
    
    subtotal float,
    cantidad float,

    */
		
		mainFramePacienteNuevo.setSize(900,600);
		//mainFramePacienteNuevo.setLayout(null);
		//145,135
		
		/*
		 * 
		embarazoLabel.setText("Embarazo");
		embarazoLabel.setBounds(320, 255, 150, 60);
		embarazoTextField.setShadowColor(new Color(219, 76, 76));
		panelEmbarazo.add(embarazoTextField,BorderLayout.CENTER);
		panelEmbarazo.setBounds(420, 265, 140, 40);*/
		
		
		
		enfermedadesCronicasLabel.setText("Enfermedades");
		enfermedadesCronicasLabel.setBounds(320, 255, 150, 60);
		panelenfermedadesCronicas.setBounds(420, 265, 140, 40);
		panelenfermedadesCronicas.add(enfermedadesCronicasTextField,BorderLayout.CENTER);
		
		numTratamientosLabel2.setText("Cantidad");
		numTratamientosLabel2.setBounds(620, 255, 150, 60);
		numTratamientos2ComboBox.setBounds(730, 265, 140, 40);
		
		tratamientos2Label.setText("Tratamientos");
		tratamientos2Label.setBounds(620, 195, 150, 60);
		TratamientosComboBox2.setBounds(730, 205, 140, 40);
		
		
		tratamientoLabel.setText("Tratamientos");
		tratamientoLabel.setBounds(620, 75, 150, 60);
		TratamientosComboBox.setBounds(730, 85, 140, 40);
		
		numTratamientosLabel.setBounds(620, 135, 150, 60);
		numTratamientosLabel.setText("Cantidad");
		numTratamientosComboBox.setBounds(730, 145, 140, 40);
		
		
		
		asmaLabel.setText("Asma");
		asmaLabel.setBounds(620, 15, 150, 60);
		AsmaComboBox.setBounds(730, 25, 140, 40);

		fechaNacimientoLabel.setText("Fecha Nacimiento");
		fechaNacimientoLabel.setBounds(320, 375, 150, 60);
		fechaNacimientoTextField.setShadowColor(new Color(219, 76, 76));
		panelfechaNacimiento.add(fechaNacimientoTextField,BorderLayout.CENTER);
		panelfechaNacimiento.setBounds(420,385, 140, 40);
		
		
		
		transfusionesLabel.setText("Transfusiones");
		transfusionesLabel.setBounds(320, 315, 150, 60);
		transfusionesTextField.setShadowColor(new Color(19, 43, 148));
		panelTransfusiones.add(transfusionesTextField,BorderLayout.CENTER);
		panelTransfusiones.setBounds(420, 325, 140, 40);
		
		
		/*
		 * enfermedadesCronicasLabel.setText("Enfermedades");
		enfermedadesCronicasLabel.setBounds(620, 315, 150, 60);
		panelenfermedadesCronicas.setBounds(730, 325, 140, 40);
		panelenfermedadesCronicas.add(enfermedadesCronicasTextField,BorderLayout.CENTER);*/
		
		embarazoLabel.setText("Embarazo");
		embarazoLabel.setBounds(620, 315, 150, 60);
		EmbarazoComboBox.setBounds(730, 325, 140, 40);
		//embarazoTextField.setShadowColor(new Color(219, 76, 76));
		//panelEmbarazo.add(embarazoTextField,BorderLayout.CENTER);
		//panelEmbarazo.setBounds(730, 325, 140, 40);
		
		medicacionLabel.setText("Medicacion");
		medicacionLabel.setBounds(320, 195, 150, 60);
		medicacionTextField.setShadowColor(new Color(19, 43, 148));
		panelMedicacion.add(medicacionTextField,BorderLayout.CENTER);
		panelMedicacion.setBounds(420, 205, 140, 40);
		
		alergiasLabel.setText("Alergias");
		alergiasLabel.setBounds(320, 135, 150, 60);
		alergiasTextField.setShadowColor(new Color(219, 76, 76));
		panelAlergias.add(alergiasTextField,BorderLayout.CENTER);
		panelAlergias.setBounds(420, 145, 140, 40);
		
		idCitaLabel.setText("ID Cita");
		idCitaLabel.setBounds(320, 75, 150, 60);
		idCitaTextField.setShadowColor(new Color(19, 43, 148));
		panelIdCita.add(idCitaTextField,BorderLayout.CENTER);
		panelIdCita.setBounds(420, 85, 140, 40);
		
		padecimientosLabel.setText("Padecimientos");
		padecimientosLabel.setBounds(320, 15, 150, 60);
		padecimientosTextField.setShadowColor(new Color(219, 76, 76));
		panelPadecimientos.add(padecimientosTextField,BorderLayout.CENTER);
		panelPadecimientos.setBounds(420, 25, 140, 40);
		
		idPacienteLabel.setText("ID Paciente");
		idPacienteLabel.setBounds(20, 435, 150, 60);
		idPacienteTextField.setShadowColor(new Color(219, 76, 76));
		panelIdPaciente.add(idPacienteTextField,BorderLayout.CENTER);
		panelIdPaciente.setBounds(110, 445, 140, 40);
		
		sexoLabel.setText("Sexo");
		sexoLabel.setBounds(20, 375, 150, 60);
		sexoTextField.setShadowColor(new Color(219, 76, 76));
		panelSexo.add(sexoTextField,BorderLayout.CENTER);
		panelSexo.setBounds(110, 385, 140, 40);
		
		telefonoEmergenciaLabel.setText("Tel Emergencia");
		telefonoEmergenciaLabel.setBounds(20, 315, 150, 60);
		telefonoEmergenciaTextField.setShadowColor(new Color(19, 43, 148));
		panelTelefonoEmergencia.add(telefonoEmergenciaTextField,BorderLayout.CENTER);
		panelTelefonoEmergencia.setBounds(110, 325, 140, 40);
		
		telefonoLabel.setText("Telefono");
		telefonoLabel.setBounds(20, 255, 150, 60);
		telefonoTextField.setShadowColor(new Color(219, 76, 76));
		panelTelefono.add(telefonoTextField,BorderLayout.CENTER);
		panelTelefono.setBounds(110, 265, 140, 40);
		
		domicilioLabel.setText("Domicilio");
		domicilioLabel.setBounds(20, 195, 150, 60);		
		domicilioTextField.setShadowColor(new Color(19, 43, 148));	
		panelDomicilio.add(domicilioTextField,BorderLayout.CENTER);
		panelDomicilio.setBounds(110, 205, 140, 40);
		
		ocupacionLabel.setText("Ocupacion");
		ocupacionLabel.setBounds(20, 135, 150, 60);		
		ocupacionTextField.setShadowColor(new Color(219, 76, 76));	
		panelOcupacion.add(ocupacionTextField,BorderLayout.CENTER);
		panelOcupacion.setBounds(110, 145, 140, 40);
		
		edadLabel.setText("Edad");
		edadLabel.setBounds(20, 75, 140, 60);		
		edadTextField.setShadowColor(new Color(19, 43, 148));
		panelEdad.add(edadTextField,BorderLayout.CENTER);
		panelEdad.setBounds(110, 85, 140, 40);
		
		
		nombreLabel.setText("Nombre");
		nombreLabel.setBounds(20, 15, 150, 60);
		nombreTextField.setShadowColor(new Color(219, 76, 76));
		panelNombre.add(nombreTextField,BorderLayout.CENTER);
		panelNombre.setBounds(110, 25, 140, 40);
		
		mainFramePacienteNuevo.add(EmbarazoComboBox);
		mainFramePacienteNuevo.add(panelenfermedadesCronicas);
		mainFramePacienteNuevo.add(numTratamientos2ComboBox);
		mainFramePacienteNuevo.add(numTratamientosComboBox);
		mainFramePacienteNuevo.add(TratamientosComboBox2);
		mainFramePacienteNuevo.add(panelIdCita);
		mainFramePacienteNuevo.add(AsmaComboBox);
		mainFramePacienteNuevo.add(TratamientosComboBox);
		mainFramePacienteNuevo.add(panelIdPaciente);
		mainFramePacienteNuevo.add(panelTransfusiones);
		mainFramePacienteNuevo.add(panelfechaNacimiento);
		mainFramePacienteNuevo.add(panelMedicacion);
		mainFramePacienteNuevo.add(panelAlergias);
		mainFramePacienteNuevo.add(panelPadecimientos);
		mainFramePacienteNuevo.add(panelSexo);
		mainFramePacienteNuevo.add(panelTelefono);
		mainFramePacienteNuevo.add(panelTelefonoEmergencia);
		mainFramePacienteNuevo.add(panelDomicilio);
		mainFramePacienteNuevo.add(panelOcupacion);
		mainFramePacienteNuevo.add(panelEdad);
		mainFramePacienteNuevo.add(panelNombre);
		
		mainFramePacienteNuevo.add(fechaNacimientoLabel);
		mainFramePacienteNuevo.add(enfermedadesCronicasLabel);
		mainFramePacienteNuevo.add(numTratamientosLabel2);
		mainFramePacienteNuevo.add(numTratamientosLabel);
		mainFramePacienteNuevo.add(tratamientos2Label);
		mainFramePacienteNuevo.add(idCitaLabel);
		mainFramePacienteNuevo.add(asmaLabel);
		mainFramePacienteNuevo.add(tratamientoLabel);
		mainFramePacienteNuevo.add(alergiasLabel);
		mainFramePacienteNuevo.add(medicacionLabel);
		mainFramePacienteNuevo.add(embarazoLabel);
		mainFramePacienteNuevo.add(transfusionesLabel);
		mainFramePacienteNuevo.add(idPacienteLabel);
		mainFramePacienteNuevo.add(padecimientosLabel);
		mainFramePacienteNuevo.add(sexoLabel);
		mainFramePacienteNuevo.add(telefonoEmergenciaLabel);
		mainFramePacienteNuevo.add(telefonoLabel);
		mainFramePacienteNuevo.add(domicilioLabel);
		mainFramePacienteNuevo.add(ocupacionLabel);
		mainFramePacienteNuevo.add(edadLabel);
		mainFramePacienteNuevo.add(nombreLabel);
		mainFramePacienteNuevo.add(new GuardarHistorialButton());
		mainFramePacienteNuevo.add(new GuardarPacienteButton());
		mainFramePacienteNuevo.add(new PacienteNuevoBG());
		mainFramePacienteNuevo.setLocationRelativeTo(null);
		mainFramePacienteNuevo.setVisible(true);
		
		
		
	}
	
	@SuppressWarnings("serial")
	public class PacienteNuevoBG extends JPanel{

				
		PacienteNuevoBG(){
			
			this.setPreferredSize(new Dimension(500,500));
			
		}
		
	}
	
	@SuppressWarnings("serial")
	public class GuardarPacienteButton extends JButton implements ActionListener {
		private Color color1 = new Color(196, 235, 188);
		private Color color2 = new Color(196, 235, 188);
		public GuardarPacienteButton() {
			
			setBounds(115,510,130,20);
			this.addActionListener(this);	
			this.setText("Guardar Paciente");
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
				
				//System.out.println(Float.parseFloat(tratamientosNum[numTratamientosComboBox.getSelectedIndex()])+" *"+precios[TratamientosComboBox.getSelectedIndex()]);
				//System.out.println(tratamientos);
				//System.out.println(total);
				
				nombre= nombreTextField.getText();
				edad = Integer.parseInt(edadTextField.getText());
				sexo = sexoTextField.getText();
				ocupacion = ocupacionTextField.getText();
				domicilio = domicilioTextField.getText();
				telefono = telefonoTextField.getText();
				telefonoEmergencia= telefonoEmergenciaTextField.getText();
				id_Paciente = idPacienteTextField.getText();
				
				
				conexion.createConnection();
				conexion.InsertDataPaciente(id_Paciente, nombre, edad, ocupacion, domicilio, telefono, sexo, telefonoEmergencia);
			
				
				
				
			}
			
		}	
	}
	
	
	
	@SuppressWarnings("serial")
	public class GuardarHistorialButton extends JButton implements ActionListener {
		private Color color1 = new Color(196, 235, 188);
		private Color color2 = new Color(196, 235, 188);
		public GuardarHistorialButton() {
			
			setBounds(525,510,130,20);
			this.addActionListener(this);	
			this.setText("Guardar Historial");
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
				
				
				
				
				tratamientos = tratamientosNum[numTratamientosComboBox.getSelectedIndex()]+" "+tratamientosLista[TratamientosComboBox.getSelectedIndex()]+", "+tratamientosNum[numTratamientos2ComboBox.getSelectedIndex()]+" "+tratamientosLista[TratamientosComboBox2.getSelectedIndex()];
				total = (Float.parseFloat(tratamientosNum[numTratamientosComboBox.getSelectedIndex()])* precios[TratamientosComboBox.getSelectedIndex()])+(Float.parseFloat(tratamientosNum[numTratamientos2ComboBox.getSelectedIndex()])*precios[TratamientosComboBox2.getSelectedIndex()]);
				IDCita =   Integer.parseInt(idCitaTextField.getText());
				id_Paciente = idPacienteTextField.getText();
				embarazo = EmbarazoComboBox.getSelectedIndex();
				Asma = AsmaComboBox.getSelectedIndex();
				padecimientos = padecimientosTextField.getText();
				alergias = alergiasTextField.getText();
				medicacion = medicacionTextField.getText();
				transfusiones = transfusionesTextField.getText();
				fechaNacimiento = fechaNacimientoTextField.getText();
				
				
				conexion.createConnection();
				conexion.InsertDataHistorialClinico(id_Paciente, fechaNacimiento, medicacion, alergias, enfermedadesCronicas,Asma,  transfusiones,embarazo);
				conexion.insertDataDiagnostico(tratamientos, padecimientos, IDCita);			
				conexion.insertDataTratamientos(tratamientos, total);				
				conexion.insertDataTratamientoHasDiagnostico(tratamientos, tratamientos, Float.parseFloat(tratamientosNum[numTratamientosComboBox.getSelectedIndex()]), total);
				
			}
			
			
		
		}
	}
}
