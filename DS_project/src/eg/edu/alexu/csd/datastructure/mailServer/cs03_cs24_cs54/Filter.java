package eg.edu.alexu.csd.datastructure.mailServer.cs03_cs24_cs54;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
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

	@Override
	public int bsearch(String x, DLinkedList y) {
		// TODO Auto-generated method stub
		return 0;
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
			String arr[] = new String[pqs.size()];
			for(int i = 0;i<pqs.size();i++) {
				arr[i]=(String) subjects.get(i);
			}
			Arrays.sort(arr);
			System.out.println(arr[0]);
			

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

	/*public void quick_sort(DLinkedList input) {
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
				// edit
				if (type.equals("to")) {
					com = pivot.comparesubject(temp.getSubject());
				} else if (type.equals("from")) {
					com = pivot.comparesubject(temp.getSubject());
				} else if (type.equals("subject")) {
					com = pivot.comparesubject(temp.getSubject());
				}
				while ((leftIndex <= rightIndex) && (com >= 0))
					leftIndex++; // increment right to find the greater
				// element than the pivot
				temp = (Mail) input.get(rightIndex);
				if (type.equals("to")) {
					com = pivot.comparesubject(temp.getSubject());
				} else if (type.equals("from")) {
					com = pivot.comparesubject(temp.getSubject());
				} else if (type.equals("subject")) {
					com = pivot.comparesubject(temp.getSubject());
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
			// edit
			if (type.equals("to")) {
				com = temp2.comparesubject(temp.getSubject());
			} else if (type.equals("from")) {
				com = temp2.comparesubject(temp.getSubject());
			} else if (type.equals("subject")) {
				com = temp2.comparesubject(temp.getSubject());
			}
			if (pivotIndex <= rightIndex)
				if (com > 0)
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
	}*/
}
