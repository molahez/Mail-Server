package eg.edu.alexu.csd.datastructure.mailServer;

import java.io.File;

public interface IMail {
	
	public void var2(String fro, String t, String sub, String email_body,String tt, String pq);
	public void var3(String fro, String t, String sub, String email_body, String tt, String pq,String o);
	public void save_email(String to, String from,String subject, String body, String path,String tt, String pq);
	public String return_contact(String email);
	public void save_attachement(File x, File y);
	public int order(String Path);
	public int order1(String path);
	public void move_attachment(String contact, String email_to);
	public void directory_contents(String contact, String email_to);
	
}
