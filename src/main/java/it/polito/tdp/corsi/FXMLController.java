package it.polito.tdp.corsi;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import it.polito.tdp.corsi.model.Corso;
import it.polito.tdp.corsi.model.Model;
import it.polito.tdp.corsi.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	Model model;
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField txtPeriodo;

	@FXML
	private TextField txtCorso;

	@FXML
	private Button btnCorsiPerPeriodo;

	@FXML
	private Button btnStudenti;

	@FXML
	private Button btnNumeroStudenti;

	@FXML
	private Button btnDivisioneStudenti;

	@FXML
	private TextArea txtRisultato;

	@FXML
	void NumeroStudenti(ActionEvent event) {
		this.txtRisultato.clear();
		String pdString= this.txtPeriodo.getText();
		
		Integer pd;
		try {
		pd= Integer.parseInt(pdString);
		}catch(NumberFormatException e) {
			this.txtRisultato.setText("Devi inserire un numero (1 o 2)!");
			return;
		}
		if(!pd.equals( 1) && !pd.equals(2)) {
			this.txtRisultato.setText("Devi inserire un numero (1 o 2)!");
			return;
		}
		Map<Corso,Integer> statistiche= this.model.getIscrittiByPeriodo(pd);
		
		for(Corso c: statistiche.keySet()) {
			this.txtRisultato.appendText(c.getNome()+" "+statistiche.get(c)+"\n");
		}
	}

	@FXML
	void StampaStudenti(ActionEvent event) {
		this.txtRisultato.clear();
		String codins= this.txtCorso.getText();
		
		if(!model.esisteCorso(new Corso(codins,null,null,null))) {
			this.txtRisultato.appendText("Il corso non esiste!\n");
			return;
		}
		List<Studente> studenti= this.model.getStudentiByCorso(new Corso(codins,null,null,null));
		if(studenti.size()==0) {
			this.txtRisultato.setText("Il corso non ha studenti iscritti");
			return;
		}
		for(Studente s: studenti) {
			this.txtRisultato.appendText(s.toString()+"\n");
		}
	}

	@FXML
	void corsiPerPeriodo(ActionEvent event) {
		this.txtRisultato.clear();
		String pdString= this.txtPeriodo.getText();
		
		Integer pd;
		try {
		pd= Integer.parseInt(pdString);
		}catch(NumberFormatException e) {
			this.txtRisultato.setText("Devi inserire un numero (1 o 2)!");
			return;
		}
		if(!pd.equals( 1) && !pd.equals(2)) {
			this.txtRisultato.setText("Devi inserire un numero (1 o 2)!");
			return;
		}
		//l'inut è corretto
		List<Corso> corsi= this.model.getCorsiByPeriodo(pd);
		
		for(Corso c: corsi) {
			txtRisultato.appendText(c.toString()+"\n");
		}
	}

	@FXML
	void stampaDivisione(ActionEvent event) {
		this.txtRisultato.clear();
		String codins= this.txtCorso.getText();
		
		if(!model.esisteCorso(new Corso(codins,null,null,null))) {
			this.txtRisultato.appendText("Il corso non esiste!\n");
			return;
		}
		Map<String,Integer> statistiche= this.model.getDivisioneCDS(new Corso(codins,null,null,null));

		for(String c: statistiche.keySet()) {
			this.txtRisultato.appendText(c+" "+statistiche.get(c)+"\n");
		}
		
	}

	public void setModel(Model model) {
		this.model = model;
	}

	@FXML
	void initialize() {
		assert txtPeriodo != null : "fx:id=\"txtPeriodo\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtCorso != null : "fx:id=\"txtCorso\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnCorsiPerPeriodo != null : "fx:id=\"btnCorsiPerPeriodo\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnStudenti != null : "fx:id=\"btnStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnNumeroStudenti != null : "fx:id=\"btnNumeroStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnDivisioneStudenti != null : "fx:id=\"btnDivisioneStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";

	}
}
