package eg.edu.alexu.csd.datastructure.mailServer.cs03_cs24_cs54;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.Objects;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import eg.edu.alexu.csd.datastructure.linkedList.cs03_cs10.DLinkedList;
import eg.edu.alexu.csd.datastructure.mailServer.IFilter;
import eg.edu.alexu.csd.datastructure.stack.cs03.MyStack;

@SuppressWarnings("unused")
public class Filter implements IFilter {
	DLinkedList state = new DLinkedList();
	DLinkedList state1 = new DLinkedList();
	DLinkedList state2 = new DLinkedList();
	DLinkedList state3 = new DLinkedList();
	public static String category;
	public static String Searched_value;
	public static Integer filter;

	public Filter() {
		category = null;
		Searched_value = null;
		filter = null;

	}

	@Override
	public void var(Integer fil, String cate, String val) {
		filter = fil;
		category = cate;
		Searched_value = val;
	}

	public void choose_filter(String type, String descrip, String contact) {
		String path = "Users/" + contact + "/Filterd mails/";
		Contact y = new Contact();

		if (type == "Sender") {
			File x = new File(path + "Sender");

			if (x.exists()) {
				x = new File(path + "Sender/" + descrip);
				x.mkdirs();
				y.create_file(path + "Sender/" + descrip);
			} else {
				x.mkdirs();
				x = new File(path + "Sender/" + descrip);
				x.mkdirs();
				y.create_file(path + "Sender/" + descrip);
			}
		} else if (type == "Subject") {
			File x = new File(path + "Subject");

			if (x.exists()) {
				x = new File(path + "Subject/" + descrip);
				x.mkdirs();
				y.create_file(path + "Subject/" + descrip);
			} else {
				x.mkdirs();
				x = new File(path + "Subject/" + descrip);
				x.mkdirs();
				y.create_file(path + "Subject/" + descrip);
			}
		} else if (type == "Sender & Subject") {
			File x = new File(path + "Sender & Subject");
			if (x.exists()) {
				x = new File(path + "Sender & Subject/" + descrip);
				x.mkdirs();
				y.create_file(path + "Sender & Subject/" + descrip);
			} else {
				x.mkdirs();
				x = new File(path + "Sender & Subject/" + descrip);
				x.mkdirs();
				y.create_file(path + "Sender & Subject/" + descrip);
			}
		}
	}

	public boolean check_filter(String contact) {
		String path = "Users/" + contact + "/Filterd mails";
		File x = new File(path);

		if (x.list().length > 0) {
			return true;
		} else {
			return false;
		}
	}

	public void put_in_filter(String to, String Subject, String contact, String from, String body, String tt,
			String pq) {
		String path = "Users/" + contact + "/Filterd mails";
		String temp;
		File x = new File(path);
		int flag = 1;
		Mail y = new Mail();

		while (flag == 1) {
			temp = path + "/Sender";
			x = new File(temp);
			File[] files = x.listFiles();
			if (x.exists()) {
				for (File file : files) {
					if (from == file.getName()) {
						temp = temp + "/" + file.getName() + "/Index file.json";
						y.save_email(to, from, Subject, body, temp, tt, pq);
						String path1 = "Users/" + contact + "/Inbox/Index file.json";
						String path2 = "Users/" + contact + "/Inbox";
						File x1 = new File(path2);
						File[] files1 = x1.listFiles();
						int n = y.order1(path1) - 1;
						for (File file1 : files1) {
							if (Objects.equals(file1.getName(), Integer.toString(n))) {
								copyFolder(file1, new File("Users/" + contact + "/Filterd mails/Sender/"
										+ file.getName() + "/" + Integer.toString(y.order1(temp))));
								break;
							}
						}

						flag = 0;
						break;
					}
				}
				if (flag == 0) {
					break;
				} else if (flag == 1) {
					flag = 2;
				}
			} else {
				flag = 2;
			}
		}
		while (flag == 2) {
			temp = path + "/Subject";
			x = new File(temp);
			File[] files = x.listFiles();
			if (x.exists()) {
				for (File file : files) {
					if (Objects.equals(Subject, file.getName())) {
						temp = temp + "/" + Subject + "/Index file.json";
						y.save_email(to, from, Subject, body, temp, tt, pq);
						String path1 = "Users/" + contact + "/Inbox/Index file.json";
						String path2 = "Users/" + contact + "/Inbox";
						File x1 = new File(path2);
						File[] files1 = x1.listFiles();
						int n = y.order1(path1) - 1;
						for (File file1 : files1) {
							if (Objects.equals(file1.getName(), Integer.toString(n))) {
								copyFolder(file1, new File("Users/" + contact + "/Filterd mails/Subject/"
										+ file.getName() + "/" + Integer.toString(y.order1(temp))));
								break;
							}
						}

						flag = 0;
						break;
					}
				}
				if (flag == 0) {
					break;
				} else if (flag == 2) {
					flag = 3;
				}
			} else {
				flag = 3;
			}
		}
		while (flag == 3) {
			temp = path + "/Sender & Subject";
			x = new File(temp);
			String h = y.return_contact(from) + "-" + Subject;
			File[] files = x.listFiles();
			if (x.exists()) {
				for (File file : files) {
					if (Objects.equals(h, file.getName())) {
						temp = temp + "/" + file.getName() + "/Index file.json";
						y.save_email(to, from, Subject, body, temp, tt, pq);
						String path1 = "Users/" + contact + "/Inbox/Index file.json";
						String path2 = "Users/" + contact + "/Inbox";
						File x1 = new File(path2);
						File[] files1 = x1.listFiles();
						int n = y.order1(path1) - 1;
						for (File file1 : files1) {
							if (Objects.equals(file1.getName(), Integer.toString(n))) {
								copyFolder(file1, new File("Users/" + contact + "/Filterd mails/Sender & Subject/"
										+ file.getName() + "/" + Integer.toString(y.order1(temp))));
								break;
							}
						}

						flag = 0;
						break;
					}
				}
				break;
			} else {
				break;
			}
		}

	}

