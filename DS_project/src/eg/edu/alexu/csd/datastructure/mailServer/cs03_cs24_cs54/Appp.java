package eg.edu.alexu.csd.datastructure.mailServer.cs03_cs24_cs54;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

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

			Object obj = parser.parse(new FileReader("Users/updates/tree.json"));
			Object obj1 = parser.parse(new FileReader("Users/updates/tree_state.json"));

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
		try (FileWriter file = new FileWriter("Users/updates/tree.json")) {

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

		try (FileWriter file = new FileWriter("Users/updates/tree_state.json")) {

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
		boolean flag = false;

		ArrayList<String> list = new ArrayList<String>() {
			{
				add("Inbox");
				add("Sent");
				add("Trash");
				add("Filterd mails");
				add("Drafts");
			}
		};

		if (dir.exists()) {
			if (contact.check(email)) {
				// mail is existed
				return false;
			} else {
				String path1 = "Users";
				File x = new File(path1);
				File[] y = x.listFiles();

				for (File file : y) {
					if (Objects.equals(cont, file.getName())) {
						contact.write_contact(email, password, cont);
						flag = true;
						break;
						
					}
					
				}
				if(flag == true) {
					return true;
				}

				else {
					path = path + "/" + cont;
					temp = path;
					dir = new File(path);
					dir.mkdirs();

					contact.write_contact(email, password, cont);

					while (counter != list.size()) {
						folders = temp + "/" + list.get(counter);
						dir = new File(folders);
						dir.mkdirs();
						if (list.get(counter) == "Inbox" || list.get(counter) == "Sent" || list.get(counter) == "Trash"
								|| list.get(counter) == "Drafts") {
							contact.create_file(folders);
						}

						counter++;
					}
					return true;
				}

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
	public void setViewingOptions(final IFolder folder, final IFilter filter, final ISort sort) {

		String folder_chosen, name, cat;
		Integer x;
		x = Filter.filter;
		cat = Filter.category;
		Contact y1 = new Contact();
		y1.var(folderr.em, null, folderr.contname);
		switch (x) {
		case 1:
			folder_chosen = folderr.folderchosen;

			name = folderr.contname;

			sort.OrderOfAll_1(name, folder_chosen); // sort decending according date
			break;
		case 2:
			folder_chosen = folderr.folderchosen;

			name = folderr.contname;
			sort.OrderOfAll_2(name, folder_chosen); // sort asending according date
			break;
		case 3:
			folder_chosen = folderr.folderchosen;

			name = folderr.contname;
			sort.OrderOfAll_3(name, folder_chosen); // alphabet of subjects
			break;
		case 4:
			folder_chosen = folderr.folderchosen;

			name = folderr.contname;
			sort.OrderOfAll_4(name, folder_chosen); // alphabet of senders
			break;
		case 5:
			folder_chosen = folderr.folderchosen;
			name = folderr.contname;

			try {
				filter.read_indexfile(cat, "Users/" + name + "/" + folder_chosen + "/Index file.json");
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // alphabet of senders
			break;
		case 6:
			folder_chosen = folderr.folderchosen;
			name = folderr.contname;
			sort.OrderOfAll_5(name, folder_chosen); // sort by Priority Queue
			break;
		/*
		 * case 7: folder_chosen = folderr.folderchosen;
		 * 
		 * name = folderr.contname;
		 * 
		 * try { sort.OrderOfAll_6(name, folder_chosen); } catch
		 * (java.text.ParseException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } // sort decending according date break;
		 */
		/*
		 * case 8: folder_chosen = folderr.folderchosen;
		 * 
		 * name = folderr.contname;
		 * 
		 * sort.OrderOfAll_7(name, folder_chosen); // sort decending according date
		 * break;
		 */
		default:
			break;
		}

	}

	@Override
	public IMail[] listEmails(int page) {

		return null;
	}

	@Override
	public void deleteEmails(ILinkedList mails) {
		String cont, chosen_folder;
		cont = DLinkedList.contact;
		chosen_folder = DLinkedList.chosen_folder;
		System.out.println(DLinkedList.contact);

		String path = "Users/" + cont + "/" + chosen_folder + "/Index file.json";

		mails.delete_from_index(path, mails);
		mails.put_in_trash(mails, cont, chosen_folder);

	}

	@Override
	public void moveEmails(ILinkedList mails, IFolder des) {

		String cont = DLinkedList.contact;
		String chosen_folder = DLinkedList.chosen_folder;
		String chosen = folderr.folderchosen;

		String path = "Users/" + cont + "/" + chosen_folder + "/Index file.json";
		String path1 = folderr.path;

		mails.delete_from_index(path, mails);
		mails.put_move(mails, path1, chosen_folder, cont);

	}

	@Override
	public boolean compose(IMail email) {
		String from, to = null, subject, email_body, time;
		String z, y, path, pq;

		Contact x = new Contact();
		Filter h = new Filter();

		from = Mail.from;
		to = Mail.to;
		subject = Mail.subject;
		email_body = Mail.email_body;
		time = Mail.time;
		pq = Mail.p;

		Integer w;
		w = Mail.send_draft;

		if (w == 1) {

			x.var2(to);
			if (x.check(to)) {
				z = ((Mail) email).return_contact(to);
				y = ((Mail) email).return_contact(from);

				path = "Users/" + z + "/Inbox/Index file.json"; // save for receiver
				email.save_email(to, from, subject, email_body, path, time, pq);
				if (h.check_filter(z)) {
					h.put_in_filter(to, subject, z, from, email_body, time, pq);
				}

				path = "Users/" + y + "/Sent/Index file.json"; // save for sender
				email.save_email(to, from, subject, email_body, path, time, pq);
				x.var(from, null, y);
				return true;
			} else {
				return false;
			}

		} else if (w == 2) {

			y = ((Mail) email).return_contact(from);
			path = "Users/" + y + "/Drafts/Index file.json"; // save for sender
			email.save_email(to, from, subject, email_body, path, time, pq);
			x.var(from, null, y);

			return true;

		} else {
			return false;
		}

	}

}
