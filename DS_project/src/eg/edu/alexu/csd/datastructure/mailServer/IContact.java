package eg.edu.alexu.csd.datastructure.mailServer;

public interface IContact {
	//here we check whether email is in our database
	public boolean check(String email);
	//here we check whether email and password are correct
	public boolean check(String email, String password);
	//here we write contacts in json file
	public void write_contact(String email, String pass,String contact_name );
	//constructor	
	public void var (String emai, String passwor, String contact_nam); 
	//
	public void var1 (String emai, String passwor);

}
