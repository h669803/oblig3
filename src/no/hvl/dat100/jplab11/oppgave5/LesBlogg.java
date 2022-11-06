package no.hvl.dat100.jplab11.oppgave5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import no.hvl.dat100.jplab11.oppgave2.*;
import no.hvl.dat100.jplab11.oppgave3.*;

public class LesBlogg {

	private static String TEKST = "TEKST";

	public static Blogg les(String mappe, String filnavn) {
		
		Scanner reader;
		Blogg blogg;
		try {
			reader = new Scanner(new File(mappe + filnavn));
		}
		catch (FileNotFoundException error) {
			System.out.print(error);
			return new Blogg();
		}
		int antall;
		if (reader.hasNextInt()) blogg = new Blogg(antall = Integer.parseInt(reader.nextLine()));
		else return new Blogg();
		
		try {
			for (int i = 0; i < antall; i++) {
				String type = reader.nextLine();
				int id = Integer.parseInt(reader.nextLine());
				String bruker = reader.nextLine();
				String dato = reader.nextLine();
				int likes = Integer.parseInt(reader.nextLine());
				String tekst = reader.nextLine();
				
				if (type.equals(TEKST)) {
					blogg.leggTil(new Tekst(id, bruker, dato, likes, tekst));
					continue;
				}
				String url = reader.nextLine();
				blogg.leggTil(new Bilde(id, bruker, dato, likes, tekst, url));
			}
		}
		catch (Exception error) {
			System.out.print(error);
			return blogg;
		}
		
		return blogg;
		
	}
}
