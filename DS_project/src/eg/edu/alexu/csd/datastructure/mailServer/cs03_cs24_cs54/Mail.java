package eg.edu.alexu.csd.datastructure.mailServer.cs03_cs24_cs54;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import eg.edu.alexu.csd.datastructure.linkedList.cs03_cs10.DLinkedList;
import eg.edu.alexu.csd.datastructure.mailServer.IMail;

public class Mail implements IMail {

	public static String from, to, subject, email_body, p, time, order;
	public static Integer send_draft;
	DLinkedList state = new DLinkedList();
	DLinkedList state1 = new DLinkedList();

	public Mail() {
		from = null;
		to = null;
		subject = null;
		email_body = null;
		time = null;
		p = null;
		order = null;
		send_draft = null;
	}

	@Override
	public void saveOrdraft(Integer sd) {
		send_draft = sd;
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
			orders.get(orders.size() - 1);
			int n = Integer.parseInt((String) orders.get(orders.size() - 1)) + 1;
			String c = "" + n;
			orders.add(c);
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
			JSONArray col1 = (JSONArray) jsonObject.get("order");

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
			orders.get(orders.size() - 1);
			int n = Integer.parseInt((String) orders.get(orders.size() - 1));
			return n;
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
			JSONArray col1 = (JSONArray) jsonObject.get("order");

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
			orders.get(orders.size() - 1);
			int n = Integer.parseInt((String) orders.get(orders.size() - 1));
			return n;
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
			z.save_attachement(file, send);
		}
	}

	public void delete_temp() {
		File index = new File("Users/temp");
		String[] entries = index.list();
		for (String s : entries) {
			File currentFile = new File(index.getPath(), s);
			currentFile.delete();
		}
		index.delete();
		index.mkdirs();
	}

	public void delete_attachment(String path) {
		File index = new File(path);
		String[] entries = index.list();
		for (String s : entries) {
			File currentFile = new File(index.getPath(), s);
			currentFile.delete();
		}
		index.delete();
	}

	@SuppressWarnings("unchecked")
	public void auto_delete(String path) {

		DLinkedList send = new DLinkedList();
		DLinkedList recieve = new DLinkedList();
		DLinkedList subjects = new DLinkedList();
		DLinkedList bodies = new DLinkedList();
		DLinkedList orders = new DLinkedList();
		DLinkedList times = new DLinkedList();
		DLinkedList pqs = new DLinkedList();

		JSONParser parser = new JSONParser();
		DLinkedList msg_order = new DLinkedList();
		DLinkedList tos_order = new DLinkedList();
		DLinkedList froms_order = new DLinkedList();
		DLinkedList subjects_order = new DLinkedList();
		DLinkedList time_order = new DLinkedList();
		DLinkedList orders_order = new DLinkedList();
		DLinkedList pqs_order = new DLinkedList();

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
		Date timee[] = new Date[orders.size()];
		Date found[] = new Date[orders.size()];
		Date current = new Date();

		for (int i = 0; i < orders.size(); i++) {

			msg_order.add("");
			tos_order.add("");
			froms_order.add("");
			subjects_order.add("");
			time_order.add("");
			orders_order.add("");
			pqs_order.add("");
		}
		for (int i = 0; i < orders.size(); i++) {
			try {


					timee[i] = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
							.parse((String) times.get(i));


			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int k = 0;
		for (int i = 0; i < orders.size(); i++) {
			long diffinmilli = Math.abs(current.getTime() - timee[i].getTime());
			long diff = TimeUnit.DAYS.convert(diffinmilli, TimeUnit.MILLISECONDS);
			if (diff < 30) {
				found[k] = timee[i];
				k++;

			} else {

			}
		}
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < orders.size(); j++) {
				if (found[i].compareTo(timee[j]) == 0 && b_ser_check(i, j)) {
					state.add(j);
					state1.add(i);
					msg_order.set(i, bodies.get(j));
					tos_order.set(i, recieve.get(j));
					froms_order.set(i, send.get(j));
					subjects_order.set(i, subjects.get(j));
					time_order.set(i, times.get(j));
					orders_order.set(i, orders.get(j));
					pqs_order.set(i, pqs.get(j));
				}
			}
		}
		for(int i = 0;i< orders.size();i++) {
			if(msg_order.get(i)=="") {
				msg_order.remove(i);
				tos_order.remove(i);
				froms_order.remove(i);
				subjects_order.remove(i);
				time_order.remove(i);
				orders_order.remove(i);
				pqs_order.remove(i);
			}
		}
		JSONObject obj1 = new JSONObject();
		JSONArray k1 = new JSONArray();
		JSONArray k2 = new JSONArray();
		JSONArray k3 = new JSONArray();
		JSONArray k4 = new JSONArray();
		JSONArray k5 = new JSONArray();
		JSONArray k6 = new JSONArray();
		JSONArray k7 = new JSONArray();

		for (int i = 0; i < msg_order.size(); i++) {
			k1.add(tos_order.get(i));
			k2.add(froms_order.get(i));
			k3.add(subjects_order.get(i));
			k4.add(msg_order.get(i));
			k5.add(orders_order.get(i));
			k6.add(time_order.get(i));
			k7.add(pqs_order.get(i));

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

	boolean b_ser_check(int l, int k) {
		for (int i = 0; i < state1.size(); i++) {
			if ((int) state1.get(i) == l) {
				return false;
			}

		}
		for (int i = 0; i < state.size(); i++) {
			if ((int) state.get(i) == k) {
				return false;
			}

		}
		return true;

	}
	@SuppressWarnings("null")
	public ArrayList<String> details_attachment (String order, String folder_chosen, String contact) {
		ArrayList<String> list = new ArrayList<String>();
		String path;
		path = "Users/" + contact + "/" + folder_chosen;
		File y = new File(path);

		File[] u = y.listFiles();

		for (File file : u) {
			if (Objects.equals(order, file.getName())) {
				path = path + "/" + order;
				File t = new File(path);
				File[] p = t.listFiles();
				for (File fil : p) {
					list.add(fil.getAbsolutePath());
				}
			}
		}
		return list;
	}
	public void delete_attachment_inst (String file_name) { //Removing mails before sending
 		String path = "Users/temp/" + file_name;
 		File x = new File (path);

 		x.delete();

	}
	public void move_attachment1(String contact) {
		String path = "Users/temp";
		File x = new File(path);
		Mail y = new Mail();

		if (x.isDirectory()) {
			if (x.list().length > 0) {
				y.directory_contents1(contact);
			} else {

			}

		} else {

		}
	}

	public void directory_contents1(String contact) {
		String path = "Users/temp";
		File x = new File(path);
		File[] files = x.listFiles();
		Mail z = new Mail();

		for (File file : files) {
			String path1;
			int t;
			String c;
			path1 = "Users/" + contact + "/Drafts/Index file.json";
			t = z.order1(path1);
			c = "" + t;
			String dest2 = "Users/" + contact + "/Drafts/" + c;
			File send = new File(dest2);
			send.mkdirs();
			send = new File(dest2 + "/" + file.getName());
			z.save_attachement(file, send);
		}
	}

}
