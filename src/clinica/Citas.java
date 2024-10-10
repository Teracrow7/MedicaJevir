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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import textfield.TextField;
import textfieldSearch.SearchOptinEvent;
import textfieldSearch.SearchOption;
import textfieldSearch.TextFieldSearchOption;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


public class Citas {
	
	Conexion con = new Conexion();
	String nombreP,idCita,motivoCita,fechaCita,PacienteId;
	
	JTable table = new JTable();
	JPanel panelTable = new JPanel(new BorderLayout());
	JPanel searchPanel = new JPanel();
	TextFieldSearchOption searchTextField = new TextFieldSearchOption();
	
	JLabel labelMotivoCita = new JLabel();
	JLabel labelfechaCita = new JLabel();
	JLabel labelidPaciente = new JLabel();
	
	
	JPanel panelbg = new JPanel();
	

	JPanel panelfechaCita = new JPanel(new BorderLayout());
	JPanel panelmotivoCita = new JPanel(new BorderLayout());
	JPanel panelidPaciente = new JPanel(new BorderLayout());
	
	JFrame mainCitasFrame = new JFrame();
	

	TextField motivoCitaTextField = new TextField();
	TextField fechaCitaTextField = new TextField();
	TextField idPacienteTextField = new TextField();
	
	public void Init() {
		con.createConnection();
		panelTable.setBounds(250, 60, 400, 400);
		panelTable.setBackground(Color.white);
		
		table.setModel(new javax.swing.table.DefaultTableModel(
	            new Object [][] {
	            
	            },
	            new String [] {
	                "ID Cita","Nombre","Motivo","Fecha"
	            }
	        ) {
	            boolean[] canEdit = new boolean [] {
	                false, false, false, false
	            };

	            public boolean isCellEditable(int rowIndex, int columnIndex) {
	                return canEdit [columnIndex];
	            }
	        });
		
		
		panelTable.add(new JScrollPane (table), BorderLayout.CENTER);
		
		mainCitasFrame.setSize(700, 550);
		
		
searchTextField.addEventOptionSelected(new SearchOptinEvent() {
			
			@Override
			public void optionSelected(SearchOption option, int index) {
				searchTextField.setText("  Buscar por "+option.getName()+"...");
				
			}
		});
		
		searchTextField.addOption(new SearchOption("Nombre",new ImageIcon(getClass().getResource("/clinica/user.png"))));
		searchTextField.addOption(new SearchOption("Fecha",new ImageIcon(getClass().getResource("/clinica/fecha.png"))));
		searchTextField.addOption(new SearchOption("Motivo",new ImageIcon(getClass().getResource("/clinica/address.png"))));
		searchTextField.addOption(new SearchOption("ID",new ImageIcon(getClass().getResource("/clinica/email.png"))));
		loadData("");
		searchTextField.setSelectedIndex(0);
		//searchTextField.setText("                            ");
		searchPanel.add(searchTextField);
		searchPanel.setBounds(450, 0, 180, 40);
		searchTextField.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				 if (searchTextField.isSelected()) {
			            int option = searchTextField.getSelectedIndex();
			            String text = searchTextField.getText().trim()+"%";
			            if(option==0) {
			            	//search by name
			            	loadData("Where Paciente.NombreP LIKE ?",text);
			            }else if(option==1) {
			            	//search by Phone number
			            	loadData("Where Citas.Horadecitas like ?",text);
			            }else if(option==2) {
			            	//search by addres
			            	loadData("Where Citas.motivosdecita like ?",text);
			            }else if(option==3) {
			            	//search by id
			            	loadData("Where Citas.ID_cita like ?",text);
			            	
			            }
			        }
				
			}
			
			
		});
		
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		
		labelfechaCita.setText("Fecha Cita");
		labelfechaCita.setBounds(20, 175, 150, 50);
		panelfechaCita.add(fechaCitaTextField,BorderLayout.CENTER);
		panelfechaCita.setBounds(90, 180, 120, 40);
		
		labelidPaciente.setText("ID Paciente");
		labelidPaciente.setBounds(20, 95, 150, 50);
		panelidPaciente.add(idPacienteTextField,BorderLayout.CENTER);
		panelidPaciente.setBounds(90, 100, 120, 40);
		
		labelMotivoCita.setText("Motivo Cita");
		labelMotivoCita.setBounds(20, 255, 150, 50);
		panelmotivoCita.add(motivoCitaTextField,BorderLayout.CENTER);
		panelmotivoCita.setBounds(90, 260, 120, 40);


		
		
		mainCitasFrame.add(labelMotivoCita);
		mainCitasFrame.add(labelfechaCita);
		mainCitasFrame.add(labelidPaciente);
		mainCitasFrame.add(panelfechaCita);
		mainCitasFrame.add(panelidPaciente);
		mainCitasFrame.add(panelmotivoCita);
		
		mainCitasFrame.add(searchPanel);
		mainCitasFrame.add(panelTable);
		mainCitasFrame.add(new GuardarCitaButton());
		mainCitasFrame.add(panelbg);
		mainCitasFrame.setResizable(false);
		mainCitasFrame.setLocationRelativeTo(null);
		mainCitasFrame.setVisible(true);
		
	}
	
	
	
	
	
	public class GuardarCitaButton extends JButton implements ActionListener{
		private Color color1 = new Color(196, 235, 188);
		private Color color2 = new Color(196, 235, 188);
	    public GuardarCitaButton() {
				
	    		setBounds(90,450,120,20);
				this.addActionListener(this);	
				this.setText("Guardar Cita");
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
				motivoCita =motivoCitaTextField.getText();
				fechaCita=fechaCitaTextField.getText();
				PacienteId=idPacienteTextField.getText();
				
				con.createConnection();
				con.insertDataCitas(PacienteId, motivoCita, fechaCita);
			}
			
			
		}
	}
	
	
	@SuppressWarnings("serial")
	public class CitasBG extends JPanel{

		private Color color1 = new Color(160, 184, 232);
		private Color color2 = new Color(139, 232, 166);
				
		CitasBG(){
			
			this.setPreferredSize(new Dimension(500,500));
			
		}
		
		public void paint(Graphics g) {
			super.paint(g);
			
			Graphics2D g2D = (Graphics2D) g;
			
			
			int w = getWidth();
			int h = getHeight();
			
			GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
			
			g2D.setPaint(gp);
			g2D.fillRect(0, 0, w, h);
			
		}
	}
	
	
	private void loadData(String where,Object...search) {
	
DefaultTableModel model1 = (DefaultTableModel) table.getModel();
		
		model1.setRowCount(0);
		try {
			PreparedStatement p = con.con.prepareStatement("SELECT Citas.*, Paciente.NombreP\r\n"
					+ "FROM Citas\r\n"
					+ "INNER JOIN Paciente ON Citas.ID_paciente = Paciente.ID_paciente "+where);
		for(int i=0;i<search.length;i++) {
			p.setObject(i+1, search[i]);
		}
		

	// "Id", "Nombre", "Edad", "Ocupacion", "Domicilio", "Tel","Sexo","Tel Emergencia",
   // "FechaNacimiento","Medicado","Alergias","Enfermedades_Cronicas","Asma","Transfusiones","Embarazada"
		
			ResultSet r = p.executeQuery();
			while(r.next()) {
				String idCita = r.getString("ID_cita");
				String HoraCita = r.getString("Horadecitas");
				String motivo = r.getString("motivosdecita");
				String nombre = r.getString("NombreP");
				
				
				model1.addRow(new Object[] {idCita,nombre,motivo,HoraCita});
			}
			r.close();
			p.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
