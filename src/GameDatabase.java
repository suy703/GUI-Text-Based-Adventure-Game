import java.io.File;
import java.util.Scanner;

public class GameDatabase {

	private Scanner file;
	public String[] readFile;
	
	public GameDatabase(String iniFile) {
		openFile(iniFile);
	}
	//OPEN FILE
	public void openFile(String txtFile) {
		try{
			file = new Scanner(new File(txtFile));
		}
		catch(Exception e) {
			System.out.println("Could not find file.");
		}
	}
	//READ FILE
	public String[] readFile() {
		String addLine = "";
		while(file.hasNextLine()) {
			addLine = addLine + file.nextLine();
		}
		this.readFile = addLine.split("  ");
		return this.readFile;
	}
	//CLOSE FILE
	public void closeFile() {
		file.close();
	}
}
