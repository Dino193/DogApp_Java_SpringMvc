package com.example.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "RasaPsa")
public class RasaPsa {

		

		@Id
		@Column(name ="RasaID") //kolona u bazi podataka
		@GeneratedValue(strategy = GenerationType.AUTO)
		private int idRasa;
		
		@Column(name ="ImeRase")
		private String imeRase;
		
		@Column(name ="Opis")
		private String opisRase;

		
		
		public RasaPsa() {
			
		}




		public RasaPsa(String imeRase, String opisRase) {
			super();
			this.imeRase = imeRase;
			this.opisRase = opisRase;
		}








		public String getImeRase() {
			return imeRase;
		}




		public void setImeRase(String imeRase) {
			this.imeRase = imeRase;
		}




		public String getOpisRase() {
			return opisRase;
		}




		public void setOpisRase(String opisRase) {
			this.opisRase = opisRase;
		}




		@Override
		public String toString() {
			return "RasaPsa [idRasa=" + idRasa + ", imeRase=" + imeRase + ", opisRase=" + opisRase + "]";
		}




	
		


	}

