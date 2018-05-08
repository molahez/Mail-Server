package eg.edu.alexu.csd.datastructure.mailServer;

import eg.edu.alexu.csd.datastructure.linkedList.cs03_cs10.DLinkedList;

public interface IFilter {
	
	public int bsearch (String x,DLinkedList y);
	public void read_indexfile (String category ,String path);

	public void choose_filter(String type, String descrip, String contact);

	public void put_in_filter(String to, String Subject, String contact, String from, String body, String tt,
			String pq);
	public boolean check_filter(String contact);

}
