package eg.edu.alexu.csd.datastructure.mailServer.cs03_cs24_cs54;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import eg.edu.alexu.csd.datastructure.linkedList.cs03_cs10.DLinkedList;
import eg.edu.alexu.csd.datastructure.mailServer.IMail;

public class Mail implements IMail {
	
	public static String from, to, subject, email_body;
	
	public Mail() {
		from = null;
		to = null;
		subject = null;
		email_body = null;
	}
	
	@Override
	public void var2(String fro, String t, String sub, String email_body) {
		from = fro;
		to = t;
		subject = sub;
		Mail.email_body = email_body;
	}

	
		
		
		
		
		
	

}
