package ocp.nio;
import java.util.Arrays;
import java.io.*;

public class FileTest01{
  public static void main(String args[]){
   //by default and empty array is passed to args
   //System.out.println("Input args:" + Arrays.toString(args));

   String fileName = "test01.txt";
   /*
   checkFileExistance(fileName);
   createNewFile(fileName);
   checkFileExistance(fileName);
   deleteFile(fileName);
   */
   //deleteFile(fileName); // calling delete twice doesn't throw any exception
   rwCharsWithReaderWriter(fileName);
 }

 public static void checkFileExistance(String fileName){
   File file = new File(fileName); //created object of file representation
   System.out.println("File " + fileName + " exists?:" + file.exists());
 }

 public static void createNewFile(String fileName){
   File file = new File(fileName);
   boolean isNewFileCreated = false;
 
   try{
	isNewFileCreated = file.createNewFile();
   }
   catch(IOException ex){
     System.out.println("Error creating file " + fileName);
   }
   System.out.println("New file \"" + fileName + "\" created? " + isNewFileCreated);
 }

 public static void deleteFile(String fileName){
   //Note: no checked exception on delete. 
   // however Files.delete(Path path) throws IOException
   File file = new File(fileName);
   boolean isFileDeleted = false;
   isFileDeleted = file.delete();
   System.out.println("Is file \"" + fileName + "\" deleted? " + isFileDeleted);
 }

 public static void deleteAllFilesInADirectory(String dirName){
   
 }

 public static void deleteTheDirectoryIncludingFiles(){

 }

 public static void rwCharsWithReaderWriter(String fileName){
   File file = new File(fileName);
   char in[] = new char[50];
   try{
     FileWriter fwriter = new FileWriter(file); // creates file if not there 
     fwriter.write("Hello!" + System.lineSeparator() + "Good Morning!");
     fwriter.flush();
     fwriter.close();
     FileReader reader = new FileReader(file); // doesn't create a new file, throws FileNotFoundException if file doesn't exist
     int size = reader.read(in);
     System.out.println("Size =" + size);
     for(char c: in){
       System.out.print(c);
     }

    reader.close();
   }
   catch(IOException ex){
	System.out.println("Exception while writing to the file " + fileName);
          
   }
 }

 public static void rwBytesWithIOStreams(){

 }

}
