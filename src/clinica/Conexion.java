package clinica;


import java.sql.*;

public class Conexion {
	
	Connection con;
	public void createConnection() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Database Connection Success");
			 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Consultorio_Dental", "root", "Gothboi71");
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	
	
	public void InsertDataPaciente(String id_Paciente,String nombre,int edad, String ocupacion,String domicilio,String NumTelefono,String sexo,String NumEmergencia) {
		
		try {
			
			//System.out.println("Database Connection Success");
			
			//insert into Paciente values('jons','Juan',10,'estudiante','teran','987556','prefiere no decir','99889')
				Statement stmt = con.createStatement();
				String dbOperation = "INSERT INTO Paciente values('"+ id_Paciente +"','"+nombre+"',"+edad+",'"+ocupacion+"','"+domicilio+"','"+NumTelefono+"','"+sexo+"','"+NumEmergencia+"')";
				stmt.execute(dbOperation);
				stmt.close();
		} catch ( SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	public void InsertDataHistorialClinico(String id_paciente,String fechaNac,String medicado,String alergias,String enfermedades_Cronicas,int asma,String transfusionesSangre,int embarazada) {
	
		try {
			Statement stmtHistorial = con.createStatement();
			String dbOperationHistorial = "INSERT INTO HistorialClinico(ID_paciente,Fechadenacimiento,Medicado,Alergias,Enfermedades_Cronicas,"
					+ "ASMA,Transfucionesdesangre,Embarazada) values('"+id_paciente+"', '"+fechaNac+"','"+medicado+"','"+alergias+"','"+enfermedades_Cronicas+"','"
					+asma+"','"+transfusionesSangre+"','"+embarazada+"')";
			stmtHistorial.execute(dbOperationHistorial);
			stmtHistorial.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

	public void insertDataCitas(String id_Paciente,String motivoCita,String fechaCita ) {
		
		Statement stmtCitas;
		try {
			stmtCitas = con.createStatement();
			String dbOperationCitas ="INSERT INTO Citas(ID_paciente,motivosdecita,Horadecitas) "
					+ "VALUES('"+id_Paciente+"','"+motivoCita+"','"+fechaCita+"')";
			stmtCitas.execute(dbOperationCitas);
			stmtCitas.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			}
	
	public void insertDataDiagnostico(String tratamiento, String padecimientos,int idCita) {
		Statement stmtDiagnostico;
	
		try {
			stmtDiagnostico = con.createStatement();
			String dbOperationDiagnostico = "INSERT INTO Diagnostico(Tratamiento,padecimientos,ID_cita)"
					+ "VALUES('"+tratamiento+"','"+padecimientos+"',"+idCita+")";
			stmtDiagnostico.execute(dbOperationDiagnostico);
			stmtDiagnostico.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void insertDataTratamientos(String tipoTratamiento,float precioTratamiento) {
		Statement stmtTratamientos;
		
		try {
			stmtTratamientos = con.createStatement();
			String dbOperationTratamientos = "INSERT INTO Tratamientos( Tipo_de_tratamiento,preciodeltratamiento)"
					+ "VALUES('"+tipoTratamiento+"',"+precioTratamiento+")";
			stmtTratamientos.execute(dbOperationTratamientos);
			stmtTratamientos.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void insertDataTratamientoHasDiagnostico(String tipoTratamiento,String tratamiento,float cantidad,float subtotal) {
		Statement stmtTratamientosHasDiagnostico;
		
		try {
			stmtTratamientosHasDiagnostico = con.createStatement();
			String dbOperationTratamientosHasDiagnostico = "INSERT INTO TratamientoHasDiagnostico values('"+tipoTratamiento+"','"+tratamiento+"',"+subtotal+","+cantidad+")";
			stmtTratamientosHasDiagnostico.execute(dbOperationTratamientosHasDiagnostico);
			stmtTratamientosHasDiagnostico.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	

	public void deleteTratamientos(String conditionValue) {
		String deleteQuery = " DELETE FROM Tratamientos\r\n"
				+ "WHERE Tipo_de_tratamiento IN (\r\n"
				+ "    SELECT Tipo_de_tratamiento \r\n"
				+ "    FROM TratamientoHasDiagnostico \r\n"
				+ "    WHERE Tratamiento IN (\r\n"
				+ "    SELECT Tratamiento \r\n"
				+ "    FROM Diagnostico WHERE ID_cita IN ( SELECT ID_cita FROM Citas WHERE ID_paciente = ?)))";
		
		
		 try (PreparedStatement preparedStatement = con.prepareStatement(deleteQuery)) {
	            preparedStatement.setString(1, conditionValue);
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
				
				e.printStackTrace();
			}
	}

	
	public void deleteTratamientoHasDiagnostico( String conditionValue) {
		 String deleteQuery = "DELETE FROM TratamientoHasDiagnostico WHERE Tratamiento IN ( " +
	                "SELECT Tratamiento FROM Diagnostico WHERE ID_cita IN ( " +
	                "SELECT ID_cita FROM Citas WHERE ID_paciente = ?))";;

	        try (PreparedStatement preparedStatement = con.prepareStatement(deleteQuery)) {
	            preparedStatement.setString(1, conditionValue);
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
				
				e.printStackTrace();
			}
		
	}



	public void deleteDiagnostico( String conditionValue) {
		
		 String deleteQuery = "DELETE FROM Diagnostico WHERE ID_cita IN ( " +
	                "SELECT ID_cita FROM Citas WHERE ID_paciente = ?)";;

	        try (PreparedStatement preparedStatement = con.prepareStatement(deleteQuery)) {
	            preparedStatement.setString(1, conditionValue);
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
				
				e.printStackTrace();
			}
	}
	
	public void deleteCitas(String conditionValue) {
		 String deleteQuery = "DELETE FROM Citas WHERE ID_paciente = ?";

	        try (PreparedStatement preparedStatement = con.prepareStatement(deleteQuery)) {
	            preparedStatement.setString(1, conditionValue);
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
				
				e.printStackTrace();
			}
	}



	public void deleteHistorialClinico(String conditionValue) {
		 String deleteQuery = "DELETE FROM HistorialClinico WHERE ID_paciente = ?";;

	        try (PreparedStatement preparedStatement = con.prepareStatement(deleteQuery)) {
	            preparedStatement.setString(1, conditionValue);
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
				
				e.printStackTrace();
			}
		
	}



	public void deletePaciente(String conditionValue) { 
		 String deleteQuery = "DELETE FROM Paciente WHERE ID_paciente = ?";

	        try (PreparedStatement preparedStatement = con.prepareStatement(deleteQuery)) {
	            preparedStatement.setString(1, conditionValue);
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
				
				e.printStackTrace();
			}
		
	}
	
	
	
	public void updatePaciente(String column,String newValue,String id) {
		 String deleteQuery = "UPDATE Paciente\r\n"
		 		+ "SET "+ column +" = ?\r\n"
		 		+ "WHERE ID_paciente ='"+id+"'";

	        try (PreparedStatement preparedStatement = con.prepareStatement(deleteQuery)) {
	            preparedStatement.setString(1, newValue);
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
				
				e.printStackTrace();
			}
	}
	
	public void updateHistorialClinico(String column,String newValue,String id) {
		 String deleteQuery = "UPDATE HistorialClinico\r\n"
			 		+ "SET "+ column +" = ?\r\n"
			 		+ "WHERE ID_paciente ='"+id+"'";

		        try (PreparedStatement preparedStatement = con.prepareStatement(deleteQuery)) {
		            preparedStatement.setString(1, newValue);
		            preparedStatement.executeUpdate();
		        } catch (SQLException e) {
					
					e.printStackTrace();
				}
		     
		}
	
	
	public void updateCitas(String column,String newValue,String id) {
		 String deleteQuery = "UPDATE Citas\r\n"
			 		+ "SET "+ column +" = ?\r\n"
			 		+ "WHERE ID_paciente ='"+id+"'";

		        try (PreparedStatement preparedStatement = con.prepareStatement(deleteQuery)) {
		            preparedStatement.setString(1, newValue);
		            preparedStatement.executeUpdate();
		        } catch (SQLException e) {
					
					e.printStackTrace();
				}
	}


	public void updateDiagnostico(String column,String newValue,String id) {
		 String deleteQuery = "UPDATE Diagnostico\r\n"
			 		+ "SET "+ column +" = ?\r\n"
			 		+ "WHERE ID_cita IN (SELECT ID_cita FROM Citas WHERE ID_paciente ='" +id+"' )";

		        try (PreparedStatement preparedStatement = con.prepareStatement(deleteQuery)) {
		            preparedStatement.setString(1, newValue);
		            preparedStatement.executeUpdate();
		        } catch (SQLException e) {
					
					e.printStackTrace();
				}
	}
	public void updateTratamientos(String column,String newValue,String id) {
		 String deleteQuery = " UPDATE Tratamientos\r\n"
		 		+ " SET "+column+" =?\r\n"
		 		+ "WHERE Tipo_de_tratamiento IN (\r\n"
		 		+ "    SELECT Tipo_de_tratamiento \r\n"
		 		+ "    FROM TratamientoHasDiagnostico \r\n"
		 		+ "    WHERE Tratamiento IN (\r\n"
		 		+ "        SELECT Tratamiento \r\n"
		 		+ "        FROM Diagnostico \r\n"
		 		+ "        WHERE ID_cita IN (\r\n"
		 		+ "            SELECT ID_cita \r\n"
		 		+ "            FROM Citas \r\n"
		 		+ "            WHERE ID_paciente = '"+id+"'"
		 		+ "        )\r\n"
		 		+ "    )\r\n"
		 		+ ")";

		        try (PreparedStatement preparedStatement = con.prepareStatement(deleteQuery)) {
		            preparedStatement.setString(1, newValue);
		            preparedStatement.executeUpdate();
		        } catch (SQLException e) {
					
					e.printStackTrace();
				}
		
	}
	public void updateTratamientoHasDiagnostico(String column,String newValue,String id) {
		 String deleteQuery = " UPDATE TratamientoHasDiagnostico \r\n"
		 		+ " SET "+ column+" = ? \r\n"
		 		+ "WHERE Tratamiento IN (\r\n"
		 		+ "    SELECT Tratamiento \r\n"
		 		+ "    FROM Diagnostico \r\n"
		 		+ "    WHERE ID_cita IN (\r\n"
		 		+ "        SELECT ID_cita \r\n"
		 		+ "        FROM Citas \r\n"
		 		+ "        WHERE ID_paciente = '"+id+"'\r\n"
		 		+ "    )\r\n"
		 		+ ")";

			        try (PreparedStatement preparedStatement = con.prepareStatement(deleteQuery)) {
			            preparedStatement.setString(1, newValue);
			            preparedStatement.executeUpdate();
			        } catch (SQLException e) {
						
						e.printStackTrace();
					}
	}
	
}









