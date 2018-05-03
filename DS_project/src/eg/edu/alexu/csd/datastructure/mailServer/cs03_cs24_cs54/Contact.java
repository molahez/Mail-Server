package eg.edu.alexu.csd.datastructure.mailServer.cs03_cs24_cs54;

import eg.edu.alexu.csd.datastructure.linkedList.cs03_cs10.DLinkedList;
import eg.edu.alexu.csd.datastructure.mailServer.IContact;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Contact implements IContact{
	/**
     * @serialField
     */
    static DLinkedList emails = new DLinkedList();
    /**
     * @serialField
     */
    static DLinkedList passwords = new DLinkedList();
    /**
     * @serialField
     */
    static DLinkedList names = new DLinkedList();


	@Override
	public boolean check(String email) {
		// TODO Auto-generated method stub
		String path = "D:\\Users\\mails.txt";
		 
		
		return false;
	}

	@Override
	public void write_contact(String email, String pass, String contact_name) {
		JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(new FileReader("Users/contact.json"));

            JSONObject jsonObject = (JSONObject) obj;
            

            

            // loop array
            //here we load content of json file
            JSONArray col1 = (JSONArray) jsonObject.get("emails");
            JSONArray col2 = (JSONArray) jsonObject.get("pass");
            JSONArray col3 = (JSONArray) jsonObject.get("name");
            Iterator<String> iterator1 = col1.iterator();
            Iterator<String> iterator2 = col2.iterator();
            Iterator<String> iterator3 = col3.iterator();
            while (iterator1.hasNext()) {
            	emails.add(iterator1.next());
                
            }
            while (iterator2.hasNext()) {
            	passwords.add(iterator2.next());
                
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
        }
        catch (Exception e) {
            e.printStackTrace();
        }
		JSONObject obj1 = new JSONObject();
		JSONArray k1 = new JSONArray ();
		JSONArray k2 = new JSONArray ();
		JSONArray k3 = new JSONArray ();
		k1.add(email);
		k2.add(pass);
		k3.add(contact_name);
		obj1.put("emails", k1);
		obj1.put("pass", k2);
		obj1.put("name", k3);
	}
	
	

}
