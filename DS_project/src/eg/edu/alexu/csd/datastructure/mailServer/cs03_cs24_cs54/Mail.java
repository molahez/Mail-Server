package eg.edu.alexu.csd.datastructure.mailServer.cs03_cs24_cs54;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Objects;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import eg.edu.alexu.csd.datastructure.linkedList.cs03_cs10.DLinkedList;
import eg.edu.alexu.csd.datastructure.mailServer.IMail;

public class Mail implements IMail {

	public static String from, to, subject, email_body, p,time, order;
	

	public Mail() {
		from = null;
		to = null;
		subject = null;
		email_body = null;
		time = null;
		p = null;
		order = null;
	}

	@Override
	public void var2(String fro, String t, String sub, String email_body, String tt, String pq) {
		from = fro;
		to = t;
		subject = sub;
		Mail.email_body = email_body;
		time = tt;
		p = pq;
	}

	@SuppressWarnings({ "unchecked", "unused" })
	@Override

	public void save_email(String to, String from, String subject, String body, String path, String tt, String pq) {
		DLinkedList send = new DLinkedList();
		DLinkedList recieve = new DLinkedList();
		DLinkedList subjects = new DLinkedList();
		DLinkedList bodies = new DLinkedList();
		DLinkedList orders = new DLinkedList();
		DLinkedList times = new DLinkedList();
		DLinkedList pqs = new DLinkedList();
		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(new FileReader(path));

			JSONObject jsonObject = (JSONObject) obj;

			// loop array
			// here we load content of json file
			JSONArray col1 = (JSONArray) jsonObject.get("tos");
			JSONArray col2 = (JSONArray) jsonObject.get("froms");
			JSONArray col3 = (JSONArray) jsonObject.get("subjects");
			JSONArray col4 = (JSONArray) jsonObject.get("bodies");
			JSONArray col5 = (JSONArray) jsonObject.get("order");
			JSONArray col6 = (JSONArray) jsonObject.get("time");
			JSONArray col7 = (JSONArray) jsonObject.get("pq");
			Iterator<String> iterator1 = col1.iterator();
			Iterator<String> iterator2 = col2.iterator();
			Iterator<String> iterator3 = col3.iterator();
			Iterator<String> iterator4 = col4.iterator();
			Iterator<String> iterator5 = col5.iterator();
			Iterator<String> iterator6 = col6.iterator();
			Iterator<String> iterator7 = col7.iterator();
			while (iterator1.hasNext()) {

				recieve.add(iterator1.next());

			}
			while (iterator2.hasNext()) {
				send.add(iterator2.next());

			}
			while (iterator3.hasNext()) {
				subjects.add(iterator3.next());

			}
			while (iterator4.hasNext()) {
				bodies.add(iterator4.next());

			}
			while (iterator5.hasNext()) {
				orders.add(iterator5.next());

			}
			while (iterator6.hasNext()) {
				times.add(iterator6.next());

			}
			while (iterator7.hasNext()) {
				pqs.add(iterator7.next());

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
		JSONArray k4 = new JSONArray();
		JSONArray k5 = new JSONArray();
		JSONArray k6 = new JSONArray();
		JSONArray k7 = new JSONArray();
		recieve.add(to);
		send.add(from);
		subjects.add(subject);
		bodies.add(body);
		times.add(tt);
		pqs.add(pq);
		if (orders.size() == 0) {
			orders.add("1");
		} else {
			int or = orders.size() + 1;
			orders.add(Integer.toString(or));
		}
		for (int i = 0; i < recieve.size(); i++) {
			k1.add(recieve.get(i));
			k2.add(send.get(i));
			k3.add(subjects.get(i));
			k4.add(bodies.get(i));
			k5.add(orders.get(i));

			k6.add(times.get(i));

		}
		for (int i = 0; i < recieve.size(); i++) {
			k7.add(pqs.get(i));

		}

		obj1.put("tos", k1);
		obj1.put("froms", k2);
		obj1.put("subjects", k3);
		obj1.put("bodies", k4);
		obj1.put("order", k5);
		obj1.put("time", k6);
		obj1.put("pq", k7);
		try (FileWriter file = new FileWriter(path)) {

			file.write(obj1.toString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("resource")
	public void save_attachement(File x, File y) {
		if (!x.exists()) {
			return;
		}
		if (!y.exists()) {
			try {
				y.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		FileChannel source = null;
		FileChannel destination = null;
		try {
			source = new FileInputStream(x).getChannel();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			destination = new FileOutputStream(y).getChannel();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (destination != null && source != null) {
			try {
				destination.transferFrom(source, 0, source.size());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (source != null) {
			try {
				source.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (destination != null) {
			try {
				destination.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public String return_contact(String email) {
		DLinkedList emails = new DLinkedList();
		DLinkedList names = new DLinkedList();
		String flag = null;
		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(new FileReader("Users/contact.json"));

			JSONObject jsonObject = (JSONObject) obj;

			// loop array
			// here we load content of json file
			JSONArray col1 = (JSONArray) jsonObject.get("emails");
			JSONArray col3 = (JSONArray) jsonObject.get("name");
			Iterator<String> iterator1 = col1.iterator();
			Iterator<String> iterator3 = col3.iterator();

			while (iterator1.hasNext()) {
				emails.add(iterator1.next());

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
			if (Objects.equals(email, emails.get(i))) {
				flag = (String) names.get(i);
			}
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int order(String path) {
		DLinkedList orders = new DLinkedList();
		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(new FileReader(path));

			JSONObject jsonObject = (JSONObject) obj;

			// loop array
			// here we load content of json file
			JSONArray col1 = (JSONArray) jsonObject.get("tos");

			Iterator<String> iterator1 = col1.iterator();

			while (iterator1.hasNext()) {

				orders.add(iterator1.next());

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
		if (orders.size() == 0) {
			return 1;
		} else {
			return orders.size();
		}

	}

	@SuppressWarnings("unchecked")
	public int order1(String path) {
		DLinkedList orders = new DLinkedList();
		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(new FileReader(path));

			JSONObject jsonObject = (JSONObject) obj;

			// loop array
			// here we load content of json file
			JSONArray col1 = (JSONArray) jsonObject.get("froms");

			Iterator<String> iterator1 = col1.iterator();

			while (iterator1.hasNext()) {

				orders.add(iterator1.next());

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
		if (orders.size() == 0) {
			return 1;
		} else {
			return orders.size();
		}

	}

	public void move_attachment(String contact, String email_to) {
		String path = "Users/temp";
		File x = new File(path);
		Mail y = new Mail();

		if (x.isDirectory()) {
			if (x.list().length > 0) {
				y.directory_contents(contact, email_to);
			} else {

			}

		} else {

		}
	}

	public void directory_contents(String contact, String email_to) {
		String path = "Users/temp";
		String cont;
		File x = new File(path);
		File[] files = x.listFiles();
		Mail z = new Mail();
		cont = z.return_contact(email_to); // contact for receiver

		for (File file : files) {
			String path1 = "Users/" + cont + "/Inbox/Index file.json";
			int t = z.order(path1);
			String c = "" + t;
			String dest1 = "Users/" + cont + "/Inbox/" + c;
			File rece = new File(dest1);
			rece.mkdirs();
			rece = new File(dest1 + "/" + file.getName());
			z.save_attachement(file, rece);

			path1 = "Users/" + contact + "/Sent/Index file.json";
			t = z.order1(path1);
			c = "" + t;
			String dest2 = "Users/" + contact + "/Sent/" + c;
			File send = new File(dest2);
			send.mkdirs();
			send = new File(dest2 + "/" + file.getName());
			file.renameTo(send);
			file.delete();
		}
	}



	
}
