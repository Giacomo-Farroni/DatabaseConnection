
public class Prodotto {


	String codiceProdotto, descrizione;
	int quantitaDisponibile;
	int prezzo;
	

	
	
	public String getCodiceProdotto() {
		return codiceProdotto;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public Integer getQuantitaDisponibile() {
		return quantitaDisponibile;
	}
	public float getPrezzo() {
		return prezzo;
	}
	
	
	
	public Prodotto(String codiceProdotto, String descrizione, int prezzo, int quantitaDisponibile) {
		super();
		this.codiceProdotto = codiceProdotto;
		this.descrizione = descrizione;
		this.quantitaDisponibile = quantitaDisponibile;
		this.prezzo = prezzo;
	}
	

	@Override
	public String toString() {
		return "Prodotto [codiceProdotto=" + codiceProdotto + ", descrizione=" + descrizione + ", quantitaDisponibile="
				+ quantitaDisponibile + ", prezzo=" + prezzo + "]";}
	

	
	

}
