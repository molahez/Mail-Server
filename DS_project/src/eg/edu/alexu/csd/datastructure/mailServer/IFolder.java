package eg.edu.alexu.csd.datastructure.mailServer;

public interface IFolder {
	
     
	//name of the folder which is inbox,sent,drafts,...
	void folderChosen(String nameFolder, String cont_name);
	public void pat(String path);
}