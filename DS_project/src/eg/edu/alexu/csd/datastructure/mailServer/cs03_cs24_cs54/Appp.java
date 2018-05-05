package eg.edu.alexu.csd.datastructure.mailServer.cs03_cs24_cs54;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import eg.edu.alexu.csd.datastructure.linkedList.ILinkedList;
import eg.edu.alexu.csd.datastructure.linkedList.cs03_cs10.DLinkedList;
import eg.edu.alexu.csd.datastructure.mailServer.IApp;
import eg.edu.alexu.csd.datastructure.mailServer.IContact;
import eg.edu.alexu.csd.datastructure.mailServer.IFilter;
import eg.edu.alexu.csd.datastructure.mailServer.IFolder;
import eg.edu.alexu.csd.datastructure.mailServer.IMail;
import eg.edu.alexu.csd.datastructure.mailServer.ISort;

public class Appp implements IApp {
	/**
	 * @serialField
	 */
	static DLinkedList fol = new DLinkedList();

	// Function used to read the data of jtree in gui which displays the content of
	// folders
	static DLinkedList read() throws IOException {
		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(new FileReader("recources/test.json"));
			Object obj1 = parser.parse(new FileReader("recources/t.json"));

			JSONObject jsonObject = (JSONObject) obj;
			JSONObject jsonObject1 = (JSONObject) obj1;
			String name = (String) jsonObject1.get("state");

			// loop array
			JSONArray msg = (JSONArray) jsonObject.get("Folders");
			@SuppressWarnings("unchecked")
			Iterator<String> iterator = msg.iterator();
			if (!Boolean.parseBoolean(name)) {
				while (iterator.hasNext()) {

					fol.add(iterator.next());

				}
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
		return fol;
	}

	// function used to write data of jtree which displays folders
	@SuppressWarnings("unchecked")
	static void write(DLinkedList x) {
		JSONObject obj = new JSONObject();

		JSONArray k = new JSONArray();
		for (int i = 0; i < x.size(); i++) {
			k.add(x.get(i));
		}

		obj.put("Folders", k);
		try (FileWriter file = new FileWriter("recources/test.json")) {

			file.write(obj.toString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// function used to write data of jtree which displays folders
	@SuppressWarnings("unchecked")
	static void writee(boolean state) {

		JSONObject obj = new JSONObject();
		obj.put("state", String.valueOf(state));

		try (FileWriter file = new FileWriter("recources/t.json")) {

			file.write(obj.toString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean signin(String email, String password) {
		Contact x = new Contact();
		x.var1(email, password);
		if (x.check(email, password)) {
			return true;

		}
		return false;

	}

	@SuppressWarnings("serial")
	public boolean signup(final IContact contact) {

		String path = "Users";
		File dir = new File(path);
		int counter = 0;
		String temp, folders;
		String email, password, cont;
		email = Contact.emal;
		password = Contact.password;
		cont = Contact.contact_name;

		ArrayList<String> list = new ArrayList<String>() {
			{
				add("Inbox");
				add("Sent");
				add("Received");
				add("Filterd mails");
				add("Junk");
			}
		};

		if (dir.exists()) {
			if (contact.check(email)) {
				// mail is existed
				return false;
			} else {
				path = path + "/" + cont;
				temp = path;
				dir = new File(path);
				dir.mkdirs();

				contact.write_contact(email, password, cont);

				while (counter != list.size()) {
					folders = temp + "/" + list.get(counter);
					dir = new File(folders);
					dir.mkdirs();
					if (list.get(counter) == "Inbox" || list.get(counter) == "Sent") {
						contact.create_file(folders);
					}

					counter++;
				}
				return true;
			}
		} else {
			dir.mkdirs();
			path = path + "/" + cont;
			temp = path;
			dir = new File(path);
			dir.mkdirs();

			while (counter != list.size()) {
				folders = temp + "/" + list.get(counter);
				dir = new File(folders);
				dir.mkdirs();
				if (list.get(counter) == "Inbox" || list.get(counter) == "Sent") {
					contact.create_file(folders);
				}
				counter++;
			}
			return true;
		}
	}

	@Override
	public void setViewingOptions(IFolder folder, IFilter filter, ISort sort) {
		// TODO Auto-generated method stub

	}

	@Override
	public IMail[] listEmails(int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteEmails(ILinkedList mails) {
		// TODO Auto-generated method stub

	}

	@Override
	public void moveEmails(ILinkedList mails, IFolder des) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean compose(IMail email) {
		String from, to = null, subject, email_body;
		String z, y, path, time, pq;
		Contact x = new Contact();

		from = Mail.from;
		to = Mail.to;
		subject = Mail.subject;
		email_body = Mail.email_body;
		time = Mail.time;
		pq = Mail.p;

		x.var2(to);

		if (x.check(to)) {
			z = ((Mail) email).return_contact(to);
			y = ((Mail) email).return_contact(from);

			path = "Users/" + z + "/Inbox/Index file.json"; // save for receiver
			email.save_email(to, from, subject, email_body, path, time, pq);

			path = "Users/" + y + "/Sent/Index file.json"; // save for sender
			email.save_email(to, from, subject, email_body, path, time, pq);
			x.var(from, null, y);

			return true;
		} else {
			return false;
		}

	}

}
