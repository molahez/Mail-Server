package eg.edu.alexu.csd.datastructure.mailServer;

import java.io.File;

public interface IMail {
	
	public void var2(String fro, String t, String sub, String email_body);
	public void save_email(String to, String from,String subject, String body, String path);
	public String return_contact(String email);
	public void save_attachement(File x, File y);
}
