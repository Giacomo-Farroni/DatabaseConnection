import java.sql.Connection;														//classe istanziata per mantenere il riferimento alla connessione stabilita verso il database 
	import java.sql.DriverManager;													//classe di riferimento per l'uso del JDBC driver installato come dipendenza nel pom.xml di Maven										
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;													//imposta classe SqlException per gestione errori nella comunicazione col database



public class Main_parte3 { 
	public static void main(String[] args) {
		
		/*
		 * /* -- PREMESSA
		a) Si consiglia di svolgere prima la PARTE 2 dell'esercizio
		"Esercizio_20231214_ConnessioneConSqlSelect"
		NOTA: utilizzo di getInt() per i tipi numerici
		b) Sempre Utilizzando come riferimento gli allegati e quanto svolto
		nell'esercizio del 14 dicembre 2023
		"Esercizio_20231214_ConnessioneConSqlSelect",
		si consideri il database "trasporti" di cui la soluzione all'esercizio
		numero 1 del test:
		1) Implementare i bean per la tabella mezzo, per la tabella
		cittadino e per la tabella spostamento (non considerare il campo
		data_ora_spostamento)
		2) Scrivere le istruzioni che implementano le seguenti query di
		SELECT:
		NOTA: tenere presente che i campi vanno elencati,
		ad esempio, non usare mezzo.* ma
		mezzo.codice_mezzo, mezzo.limite_km_percorribili ecc..
		-- a) selezionare tutti i mezzi di trasporto di tipo tram
		SELECT mezzo.*
		FROM mezzo
		WHERE mezzo.codice_mezzo like 'TN%'
		-- d) selezionare tutti gli spostamenti effettuati in una
		determinata data
		SELECT spostamento.*
		FROM spostamento
		WHERE spostamento.data_spostamento = '2023-05-12'
		-- f) calcolare il totale di chilometri percorsi da un
		determinato cittadino di cui Ã ̈ noto il codice fiscale,
		-- indipendentemente dal mezzo di trasporto usato
		SELECT spostamento.codice_fiscale,
		SUM(spostamento.km_percorsi) as km_percorsi
		FROM spostamento
		WHERE spostamento.codice_fiscale = 'EDRFRS0123456789'
		GROUP BY spostamento.codice_fiscale
		Nel caso f) si suggerisce di creare un bean apposito con
		due attributi: codice_fiscale e km_percorsi
		 */	
				
	
			
			// Connessione al database
			String databaseName = "trasposrti pubblici";										//nome del database a cui connettersi
			String dbmsUserName = "root";											//nome utente configurato nel dbms
			String dbmsPassword = "";												//password utente configurato nel dbms
			String jdbcUrl = "jdbc:mariadb://localhost:3306/" + databaseName;		
			
			try {																	//prova ad eseguire le istruzioni interne al blocco try-catch

				/****************************************************************************/
				/*					CONNESSIONE AL DATABASE									*/
				/****************************************************************************/
				Connection jdbcConnectionToDatabase = 								//esegue la connessione al dbms con riferimento al database, se fallisce genera eccezzione SQLException (effettuare il debugging per verificarlo)
						DriverManager.getConnection(jdbcUrl
												  , dbmsUserName
												  , dbmsPassword);
				
				System.out.println("Connessione al database magazzino riuscita!");	//visualizza messaggio per avvenuta connessione al database
				

				/****************************************************************************/
				/*					ESECUZIONE DELLA QUERY DI SELECT						*/
				/****************************************************************************/
				
			    String selectFromMezzobyCodiceMezzo =							
			    			" SELECT codice_mezzo, descrizione,  anno_produzione, "
			    			+ "limite_km_percorribili, capienza_massima, indice_ecologico"
			              + "   FROM mezzo                    "
			              + "  WHERE mezzo.codice_mezzo = ? ";

			    String parametroCodiceMezzo = "BS-002";					
			    
	            PreparedStatement preparedStatement =								  
	            		jdbcConnectionToDatabase.prepareStatement(selectFromMezzobyCodiceMezzo);
	            preparedStatement.setString(1, parametroCodiceMezzo);				
	            
	            ResultSet rsSelect
		            = preparedStatement.executeQuery();								

	           Mezzo mezzo = null;												
			            
					    if (rsSelect.next()) {												
					
					        String codice_mezzo
					        		= rsSelect.getString("codice_mezzo");		
					        if (rsSelect.wasNull()) {
					            codice_mezzo = "";
					        }
							      int anno_produzione
					        		= rsSelect.getInt("anno_produzione");
								        if (rsSelect.wasNull()) {
								        	anno_produzione = 0;}
					        		
					        			int limite_km_percorsi
					        					=rsSelect.getInt("limite_km_percorribili");
					        					if (rsSelect.wasNull()) {
					        						limite_km_percorsi = 0;}
					        					
				
					    	        			int capienza_massima
					    	        					=rsSelect.getInt("capienza_massima");
					    	        			
					    	        						if (rsSelect.wasNull()) {
					    	        							capienza_massima = 0;}
					    	        						
							    	        					
							    	        				int indice_ecologico
								    	        					=rsSelect.getInt("indice_ecologico");
									    	        					if (rsSelect.wasNull()) {
								    	        					    	indice_ecologico = 0;}
								    	        					
							    	        								        
																		  String descrizione												//lettura del valore del campo 'nominativo'
																                = rsSelect.getString("descrizione");
																        		if (rsSelect.wasNull()) {
																        			descrizione = "";}
			       
					        
			       mezzo = new Mezzo( indice_ecologico, anno_produzione, limite_km_percorsi, capienza_massima, codice_mezzo, descrizione);
			       
			       //istanzia un oggetto di tipo classe Cliente inizializzandolo con i valori letti dal record
			
			    }            
			    
			    if (mezzo != null) {
			    	System.out.println("Dati del cliente letto=> " + mezzo.toString());
			    }
			    else {
			    	System.out.println("Il cliente ricercato non � presente!!!");
			    }
			    
			    
			    
			    
			    
			} catch (SQLException e) {												//errore di tipo classe SQLException
				// TODO Auto-generated catch block
				e.printStackTrace();												//stampa la pila (stack) degli errori, dal pi� recente al meno recente
			}
			
		}}
