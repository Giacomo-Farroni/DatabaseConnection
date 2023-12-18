
public class Spostamento {
String codice_fiscale, codice_mezzo;
int km_percorsi, id_spostamento;
public String getCodice_fiscale() {
	return codice_fiscale;
}
public void setCodice_fiscale(String codice_fiscale) {
	this.codice_fiscale = codice_fiscale;
}
public String getCodice_mezzo() {
	return codice_mezzo;
}
public void setCodice_mezzo(String codice_mezzo) {
	this.codice_mezzo = codice_mezzo;
}
public int getKm_percorsi() {
	return km_percorsi;
}
public void setKm_percorsi(int km_percorsi) {
	this.km_percorsi = km_percorsi;
}
public int getId_spostamento() {
	return id_spostamento;
}
public void setId_spostamento(int id_spostamento) {
	this.id_spostamento = id_spostamento;
}
public Spostamento(String codice_fiscale, String codice_mezzo, int km_percorsi, int id_spostamento) {
	super();
	this.codice_fiscale = codice_fiscale;
	this.codice_mezzo = codice_mezzo;
	this.km_percorsi = km_percorsi;
	this.id_spostamento = id_spostamento;
}
@Override
public String toString() {
	return "Spostamento [codice_fiscale=" + codice_fiscale + ", codice_mezzo=" + codice_mezzo + ", km_percorsi="
			+ km_percorsi + ", id_spostamento=" + id_spostamento + "]";
}



}
