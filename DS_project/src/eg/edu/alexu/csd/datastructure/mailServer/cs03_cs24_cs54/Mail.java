package eg.edu.alexu.csd.datastructure.mailServer.cs03_cs24_cs54;

import eg.edu.alexu.csd.datastructure.mailServer.IMail;

public class Mail implements IMail {
	
	public static String from, to, subject, email_body;
	
	public Mail() {
		from = null;
		to = null;
		subject = null;
		email_body = null;
	}
	
	public void var2(String fro, String t, String sub, String email_body) {
		from = fro;
		to = t;
		subject = sub;
		Mail.email_body = email_body;
	}

}
