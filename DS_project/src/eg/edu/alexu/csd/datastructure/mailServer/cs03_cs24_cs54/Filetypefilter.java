package eg.edu.alexu.csd.datastructure.mailServer.cs03_cs24_cs54;

import java.io.File;
import java.nio.file.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;


public class Filetypefilter  extends FileFilter{
	
	private final String extension;
	private final String description;
	
	public Filetypefilter(String ext, String des) {
	    this.extension = ext;
	    this.description = des;
	}

	@Override
	public boolean accept(File file) {
		if (file.isDirectory()) {
		return true;}
		return file.getName().endsWith(extension);
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return description + String.format("(*%s)", extension);
	}
	


}
