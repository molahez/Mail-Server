package eg.edu.alexu.csd.datastructure.linkedList.cs03_cs10;

import java.io.File;
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

import eg.edu.alexu.csd.datastructure.linkedList.ILinkedList;
import eg.edu.alexu.csd.datastructure.mailServer.cs03_cs24_cs54.Filter;
import eg.edu.alexu.csd.datastructure.mailServer.cs03_cs24_cs54.Mail;
import eg.edu.alexu.csd.datastructure.mailServer.cs03_cs24_cs54.Sorting;

/**
 * @author ahmed molahez
 *
 */
public class DLinkedList implements ILinkedList {
	/**
	 * @serialField
	 */
	public Node first;
	/**
	 * @serialField
	 */
	public Node last;
	/**
	 * @serialField
	 */
	public int counttt = 0;

	/**
	 * @serialField
	 */
	public DLinkedList() { // constructs an
		chosen_folder = null;
		contact = null;
	}

	/**
	 * Inserts a specified element at the specified position in the list.
	 *
	 * @param index
	 *            dv
	 * @param element
	 *            dv
	 * @throws IndexOutOfBoundsException
	 *             d
	 */
	public void add(final int index, final Object element) {

		Node newNode = new Node(element);
		Node temp = first;
		if (index < 0 || index > size()) {

			throw new IndexOutOfBoundsException();
		} else if (index == 0 && size() == 0) {
			first = newNode;
			last = newNode;
			counttt++;
		} else if (index == 0) {
			newNode.next = first;
			first.previous = newNode;
			first = newNode;
			counttt++;
		} else if (index == counttt) {
			add(element);
		} else {
			for (int i = 1; i < index; i++) {

				temp = temp.next;
				if (temp == null) {
					throw new IndexOutOfBoundsException();
				}
			}
			newNode.next = temp.next;
			newNode.previous = temp;
			temp.next.previous = newNode;
			temp.next = newNode;
			counttt++;
		}
	}

	/**
	 * Inserts the specified element at the end of the list.
	 *
	 * @param element
	 *            d
	 */
	public void add(final Object element) { // working
		Node newNode = new Node(element);
		if (first == null && last == null) {
			first = newNode;
			last = newNode;
		} else {
			last.next = newNode;
			newNode.previous = last;
			last = newNode;
		}
		counttt++;
	}

	/**
	 * @param index
	 *            sc
	 * @return the element at the specified position in this list.
	 */
	public Object get(final int index) { // working

		if (first == null) {
			throw new IndexOutOfBoundsException();
		}

		if (index > (size() - 1) || index < 0) {

			throw new IndexOutOfBoundsException();
		}

		Node temp;
		temp = first;
		if (index == size() - 1) {
			temp = last;
		} else {
			for (int i = 0; i < index; i++) {
				temp = temp.next;
			}
		}

		return temp.data;

	}

	/**
	 * Replaces the element at the specified position in this list with the
	 * specified element.
	 *
	 * @param index
	 *            s
	 * @param element
	 *            cs
	 */
	public void set(final int index, final Object element) { // working

		Node temp;
		temp = first;

		if (first == null) {
			throw new NullPointerException();
		}

		if (index > (size() - 1)) {
			throw new NullPointerException();
		}

		for (int i = 0; i < index; i++) {
			temp = temp.next;
		}
		temp.data = element;
	}

	/**
	 * Removes all of the elements from this list.
	 */
	public void clear() {

		first = null;
		last = null;
		counttt = 0;
	}

	/**
	 * @return true if this list contains no elements.
	 */
	public boolean isEmpty() {

		return (first == null);

	}

