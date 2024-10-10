package clinica;

import java.awt.BorderLayout; 

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import textfieldSearch.SearchOptinEvent;
import textfieldSearch.SearchOption;
import textfieldSearch.TextFieldSearchOption;

public class ChecarDatosPaciente  {
	
	Conexion conexion = new Conexion();
	JTable table = new JTable();
	JPanel panelTable = new JPanel(new BorderLayout());
	JFrame mainCheckDataFrame = new JFrame();
	JPanel searchPanel = new JPanel();
	TextFieldSearchOption searchTextField = new TextFieldSearchOption();
	
	JPanel panelbg = new JPanel();
	ChecarDatosPaciente(){
		
		
		
		conexion.createConnection();
		init();
		
		
		
	}

	@SuppressWarnings("serial")
	public void init() {
		
		panelTable.setBounds(10, 60, 1310, 510);
		//panelTable.setLayout(null);
		//panelTable.setBackground(Color.white);
		table.setLayout(null);
		table.setModel(new javax.swing.table.DefaultTableModel(
		            new Object [][] {
		            
		            },
		            new String [] {
		                "Id", "Nombre", "Edad", "Ocupacion", "Domicilio", "Telefono","Sexo","Tel Emergencia",
		                "FechaNacimiento","Medicado","Alergias","Enfermedades_Cronicas","Asma","Transfusiones","Embarazada"
		                ,"Tratamiento","Total"
		            }
		        ) {
		            boolean[] canEdit = new boolean [] {
		                false, false, false, false, false, false,false,false,false,false,false,false,false,false,false,false,false
		            };

		            public boolean isCellEditable(int rowIndex, int columnIndex) {
		                return canEdit [columnIndex];
		            }
		        });
		
		
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		table.getColumnModel().getColumn(2).setPreferredWidth(20);
		table.getColumnModel().getColumn(10).setPreferredWidth(50);
		table.getColumnModel().getColumn(12).setPreferredWidth(40);
		table.getColumnModel().getColumn(15).setPreferredWidth(140);
		
		//table.getColumnModel().getColumn(8).setPreferredWidth(120);
		//table.getTableHeader().setPreferredSize(new Dimension(100, 30));
		 	//table.setSize(670, 420);
			panelTable.add(new JScrollPane (table), BorderLayout.CENTER);
		
		
		
		
		mainCheckDataFrame.setSize(1350,620);
		//mainCheckDataFrame.setLayout(null);
		//searchTextField.setSize(400, 40);
		//searchTextField.setLayout(null);
		searchTextField.addEventOptionSelected(new SearchOptinEvent() {
			
			@Override
			public void optionSelected(SearchOption option, int index) {
				searchTextField.setText("  Buscar por "+option.getName()+"...");
				
			}
		});
		
		searchTextField.addOption(new SearchOption("Nombre",new ImageIcon(getClass().getResource("/clinica/user.png"))));
		searchTextField.addOption(new SearchOption("Telefono",new ImageIcon(getClass().getResource("/clinica/tel.png"))));
		searchTextField.addOption(new SearchOption("Direccion",new ImageIcon(getClass().getResource("/clinica/address.png"))));
		searchTextField.addOption(new SearchOption("ID",new ImageIcon(getClass().getResource("/clinica/email.png"))));
		loadData("");
		searchTextField.setSelectedIndex(0);
		//searchTextField.setText("                            ");
		searchPanel.add(searchTextField);
		searchPanel.setBounds(1145, 0, 180, 40);
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
			            	loadData("Where Paciente.Telefono like ?",text);
			            }else if(option==2) {
			            	//search by addres
			            	loadData("Where Paciente.Domicilio like ?",text);
			            }else if(option==3) {
			            	//search by id
			            	loadData("Where Paciente.ID_paciente like ?",text);
			            	
			            }
			        }
				
			}
			
			
		});
		
		mainCheckDataFrame.add(panelTable);
		mainCheckDataFrame.add(searchPanel);
		mainCheckDataFrame.add(panelbg);
		mainCheckDataFrame.setLocationRelativeTo(null);
		mainCheckDataFrame.setVisible(true);
		
	
	}
	
	
	private void loadData(String where,Object...search) {
		
		DefaultTableModel model1 = (DefaultTableModel) table.getModel();
		
		model1.setRowCount(0);
		try {
			PreparedStatement p = conexion.con.prepareStatement("SELECT Paciente.*, HistorialClinico.*, TratamientoHasDiagnostico.Tratamiento, TratamientoHasDiagnostico.cantidad, TratamientoHasDiagnostico.subtotal  \r\n"
					+ "                               FROM Paciente \r\n"
					+ "                               INNER JOIN HistorialClinico ON Paciente.ID_paciente = HistorialClinico.ID_paciente \r\n"
					+ "                               LEFT JOIN Citas ON Paciente.ID_paciente = Citas.ID_paciente \r\n"
					+ "                               LEFT JOIN Diagnostico ON Citas.ID_cita = Diagnostico.ID_cita \r\n"
					+ "                               LEFT JOIN TratamientoHasDiagnostico ON Diagnostico.Tratamiento = TratamientoHasDiagnostico.Tratamiento "+where+ " ORDER BY CAST(Paciente.ID_paciente AS UNSIGNED)");
		for(int i=0;i<search.length;i++) {
			p.setObject(i+1, search[i]);
		}
		

	// "Id", "Nombre", "Edad", "Ocupacion", "Domicilio", "Tel","Sexo","Tel Emergencia",
   // "FechaNacimiento","Medicado","Alergias","Enfermedades_Cronicas","Asma","Transfusiones","Embarazada"
		
			ResultSet r = p.executeQuery();
			while(r.next()) {
				String id = r.getString("ID_paciente");
				String name = r.getString("NombreP");
				String edad = r.getString("Edad");
				String ocupacion = r.getString("Ocupacion");
				String domicilio = r.getString("Domicilio");
				String tel = r.getString("Telefono");
				String sexo = r.getString("Sexo");
				String telEmergencia = r.getString("Numerodeemergencia");
				String fechaNac = r.getString( "Fechadenacimiento");
				String medicado=r.getString("Medicado");
				String alergias = r.getString("Alergias");
				String enfermedadesCronicas = r.getString("Enfermedades_Cronicas");
				String asma = r.getString("ASMA");
				String Transfusiones = r.getString("Transfucionesdesangre");
				String embarazada = r.getString("Embarazada");
				String Tratamiento = r.getString("Tratamiento");
				String Total = r.getString("subtotal");
				
				model1.addRow(new Object[] {id,name,edad,ocupacion,domicilio,tel,sexo,telEmergencia,fechaNac,medicado,
						alergias,enfermedadesCronicas,asma,Transfusiones,embarazada,Tratamiento,Total});
			}
			r.close();
			p.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	 
	
}



