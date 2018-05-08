package eg.edu.alexu.csd.datastructure.mailServer.cs03_cs24_cs54;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
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
	public static String category;
	public static String Searched_value;
	public static Integer filter;
	public Filter() {
		category = null;
		Searched_value = null;
	    filter =  null;
	   

	}
	
	@Override
	public void var(Integer fil,String cate,String val) {
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
		File x = new File(path);
		int flag = 1;
		Mail y = new Mail();

		while (flag == 1) {
			path = path + "/Sender";
			File[] files = x.listFiles();
			for (File file : files) {
				if (from == file.getName()) {
					path = path + "/" + file.getName() + "/Index file.json";
					y.save_email(to, from, Subject, body, path, tt, pq);
					String path1 = "Users/" + contact + "/Inbox/Index file.json";
					String path2 = "Users/" + contact + "/Inbox";
					File x1 = new File(path2);
					File[] files1 = x1.listFiles();
					int n = y.order1(path1) - 1;
					for (File file1 : files1) {
						if (Integer.toString(n) == file1.getName()) {
							file1.renameTo(new File("Users/" + contact + "/Filter mails/Sender/" + file.getName() + "/"
									+ Integer.toString(y.order1(path))));
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
		}
		while (flag == 2) {
			path = path + "/Subject";
			File[] files = x.listFiles();
			for (File file : files) {
				if (Subject == file.getName()) {
					path = path + "/" + file.getName() + "/Index file.json";
					y.save_email(to, from, Subject, body, path, tt, pq);
					String path1 = "Users/" + contact + "/Inbox/Index file.json";
					String path2 = "Users/" + contact + "/Inbox";
					File x1 = new File(path2);
					File[] files1 = x1.listFiles();
					int n = y.order1(path1) - 1;
					for (File file1 : files1) {
						if (Integer.toString(n) == file1.getName()) {
							file1.renameTo(new File("Users/" + contact + "/Filter mails/Subject/" + file.getName() + "/"
									+ Integer.toString(y.order1(path))));
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
		}
		while (flag == 3) {
			path = path + "/Sender & Subject";
			File[] files = x.listFiles();
			for (File file : files) {
				if (Subject == file.getName()) {
					path = path + "/" + file.getName() + "/Index file.json";
					y.save_email(to, from, Subject, body, path, tt, pq);
					String path1 = "Users/" + contact + "/Inbox/Index file.json";
					String path2 = "Users/" + contact + "/Inbox";
					File x1 = new File(path2);
					File[] files1 = x1.listFiles();
					int n = y.order1(path1) - 1;
					for (File file1 : files1) {
						if (Integer.toString(n) == file1.getName()) {
							file1.renameTo(new File("Users/" + contact + "/Filter mails/Sender & Subject/"
									+ file.getName() + "/" + Integer.toString(y.order1(path))));
							break;
						}
					}

					flag = 0;
					break;
				}
			}
			break;
		}

	}

	public void bsearch(String x, String path) {
		
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
		DLinkedList dup = new DLinkedList();;
		for (int i = orders.size() - 1; i >= 0; i--) {
			names[i] = (subjects.get(orders.size() - 1 - i).toString());
		}
		int low = 0;
		int high = names.length - 1;
		int mid;
		int it1 = 0;
		int it2 = 0;
		
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
				while (Objects.equals(x,names[it1]) && it1 != 0) {
					if (Objects.equals(x,names[it1])) {
						dup.add(names[it1]);
						it1--;

					}
				}
				while (Objects.equals(x,names[it2]) && it2 != (names.length - 1)) {
					if (Objects.equals(x,names[it2])) {
						dup.add(names[it2]);
						it2++;

					}
				}
				break;
			}
		}
		
        for(int i=0; i<dup.size();i++) {
        	System.out.println(dup.get(i));
        }
		

	}

	@SuppressWarnings({ "static-access", "unchecked" })
	@Override
	public void read_indexfile(String category, String path) {
		JSONParser parser = new JSONParser();
		DLinkedList mail = new DLinkedList();
		DLinkedList send = new DLinkedList();
		DLinkedList recieve = new DLinkedList();
		DLinkedList subjects = new DLinkedList();
		DLinkedList bodies = new DLinkedList();
		DLinkedList orders = new DLinkedList();
		DLinkedList times = new DLinkedList();
		DLinkedList pqs = new DLinkedList();
		Mail x = new Mail();

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
			String names[] = new String[orders.size()];
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
			DLinkedList n = new DLinkedList();
			DLinkedList msg_order = new DLinkedList();
			DLinkedList tos_order = new DLinkedList();
			DLinkedList froms_order = new DLinkedList();
			DLinkedList subjects_order = new DLinkedList();
			DLinkedList time_order = new DLinkedList();
			DLinkedList orders_order = new DLinkedList();
			DLinkedList pqs_order = new DLinkedList();
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
					if (Objects.equals((String) n.get(i), (String) subjects.get(j))) {
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

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        String ser = Filter.Searched_value;
		bsearch(ser, "Users/temp2.json");

	}


}
