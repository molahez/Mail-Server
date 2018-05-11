package eg.edu.alexu.csd.datastructure.mailServer.cs03_cs24_cs54;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import eg.edu.alexu.csd.datastructure.linkedList.cs03_cs10.DLinkedList;
import eg.edu.alexu.csd.datastructure.linkedList.cs03_cs10.SLinkedList;
import eg.edu.alexu.csd.datastructure.mailServer.ISort;
import eg.edu.alexu.csd.datastructure.stack.cs03.MyStack;

public class Sorting implements ISort {

	DLinkedList state = new DLinkedList();
	DLinkedList state1 = new DLinkedList();

	public String pq;
	public String to;
	public String from;
	public String Subject;
	public String body;
	public String time;
	public String order;

	public Sorting() {
		pq = null;
		to = null;
		from = null;
		Subject = null;
		body = null;
		time = null;
		order = null;
	}

	public Sorting(String a, String b, String c, String d, String e, String f, String g) {
		pq = a;
		to = b;
		from = c;
		Subject = d;
		body = e;
		time = f;
		order = g;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void OrderOfAll_1(String namee, String folderChosennn) {

		DLinkedList allOrders = new DLinkedList();
		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(new FileReader("Users/" + namee + "/" + folderChosennn + "/Index file.json"));

			JSONObject jsonObject = (JSONObject) obj;

			// loop array
			// here we load content of json file
			JSONArray col1 = (JSONArray) jsonObject.get("order");
			Iterator<String> iterator1 = col1.iterator();

			while (iterator1.hasNext()) {
				allOrders.add(iterator1.next());
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

		int[] arr = new int[allOrders.size()];
		for (int i = allOrders.size() - 1; i >= 0; i--) {
			arr[i] = (Integer.parseInt(allOrders.get(allOrders.size() - 1 - i).toString()));
		}

		quick_sort(arr);

		DLinkedList bodies = new DLinkedList();
		DLinkedList tos = new DLinkedList();
		DLinkedList froms = new DLinkedList();
		DLinkedList subjects = new DLinkedList();
		DLinkedList time = new DLinkedList();
		DLinkedList pq = new DLinkedList();
		JSONParser parserr = new JSONParser();

		try {

			Object obj = parserr.parse(new FileReader("Users/" + namee + "/" + folderChosennn + "/Index file.json"));

			JSONObject jsonObject = (JSONObject) obj;

			// loop array
			// here we load content of json file
			JSONArray col1 = (JSONArray) jsonObject.get("bodies");
			JSONArray col2 = (JSONArray) jsonObject.get("tos");
			JSONArray col3 = (JSONArray) jsonObject.get("froms");
			JSONArray col4 = (JSONArray) jsonObject.get("subjects");
			JSONArray col5 = (JSONArray) jsonObject.get("time");
			JSONArray col6 = (JSONArray) jsonObject.get("pq");
			Iterator<String> iterator1 = col1.iterator();
			Iterator<String> iterator2 = col2.iterator();
			Iterator<String> iterator3 = col3.iterator();
			Iterator<String> iterator4 = col4.iterator();
			Iterator<String> iterator5 = col5.iterator();
			Iterator<String> iterator6 = col6.iterator();
			while (iterator1.hasNext()) {

				bodies.add(iterator1.next());

			}
			while (iterator2.hasNext()) {
				tos.add(iterator2.next());

			}
			while (iterator3.hasNext()) {
				froms.add(iterator3.next());

			}
			while (iterator4.hasNext()) {
				subjects.add(iterator4.next());

			}
			while (iterator5.hasNext()) {
				time.add(iterator5.next());

			}
			while (iterator6.hasNext()) {
				pq.add(iterator6.next());

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

		DLinkedList msg_order = new DLinkedList();
		DLinkedList tos_order = new DLinkedList();
		DLinkedList froms_order = new DLinkedList();
		DLinkedList subjects_order = new DLinkedList();
		DLinkedList time_order = new DLinkedList();
		DLinkedList pq_order = new DLinkedList();

		for (int i = 0; i <= allOrders.size() - 1; i++) {
			msg_order.add(i, bodies.get(allOrders.size() - arr[i]));
			tos_order.add(i, tos.get(allOrders.size() - arr[i]));
			froms_order.add(i, froms.get(allOrders.size() - arr[i]));
			subjects_order.add(i, subjects.get(allOrders.size() - arr[i]));
			time_order.add(i, time.get(allOrders.size() - arr[i]));
			pq_order.add(i, pq.get(allOrders.size() - arr[i]));
		}
		JSONObject obj1 = new JSONObject();
		JSONArray k1 = new JSONArray();
		JSONArray k2 = new JSONArray();
		JSONArray k3 = new JSONArray();
		JSONArray k4 = new JSONArray();
		JSONArray k5 = new JSONArray();
		JSONArray k6 = new JSONArray();
		JSONArray k7 = new JSONArray();

		for (int i = 0; i < allOrders.size(); i++) {
			k1.add(tos_order.get(i));
			k2.add(froms_order.get(i));
			k3.add(subjects_order.get(i));
			k4.add(msg_order.get(i));
			k5.add(Integer.toString(allOrders.size() - arr[i] + 1));
			k6.add(time_order.get(i));
			k7.add(pq_order.get(i));

		}

		obj1.put("tos", k1);
		obj1.put("froms", k2);
		obj1.put("subjects", k3);
		obj1.put("bodies", k4);
		obj1.put("order", k5);
		obj1.put("time", k6);
		obj1.put("pq", k7);
		try (FileWriter file = new FileWriter("Users/temp.json")) {

			file.write(obj1.toString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "unused", "unchecked" })
	@Override
	public void OrderOfAll_2(String namee, String folderChosennn) {

		DLinkedList allOrders = new DLinkedList();
		DLinkedList bodies = new DLinkedList();
		DLinkedList tos = new DLinkedList();
		DLinkedList froms = new DLinkedList();
		DLinkedList subjects = new DLinkedList();
		DLinkedList time = new DLinkedList();
		DLinkedList pq = new DLinkedList();

		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(new FileReader("Users/" + namee + "/" + folderChosennn + "/Index file.json"));

			JSONObject jsonObject = (JSONObject) obj;

			// loop array
			// here we load content of json file
			JSONArray col1 = (JSONArray) jsonObject.get("order");
			JSONArray col2 = (JSONArray) jsonObject.get("bodies");
			JSONArray col3 = (JSONArray) jsonObject.get("tos");
			JSONArray col4 = (JSONArray) jsonObject.get("froms");
			JSONArray col5 = (JSONArray) jsonObject.get("subjects");
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
				allOrders.add(iterator1.next());
			}
			while (iterator2.hasNext()) {

				bodies.add(iterator2.next());

			}
			while (iterator3.hasNext()) {
				tos.add(iterator3.next());

			}
			while (iterator4.hasNext()) {
				froms.add(iterator4.next());

			}
			while (iterator5.hasNext()) {
				subjects.add(iterator5.next());

			}
			while (iterator6.hasNext()) {
				time.add(iterator6.next());

			}
			while (iterator7.hasNext()) {
				pq.add(iterator7.next());

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

		for (int i = 0; i < allOrders.size(); i++) {
			k1.add(tos.get(i));
			k2.add(froms.get(i));
			k3.add(subjects.get(i));
			k4.add(bodies.get(i));
			k5.add(allOrders.get(i));
			k6.add(time.get(i));
			k7.add(pq.get(i));

		}

		obj1.put("tos", k1);
		obj1.put("froms", k2);
		obj1.put("subjects", k3);
		obj1.put("bodies", k4);
		obj1.put("order", k5);
		obj1.put("time", k6);
		obj1.put("pq", k7);
		try (FileWriter file = new FileWriter("Users/temp.json")) {

			file.write(obj1.toString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public void OrderOfAll_3(String namee, String folderChosennn) {

		DLinkedList allOrders = new DLinkedList();
		DLinkedList bodies = new DLinkedList();
		DLinkedList tos = new DLinkedList();
		DLinkedList froms = new DLinkedList();
		DLinkedList subjects = new DLinkedList();
		DLinkedList time = new DLinkedList();
		DLinkedList pq = new DLinkedList();
		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(new FileReader("Users/" + namee + "/" + folderChosennn + "/Index file.json"));

			JSONObject jsonObject = (JSONObject) obj;

			// loop array
			// here we load content of json file
			JSONArray col1 = (JSONArray) jsonObject.get("order");
			JSONArray col2 = (JSONArray) jsonObject.get("bodies");
			JSONArray col3 = (JSONArray) jsonObject.get("tos");
			JSONArray col4 = (JSONArray) jsonObject.get("froms");
			JSONArray col5 = (JSONArray) jsonObject.get("subjects");
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
				allOrders.add(iterator1.next());
			}
			while (iterator2.hasNext()) {

				bodies.add(iterator2.next());

			}
			while (iterator3.hasNext()) {
				tos.add(iterator3.next());

			}
			while (iterator4.hasNext()) {
				froms.add(iterator4.next());

			}
			while (iterator5.hasNext()) {
				subjects.add(iterator5.next());

			}
			while (iterator6.hasNext()) {
				time.add(iterator6.next());

			}
			while (iterator7.hasNext()) {
				pq.add(iterator7.next());

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
		String names[] = new String[allOrders.size()];
		for (int i = allOrders.size() - 1; i >= 0; i--) {
			names[i] = (subjects.get(allOrders.size() - 1 - i).toString());
		}
		for (int i = 0; i < allOrders.size(); i++) {
			for (int j = i + 1; j < allOrders.size(); j++) {
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
		DLinkedList pq_order = new DLinkedList();

		for (int i = 0; i < allOrders.size(); i++) {
			n.add(i, names[i]);
			msg_order.add(1);
			tos_order.add(1);
			froms_order.add(1);
			subjects_order.add(1);
			time_order.add(1);
			orders_order.add(1);
			pq_order.add(1);

		}

		for (int i = 0; i < allOrders.size(); i++) {
			for (int j = 0; j < allOrders.size(); j++) {
				if (Objects.equals(n.get(i), subjects.get(j)) && b_ser_check1(i, j)) {
					state.add(j);
					state1.add(i);
					msg_order.add(i, bodies.get(j));
					tos_order.add(i, tos.get(j));
					froms_order.add(i, froms.get(j));
					subjects_order.add(i, subjects.get(j));
					time_order.add(i, time.get(j));
					orders_order.add(i, allOrders.get(j));
					pq_order.add(i, pq.get(j));
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

		for (int i = 0; i < allOrders.size(); i++) {
			k1.add(tos_order.get(i));
			k2.add(froms_order.get(i));
			k3.add(subjects_order.get(i));
			k4.add(msg_order.get(i));
			k5.add(orders_order.get(i));
			k6.add(time_order.get(i));
			k7.add(pq_order.get(i));

		}

		obj1.put("tos", k1);
		obj1.put("froms", k2);
		obj1.put("subjects", k3);
		obj1.put("bodies", k4);
		obj1.put("order", k5);
		obj1.put("time", k6);
		obj1.put("pq", k7);
		try (FileWriter file = new FileWriter("Users/temp.json")) {

			file.write(obj1.toString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void OrderOfAll_4(String namee, String folderChosennn) {

		DLinkedList allOrders = new DLinkedList();
		DLinkedList bodies = new DLinkedList();
		DLinkedList tos = new DLinkedList();
		DLinkedList froms = new DLinkedList();
		DLinkedList subjects = new DLinkedList();
		DLinkedList time = new DLinkedList();
		DLinkedList pq = new DLinkedList();
		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(new FileReader("Users/" + namee + "/" + folderChosennn + "/Index file.json"));

			JSONObject jsonObject = (JSONObject) obj;

			// loop array
			// here we load content of json file
			JSONArray col1 = (JSONArray) jsonObject.get("order");
			JSONArray col2 = (JSONArray) jsonObject.get("bodies");
			JSONArray col3 = (JSONArray) jsonObject.get("tos");
			JSONArray col4 = (JSONArray) jsonObject.get("froms");
			JSONArray col5 = (JSONArray) jsonObject.get("subjects");
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
				allOrders.add(iterator1.next());
			}
			while (iterator2.hasNext()) {

				bodies.add(iterator2.next());

			}
			while (iterator3.hasNext()) {
				tos.add(iterator3.next());

			}
			while (iterator4.hasNext()) {
				froms.add(iterator4.next());

			}
			while (iterator5.hasNext()) {
				subjects.add(iterator5.next());

			}
			while (iterator6.hasNext()) {
				time.add(iterator6.next());

			}
			while (iterator7.hasNext()) {
				pq.add(iterator7.next());

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
		String names[] = new String[allOrders.size()];
		for (int i = allOrders.size() - 1; i >= 0; i--) {
			names[i] = (froms.get(allOrders.size() - 1 - i).toString());
		}
		for (int i = 0; i < allOrders.size(); i++) {
			for (int j = i + 1; j < allOrders.size(); j++) {
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
		DLinkedList pq_order = new DLinkedList();

		for (int i = 0; i < allOrders.size(); i++) {
			n.add(i, names[i]);
			msg_order.add(1);
			tos_order.add(1);
			froms_order.add(1);
			subjects_order.add(1);
			time_order.add(1);
			orders_order.add(1);
			pq_order.add(1);
		}

		for (int i = 0; i < allOrders.size(); i++) {
			for (int j = 0; j < allOrders.size(); j++) {
				if (Objects.equals(n.get(i), froms.get(j)) && b_ser_check1(i, j)) {
					state.add(j);
					state1.add(i);
					msg_order.add(i, bodies.get(j));
					tos_order.add(i, tos.get(j));
					froms_order.add(i, froms.get(j));
					subjects_order.add(i, subjects.get(j));
					time_order.add(i, time.get(j));
					orders_order.add(i, allOrders.get(j));
					pq_order.add(i, pq.get(j));
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

		for (int i = 0; i < allOrders.size(); i++) {
			k1.add(tos_order.get(i));
			k2.add(froms_order.get(i));
			k3.add(subjects_order.get(i));
			k4.add(msg_order.get(i));
			k5.add(orders_order.get(i));
			k6.add(time_order.get(i));
			k7.add(pq_order.get(i));

		}

		obj1.put("tos", k1);
		obj1.put("froms", k2);
		obj1.put("subjects", k3);
		obj1.put("bodies", k4);
		obj1.put("order", k5);
		obj1.put("time", k6);
		obj1.put("pq", k7);
		try (FileWriter file = new FileWriter("Users/temp.json")) {

			file.write(obj1.toString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void OrderOfAll_5(String namee, String folderChosennn) {
		DLinkedList allOrders = new DLinkedList();
		DLinkedList bodies = new DLinkedList();
		DLinkedList tos = new DLinkedList();
		DLinkedList froms = new DLinkedList();
		DLinkedList subjects = new DLinkedList();
		DLinkedList time = new DLinkedList();
		DLinkedList priorityq = new DLinkedList();

		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(new FileReader("Users/" + namee + "/" + folderChosennn + "/Index file.json"));

			JSONObject jsonObject = (JSONObject) obj;

			// loop array
			// here we load content of json file
			JSONArray col1 = (JSONArray) jsonObject.get("order");
			JSONArray col2 = (JSONArray) jsonObject.get("bodies");
			JSONArray col3 = (JSONArray) jsonObject.get("tos");
			JSONArray col4 = (JSONArray) jsonObject.get("froms");
			JSONArray col5 = (JSONArray) jsonObject.get("subjects");
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
				allOrders.add(iterator1.next());
			}
			while (iterator2.hasNext()) {

				bodies.add(iterator2.next());

			}
			while (iterator3.hasNext()) {
				tos.add(iterator3.next());

			}
			while (iterator4.hasNext()) {
				froms.add(iterator4.next());

			}
			while (iterator5.hasNext()) {
				subjects.add(iterator5.next());

			}
			while (iterator6.hasNext()) {
				time.add(iterator6.next());

			}
			while (iterator7.hasNext()) {
				priorityq.add(iterator7.next());

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

		int[] arr = new int[allOrders.size()];
		for (int i = allOrders.size() - 1; i >= 0; i--) {
			arr[i] = (Integer.parseInt(priorityq.get(i).toString()));
		}

		PriorityQ p = new PriorityQ(); // here priority queue
		for (int i = 0; i < allOrders.size(); i++) {
			p.insert(allOrders.get(i), arr[i]);
		}

		int[] arrr = new int[allOrders.size()];
		for (int i = 0; i < allOrders.size(); i++) {
			arrr[i] = (Integer.parseInt(p.removeMin().toString()));
		}

		int[] arrrr = new int[allOrders.size()];
		for (int i = allOrders.size() - 1; i >= 0; i--) {
			arrrr[i] = (Integer.parseInt(allOrders.get(i).toString()));
		}

		DLinkedList n = new DLinkedList();
		DLinkedList msg_order = new DLinkedList();
		DLinkedList tos_order = new DLinkedList();
		DLinkedList froms_order = new DLinkedList();
		DLinkedList subjects_order = new DLinkedList();
		DLinkedList time_order = new DLinkedList();
		DLinkedList orders_order = new DLinkedList();
		DLinkedList pq_order = new DLinkedList();

		for (int i = 0; i < allOrders.size(); i++) {
			n.add(i, arrr[i]);
			msg_order.add(1);
			tos_order.add(1);
			froms_order.add(1);
			subjects_order.add(1);
			time_order.add(1);
			orders_order.add(1);
			pq_order.add(1);
		}

		for (int i = 0; i < allOrders.size(); i++) {
			for (int j = 0; j < allOrders.size(); j++) {
				if (arrr[i] == arrrr[j]) {
					pq_order.add(i, priorityq.get(j));
					msg_order.add(i, bodies.get(j));
					tos_order.add(i, tos.get(j));
					froms_order.add(i, froms.get(j));
					subjects_order.add(i, subjects.get(j));
					time_order.add(i, time.get(j));
					orders_order.add(i, allOrders.get(j));
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

		for (int i = 0; i < allOrders.size(); i++) {
			k1.add(tos_order.get(i));
			k2.add(froms_order.get(i));
			k3.add(subjects_order.get(i));
			k4.add(msg_order.get(i));
			k5.add(orders_order.get(i));
			k6.add(time_order.get(i));
			k7.add(pq_order.get(i));

		}

		obj1.put("tos", k1);
		obj1.put("froms", k2);
		obj1.put("subjects", k3);
		obj1.put("bodies", k4);
		obj1.put("order", k5);
		obj1.put("time", k6);
		obj1.put("pq", k7);
		try (FileWriter file = new FileWriter("Users/temp.json")) {

			file.write(obj1.toString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	boolean b_ser_check1(int l, int k) {
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

	public static void quick_sort(int[] input) {
		MyStack stack = new MyStack();
		int high = input.length - 1;
		int low = 0;
		stack.push(low);
		stack.push(high);

		while (!stack.isEmpty()) {
			int h = (int) stack.pop();
			int l = (int) stack.pop();
			int partionIndex = partition(input, l, h, input[h]);
			if (partionIndex - 1 > l) {
				stack.push(l);
				stack.push(partionIndex - 1);
			}
			if (partionIndex + 1 < h) {
				stack.push(partionIndex + 1);
				stack.push(h);
			}
		}
	}

	@SuppressWarnings("unchecked")
	static DLinkedList read_sorted() {
		JSONParser parser = new JSONParser();
		DLinkedList emails = new DLinkedList();
		try {

			Object obj = parser.parse(new FileReader("Users/temp.json"));

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
	@SuppressWarnings("unchecked")
	public static void save_val(int x) {
		JSONObject obj = new JSONObject();
		obj.put("val", Integer.toString(x));
		 try (FileWriter file = new FileWriter("Users/updates/delete.json")) {

	            file.write(obj.toString());
	            file.flush();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	public static int get_val() {
		JSONParser parser = new JSONParser();
		String val = "";
		try {

			Object obj = parser.parse(new FileReader("Users/updates/delete.json"));

			JSONObject jsonObject = (JSONObject) obj;
			 val = (String) jsonObject.get("val");
			
			

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Integer.parseInt(val);
	}


	public static int get_page() {
		JSONParser parser = new JSONParser();
		String pg = "";
		try {

			Object obj = parser.parse(new FileReader("Users/page.json"));

			JSONObject jsonObject = (JSONObject) obj;
			pg = (String) jsonObject.get("pg");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Integer.parseInt(pg);
	}

	public static int get_state() {
		JSONParser parser = new JSONParser();
		String pg = "";
		try {

			Object obj = parser.parse(new FileReader("Users/state.json"));

			JSONObject jsonObject = (JSONObject) obj;
			pg = (String) jsonObject.get("state");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Integer.parseInt(pg);
	}

	@SuppressWarnings("unchecked")
	public static void save_page(int x) {
		JSONObject obj = new JSONObject();
		obj.put("pg", Integer.toString(x));
		try (FileWriter file = new FileWriter("Users/page.json")) {

			file.write(obj.toString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static void save_state(int x) {
		JSONObject obj = new JSONObject();
		obj.put("state", Integer.toString(x));
		try (FileWriter file = new FileWriter("Users/state.json")) {

			file.write(obj.toString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// here we want to save the email we want to display after we view all emails in
	// any folder such as Inbox
	@SuppressWarnings("unchecked")
	public static void save_email(SLinkedList y, int order) {
		SLinkedList emails_cont = new SLinkedList();

		JSONObject obj1 = new JSONObject();
		JSONArray k1 = new JSONArray();
		JSONArray k2 = new JSONArray();
		JSONArray k3 = new JSONArray();
		JSONArray k4 = new JSONArray();
		JSONArray k5 = new JSONArray();
		JSONArray k6 = new JSONArray();
		JSONArray k7 = new JSONArray();

		k1.add(((Sorting) y.get(order)).to);
		k2.add(((Sorting) y.get(order)).from);
		k3.add(((Sorting) y.get(order)).Subject);
		k4.add(((Sorting) y.get(order)).body);
		k5.add(((Sorting) y.get(order)).order);
		k6.add(((Sorting) y.get(order)).time);
		k7.add(((Sorting) y.get(order)).pq);

		obj1.put("tos", k1);
		obj1.put("froms", k2);
		obj1.put("subjects", k3);
		obj1.put("bodies", k4);
		obj1.put("order", k5);
		obj1.put("time", k6);
		obj1.put("pq", k7);
		try (FileWriter file = new FileWriter("Users/cont.json")) {

			file.write(obj1.toString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static SLinkedList load_email() {
		SLinkedList emails_cont = new SLinkedList();
		JSONParser parser = new JSONParser();
		String pg = "";
		try {

			Object obj = parser.parse(new FileReader("Users/cont.json"));

			JSONObject jsonObject = (JSONObject) obj;
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
				emails_cont.add(new Sorting(iterator1.next(), iterator2.next(), iterator3.next(), iterator4.next(),
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

		return emails_cont;

	}

	private static void SwapElement(int i, int j, int[] arr) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	private static int partition(int[] input, int low, int high, int pivot) {
		int left = low - 1;
		int right = high;
		while (true) {
			while (input[++left] < pivot && left < right)
				;
			while (input[--right] > pivot && right > 0)
				;
			if (left <= right)
				SwapElement(left, right, input);
			else
				break;
		}
		SwapElement(left, high, input);
		return left;
	}

}
