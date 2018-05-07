package eg.edu.alexu.csd.datastructure.mailServer;

public interface IFilter {

	public void choose_filter(String type, String descrip, String contact);

	public void put_in_filter(String to, String Subject, String contact, String from, String body, String tt,
			String pq);

}
