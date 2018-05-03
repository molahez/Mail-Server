package eg.edu.alexu.csd.datastructure.mailServer;

public interface IContact {
	public boolean check(String email);
	//here we write contacts in json file
	public void write_contact(String email, String pass,String contact_name );
	
}
