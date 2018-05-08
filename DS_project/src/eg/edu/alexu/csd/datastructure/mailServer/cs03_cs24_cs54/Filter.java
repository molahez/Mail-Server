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
import eg.edu.alexu.csd.datastructure.mailServer.IFilter;
import eg.edu.alexu.csd.datastructure.stack.cs03.MyStack;

public class Filter implements IFilter {

	String type;

	@Override
	public void read_indexfile(String category, String path) {
		JSONParser parser = new JSONParser();
		DLinkedList mail = new DLinkedList();
		Mail x = new Mail();
		this.type = category;
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
				x.setTo(iterator1.next());
				x.setFrom(iterator2.next());
				x.setFrom(iterator3.next());
				x.setEmail_body(iterator4.next());
				x.setOrder(iterator5.next().toString());
				x.setTime(iterator6.next());
				x.setOrder(iterator7.next());
				
				mail.add(x);
				quick_sort(mail);
			}
			Mail current = (Mail) mail.get(0);
			System.out.println(current.getSubject());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		/*
		 * for (int i = 0; i < recieve.size(); i++) { k1.add(recieve.get(i));
		 * k2.add(send.get(i)); k3.add(subjects.get(i)); k4.add(bodies.get(i));
		 * k5.add(orders.get(i)); k6.add(times.get(i)); k7.add(pqs.get(i)); }
		 * 
		 * obj1.put("tos", k1); obj1.put("froms", k2); obj1.put("subjects", k3);
		 * obj1.put("bodies", k4); obj1.put("order", k5); obj1.put("time", k6);
		 * obj1.put("pq", k7); try (FileWriter file = new FileWriter(path)) {
		 * 
		 * file.write(obj1.toString()); file.flush();
		 * 
		 * } catch (IOException e) { e.printStackTrace(); }
		 */

	}

	public void quick_sort(DLinkedList input) {
		MyStack stack = new MyStack();
		Mail pivot;
		int pivotIndex = 0;
		int leftIndex = pivotIndex + 1;
		int rightIndex = input.size() - 1;

		stack.push(pivotIndex);// Push always with left and right
		stack.push(rightIndex);

		int leftIndexOfSubSet, rightIndexOfSubset;

		while (stack.size() > 0) {
			rightIndexOfSubset = (int) stack.pop();// pop always with right and left
			leftIndexOfSubSet = (int) stack.pop();

			if (rightIndexOfSubset - leftIndexOfSubSet < 2) {
				continue;
			}

			leftIndex = leftIndexOfSubSet + 1;
			pivotIndex = leftIndexOfSubSet;
			rightIndex = rightIndexOfSubset;

			pivot = (Mail) input.get(pivotIndex);

			if (leftIndex > rightIndex)
				continue;

			while (leftIndex < rightIndex) {
				int com = 0;
				Mail temp = (Mail) input.get(leftIndex);
				//edit
				if (type.equals("to")) {
					com = pivot.compare(temp);
				}
				else if(type.equals("from")) {
					com = pivot.comparefrom(temp);
				}
				else if(type.equals("subject")) {
					com = pivot.comparesubject(temp);
				}
				while ((leftIndex <= rightIndex) && (com >= 0))
					leftIndex++; // increment right to find the greater
				// element than the pivot
				temp = (Mail) input.get(rightIndex);
				if (type.equals("to")) {
					com = pivot.compare(temp);
				}
				else if(type.equals("from")) {
					com = pivot.comparefrom(temp);
				}
				else if(type.equals("subject")) {
					com = pivot.comparesubject(temp);
				}
				while ((leftIndex <= rightIndex) && (com <= 0))
					rightIndex--;// decrement right to find the
				// smaller element than the pivot

				if (rightIndex >= leftIndex) // if right index is
					// greater then only swap
					SwapElement(input, leftIndex, rightIndex);
			}
			int com = 0;
			Mail temp = (Mail) input.get(rightIndex);
			Mail temp2 = (Mail) input.get(pivotIndex);
			//edit
			if (type.equals("to")) {
				com = temp2.compare(temp);
			}
			else if(type.equals("from")) {
				com = temp2.comparefrom(temp);
			}
			else if(type.equals("subject")) {
				com = temp2.comparesubject(temp);
			}
			if (pivotIndex <= rightIndex)
				if (com>0)
					SwapElement(input, pivotIndex, rightIndex);

			if (leftIndexOfSubSet < rightIndex) {
				stack.push(leftIndexOfSubSet);
				stack.push(rightIndex - 1);
			}

			if (rightIndexOfSubset > rightIndex) {
				stack.push(rightIndex + 1);
				stack.push(rightIndexOfSubset);
			}
		}
	}

	private static void SwapElement(DLinkedList arr, int left, int right) {
		Mail temp = (Mail) arr.get(left);
		arr.set(left, arr.get(right));
		arr.set(right, temp);
	}

	@Override
	public int bsearch(String x, DLinkedList y) {
		return 0;
		// linked list of mails

	}
}