	@SuppressWarnings("unchecked")
	public void bsearch(String x, String path) throws java.text.ParseException {

		// first we read the file

		JSONParser parser = new JSONParser();
		DLinkedList mail = new DLinkedList();
		DLinkedList send = new DLinkedList();
		DLinkedList recieve = new DLinkedList();
		DLinkedList subjects = new DLinkedList();
		DLinkedList bodies = new DLinkedList();
		DLinkedList orders = new DLinkedList();
		DLinkedList times = new DLinkedList();
		DLinkedList pqs = new DLinkedList();
		DLinkedList compared = new DLinkedList();
		boolean flag1 = false;

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
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Binary search starts here
		String names[] = new String[orders.size()];
		DLinkedList dup = new DLinkedList();
		Date[] timee = new Date[orders.size()];
		Date[] comparedd = new Date[orders.size()];
		Date[] dupp = new Date[orders.size()];
		if (Objects.equals(category, "subject")) {
			for (int i = 0; i < orders.size(); i++) {
				compared.add(subjects.get(i));
				names[i] = (subjects.get(i).toString());
			}

		} else if (Objects.equals(category, "sender")) {
			for (int i = 0; i < orders.size(); i++) {
				compared.add(send.get(i));
				names[i] = (send.get(i).toString());
			}

		} else if (Objects.equals(category, "receiver")) {
			for (int i = 0; i < orders.size(); i++) {
				compared.add(recieve.get(i));
				names[i] = (recieve.get(i).toString());
			}

		} else if (Objects.equals(category, "priority")) {
			for (int i = 0; i < orders.size(); i++) {
				compared.add(pqs.get(i));
				names[i] = (pqs.get(i).toString());
			}

		} else if (Objects.equals(category, "time")) {
			flag1 = true;
			for (int i = 0; i < orders.size(); i++) {
				try {
					comparedd[i] = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse((String) times.get(i));
					timee[i] = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse((String) times.get(i));
				} catch (java.text.ParseException e) {

					e.printStackTrace();
				}

			}

		}

		int low = 0;
		int high = names.length - 1;
		int mid;
		int it1 = 0;
		int it2 = 0;
		int k = 0;

		DLinkedList msg_order = new DLinkedList();
		DLinkedList tos_order = new DLinkedList();
		DLinkedList froms_order = new DLinkedList();
		DLinkedList subjects_order = new DLinkedList();
		DLinkedList time_order = new DLinkedList();
		DLinkedList orders_order = new DLinkedList();
		DLinkedList pqs_order = new DLinkedList();
		for (int i = 0; i < orders.size(); i++) {
			msg_order.add("");
			tos_order.add("");
			froms_order.add("");
			subjects_order.add("");
			time_order.add("");
			orders_order.add("");
			pqs_order.add("");
		}
		

		if (!flag1) {
			while (low <= high) {
				mid = (low + high) / 2;

				if (names[mid].compareTo(x) < 0) {
					low = mid + 1;

				} else if (names[mid].compareTo(x) > 0) {
					high = mid - 1;
				} else {

					dup.add(names[mid]);
					it1 = mid - 1;
					it2 = mid + 1;
					if (it1 >= 0) {

						while (it1 >= 0 && Objects.equals(x, names[it1])) {

							if (Objects.equals(x, names[it1])) {
								dup.add(names[it1]);
								it1--;

							}
						}
					}
					if (it2 <= names.length - 1) {

						while (it2 <= (names.length - 1) && Objects.equals(x, names[it2])) {

							if (Objects.equals(x, names[it2])) {
								dup.add(names[it2]);
								it2++;

							}
						}
						break;
					}
				}
			}

			

			for (int i = 0; i < dup.size(); i++) {
				for (int j = 0; j < orders.size(); j++) {
					if (Objects.equals(dup.get(i), compared.get(j)) && b_ser_check(i, j)) {
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
		} else {
			while (low <= high) {
				mid = (low + high) / 2;

				if (timee[mid].compareTo(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(x)) < 0) {

					low = mid + 1;

				} else if (timee[mid].compareTo(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(x)) > 0) {
					high = mid - 1;
				} else {

					dupp[k] = timee[mid];
					k++;
					it1 = mid - 1;
					it2 = mid + 1;
					if (it1 >= 0) {

						while (it1 >= 0
								&& timee[it1].compareTo(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(x)) == 0) {

							if (timee[it1].compareTo(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(x)) == 0) {
								dupp[k] = timee[it1];
								k++;
								it1--;

							}
						}
					}
					if (it2 <= names.length - 1) {

						while (it2 <= (names.length - 1)
								&& timee[it2].compareTo(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(x)) == 0) {

							if (timee[it2].compareTo(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(x)) == 0) {
								dupp[k] = timee[it2];
								k++;
								it2++;

							}
						}
						break;
					}

				}
			}

			

			for (int i = 0; i < k; i++) {
				for (int j = 0; j < orders.size(); j++) {
					if (dupp[i].compareTo(comparedd[j]) == 0 && b_ser_check(i, j)) {
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
		}

		JSONObject obj1 = new JSONObject();
		JSONArray k1 = new JSONArray();
		JSONArray k2 = new JSONArray();
		JSONArray k3 = new JSONArray();
		JSONArray k4 = new JSONArray();
		JSONArray k5 = new JSONArray();
		JSONArray k6 = new JSONArray();
		JSONArray k7 = new JSONArray();

		for (int i = 0; i < recieve.size(); i++) {
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
		try (FileWriter file = new FileWriter("Users/temp3.json")) {

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

	}//here we read the file that contains results  
	static DLinkedList read_sorted() {
		JSONParser parser = new JSONParser();
		DLinkedList emails = new DLinkedList();
		try {

			Object obj = parser.parse(new FileReader("Users/temp3.json"));

			JSONObject jsonObject = (JSONObject) obj;

			// loop array
			JSONArray col1 = (JSONArray) jsonObject.get("pq");
			JSONArray col2 = (JSONArray) jsonObject.get("tos");
			JSONArray col3 = (JSONArray) jsonObject.get("froms");
			JSONArray col4 = (JSONArray) jsonObject.get("subjects");
			JSONArray col5 = (JSONArray) jsonObject.get("bodies");
			JSONArray col6 = (JSONArray) jsonObject.get("time");
			JSONArray col7 = (JSONArray) jsonObject.get("order");
			Iterator<String> iterator1 = col1.iterator();
			Iterator<String> iterator2 = col2.iterator();
			Iterator<String> iterator3 = col3.iterator();
			Iterator<String> iterator4 = col4.iterator();
			Iterator<String> iterator5 = col5.iterator();
			Iterator<String> iterator6 = col6.iterator();
			Iterator<String> iterator7 = col7.iterator();
			

			while (iterator1.hasNext()) {
				emails.add(new Sorting(iterator1.next(), iterator2.next(), iterator3.next(), iterator4.next(),
						iterator5.next(), iterator6.next(), iterator7.next()));

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
		return emails;

	}
	static DLinkedList filter_results(DLinkedList x) {
		DLinkedList emails = new DLinkedList();
		for(int i=0;i<x.size();i++) {
			if((String)((Sorting) x.get(i)).pq!="") {
				emails.add(x.get(i));
			}
			
		}
		return emails;
		
	}

	@SuppressWarnings({ "static-access", "unchecked" })
	@Override
	//here we sort according to category but only in body we search
	public void read_indexfile(String category, String path) throws java.text.ParseException {

		JSONParser parser = new JSONParser();
		DLinkedList mail = new DLinkedList();
		DLinkedList send = new DLinkedList();
		DLinkedList recieve = new DLinkedList();
		DLinkedList subjects = new DLinkedList();
		DLinkedList bodies = new DLinkedList();
		DLinkedList orders = new DLinkedList();
		DLinkedList times = new DLinkedList();
		DLinkedList pqs = new DLinkedList();
		DLinkedList compared = new DLinkedList();
		boolean flag = false;
		String ser = Filter.Searched_value;
		Mail x = new Mail();

		try {

			Object obj = parser.parse(new FileReader(path));

			JSONObject jsonObject = (JSONObject) obj;

			// loop array
			// here we load content of json file
			JSONArray col1 = (JSONArray) jsonObject.get("tos");// destination
			JSONArray col2 = (JSONArray) jsonObject.get("froms");// source
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
			DLinkedList n = new DLinkedList();
			DLinkedList msg_order = new DLinkedList();
			DLinkedList tos_order = new DLinkedList();
			DLinkedList froms_order = new DLinkedList();
			DLinkedList subjects_order = new DLinkedList();
			DLinkedList time_order = new DLinkedList();
			DLinkedList orders_order = new DLinkedList();
			DLinkedList pqs_order = new DLinkedList();
			if (Objects.equals(category, "body")) {
				for (int i = 0; i < orders.size(); i++) {
					
					msg_order.add("");
					tos_order.add("");
					froms_order.add("");
					subjects_order.add("");
					time_order.add("");
					orders_order.add("");
					pqs_order.add("");
				}
				
				for (int i = 0; i < bodies.size(); i++) {
					if (((String) bodies.get(i)).toLowerCase().contains(ser.toLowerCase())) {
						
						msg_order.add(i,bodies.get(i));
						tos_order.add(i,recieve.get(i));
						froms_order.add(i,send.get(i));
						subjects_order.add(i,subjects.get(i));
						time_order.add(i,times.get(i));
						orders_order.add(i,orders.get(i));
						pqs_order.add(i,pqs.get(i));
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

				for (int i = 0; i < recieve.size(); i++) {
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
				try (FileWriter file = new FileWriter("Users/temp3.json")) {

					file.write(obj1.toString());
					file.flush();

				} catch (IOException e) {
					e.printStackTrace();
				}
				

			} else {
				String names[] = new String[orders.size()];
				Date timee[] = new Date[orders.size()];
				Date comparedd[] = new Date[orders.size()];
				if (Objects.equals(category, "subject")) {
					for (int i = 0; i < orders.size(); i++) {
						compared.add(subjects.get(i));
					}

					for (int i = orders.size() - 1; i >= 0; i--) {
						names[i] = (subjects.get(orders.size() - 1 - i).toString());
					}
					for (int i = 0; i < orders.size(); i++) {

						for (int j = i + 1; j < orders.size(); j++) {
							if (names[i].compareTo(names[j]) > 0) {
								String temp = names[i];
								names[i] = names[j];
								names[j] = temp;
							}
						}
					}
				} else if (Objects.equals(category, "sender")) {
					for (int i = 0; i < orders.size(); i++) {
						compared.add(send.get(i));
					}
					for (int i = orders.size() - 1; i >= 0; i--) {
						names[i] = (send.get(orders.size() - 1 - i).toString());
					}
					for (int i = 0; i < orders.size(); i++) {

						for (int j = i + 1; j < orders.size(); j++) {
							if (names[i].compareTo(names[j]) > 0) {
								String temp = names[i];
								names[i] = names[j];
								names[j] = temp;
							}
						}
					}
				} else if (Objects.equals(category, "receiver")) {
					for (int i = 0; i < orders.size(); i++) {
						compared.add(recieve.get(i));
					}
					for (int i = orders.size() - 1; i >= 0; i--) {
						names[i] = (recieve.get(orders.size() - 1 - i).toString());
					}
					for (int i = 0; i < orders.size(); i++) {

						for (int j = i + 1; j < orders.size(); j++) {
							if (names[i].compareTo(names[j]) > 0) {
								String temp = names[i];
								names[i] = names[j];
								names[j] = temp;
							}
						}
					}
				} else if (Objects.equals(category, "time")) {
					flag = true;

					for (int i = 0; i < orders.size(); i++) {
						comparedd[i] = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse((String) times.get(i));
					}
					for (int i = orders.size() - 1; i >= 0; i--) {
						timee[i] = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
								.parse((String) times.get(orders.size() - 1 - i));
					}
					for (int i = 0; i < orders.size(); i++) {

						for (int j = i + 1; j < orders.size(); j++) {
							if (timee[i].compareTo(timee[j]) > 0) {
								Date temp = timee[i];
								timee[i] = timee[j];
								timee[j] = temp;
							}
						}
					}
				} else if (Objects.equals(category, "priority")) {

					for (int i = 0; i < orders.size(); i++) {
						compared.add(pqs.get(i));
					}
					for (int i = orders.size() - 1; i >= 0; i--) {
						names[i] = (pqs.get(orders.size() - 1 - i).toString());
					}
					for (int i = 0; i < orders.size(); i++) {

						for (int j = i + 1; j < orders.size(); j++) {
							if (names[i].compareTo(names[j]) > 0) {
								String temp = names[i];
								names[i] = names[j];
								names[j] = temp;
							}
						}
					}

				}

				
				DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				if (!flag) {
					for (int i = 0; i < orders.size(); i++) {
						n.add(i, names[i]);
						msg_order.add(1);
						tos_order.add(1);
						froms_order.add(1);
						subjects_order.add(1);
						time_order.add(1);
						orders_order.add(1);
						pqs_order.add(1);
					}

					for (int i = 0; i < orders.size(); i++) {
						for (int j = 0; j < orders.size(); j++) {

							if (Objects.equals((String) n.get(i), (String) compared.get(j)) && b_ser_check1(i, j)) {

								state2.add(j);
								state3.add(i);
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
				} else {
					for (int i = 0; i < orders.size(); i++) {
						n.add(i, timee[i]);
						msg_order.add(1);
						tos_order.add(1);
						froms_order.add(1);
						subjects_order.add(1);
						time_order.add(1);
						orders_order.add(1);
						pqs_order.add(1);
					}

					for (int i = 0; i < orders.size(); i++) {
						for (int j = 0; j < orders.size(); j++) {

							if (timee[i].compareTo(comparedd[j]) == 0 && b_ser_check1(i, j)) {

								state2.add(j);
								state3.add(i);
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
				}

				JSONObject obj1 = new JSONObject();
				JSONArray k1 = new JSONArray();
				JSONArray k2 = new JSONArray();
				JSONArray k3 = new JSONArray();
				JSONArray k4 = new JSONArray();
				JSONArray k5 = new JSONArray();
				JSONArray k6 = new JSONArray();
				JSONArray k7 = new JSONArray();

				for (int i = 0; i < recieve.size(); i++) {
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
				try (FileWriter file = new FileWriter("Users/temp2.json")) {

					file.write(obj1.toString());
					file.flush();

				} catch (IOException e) {
					e.printStackTrace();
				}
				bsearch(ser, "Users/temp2.json");

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

		

	}

	boolean b_ser_check1(int l, int k) {
		for (int i = 0; i < state3.size(); i++) {
			if ((int) state3.get(i) == l) {
				return false;
			}

		}
		for (int i = 0; i < state2.size(); i++) {
			if ((int) state2.get(i) == k) {
				return false;
			}

		}
		return true;

	}

	public void copyFolder(File sourceFolder, File destinationFolder) {
		// Check if sourceFolder is a directory or file
		// If sourceFolder is file; then copy the file directly to new location
		if (sourceFolder.isDirectory()) {
			// Verify if destinationFolder is already present; If not then create it
			if (!destinationFolder.exists()) {
				destinationFolder.mkdirs();

			}

			// Get all files from source directory
			String files[] = sourceFolder.list();

			// Iterate over all files and copy them to destinationFolder one by one
			for (String file : files) {
				File srcFile = new File(sourceFolder, file);
				File destFile = new File(destinationFolder, file);

				// Recursive function call
				copyFolder(srcFile, destFile);
			}
		} else {
			// Copy the file content from one place to another
			try {
				Files.copy(sourceFolder.toPath(), destinationFolder.toPath(), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {

			}
		}
	}

}
