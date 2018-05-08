package eg.edu.alexu.csd.datastructure.mailServer.cs03_cs24_cs54;

import eg.edu.alexu.csd.datastructure.mailServer.IFolder;

public class folderr implements IFolder {
	
	public static String folderchosen;

	public folderr() {
		folderchosen = null;
	}
	//name of the folder which is inbox,sent,drafts,...

	@Override
	public void folderChosen(String nameFolder) {
		folderchosen = nameFolder;
	}
	

}
