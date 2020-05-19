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

		String currentDir = "/Home";
		
		boolean hasRequestedExit = false;
		System.out.println("v20.05.19");
		System.out.println("'help' for list of commands\n");
		do{
			System.out.print(user+"@"+device+":"+currentDir+"$ ");

			fullCommand = console.nextLine();
			if(fullCommand.indexOf(" ") > -1){
				command = fullCommand.substring(0, fullCommand.indexOf(" "));
				argument = fullCommand.substring(fullCommand.indexOf(" ")+1);
			} else { command = fullCommand; }
			String fileName = currentDir.substring(1)+"/"+argument;

			
			switch(command){
				case "echo":
				fileName = argument;
				while(fileName.indexOf(">") > -1){
					fileName = argument.substring(argument.indexOf(">")+2);
				}
				fileName = fileName.substring(1);
				fileName = currentDir.substring(1)+"/"+fileName;
				String flag = argument.substring(argument.indexOf(">"));
				flag = flag.substring(0, flag.indexOf(" "));
				argument = argument.substring(0, argument.indexOf(">")-1);
				boolean append = true;
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
      			File myObj = new File(fileName);
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
				File myObj = new File(fileName);
				if (myObj.delete()) { 
      		System.out.println("Deleted the file: " + myObj.getName());
    		} else {
      		System.out.println("Failed to delete the file.");
    		} 
				break;
				case "pwd":
					String cwd = System.getProperty("user.dir");
					System.out.println(cwd+currentDir);
				break;
				case "ls":
					String[] pathnames;
        	File f = new File("/home/runner/FinalProject/"+currentDir);
	        pathnames = f.list();
  	      for (String pathname : pathnames) {
    	        System.out.println(pathname);
      	  }
				break;
				case "cd":
					currentDir += "/"+argument;
				break;
				case "mkdir":
					File file = new File(fileName);
      		//Creating the directory
      		boolean bool = file.mkdir();
      		if(bool){
         		System.out.println("Directory created successfully");
      		}else{
         		System.out.println("Sorry couldn’t create specified directory");
      		}
				break;
				case "rmdir":
					File rmDir = new File(fileName);
    		if(!rmDir.exists()){
           	System.out.println("Directory does not exist.");
           	System.exit(0);
        	}else{
           	if(rmDir.delete()){
							System.out.println("removed");
						} else{
							System.out.println("failed");
						}
        	}
				break;
				case "help":
					try {
      			File myOb = new File("HELP.txt");
      			Scanner cat = new Scanner(myOb);
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
		console.close();
	}
}