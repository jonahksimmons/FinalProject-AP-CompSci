import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
	public static void main(String[] args){
		Scanner console = new Scanner(System.in);
		String user = "jonah";
		String device = "final-project";

		String fullCommand;
		String command = "";
		String argument =  "";

		ArrayList<String> directory = new ArrayList<String>();
		directory.add("~");
		String currentDir = "~";
		int position = 0;
		
		boolean hasRequestedExit = false;
		boolean hasRequestedExitUm = false;
		do{
			System.out.print(user+"@"+device+":"+currentDir+"$ ");

			fullCommand = console.nextLine();
			if(fullCommand.indexOf(" ") > -1){
				command = fullCommand.substring(0, fullCommand.indexOf(" "));
				argument = fullCommand.substring(fullCommand.indexOf(" ")+1);
			} else { command = fullCommand; }

			
			switch(command){
				case "echo":
				String fileName = argument;
				while(fileName.indexOf(">") > -1){
					fileName = argument.substring(argument.indexOf(">")+2);
				}
				fileName = fileName.substring(1);
				String flag = argument.substring(argument.indexOf(">"));
				flag = flag.substring(0, flag.indexOf(" "));
				argument = argument.substring(0, argument.indexOf(">")-1);
				boolean append = true;
				System.out.println("a-"+argument+"\nf-"+flag+"[");
				append = flag.equals(">>");
				try {
      		FileWriter myWriter = new FileWriter(fileName, append);
      		myWriter.write(argument);
      		myWriter.close();
    			System.out.println("Successfully wrote to the file.");
  			} catch (IOException e) {
    			System.out.println("An error occurred.");
      		e.printStackTrace();
    		}
				break;
				case "cat":
					try {
      			File myObj = new File(argument);
      			Scanner cat = new Scanner(myObj);
      			while (cat.hasNextLine()) {
        			String data = cat.nextLine();
        			System.out.println(data);
      			}
      			cat.close();
    			} catch (FileNotFoundException e) {
      			System.out.println("An error occurred.");
      			e.printStackTrace();
    			}
				break;
				case "rm":
				File myObj = new File(argument);
				if (myObj.delete()) { 
      		System.out.println("Deleted the file: " + myObj.getName());
    		} else {
      		System.out.println("Failed to delete the file.");
    		} 
				break;
				case "clear":
					System.out.print("\033[H\033[2J");  
   				System.out.flush();  
				break;
				case "exit":
					hasRequestedExit = true;
				break;

				default:
					System.out.println(command+": command not found");
				break;
			}
		}while(! hasRequestedExit);
	}
}