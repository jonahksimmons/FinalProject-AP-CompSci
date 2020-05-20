import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
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
		System.out.print("\u001B[93m");//orange
		System.out.println("run 'help' for list of commands\n");
		System.out.print("\u001B[0m");//reset color
		do{
			System.out.print("\u001B[92m"+user+"@"+device);//green
			System.out.print("\u001B[0m"+":");//white
			System.out.print("\u001B[94m"+currentDir);//blue
			System.out.print("\u001B[0m"+"$ ");//reset color

			fullCommand = console.nextLine();
			if(fullCommand.indexOf(" ") > -1){
				command = fullCommand.substring(0, fullCommand.indexOf(" "));
				argument = fullCommand.substring(fullCommand.indexOf(" ")+1);
			} else { command = fullCommand; }
			String fileName = currentDir.substring(1)+"/"+argument;

			
			switch(command){
				case "echo":
				fileName = argument;
				int ii = 0;
				fileName = argument.substring(argument.indexOf(">")+1);
				fileName = fileName.substring(fileName.indexOf(" ")+1);
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
      			System.out.println("File not found: "+fileName);
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
					System.out.println(currentDir);
				break;
				case "ls":
					String[] pathnames;
        	File f = new File("/home/runner/FinalProject/"+currentDir);
	        pathnames = f.list();
  	      for (String pathname : pathnames) {
						if(pathname.indexOf(".") == -1){
							System.out.print("\u001B[94m");//blue
						} else{
							System.out.print("\u001B[36m");//aqua
						}
    	      System.out.print(pathname+"      ");
						System.out.print("\u001B[0m");//reset color
      	  }
					System.out.print("\n");
				break;
				case "cd":
					if(argument.equals("..")){
						int i = currentDir.length()-1;
						while(currentDir.charAt(i) != '/'){
							i--;
						}
						currentDir = currentDir.substring(0,i);
					} else{
						boolean moved = false;
        		File fil = new File("/home/runner/FinalProject/"+currentDir);
	        	pathnames = fil.list();
						for(String paths : pathnames){
							if(paths.equals(argument)){
								currentDir += "/"+argument;
								moved = true;
							}
						}
						if(!moved){
							System.out.println("Directory does not exsist");
						}
					}
				break;
				case "mkdir":
					File file = new File(fileName);
      		//returns true if it is created
      		if(file.mkdir()){
         		System.out.println("Directory created: "+fileName);
      		}else{
         		System.out.println("Couldn’t create directory: "+fileName);
      		}
				break;
				case "rmdir":
					File rmDir = new File(fileName);
    		if(!rmDir.exists()){
           	System.out.println("Directory does not exist: "+fileName);
           	System.exit(0);
        	}else{
           	if(rmDir.delete()){
							System.out.println("Removed directory: "+fileName);
						} else{
							System.out.println("Failed to remove directory: "+fileName);
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
      			System.out.println("An error occurred");
      			e.printStackTrace();
    			}
				break;
				case "about":
					try {
      			File myOb = new File("ABOUT.txt");
      			Scanner cat = new Scanner(myOb);
      			while (cat.hasNextLine()) {
        			String data = cat.nextLine();
        			System.out.println(data);
      			}
      			cat.close();
    			} catch (FileNotFoundException e) {
      			System.out.println("An error occurred");
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