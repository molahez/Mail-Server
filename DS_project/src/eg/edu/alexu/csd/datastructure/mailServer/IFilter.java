package eg.edu.alexu.csd.datastructure.mailServer;

import java.io.File;
import java.text.ParseException;
import java.util.Date;

public interface IFilter {
	public void var(Integer fil,String cate,String val);
	public void bsearch (String x,String path) throws ParseException;
	public void read_indexfile (String category ,String path) throws ParseException;

	public void choose_filter(String type, String descrip, String contact);

	public void put_in_filter(String to, String Subject, String contact, String from, String body, String tt,
			String pq); 
	public boolean check_filter(String contact);
	public void copyFolder(File sourceFolder, File destinationFolder);


}
