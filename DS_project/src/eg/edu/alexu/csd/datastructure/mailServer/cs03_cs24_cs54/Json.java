package eg.edu.alexu.csd.datastructure.mailServer.cs03_cs24_cs54;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class Json {

	@SuppressWarnings("unchecked")
	public static void main(String[] args)  {
		JSONObject obj = new JSONObject();
		obj.put("name", "beka");
		obj.put("loc", "usa");
		JSONArray k = new JSONArray ();
		k.add("java");
		k.add("JSP");
		obj.put("courses", k);
		 try (FileWriter file = new FileWriter("F:\\test.json")) {

	            file.write(obj.toString());
	            file.flush();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		 System.out.println(obj);
	}

}
