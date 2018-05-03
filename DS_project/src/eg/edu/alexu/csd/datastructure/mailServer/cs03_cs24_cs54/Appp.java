package eg.edu.alexu.csd.datastructure.mailServer.cs03_cs24_cs54;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import eg.edu.alexu.csd.datastructure.linkedList.ILinkedList;
import eg.edu.alexu.csd.datastructure.linkedList.cs03_cs10.DLinkedList;
import eg.edu.alexu.csd.datastructure.mailServer.IApp;
import eg.edu.alexu.csd.datastructure.mailServer.IContact;
import eg.edu.alexu.csd.datastructure.mailServer.IFilter;
import eg.edu.alexu.csd.datastructure.mailServer.IFolder;
import eg.edu.alexu.csd.datastructure.mailServer.IMail;
import eg.edu.alexu.csd.datastructure.mailServer.ISort;

public class Appp  implements IApp{
	/**
     * @serialField
     */
    static DLinkedList fol = new DLinkedList();
    //Function used to read the data of jtree in gui which displays the content of folders
	static DLinkedList read() throws IOException {
		JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(new FileReader("F:\\test.json"));

            JSONObject jsonObject = (JSONObject) obj;
            

            

            // loop array
            JSONArray msg = (JSONArray) jsonObject.get("Folders");
            Iterator<String> iterator = msg.iterator();
            while (iterator.hasNext()) {
            	fol.add(iterator.next());
               
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
        return fol;
	}
	@SuppressWarnings("unchecked")
	 static void write(DLinkedList x)  {
		JSONObject obj = new JSONObject();
		
		JSONArray k = new JSONArray ();
		for(int i = 0;i<x.size();i++) {
			k.add(x.get(i));
		}
		
		obj.put("Folders", k);
		 try (FileWriter file = new FileWriter("F:\\test.json")) {

	            file.write(obj.toString());
	            file.flush();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		 
	}


	@Override
	public boolean signin(String email, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean signup(IContact contact) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setViewingOptions(IFolder folder, IFilter filter, ISort sort) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IMail[] listEmails(int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteEmails(ILinkedList mails) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveEmails(ILinkedList mails, IFolder des) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean compose(IMail email) {
		// TODO Auto-generated method stub
		return false;
	}

}