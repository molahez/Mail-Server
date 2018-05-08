package eg.edu.alexu.csd.datastructure.mailServer;

import eg.edu.alexu.csd.datastructure.linkedList.cs03_cs10.DLinkedList;

public interface IFilter {
	
	public int bsearch (String x,DLinkedList y);
	public void read_indexfile (String category ,String path);

}
