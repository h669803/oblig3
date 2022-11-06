package no.hvl.dat100.jplab11.oppgave6;

import no.hvl.dat100.jplab11.oppgave1.Innlegg;
import no.hvl.dat100.jplab11.oppgave2.*;
import no.hvl.dat100.jplab11.oppgave3.Blogg;

public class HtmlBlogg extends Blogg {

	public HtmlBlogg() {
		super();
	}
	
	private static String HTMLPREFIX = 
			"<!DOCTYPE html>\t<html>\n<head>\n\t<title>DAT100 Blogg</title>\n</head>\n<body>\n";
	
	private static String HTMLPOSTFIX = 
			"\n</body>\n</html>";
	
	private static String TEKSTFORMAT = "\t<h2>%s@%s [%d]</h2>\n\t<p>%s</p>";
	private static String BILDEFORMAT = "\n\t<img src=\"%s\">";
	
	@Override
	public String toString() {
		Innlegg[] samling = getSamling();
		int antall = getAntall();
		String[] result = new String[antall];
		for (int i = 0; i < antall; i++) {
			Tekst innlegg = (Tekst) samling[i];
			
			String str = String.format(
				TEKSTFORMAT, 
				innlegg.getBruker(), 
				innlegg.getDato(),
				innlegg.getLikes(),
				innlegg.getTekst()
			);
			if (innlegg instanceof Bilde) str += String.format(BILDEFORMAT, ((Bilde)innlegg).getUrl());
			result[i] = str;
			
		}
		return HTMLPREFIX + String.join("\n\t<hr>\n", result) + HTMLPOSTFIX;
		
	}
}
