//package ForkJoinDC;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Parallel extends RecursiveTask<ArrayList<Double>>  {
	  int lo; // arguments
	  int hi;
     static int filter;
     ArrayList<Double> inputArr;                        //input array with all elements before median filter 
     static final int SEQUENTIAL_CUTOFF=650000;
     ;
     int width = (filter-1)/2;
       
	  Parallel(ArrayList<Double> a, int l, int h,int f) { 
	    inputArr =a; 
       lo = l; 
       hi = h;
       filter = f;
           
	  }

  protected ArrayList<Double> compute(){// return answer - instead of run
		  if((hi-lo) < SEQUENTIAL_CUTOFF) {
        ArrayList<Double> outputArray= new ArrayList<Double>();
	     ArrayList<Double> fElements= new ArrayList<Double>(filter);                   //stores the elements of size filter
        int mid = (filter+1)/2; 
        
		 // adding the start boundary
        for(int i = 0; i<width;i++){
            outputArray.add(inputArr.get(i));
        }
        
         //getting the elements in the array of size filter by visiting each element 
          for(int i = 0; i< inputArr.size()-filter+1;i++)
         {  
             for (int z = i;z<i+filter;z++){
               fElements.add(inputArr.get(z));
             }
            // System.out.println(fElements);                   // print out the filtered elements
             Collections.sort(fElements);                       // sorting the filtered elements to be able to get the correct median
            
             outputArray.add(fElements.get(mid-1));             // adding median elements to output array
             fElements.clear();                                 // clear the f elements list for the next elements
             
         }
        
       // adding the end boundary
         for(int i = inputArr.size()-width; i<inputArr.size();i++){     
            outputArray.add(inputArr.get(i));
        }
         return outputArray;
        
       }
       
		 else {
		        Parallel left = new Parallel(inputArr,lo,(hi+lo)/2,filter);
		        Parallel right= new Parallel(inputArr,(hi+lo)/2,hi,filter);
		        // order of next 4 lines
		       // essential � why?
		       left.fork();
		       ArrayList<Double> rightAns = right.compute();
		       ArrayList<Double> leftAns  = left.join(); 
		       leftAns.addAll(rightAns);
             return leftAns;     
		    }
		  
        }

	  }


