package eg.edu.alexu.csd.datastructure.mailServer.cs03_cs24_cs54;

import eg.edu.alexu.csd.datastructure.mailServer.IMail;

public class Mail implements IMail {
	
	String from, to, subject, email_body;
	
	public void var2(String fro, String t, String sub, String email_body) {
		from = fro;
		to = t;
		subject = sub;
		this.email_body = email_body;
	}

}
