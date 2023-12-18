
public class Cittadino {
String nominativo, zona_residenza, codice_fiscale;

public String getNominativo() {
	return nominativo;
}

public void setNominativo(String nominativo) {
	this.nominativo = nominativo;
}

public String getZona_residenza() {
	return zona_residenza;
}

public void setZona_residenza(String zona_residenza) {
	this.zona_residenza = zona_residenza;
}

public String getCodice_fiscale() {
	return codice_fiscale;
}

public void setCodice_fiscale(String codice_fiscale) {
	this.codice_fiscale = codice_fiscale;
}

public Cittadino(String nominativo, String zona_residenza, String codice_fiscale) {
	super();
	this.nominativo = nominativo;
	this.zona_residenza = zona_residenza;
	this.codice_fiscale = codice_fiscale;
}

@Override
public String toString() {
	return "Cittadino [nominativo=" + nominativo + ", zona_residenza=" + zona_residenza + ", codice_fiscale="
			+ codice_fiscale + "]";
}



}
