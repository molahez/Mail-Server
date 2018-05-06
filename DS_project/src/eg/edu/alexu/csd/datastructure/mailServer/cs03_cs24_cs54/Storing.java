package eg.edu.alexu.csd.datastructure.mailServer.cs03_cs24_cs54;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import eg.edu.alexu.csd.datastructure.linkedList.cs03_cs10.DLinkedList;
import eg.edu.alexu.csd.datastructure.mailServer.ISort;
import eg.edu.alexu.csd.datastructure.stack.cs03.MyStack;

public class Storing {
	
	
	
	public  int[] Order_Des() {
		DLinkedList allOrders = new DLinkedList();
		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(new FileReader("Users/"+Contact.contact_name+"/Inbox/Index file.json"));
                  
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
              arr[i]=(Integer.parseInt(allOrders.get(i).toString()));
		}
		
		quick_sort(arr);
		
		return arr;
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
