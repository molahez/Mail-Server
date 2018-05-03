package eg.edu.alexu.csd.datastructure.mailServer.cs03_cs24_cs54;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class Jso {
	public static void main(String[] args) {

        JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(new FileReader("F:\\test.json"));

            JSONObject jsonObject = (JSONObject) obj;
            

            String name = (String) jsonObject.get("name");
            System.out.println(name);

            String loc = (String) jsonObject.get("loc");
            System.out.println(loc);

            // loop array
            JSONArray msg = (JSONArray) jsonObject.get("courses");
            Iterator<String> iterator = msg.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
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

    }


}
