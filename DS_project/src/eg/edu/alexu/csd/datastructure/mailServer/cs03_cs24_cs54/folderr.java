package eg.edu.alexu.csd.datastructure.mailServer.cs03_cs24_cs54;

import eg.edu.alexu.csd.datastructure.mailServer.IFolder;

public class folderr implements IFolder {
	
	public static String folderchosen,contname;

	public folderr() {
		folderchosen = null;
		contname = null;
	}
	//name of the folder which is inbox,sent,drafts,...

	@Override
	public void folderChosen(String nameFolder,String cont_name) {
		folderchosen = nameFolder;
		contname = cont_name;
		
	}

	

}