	/**
	 * Removes the element at the specified position in this list.
	 *
	 * @param index
	 *            cs
	 */
	public void remove(final int index) { // working

		Node temp;
		temp = first;

		if (index > (size() - 1) || (index == 0 && size() == 0) || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		if (index == 0 && size() == 1) {
			first = first.next;
			last = null;
		} else if (index == 0) {
			first = first.next;
			first.previous = null;
		} else if (index < size() - 1) {
			for (int i = 1; i < index; i++) {
				temp = temp.next;
			}
			if (index != size() - 2) {
				temp.next = temp.next.next;
				temp.next.next.previous = temp;
			} else {
				temp.next = temp.next.next;

			}
		} else if (index == size() - 1) {
			temp = last.previous;
			last = temp;
			temp.next.previous = null;
			temp.next = null;
		}
		counttt--;
	}

	/**
	 * @return the number of elements in this list.
	 */
	public int size() { // working

		if (isEmpty()) {
			return 0;
		} else {
			return counttt;
		}
	}

	/**
	 * @param fromIndex
	 *            c
	 * @param toIndex
	 *            sc
	 * @return a view of the portion of this list between the specified fromIndex
	 *         and toIndex, inclusively.
	 */
	public ILinkedList sublist(final int fromIndex, final int toIndex) {

		DLinkedList subList = new DLinkedList();
		for (int i = fromIndex; i <= toIndex; i++) {

			subList.add(get(i));

		}
		return subList;

	}

	/**
	 * @param o
	 *            sc
	 * @return true if this list contains an element with the same value as the
	 *         specified element.
	 */
	public boolean contains(final Object o) {
		if (first == null) {
			return false;
		}
		Node temp = first;
		while (!o.equals(temp.data) && temp.next != null) {
			temp = temp.next;
		}
		return o.equals(temp.data);
	}

	public static String chosen_folder;
	public static String contact;

	public void var(String chosen_folder, String contact) {
		DLinkedList.chosen_folder = chosen_folder;
		DLinkedList.contact = contact;

	}

	@SuppressWarnings("unchecked")
	public void delete_from_index(String path, ILinkedList details) {
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
		
		for (int i = 0; i < details.size(); i++) {
			if (!((Sorting)details.get(i)).to.isEmpty()) {
				for (int k = 0; k < orders.size(); k++) {
					if (Objects.equals(orders.get(k), ((Sorting)details.get(i)).order)) {
						send.remove(k);
						recieve.remove(k);
						subjects.remove(k);
						bodies.remove(k);
						orders.remove(k);
						times.remove(k);
						pqs.remove(k);
						break;
					}
				}
			}
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

	public void put_in_trash(ILinkedList details, String cont, String chosen) {
        Filter h = new Filter();
		Mail x = new Mail();
		String path = "Users/" + cont + "/Trash/Index file.json";
		System.out.println(path);
		for (int i = 0; i < details.size(); i++) {
			if (!((Sorting) details.get(i)).to.isEmpty()) {
				System.out.println(((Sorting) details.get(i)).to);
				x.save_email(((Sorting) details.get(i)).to, ((Sorting) details.get(i)).from,
						((Sorting) details.get(i)).Subject, ((Sorting) details.get(i)).body, path,
						((Sorting) details.get(i)).time, ((Sorting) details.get(i)).pq);
				details.set(i, new Sorting("","","","","","",((Sorting) details.get(i)).order));
				int n = x.order(path);
				String y = "" + n;
				
				String path1 = "Users/" + cont + "/" + chosen;
				File z = new File(path1);
				File[] files = z.listFiles();
				
				for (File file : files) {
					if (Objects.equals(file.getName(), ((Sorting) details.get(i)).order)) {
						h.copyFolder(file, new File("Users/" + cont + "/Trash/" + y));
						x.delete_attachment(file.getAbsolutePath());

					}
				}

			}
		}

	}
	public void put_move(ILinkedList details, String cont, String chosen) {
        Filter h = new Filter();

		Mail x = new Mail();
		String path = "Users/" + cont + "/" + chosen + "/Index file.json";
		System.out.println(path);
		for (int i = 0; i < details.size(); i++) {
			if (!((Sorting) details.get(i)).to.isEmpty()) {
				System.out.println(((Sorting) details.get(i)).to);
				x.save_email(((Sorting) details.get(i)).to, ((Sorting) details.get(i)).from,
						((Sorting) details.get(i)).Subject, ((Sorting) details.get(i)).body, path,
						((Sorting) details.get(i)).time, ((Sorting) details.get(i)).pq);
				details.set(i, new Sorting("","","","","","",((Sorting) details.get(i)).order));
				int n = x.order(path);
				String y = "" + n;
				
				String path1 = "Users/" + cont + "/" + chosen;
				File z = new File(path1);
				File[] files = z.listFiles();
				
				for (File file : files) {
					if (Objects.equals(file.getName(), ((Sorting) details.get(i)).order)) {
						h.copyFolder(file, new File("Users/" + cont + "/Trash/" + y));
						x.delete_attachment(file.getAbsolutePath());

					}
				}

			}
		}

	}

}
