package component;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class FileChooser extends JFileChooser{

	private static final long serialVersionUID = 674130564998187763L;
	
	public FileChooser(JFrame parent) {
		
		this.setCurrentDirectory(new File("."));
		this.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		this.setDialogTitle("Salveaza raport activitate");
	}
	
}
