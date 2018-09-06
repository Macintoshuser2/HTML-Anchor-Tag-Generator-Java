package com.macintoshuser2.generator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	static ArrayList<String> linkTitles = null;
	static ArrayList<String> linkIDs = null;
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Hello, start entering link titles!");
		linkTitles = askForNames();
		linkIDs = askForIDs(linkTitles);
		
		StringBuilder builder = new StringBuilder();
		
		for(int i = 0; i < linkTitles.size(); i++) {
			builder.append("<a href='#" + linkIDs.get(i) + "' title='" + linkTitles.get(i) + "'>" + linkTitles.get(i) + "</a>\n");
		}
		
		try {
			Files.write(Paths.get("links.txt"), builder.toString().getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static ArrayList<String> askForNames() {
		ArrayList<String> linkTitles = new ArrayList<String>();
		String choice = null;
		
		do {
			System.out.print("Enter a name: ");
			String name = scanner.nextLine();
			linkTitles.add(name);
			System.out.print("Do you have another name? (y/n)");
			choice = scanner.nextLine();
		} while(choice.equalsIgnoreCase("y"));
		
		String[] titles = new String[linkTitles.size()];
		titles = linkTitles.toArray(titles);
		Arrays.sort(titles);
		
		linkTitles.clear();
		
		for(String s : titles) {
			linkTitles.add(s);
		}
		
		return linkTitles;
	}
	
	private static ArrayList<String> askForIDs(ArrayList<String> linkNames) {
		ArrayList<String> linkIDs = new ArrayList<String>();
		
		for(String s : linkNames) {
			System.out.print("Enter an ID for " + s);
			String id = scanner.nextLine();
			linkIDs.add(id);
		}
		
		return linkIDs;
	}
}
