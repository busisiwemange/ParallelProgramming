/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package assign1;

import java.io.*;
import java.util.*;


/**
 *
 * @author mngbus006
 */
public class Serial
 {

    /**
     * @param args the command line arguments
     */
    static long startTime = 0;
	
	private static void tick(){
		startTime = System.currentTimeMillis();
	}
	private static float toc(){
		return (System.currentTimeMillis() - startTime) / 1000.0f; 
	}

    public static void main(String[] args) throws IOException{
       //* taking the input for the datafile name,filtersize and outputfilename  and assignning to variables
         Scanner in = new Scanner(System.in);
         String inpt = in.nextLine();
         String[] inpt1 = inpt.split(" ");
         String dataFileName  = inpt1[0];
         int f_size  = Integer.parseInt(inpt1[1]);
         String outputFileName  = inpt1[2];
         File f   = null;
         
         FileReader fr = null;  
         BufferedReader br = null;
         BufferedWriter writer = null; 
         
         try{ f   = new File(dataFileName);
         
         fr = new FileReader(f);  
         br = new BufferedReader(fr);   //reading the text file
         
         //creating a new file to write to
         writer = new BufferedWriter(new FileWriter(outputFileName)); 
         }
         catch(FileNotFoundException e)
        {
         System.out.println("Error, file not found");
         System.exit(0);
         }

         int filter =f_size;
         int width = (filter-1)/2;
         
         ArrayList<Double> inputArray= new ArrayList<Double>();
         ArrayList<Double> outputArray= new ArrayList<Double>();
         ArrayList<Double> fElements= new ArrayList<Double>();        //stores the elements of size filter
        
         int mid = (filter+1)/2; 
        
         
         //reading the text file and populating x (input) array
        String line  = br.readLine();
        int c =0;
        while ((line=br.readLine()) != null) {
            String value  = line.substring(line.indexOf(" ")+1);
            c+=1;
            double input = Double.parseDouble(value);
            //if(c<100000+1)             //differentiating array size
            inputArray.add(input);
           }
        fr.close();
        
        tick();
        // adding the start boundary
        for(int i = 0; i<width;i++){
            outputArray.add(inputArray.get(i));
        }
        
         //getting the elements in the array of size filter by visiting each element 
          for(int i = 0; i< inputArray.size()-filter+1;i++)
         {  
             for (int z = i;z<i+filter;z++){
               fElements.add(inputArray.get(z));
             }
            // System.out.println(fElements);                   // print out the filtered elements
             Collections.sort(fElements);                       // sorting the filtered elements to be able to get the correct median
            
             outputArray.add(fElements.get(mid-1));             // adding median elements to output array
             fElements.clear();                                 // clear the f elements list for the next elements
             
         }
        
       // adding the end boundary
         for(int i = inputArray.size()-width; i<inputArray.size();i++){     
            outputArray.add(inputArray.get(i));
        }
       float time = toc();
       
       int elements = 0;    //to store the numer of lines in the file
       for(int e= 0;e<outputArray.size();e++){
         outputArray.get(e);
         elements ++;
       
       }
       // printing the output to outputFileName file
       int count = 0;
       writer.write(elements);

       for(int i = 0 ; i<outputArray.size();i++){
       count ++;
       writer.write(count+" "+outputArray.get(i));
       //System.out.println(count+" "+outputArray.get(i));
       
       } 
              System.out.println("Run took "+ time +" seconds");
       }
       
    }
    

    

