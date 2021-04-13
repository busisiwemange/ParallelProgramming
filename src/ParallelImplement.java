//package ForkJoinDC;
import java.io.*;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import  java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ParallelImplement {
	static long startTime = 0;
	
	private static void tick(){
		startTime = System.currentTimeMillis();
	}
	private static float toc(){
		return (System.currentTimeMillis() - startTime) / 1000.0f; 
	}
	static final ForkJoinPool fjPool = new ForkJoinPool();
	static ArrayList<Double> sum(ArrayList<Double> arr,int f_size){
	  return fjPool.invoke(new Parallel(arr,0,arr.size(),f_size));      // parsing input array ,first value of the array and the last value 
	}

	
	public static void main(String[] args) throws IOException {
		
	//* taking the input for the datafile name,filtersize and outputfilename  and assignning to variables
         Scanner in = new Scanner(System.in);
         String inpt = in.nextLine();
         String[] inpt1 = inpt.split(" ");
         String dataFileName  = inpt1[0];
         int f_size  = Integer.parseInt(inpt1[1]);
         String outputFileName  = inpt1[2];
         File f= null;
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

      
         //reading the text file and populating the input array
        ArrayList<Double> inputArray= new ArrayList<Double>();
        String line  = br.readLine();
        int c = 0;
        while ((line=br.readLine()) != null) {
       
            String value  = line.substring(line.indexOf(" ")+1);
            c +=1;
            double input = Double.parseDouble(value);
            if(c<100000+1)             //differentiating array size
            inputArray.add(input);
           }
           System.gc();
        fr.close();
        
		
	   tick();
      ArrayList<Double> sumArr = sum(inputArray,f_size);
      float time = toc();
		
      int elements = 0;    //to store the numer of lines in the file
       for(int e= 0;e<sumArr.size();e++){
         sumArr.get(e);
         elements ++;
       
       }
       // printing the output to outputFileName file
       int count = 0;
       writer.write(elements);

       for(int i = 0 ; i<sumArr.size();i++){
       count ++;
       writer.write(count+" "+sumArr.get(i));
       //System.out.println(count+" "+outputArray.get(i));
       
       } 
         System.out.println("Run took "+ time +" seconds");
		
	}
}

