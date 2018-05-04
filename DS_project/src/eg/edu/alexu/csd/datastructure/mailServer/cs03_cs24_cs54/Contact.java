package eg.edu.alexu.csd.datastructure.mailServer.cs03_cs24_cs54;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import eg.edu.alexu.csd.datastructure.linkedList.cs03_cs10.DLinkedList;
import eg.edu.alexu.csd.datastructure.mailServer.IContact;

public class Contact implements IContact {

	public static String emal;
	public static String password;
	public static String contact_name;

	public Contact() {
		emal = null;
		password = null;
		contact_name = null;

	}
	
	public void create_file(String path) {
		try {
		File x = new File (path + "/Index file.json");
		x.createNewFile();
		} catch (IOException e) {
			
		}
	}

	@Override
	public void var(String emai, String passwor, String contact_nam) {
		emal = emai;
		password = passwor;
		contact_name = contact_nam;
	}

	public void var1(String emai, String passwor) {
		emal = emai;
		password = passwor;

	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean check(String email) {
		email = emal;
		DLinkedList emails = new DLinkedList();
		boolean flag = false;
		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(new FileReader("Users/contact.json"));

			JSONObject jsonObject = (JSONObject) obj;

			// loop array
			// here we load content of json file
			JSONArray col1 = (JSONArray) jsonObject.get("emails");
			Iterator<String> iterator1 = col1.iterator();

			while (iterator1.hasNext()) {
				emails.add(iterator1.next());

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int i = 0; i < emails.size(); i++) {
			if (Objects.equals(email, emails.get(i))) {
				flag = true;
			}
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void write_contact(String email, String pass, String contact_nam) {
		email = emal;
		pass = password;
		contact_nam = contact_name;
		DLinkedList emails = new DLinkedList();
		DLinkedList passwords = new DLinkedList();
		DLinkedList names = new DLinkedList();
		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(new FileReader("Users/contact.json"));

			JSONObject jsonObject = (JSONObject) obj;

			// loop array
			// here we load content of json file
			JSONArray col1 = (JSONArray) jsonObject.get("emails");
			JSONArray col2 = (JSONArray) jsonObject.get("pass");
			JSONArray col3 = (JSONArray) jsonObject.get("name");
			Iterator<String> iterator1 = col1.iterator();
			Iterator<String> iterator2 = col2.iterator();
			Iterator<String> iterator3 = col3.iterator();
			while (iterator1.hasNext()) {

				emails.add(iterator1.next());

			}
			while (iterator2.hasNext()) {
				passwords.add(iterator2.next());

			}
			while (iterator3.hasNext()) {
				names.add(iterator3.next());

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject obj1 = new JSONObject();
		JSONArray k1 = new JSONArray();
		JSONArray k2 = new JSONArray();
		JSONArray k3 = new JSONArray();
		emails.add(email);
		passwords.add(pass);
		names.add(contact_nam);
		for (int i = 0; i < emails.size(); i++) {
			k1.add(emails.get(i));
			k2.add(passwords.get(i));
			k3.add(names.get(i));
		}

		obj1.put("emails", k1);
		obj1.put("pass", k2);
		obj1.put("name", k3);
		try (FileWriter file = new FileWriter("Users/contact.json")) {

			file.write(obj1.toString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	


	@SuppressWarnings({ "unchecked", "unused" })
	@Override
	public boolean check(String email, String pass) {
		email = emal;

		pass = password;
		DLinkedList emails = new DLinkedList();
		DLinkedList passwords = new DLinkedList();
		DLinkedList names = new DLinkedList();
		boolean flag = false;
		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(new FileReader("Users/contact.json"));

			JSONObject jsonObject = (JSONObject) obj;

			// loop array
			// here we load content of json file
			JSONArray col1 = (JSONArray) jsonObject.get("emails");
			JSONArray col2 = (JSONArray) jsonObject.get("pass");
			JSONArray col3 = (JSONArray) jsonObject.get("name");
			Iterator<String> iterator1 = col1.iterator();
			Iterator<String> iterator2 = col2.iterator();
			Iterator<String> iterator3 = col3.iterator();

			while (iterator1.hasNext()) {
				emails.add(iterator1.next());

			}
			while (iterator2.hasNext()) {
				passwords.add(iterator2.next());

			}
			while (iterator3.hasNext()) {
				names.add(iterator3.next());

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (int i = 0; i < emails.size(); i++) {

			if ((Objects.equals(email, emails.get(i))) && (Objects.equals(pass, passwords.get(i)))) {

				flag = true;
				emal = (String) emails.get(i);
				password = (String) passwords.get(i);
				contact_name = (String) names.get(i);
			}
		}

		return flag;
	}

}
