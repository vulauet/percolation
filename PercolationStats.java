import edu.princeton.cs.algs4.StdRandom; 
import edu.princeton.cs.algs4.StdStats; 
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {
 private double[] results;
 private int time;
 
 public PercolationStats(int N, int T)    // perform T independent experiments on an N-by-N grid
 {
  if (N <= 0 || T <= 0) throw new IllegalArgumentException("Illegal");
  results = new double[T];
  time = T;
  for (int i = 0; i < time; i++) {
   int count = 0;
   Percolation pcl = new Percolation(N);
   while (!pcl.percolates()) {
    int x = StdRandom.uniform(N)+1;
    int y = StdRandom.uniform(N)+1;
    if (!pcl.isOpen(x, y)) {
     pcl.open(x, y);
     count++;
    }
   }
   results[i] = count*1.0/(N*N);
  }
 }

 public double mean()    // sample mean of percolation threshold
 {
  return StdStats.mean(results);
 }

 public double stddev()    // sample standard deviation of percolation threshold
 {
  return StdStats.stddev(results);
 }

 public double confidenceLo()    // low endpoint of 95% confidence interval
 {
  return mean()-1.96*stddev()/Math.sqrt(time);
 }

 public double confidenceHi()    // high endpoint of 95% confidence interval
 {
  return mean()+1.96*stddev()/Math.sqrt(time);
 }

 public static void main(String[] args)    // test client (described below)
 {
  int N, T;
  N = Integer.parseInt(args[0]);
  T = Integer.parseInt(args[1]);
  PercolationStats pcs = new PercolationStats(N, T);
  StdOut.println("mean                    = " + pcs.mean());
  StdOut.println("stddev                  = " + pcs.stddev());
  StdOut.println("95% confidence interval = " + pcs.confidenceLo() + ", " + pcs.confidenceHi());
  
 }
}