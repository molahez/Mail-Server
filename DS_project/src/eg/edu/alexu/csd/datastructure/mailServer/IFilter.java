package eg.edu.alexu.csd.datastructure.mailServer;

public interface IFilter {
	public void var(Integer fil,String cate,String val);
	public void bsearch (String x,String path);
	public void read_indexfile (String category ,String path);

	public void choose_filter(String type, String descrip, String contact);

	public void put_in_filter(String to, String Subject, String contact, String from, String body, String tt,
			String pq);
	public boolean check_filter(String contact);

}
