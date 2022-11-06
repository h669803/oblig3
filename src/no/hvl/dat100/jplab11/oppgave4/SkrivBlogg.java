package no.hvl.dat100.jplab11.oppgave4;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import no.hvl.dat100.jplab11.oppgave3.*;

public class SkrivBlogg {

	public static boolean skriv(Blogg samling, String mappe, String filnavn) {
		
		PrintWriter writer;
		try {
			writer = new PrintWriter(mappe + filnavn);
		}
		catch (FileNotFoundException error) {
			System.out.println(error);
			return false;
		}
		writer.print(samling);
		writer.close();
		return true;
		
	}
}
