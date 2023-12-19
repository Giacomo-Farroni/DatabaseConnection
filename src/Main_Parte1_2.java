

import java.sql.Connection;														//classe istanziata per mantenere il riferimento alla connessione stabilita verso il database 
import java.sql.DriverManager;													//classe di riferimento per l'uso del JDBC driver installato come dipendenza nel pom.xml di Maven										
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;													//imposta classe SqlException per gestione errori nella comunicazione col database
import java.util.ArrayList;
import java.util.List;


public class Main_Parte1_2 {

	
	public static void main(String[] args) {									//metodo di accesso all'applicazione da parte della Java Virtual Machine (vedi slide architettura Java)
		
		
		// Connessione al database
		String databaseName = "magazzino";										//nome del database a cui connettersi
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
			
		    String selectFromProdottobyCodiceProdoto =							
		    			" SELECT codice_Prodotto, descrizione, quantita_disponibile, prezzo "
		              + "   FROM prodotto                    "
		              + "  WHERE prodotto.codice_Prodotto = ? ";

		    String parametroCodiceProdotto = "LVDR1500";					
		    
            PreparedStatement preparedStatement =								
            		jdbcConnectionToDatabase.prepareStatement(selectFromProdottobyCodiceProdoto);
            preparedStatement.setString(1, parametroCodiceProdotto);				
            
            ResultSet rsSelect
	            = preparedStatement.executeQuery();								

            Prodotto prodotto= null;
            
		    if (rsSelect.next()) {												
		
		        String codiceProdotto 
		        		= rsSelect.getString("codice_Prodotto");		
		        if (rsSelect.wasNull()) {
		            codiceProdotto = "";
		        }
		        
		        
		       int prezzo 
        				= rsSelect.getInt("prezzo");
		       if (rsSelect.wasNull()) {
		    	   prezzo = 0;}
        		
		       
    		  int quantitaDisponibile 
    					=rsSelect.getInt("quantita_disponibile");
    			 if (rsSelect.wasNull()) {
		            quantitaDisponibile = 0;}
		        
		        
		        String descrizione												
		                = rsSelect.getString("descrizione");
		        if (rsSelect.wasNull()) {
		            descrizione = "";
		        }
		       
		       
		
		        prodotto = new Prodotto(codiceProdotto, descrizione, prezzo, quantitaDisponibile);					
		    }            
		    
		    if (prodotto != null) {
		    	System.out.println("Dati del cliente letto=> " + prodotto.toString());
		    }
		    else {
		    	System.out.println("Il cliente ricercato non � presente!!!");
		    }
		    
			
		    /* --- PARTE 2 ---
		    7) Scrivere le istruzioni per la SELECT con LIKE che ritorna l'elenco di
		    prodotti di tipo televisore
		    
		     */
		    		
		    		
		    // 7) Scrivere le istruzioni per la SELECT con LIKE che ritorna l'elenco di
		    // prodotti di tipo televisore
		    
		    String selectFromProdottobyTelevisore =							
	    			" SELECT codice_Prodotto, descrizione, quantita_disponibile, prezzo "
	              + "   FROM prodotto                    "
	              + "  WHERE prodotto.codice_Prodotto LIKE ?  ";
		    
		    String parametroTelevisore = "TV%";
		    
		    preparedStatement =								
            		jdbcConnectionToDatabase.prepareStatement(selectFromProdottobyTelevisore);
            preparedStatement.setString(1, parametroTelevisore);
		    
            rsSelect
            = preparedStatement.executeQuery();								

            List <Prodotto> televisori = new ArrayList<>();
            
            while (rsSelect.next()) {
            	
            	 String codiceProdotto 
	        		= rsSelect.getString("codice_Prodotto");		
	        if (rsSelect.wasNull()) {
	            codiceProdotto = "";
	        }
	        
	        
	       int prezzo 
 				= rsSelect.getInt("prezzo");
	       if (rsSelect.wasNull()) {
	    	   prezzo = 0;}
 		
	       
		  int quantitaDisponibile 
					=rsSelect.getInt("quantita_disponibile");
			 if (rsSelect.wasNull()) {
	            quantitaDisponibile = 0;}
	        
	        
	        String descrizione												
	                = rsSelect.getString("descrizione");
	        if (rsSelect.wasNull()) {
	            descrizione = "";
	        } 
            	
	        televisori.add(new Prodotto(codiceProdotto, descrizione, prezzo, quantitaDisponibile));
            	
            }
		    
            if(!televisori.isEmpty())
            	System.out.println("/nRisultati trovati: " + televisori.toString());
            else
            	System.out.println("Nessun risultato trovato");
		    
		    
            /* 8) Inserire un nuovo prodotto di tipo televisore
		    
		    */
                      
          
            
            
            String insertFromProdottobyTelevisore =							
	    			" INSERT INTO prodotto (codice_prodotto, descrizione, quantita_disponibile, prezzo "
	              + "   VALUES ( ?,?,?,?)                ";
            
           /* String insertCodice =" Tv100";
            String insertDescrizione = "Smart-tv";
            int insertQuantita = 10;
            float insertPrezzo = 500;
            
            preparedStatement =								
            		jdbcConnectionToDatabase.prepareStatement(insertFromProdottobyTelevisore);
            preparedStatement.setString(1, insertCodice);
            preparedStatement.setString(2, insertDescrizione);
            preparedStatement.setInt(3, insertQuantita);
            preparedStatement.setFloat(4, insertPrezzo); */
            
            Prodotto p=new Prodotto("TV100", "Smart-Tv", 10, 500);
	              
            preparedStatement =								
            		jdbcConnectionToDatabase.prepareStatement(insertFromProdottobyTelevisore);
            preparedStatement.setString(1, p.getCodiceProdotto());
            preparedStatement.setString(2, p.getDescrizione());
            preparedStatement.setInt(3, p.getQuantitaDisponibile());
            preparedStatement.setFloat(4, p.getPrezzo());
           
            preparedStatement.executeUpdate();
            
          System.out.println("prodotto inserito " + p.toString());

            
            
            
            // 9) Ricercare tutti i prodotti di tipo televisor
            
            
            
            
            
		} catch (SQLException e) {												//errore di tipo classe SQLException
			// TODO Auto-generated catch block
			e.printStackTrace();												//stampa la pila (stack) degli errori, dal pi� recente al meno recente
		}
		
		
		
		
		
		
		

		 
		

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	} 
	
	
	
	
}