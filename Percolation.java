import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation
{
 private boolean[][] grid;
 private WeightedQuickUnionUF wqu;
 private int size;
 private int opened;
 private boolean percolated;
 private boolean[] full;
 private boolean[] bott;
 
 public Percolation(int N) 
 { 
  if (N <= 0) throw new IllegalArgumentException("N is out of range");
  grid = new boolean[N+1][N+1]; 
  wqu = new WeightedQuickUnionUF((N+1)*(N+1));
  size = N;
  opened = 0;
  full = new boolean[(N+1)*(N+1)];
  bott = new boolean[(N+1)*(N+1)];
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
  if (i == 1) full[j] = true;
  else if (i == size) bott[size*(size-1)+j] = true;
  opened++;
  int index = (i-1)*size+j;
  int f_index = wqu.find(index);
  // if (checkInput(i, j-1) && isOpen(i, j-1) && !wqu.connected(index-1, index)) {
  if (checkInput(i, j-1) && isOpen(i, j-1) && (f_index != wqu.find(index-1))) {
    wqu.union(index-1, index);
    if (full[index-1]) full[index] = true;
    if (bott[index-1]) bott[index] = true;
  }
  // if (checkInput(i, j+1) && isOpen(i, j+1) && !wqu.connected(index+1, index)) {
  if (checkInput(i, j+1) && isOpen(i, j+1) && (f_index != wqu.find(index+1))) {
    wqu.union(index+1, index);  
    if (full[index+1]) full[index] = true;
    if (bott[index+1]) bott[index] = true;
  }
  // if (checkInput(i-1, j) && isOpen(i-1, j) && !wqu.connected(index-size, index)) {
  if (checkInput(i-1, j) && isOpen(i-1, j) && (f_index != wqu.find(index-size))) {
    wqu.union(index-size, index);
    if (full[index-size]) full[index] = true;
    if (bott[index-size]) bott[index] = true;
  }
  // if (checkInput(i+1, j) && isOpen(i+1, j) && !wqu.connected(index+size, index)) {
  if (checkInput(i+1, j) && isOpen(i+1, j) && (f_index != wqu.find(index+size))) {
    wqu.union(index+size, index);
    if (full[index+size]) full[index] = true;
    if (bott[index+size]) bott[index] = true;
  }
  // if (opened >= size && isFull(i, j) && connectToBottom(i, j)) percolated = true;
  if (isFull(i, j) && connectToBottom(i, j)) percolated = true;
 }
 
 public boolean isOpen(int i, int j) 
 {
  if (i < 1 || i > size || j < 1 || j > size) throw new IndexOutOfBoundsException("index out of bounds");
  return grid[i][j]; 
 }
 
 private int dist(int x, int y, int t, int k) {
  return Math.abs(x-t) + Math.abs(y - k)+1;
 }
 
 public boolean isFull(int i, int j) 
 {
  if (i < 1 || i > size || j < 1 || j > size) throw new IndexOutOfBoundsException("index out of bounds");
  if (!isOpen(i, j)) return false;
  if (full[(i-1)*size+j]) return true;
  int f_index = wqu.find((i-1)*size+j);
  for (int k = 1; k <= size; k++) {
   if ((dist(i, j, 1, k) <= opened) && isOpen(1, k) && (f_index == wqu.find(k))) {
    full[(i-1)*size+j] = true;
    return true;
   }  
  } 
  return false;
 }
 
private boolean connectToBottom(int i, int j)
 {
  if (i < 1 || i > size || j < 1 || j > size) throw new IndexOutOfBoundsException("index out of bounds");
  if (!isOpen(i, j)) return false;
  if (bott[(i-1)*size+j]) return true;
  int f_index = wqu.find((i-1)*size+j);
  for (int k = 1; k <= size; k++) {
   if ((dist(i, j, size, k) <= opened) && isOpen(size, k) && (f_index == wqu.find((size-1)*size+k))) {
    bott[(i-1)*size+j] = true;
    return true;
   }
  }
  return false;
 }
 
 public boolean percolates()
 {
  if (opened < size) return false;
  return percolated;
 }
 
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
