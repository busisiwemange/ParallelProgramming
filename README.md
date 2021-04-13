# ParallelProgramming

Parallel Programming with the Java Fork/Join framework: 1D Median filter

Median filtering is a nonlinear digital filtering technique that is often used to remove noise from a data set. Noise reduction is usually a pre-processing step to that improves or “cleans” the data in preparation for later processing. In this method, a median filter slides over the data item by item, generating a new data set, where each item is replaced by the median of neighbouring entries.
The size of the filter determines the number of neighbours considered for the median. In this assignment, you will consider only one-dimensional data sets and filters of odd size, ranging from 3 to 21 data items.

For example, a median filter of size 3 applied to the simple 1D array x: x = [2 80 6 3 1]

produces output array y: y = [2 6 6 3 1]

where each element of y is calculated as follows: y[1] = Median[2 80 6] = 6
 y[2] = Median[80 6 3] = 6
 y[3] = Median[6 3 1] = 3
 
Note that the borders are not changed and that the size of the borders depends on the filter width. In this example the border is 1 element.
The naïve approach to median filtering sorts the elements within the filter window at each step to calculate the median (which will then be the middle element).
Using this naïve approach (which we will do in this assignment) the median computation can be quite expensive, especially if the filter window is large. Therefore, in this assignment you will attempt to parallelize the problem in order to speed it up. You will:
• Use the Java Fork/Join framework to parallelize the median filter operation using a divide-and- conquer algorithm.

• Evaluate your program experimentally:

o Usinghigh-precisiontimingtoevaluatetheruntimesofyourserialandyourparallel method, across a range of input sizes and a range of filter sizes, and
 
o Experimentingwithdifferentparameterstoestablishthelimitsatwhichsequential processing should begin.

Note that parallel programs need to be both correct and faster than the serial versions. Therefore, you need to demonstrate both correctness and speedup for your assignment.

Input and Output

Your program must take the following command-line parameters:
<data file name> <filter size (must be an odd integer >= 3)> <output file name>

You can assume that the data file starts with an integer on a single line, specifying the number of subsequent lines in the file. Successive line contain two numbers separated by spaces:
<line number> <floating point value>
We will provide you with sample input files of different sizes (on Vula), though you may also create your own.
Your program should output the cleaned 1D data set in the same format as the input file.

Benchmarking

You must time the execution of your parallel sorts across a range of data array sizes and number of threads (sequential cutoffs) and report the speedup relative to a serial implementation. The code should be tested on at least two different machine architectures (at least one of which must be a multi-CPU/multi-core machine).
Timing should be done at least 5 times. Use the System.currentTimeMillis method to do the measurements. Timing must be restricted to the median filter code and must exclude the time to read in the files and other unnecessary operations, as discussed in class. Call System.gc()to minimize the likelihood that the garbage collector will run during your execution (but do not call this within your timing block!).
