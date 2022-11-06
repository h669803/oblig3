package no.hvl.dat100.jplab11.oppgave3;

import java.util.Arrays;
import java.lang.StringBuilder;
import no.hvl.dat100.jplab11.oppgave1.*;
import no.hvl.dat100.jplab11.oppgave2.Tekst;

public class Blogg {
	private Innlegg[] innleggtabell;
	private int nesteledig = 0;

	public Blogg() {
		innleggtabell = new Innlegg[20];
	}

	public Blogg(int lengde) {
		innleggtabell = new Innlegg[lengde];
	}

	public int getAntall() {
		return nesteledig;
	}
	
	public Innlegg[] getSamling() {
		return innleggtabell;
	}
	
	public int finnInnlegg(Innlegg innlegg) {
		for (int i = 0; i < nesteledig; i++)
			if (innleggtabell[i].erLik(innlegg)) return i;
		return -1;
	}

	public boolean finnes(Innlegg innlegg) {
		for (int i = 0; i < nesteledig; i++)
			if (innlegg.erLik(innleggtabell[i])) return true;
		return false;
	}

	public boolean ledigPlass() {
		return nesteledig < innleggtabell.length;
	}
	
	public boolean leggTil(Innlegg innlegg) {
		if (!ledigPlass() || finnes(innlegg)) return false;
		innleggtabell[nesteledig++] = innlegg;
		return true;
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder(nesteledig + "\n");
		for (Innlegg innlegg : Arrays.copyOf(innleggtabell, nesteledig))
			str.append(innlegg);
		
		return str.toString();
	}

	// valgfrie oppgaver nedenfor
	
	public void utvid() {
		int l = innleggtabell.length;
		innleggtabell = Arrays.copyOf(innleggtabell, l * 2);
		// Kan også enkelt implementeres med en for-løkke
		
		// Innlegg[] copy = new Innlegg[l * 2];
		// for (int i = 0; i < l; i++)
		// 	copy[i] = innleggtabell[i];
		
		// innleggtabell = copy;
	}
	
	public boolean leggTilUtvid(Innlegg innlegg) {
		if (finnes(innlegg)) return false;
		if (!ledigPlass()) utvid();
		innleggtabell[nesteledig++] = innlegg;
		return true;
	}
	
	public boolean slett(Innlegg innlegg) {
		int id = finnInnlegg(innlegg);
		if (id == -1) return false;
		// Flytter alle innleggene bak en index frem
		for (int i = id + 1; i < nesteledig; i++)
			innleggtabell[i - 1] = innleggtabell[i];
		
		nesteledig--;
		return true;
	}
	
	public int[] search(String keyword) {
		
		int[] result = new int[nesteledig];
		int num = 0;
		for (int i = 0; i < nesteledig; i++) {
			Innlegg innlegg = innleggtabell[i];
			// Typecaster for å kunne bruke getTekst() metoden

			if (((Tekst)innlegg).getTekst().toLowerCase().contains(keyword.toLowerCase()))
				result[num++] = innlegg.getId();
			
			// Kan også implementeres ved å kalle .toString().split("\n")[4] uten typecasting,
			// men dette vil være mer sårbart for endringer i koden
			
		}
		
		// Trimmer tabellen til riktig lengde
		return Arrays.copyOf(result, num);

	}
}