import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation
{
 private boolean[][] grid;
 private WeightedQuickUnionUF wqu;
 private int size;
 
 public Percolation(int N) 
 { 
  if (N <= 0) throw new IllegalArgumentException("N is out of range");
  grid = new boolean[N+1][N+1]; 
  wqu = new WeightedQuickUnionUF((N+1)*(N+1));
  size = N;
 }
 
 private boolean checkInput(int i, int j) 
 {
  if (i < 1 || i > size || j < 1 || j > size) return false;
  return true;
 }
 
 public void open(int i, int j) 
 {
  if (i < 1 || i > size || j < 1 || j > size) throw new IndexOutOfBoundsException("index out of bounds");
  grid[i][j] = true;  
  int index = (i-1)*size+j;
  if (i == 1) wqu.union(index, 0);
  if (i == size) wqu.union(index, size*size+1);
  if (checkInput(i, j-1) && isOpen(i, j-1) && !wqu.connected(index-1, index))
    wqu.union(index-1, index);
  if (checkInput(i, j+1) && isOpen(i, j+1) && !wqu.connected(index+1, index))
    wqu.union(index+1, index);  
  if (checkInput(i-1, j) && isOpen(i-1, j) && !wqu.connected(index-size, index))
    wqu.union(index-size, index);
  if (checkInput(i+1, j) && isOpen(i+1, j) && !wqu.connected(index+size, index))
    wqu.union(index+size, index);
 }
 
 public boolean isOpen(int i, int j) 
 {
  if (i < 1 || i > size || j < 1 || j > size) throw new IndexOutOfBoundsException("index out of bounds");
  return grid[i][j]; 
 }
 
 public boolean isFull(int i, int j) 
 {
  if (i < 1 || i > size || j < 1 || j > size) throw new IndexOutOfBoundsException("index out of bounds");
  return isOpen(i, j) && wqu.connected(0, (i-1)*size+j);
 }
 
 public boolean percolates()
 {  return wqu.connected(0, size*size+1); }
 
 // public static void main(String[] args)
 // {
  // Perculation pcl = new Perculation(4);
  // // while (!StdIn.isEmpty())
  // // {
   // // int p = StdIn.readInt();
   // // int q = StdIn.readInt();
   // // if (!pcl.isOpen(p, q)) {
    // // pcl.open(p, q);
    // // StdOut.println(p + " " + q);
   // // }
  // // }
  // pcl.open(1,1);
  // pcl.open(2,1);
  // pcl.open(3,1);
  // pcl.open(4,1);
  // if (pcl.percolates()) StdOut.println("Success");
  // else StdOut.println("Try Again");
 // }
}
