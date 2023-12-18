
public class Mezzo {
	
	
	int anno_produzione, limite_km_percorsi, capienza_massima, indice_ecologico;
	String descrizione, codice_mezzo;
	public int getAnno_produzione() {
		return anno_produzione;
	}
	public int getLimite_km_percorsi() {
		return limite_km_percorsi;
	}
	public int getCapienza_massima() {
		return capienza_massima;
	}
	public int getIndice_ecologico() {
		return indice_ecologico;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public String getCodice_mezzo() {
		return codice_mezzo;
	}
	public Mezzo(int anno_produzione, int limite_km_percorsi, int capienza_massima, int indice_ecologico,
			String descrizione, String codice_mezzo) {
		super();
		this.anno_produzione = anno_produzione;
		this.limite_km_percorsi = limite_km_percorsi;
		this.capienza_massima = capienza_massima;
		this.indice_ecologico = indice_ecologico;
		this.descrizione = descrizione;
		this.codice_mezzo = codice_mezzo;
	}
	@Override
	public String toString() {
		return "Mezzo [anno_produzione=" + anno_produzione + ", limite_km_percorsi=" + limite_km_percorsi
				+ ", capienza_massima=" + capienza_massima + ", indice_ecologico=" + indice_ecologico + ", descrizione="
				+ descrizione + ", codice_mezzo=" + codice_mezzo + "]";
	}
	
	
	

	
}
