package eg.edu.alexu.csd.datastructure.mailServer.cs03_cs24_cs54;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import eg.edu.alexu.csd.datastructure.linkedList.cs03_cs10.DLinkedList;
import eg.edu.alexu.csd.datastructure.mailServer.ISort;
import eg.edu.alexu.csd.datastructure.stack.cs03.MyStack;

public class Sorting implements ISort{
	
	@SuppressWarnings("unchecked")
	@Override
	public void OrderOfAll_1(String namee,String folderChosennn) {
		
		DLinkedList allOrders = new DLinkedList();
		JSONParser parser = new JSONParser();
        
		try {

			Object obj = parser.parse(new FileReader("Users/"+namee+"/"+folderChosennn+"/Index file.json"));
                  
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

		int[] arr = new int[allOrders.size()] ;
		for (int i = allOrders.size()-1; i >=0; i--) {
              arr[i]=(Integer.parseInt(allOrders.get(allOrders.size()-1-i).toString()));
		}
		
		quick_sort(arr);
		
		DLinkedList bodies = new DLinkedList();
		DLinkedList tos = new DLinkedList();
		DLinkedList froms = new DLinkedList();
		DLinkedList subjects = new DLinkedList();
		DLinkedList time = new DLinkedList();
		JSONParser parserr = new JSONParser();

		try {

			Object obj = parserr.parse(new FileReader("Users/"+namee+"/"+folderChosennn+"/Index file.json"));

			JSONObject jsonObject = (JSONObject) obj;

			// loop array
			// here we load content of json file
			JSONArray col1 = (JSONArray) jsonObject.get("bodies");
			JSONArray col2 = (JSONArray) jsonObject.get("tos");
			JSONArray col3 = (JSONArray) jsonObject.get("froms");
			JSONArray col4 = (JSONArray) jsonObject.get("subjects");
			JSONArray col5 = (JSONArray) jsonObject.get("time");
			Iterator<String> iterator1 = col1.iterator();
			Iterator<String> iterator2 = col2.iterator();
			Iterator<String> iterator3 = col3.iterator();
			Iterator<String> iterator4 = col4.iterator();
			Iterator<String> iterator5 = col5.iterator();
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

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		DLinkedList msg_order =new DLinkedList();
		DLinkedList tos_order =new DLinkedList();
		DLinkedList froms_order =new DLinkedList();
		DLinkedList subjects_order =new DLinkedList();
		DLinkedList time_order =new DLinkedList();
		
		for (int i = 0; i <= allOrders.size()-1; i++) {
			  msg_order.add(i,bodies.get(arr[i]-1));
			  tos_order.add(i,tos.get(arr[i]-1));
			  froms_order.add(i,froms.get(arr[i]-1));
			  subjects_order.add(i,subjects.get(arr[i]-1));
			  time_order.add(i,time.get(arr[i]-1));
		    }
	}
	@SuppressWarnings({ "unused", "unchecked" })
	@Override
  public void OrderOfAll_2(String namee,String folderChosennn) {
		
		DLinkedList allOrders = new DLinkedList();
		DLinkedList bodies = new DLinkedList();
		DLinkedList tos = new DLinkedList();
		DLinkedList froms = new DLinkedList();
		DLinkedList subjects = new DLinkedList();
		DLinkedList time = new DLinkedList();
		JSONParser parser = new JSONParser();
        
		try {

			Object obj = parser.parse(new FileReader("Users/"+namee+"/"+folderChosennn+"/Index file.json"));
                  
			JSONObject jsonObject = (JSONObject) obj;

			// loop array
			// here we load content of json file
			JSONArray col1 = (JSONArray) jsonObject.get("order");
			JSONArray col2 = (JSONArray) jsonObject.get("bodies");
			JSONArray col3 = (JSONArray) jsonObject.get("tos");
			JSONArray col4 = (JSONArray) jsonObject.get("froms");
			JSONArray col5 = (JSONArray) jsonObject.get("subjects");
			JSONArray col6 = (JSONArray) jsonObject.get("time");
			Iterator<String> iterator1 = col1.iterator();
			Iterator<String> iterator2 = col1.iterator();
			Iterator<String> iterator3 = col2.iterator();
			Iterator<String> iterator4 = col3.iterator();
			Iterator<String> iterator5 = col4.iterator();
			Iterator<String> iterator6 = col5.iterator();

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
	@SuppressWarnings("unchecked")
	@Override
	public void OrderOfAll_3(String namee,String folderChosennn) {
		
		DLinkedList allOrders = new DLinkedList();
		DLinkedList bodies = new DLinkedList();
		DLinkedList tos = new DLinkedList();
		DLinkedList froms = new DLinkedList();
		DLinkedList subjects = new DLinkedList();
		DLinkedList time = new DLinkedList();
		JSONParser parser = new JSONParser();
        
		try {

			Object obj = parser.parse(new FileReader("Users/"+namee+"/"+folderChosennn+"/Index file.json"));
                  
			JSONObject jsonObject = (JSONObject) obj;

			// loop array
			// here we load content of json file
			JSONArray col1 = (JSONArray) jsonObject.get("order");
			JSONArray col2 = (JSONArray) jsonObject.get("bodies");
			JSONArray col3 = (JSONArray) jsonObject.get("tos");
			JSONArray col4 = (JSONArray) jsonObject.get("froms");
			JSONArray col5 = (JSONArray) jsonObject.get("subjects");
			JSONArray col6 = (JSONArray) jsonObject.get("time");
			Iterator<String> iterator1 = col1.iterator();
			Iterator<String> iterator2 = col2.iterator();
			Iterator<String> iterator3 = col3.iterator();
			Iterator<String> iterator4 = col4.iterator();
			Iterator<String> iterator5 = col5.iterator();
			Iterator<String> iterator6 = col6.iterator();

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
		 for (int i = allOrders.size()-1; i >=0; i--) {
             names[i]=(subjects.get(allOrders.size()-1-i).toString());
		}	
	        for (int i = 0; i < allOrders.size(); i++) 
	        {
	            for (int j = i + 1; j < allOrders.size(); j++) 
	            {
	                if ( names[i].compareTo( names[j])>0 ) 
	                {
	                  String  temp = names[i];
	                    names[i] = names[j];
	                    names[j] = temp;
	                }
	            }
	         }
	        DLinkedList n =new DLinkedList();
	        DLinkedList msg_order =new DLinkedList();
			DLinkedList tos_order =new DLinkedList();
			DLinkedList froms_order =new DLinkedList();
			DLinkedList subjects_order =new DLinkedList();
			DLinkedList time_order =new DLinkedList();
			DLinkedList orders_order =new DLinkedList();
			
	        for (int i = 0; i < allOrders.size(); i++) {
	        	n.add(i,names[i]);
	        }
	        
	        for (int i = 0; i < allOrders.size()-1; i++) {
	        	 for (int j = 0; j < allOrders.size()-1; j++) {
	        		 if (Objects.equals(n.get(i), subjects.get(j))) {
	        			 msg_order.add(i, bodies.get(j));
	        			 tos_order.add(i, tos.get(j));
	        			 froms_order.add(i, froms.get(j));
	        			 subjects_order.add(i, subjects.get(j));
	        			 time_order.add(i, time.get(j));
	        			 orders_order.add(i, allOrders.get(j));
	        		 }
	        	 }
	        }
		
	}
	@SuppressWarnings("unchecked")
	@Override
public void OrderOfAll_4(String namee,String folderChosennn) {
		
		DLinkedList allOrders = new DLinkedList();
		DLinkedList bodies = new DLinkedList();
		DLinkedList tos = new DLinkedList();
		DLinkedList froms = new DLinkedList();
		DLinkedList subjects = new DLinkedList();
		DLinkedList time = new DLinkedList();
		JSONParser parser = new JSONParser();
        
		try {

			Object obj = parser.parse(new FileReader("Users/"+namee+"/"+folderChosennn+"/Index file.json"));
                  
			JSONObject jsonObject = (JSONObject) obj;

			// loop array
			// here we load content of json file
			JSONArray col1 = (JSONArray) jsonObject.get("order");
			JSONArray col2 = (JSONArray) jsonObject.get("bodies");
			JSONArray col3 = (JSONArray) jsonObject.get("tos");
			JSONArray col4 = (JSONArray) jsonObject.get("froms");
			JSONArray col5 = (JSONArray) jsonObject.get("subjects");
			JSONArray col6 = (JSONArray) jsonObject.get("time");
			Iterator<String> iterator1 = col1.iterator();
			Iterator<String> iterator2 = col2.iterator();
			Iterator<String> iterator3 = col3.iterator();
			Iterator<String> iterator4 = col4.iterator();
			Iterator<String> iterator5 = col5.iterator();
			Iterator<String> iterator6 = col6.iterator();

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
		 for (int i = allOrders.size()-1; i >=0; i--) {
             names[i]=(froms.get(allOrders.size()-1-i).toString());
		}	
	        for (int i = 0; i < allOrders.size(); i++) 
	        {
	            for (int j = i + 1; j < allOrders.size(); j++) 
	            {
	                if ( names[i].compareTo( names[j])>0) 
	                {
	                  String  temp = names[i];
	                    names[i] = names[j];
	                    names[j] = temp;
	                }
	            }
	        }
	        DLinkedList n =new DLinkedList();
	        DLinkedList msg_order =new DLinkedList();
			DLinkedList tos_order =new DLinkedList();
			DLinkedList froms_order =new DLinkedList();
			DLinkedList subjects_order =new DLinkedList();
			DLinkedList time_order =new DLinkedList();
			DLinkedList orders_order =new DLinkedList();
			
	        for (int i = 0; i < allOrders.size(); i++) {
	        	n.add(i,names[i]);
	        }
	        
	        for (int i = 0; i < allOrders.size()-1; i++) {
	        	 for (int j = 0; j < allOrders.size()-1; j++) {
	        		 if (Objects.equals(n.get(i), froms.get(j))) {
	        			 msg_order.add(i, bodies.get(j));
	        			 tos_order.add(i, tos.get(j));
	        			 froms_order.add(i, froms.get(j));
	        			 subjects_order.add(i, subjects.get(j));
	        			 time_order.add(i, time.get(j));
	        			 orders_order.add(i, allOrders.get(j));
	        		 }
	        	 }
	        }
	
		
	}
	public static void quick_sort( int[] input)
    {
        MyStack stack = new MyStack();
        int pivot;
        int pivotIndex = 0;
        int leftIndex = pivotIndex + 1;
        int rightIndex = input.length - 1;

        stack.push(pivotIndex);//Push always with left and right
        stack.push(rightIndex);

        int leftIndexOfSubSet, rightIndexOfSubset;

        while (stack.size() > 0)
        {
            rightIndexOfSubset = (int)stack.pop();//pop always with right and left
            leftIndexOfSubSet = (int)stack.pop();

            if (rightIndexOfSubset - leftIndexOfSubSet < 2) { 
            	continue; 
            	}
            
            leftIndex = leftIndexOfSubSet + 1;
            pivotIndex = leftIndexOfSubSet;
            rightIndex = rightIndexOfSubset;

            pivot = input[pivotIndex];

            if (leftIndex > rightIndex)
                continue;

            while (leftIndex < rightIndex)
            {
                while ((leftIndex <= rightIndex) && (input[leftIndex] <= pivot))
                    leftIndex++;	//increment right to find the greater 
			//element than the pivot

                while ((leftIndex <= rightIndex) && (input[rightIndex] >= pivot))
                    rightIndex--;//decrement right to find the 
			//smaller element than the pivot

                if (rightIndex >= leftIndex)   //if right index is 
					//greater then only swap
                    SwapElement(input, leftIndex, rightIndex);
            }

            if (pivotIndex <= rightIndex)
                if( input[pivotIndex] > input[rightIndex])
                    SwapElement(input, pivotIndex, rightIndex);
           
            if (leftIndexOfSubSet < rightIndex)
            {
                stack.push(leftIndexOfSubSet);
                stack.push(rightIndex - 1);
            }

            if (rightIndexOfSubset > rightIndex)
            {
                stack.push(rightIndex + 1);
                stack.push(rightIndexOfSubset);
            }
        }
    }

    private static void SwapElement(int[] arr, int left, int right)
    {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }
   
}
