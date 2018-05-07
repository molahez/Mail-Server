package eg.edu.alexu.csd.datastructure.mailServer.cs03_cs24_cs54;

import java.io.File;

public class Filter {

	public void choose_filter(String type, String descrip, String contact) {
		String path = "Users/" + contact + "/Filterd mails/";
		Contact y = new Contact();

		if (type == "Sender") {
			File x = new File(path + "Sender");

			if (x.exists()) {
				x = new File(path + "Sender/" + descrip);
				x.mkdirs();
				y.create_file(path + "Sender/" + descrip);
			} else {
				x.mkdirs();
				x = new File(path + "Sender/" + descrip);
				x.mkdirs();
				y.create_file(path + "Sender/" + descrip);
			}
		} else if (type == "Subject") {
			File x = new File(path + "Subject");

			if (x.exists()) {
				x = new File(path + "Subject/" + descrip);
				x.mkdirs();
				y.create_file(path + "Subject/" + descrip);
			} else {
				x.mkdirs();
				x = new File(path + "Subject/" + descrip);
				x.mkdirs();
				y.create_file(path + "Subject/" + descrip);
			}
		} else if (type == "Sender & Subject") {
			File x = new File(path + "Sender & Subject");
			if (x.exists()) {
				x = new File(path + "Sender & Subject/" + descrip);
				x.mkdirs();
				y.create_file(path + "Sender & Subject/" + descrip);
			} else {
				x.mkdirs();
				x = new File(path + "Sender & Subject/" + descrip);
				x.mkdirs();
				y.create_file(path + "Sender & Subject/" + descrip);
			}
		}
	}

	public boolean check_filter(String contact) {
		String path = "Users/" + contact + "/Filterd mails";
		File x = new File(path);

		if (x.list().length > 0) {
			return true;
		} else {
			return false;
		}
	}

	public void put_in_filter(String to, String Subject, String contact, String from, String body, String tt,
			String pq) {
		String path = "Users/" + contact + "/Filterd mails";
		File x = new File(path);
		int flag = 1;
		Mail y = new Mail();

		while (flag == 1) {
			path = path + "/Sender";
			File[] files = x.listFiles();
			for (File file : files) {
				if (from == file.getName()) {
					path = path + "/" + file.getName() + "/Index file.json";
					y.save_email(to, from, Subject, body, path, tt, pq);
					String path1 = "Users/" + contact + "/Inbox/Index file.json";
					File x1 = new File(path1);
					File[] files1 = x1.listFiles();
					int n = y.order1(path1) - 1;
					for (File file1 : files1) {
						if (Integer.toString(n) == file1.getName()) {
							file1.renameTo(new File("Users/" + contact + "/Sender/Filter mails/" + file.getName() + "/"
									+ Integer.toString(y.order1(path))));
							break;
						}
					}

					flag = 0;
					break;
				}
			}
			if (flag == 0) {
				break;
			} else if (flag == 1) {
				flag = 2;
			}
		}
		while (flag == 2) {
			path = path + "/Subject";
			File[] files = x.listFiles();
			for (File file : files) {
				if (Subject == file.getName()) {
					path = path + "/" + file.getName() + "/Index file.json";
					y.save_email(to, from, Subject, body, path, tt, pq);
					String path1 = "Users/" + contact + "/Inbox/Index file.json";
					File x1 = new File(path1);
					File[] files1 = x1.listFiles();
					int n = y.order1(path1) - 1;
					for (File file1 : files1) {
						if (Integer.toString(n) == file1.getName()) {
							file1.renameTo(new File("Users/" + contact + "/Subject/Filter mails/" + file.getName() + "/"
									+ Integer.toString(y.order1(path))));
							break;
						}
					}

					flag = 0;
					break;
				}
			}
			if (flag == 0) {
				break;
			} else if (flag == 2) {
				flag = 3;
			}
		}
		while (flag == 3) {
			path = path + "/Sender & Subject";
			File[] files = x.listFiles();
			for (File file : files) {
				if (Subject == file.getName()) {
					path = path + "/" + file.getName() + "/Index file.json";
					y.save_email(to, from, Subject, body, path, tt, pq);
					String path1 = "Users/" + contact + "/Inbox/Index file.json";
					File x1 = new File(path1);
					File[] files1 = x1.listFiles();
					int n = y.order1(path1) - 1;
					for (File file1 : files1) {
						if (Integer.toString(n) == file1.getName()) {
							file1.renameTo(new File("Users/" + contact + "/Sender & Subject/Filter mails/" + file.getName() + "/"
									+ Integer.toString(y.order1(path))));
							break;
						}
					}

					flag = 0;
					break;
				}
			}
			break;
		}

	}

}