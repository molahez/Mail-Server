package eg.edu.alexu.csd.datastructure.mailServer;

public interface IMail {
	
	public void var2(String fro, String t, String sub, String email_body);
	public void save_email(String to, String from,String subject, String body );

}
