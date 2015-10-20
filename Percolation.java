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
  // if (i == 1) full[j] = true;
  // else if (i == size) bott[size*(size-1)+j] = true;
  opened++;
  int index = (i-1)*size+j;
  if (i == 1) wqu.union(index, 0);
  else if (i == size) wqu.union(index, size*size+1);
  // int f_index = wqu.find(index);
  if (checkInput(i, j-1) && isOpen(i, j-1) && !wqu.connected(index-1, index)) {
  // if (checkInput(i, j-1) && isOpen(i, j-1) && (f_index != wqu.find(index-1))) {
    wqu.union(index-1, index);
    // if (full[index-1]) full[index] = true;
    // if (bott[index-1]) bott[index] = true;
  }
  if (checkInput(i, j+1) && isOpen(i, j+1) && !wqu.connected(index+1, index)) {
  // if (checkInput(i, j+1) && isOpen(i, j+1) && (f_index != wqu.find(index+1))) {
    wqu.union(index+1, index);  
    // if (full[index+1]) full[index] = true;
    // if (bott[index+1]) bott[index] = true;
  }
  if (checkInput(i-1, j) && isOpen(i-1, j) && !wqu.connected(index-size, index)) {
  // if (checkInput(i-1, j) && isOpen(i-1, j) && (f_index != wqu.find(index-size))) {
    wqu.union(index-size, index);
    // if (full[index-size]) full[index] = true;
    // if (bott[index-size]) bott[index] = true;
  }
  if (checkInput(i+1, j) && isOpen(i+1, j) && !wqu.connected(index+size, index)) {
  // if (checkInput(i+1, j) && isOpen(i+1, j) && (f_index != wqu.find(index+size))) {
    wqu.union(index+size, index);
    // if (full[index+size]) full[index] = true;
    // if (bott[index+size]) bott[index] = true;
  }
  // if (opened >= size && isFull(i, j) && connectToBottom(i, j)) percolated = true;
  // if (full[index]) update_full(i, j);
  // if (bott[index]) update_bott(i, j);
  // if (isFull(i, j) && connectToBottom(i, j)) percolated = true;
  // if (full[index] && bott[index]) percolated = true;
 }
 
 private void update_full(int i, int j) {
  int index = (i-1)*size + j;
  // int left = j-1;
  // int right = j+1;
  // int up = i-1;
  // int down = i+1;
  // boolean flag1 = false;
  // boolean flag2 = false;
  // boolean flag3 = false;
  // boolean flag4 = false;
  
  // while (checkInput(i, left) && isOpen(i, left) && !full[index-(j-left)]) {
   // full[index-(j-left)] = true;
   // left--;
   // flag1 = true;
  // }

  // while (checkInput(i, right) && isOpen(i, right) && !full[index+(right-j)]) {
   // full[index+(right-j)] = true;
   // right++;
   // flag2 = true;
  // } 
  
  // while (checkInput(up, j) && isOpen(up, j) && !full[index-(i-up)*size]) {
   // full[index-(i-up)*size] = true;
   // up--;
   // flag3 = true;
  // }

  // while (checkInput(down, j) && isOpen(down, j) && !full[index+(down-i)*size]) {
   // full[index+(down-i)*size] = true;
   // down++;
   // flag4 = true;
  // }
  // left = j-1;
  // right = j+1;
  // up = i-1;
  // down = i+1;
  // if (flag1) while(checkInput(i, left)) update_full(i, left--);
  // if (flag2) while(checkInput(i, right)) update_full(i, right++);
  // if (flag3) while(checkInput(up, j)) update_full(up--, j);
  // if (flag4) while(checkInput(down, j)) update_full(down++, j);
  if (checkInput(i-1, j) && isOpen(i-1, j) && !full[index-size]) {
   full[index-size] = true;
   update_full(i-1, j);   
  }
  if (checkInput(i, j-1) && isOpen(i, j-1) && !full[index-1]) {
   full[index-1] = true;
   update_full(i, j-1);   
  } 
  if (checkInput(i, j+1) && isOpen(i, j+1) && !full[index+1]) {
   full[index+1] = true;
   update_full(i, j+1);   
  }
  if (checkInput(i+1, j) && isOpen(i+1, j) && !full[index+size]) {
   full[index+size] = true;
   update_full(i+1, j);   
  } 
 }
 
 private void update_bott(int i, int j) {
  int index = (i-1)*size + j;
  // int left = j-1;
  // int right = j+1;
  // int up = i-1;
  // int down = i+1;
  // boolean flag1 = false;
  // boolean flag2 = false;
  // boolean flag3 = false;
  // boolean flag4 = false;
  
  // while (checkInput(i, left) && isOpen(i, left) && !bott[index-(j-left)]) {
   // bott[index-(j-left)] = true;
   // left--;
   // flag1 = true;
  // }

  // while (checkInput(i, right) && isOpen(i, right) && !bott[index+(right-j)]) {
   // bott[index+(right-j)] = true;
   // right++;
   // flag2 = true;
  // } 
  
  // while (checkInput(up, j) && isOpen(up, j) && !bott[index-(i-up)*size]) {
   // bott[index-(i-up)*size] = true;
   // up--;
   // flag3 = true;
  // }

  // while (checkInput(down, j) && isOpen(down, j) && !bott[index+(down-i)*size]) {
   // bott[index+(down-i)*size] = true;
   // down++;
   // flag4 = true;
  // }
  // left = j-1;
  // right = j+1;
  // up = i-1;
  // down = i+1; 
  // if (flag1) while(checkInput(i, left)) update_bott(i, left--);
  // if (flag2) while(checkInput(i, right)) update_bott(i, right++);
  // if (flag3) while(checkInput(up, j)) update_bott(up--, j);
  // if (flag4) while(checkInput(down, j)) update_bott(down++, j);
  
  if (checkInput(i-1, j) && isOpen(i-1, j) && !bott[index-size]) {
   bott[index-size] = true;
   update_bott(i-1, j);   
  }
  if (checkInput(i, j-1) && isOpen(i, j-1) && !bott[index-1]) {
   bott[index-1] = true;
   update_bott(i, j-1);   
  } 
  if (checkInput(i, j+1) && isOpen(i, j+1) && !bott[index+1]) {
   bott[index+1] = true;
   update_bott(i, j+1);   
  }
  if (checkInput(i+1, j) && isOpen(i+1, j) && !bott[index+size]) {
   bott[index+size] = true;
   update_bott(i+1, j);   
  } 
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
  // if (full[(i-1)*size+j]) return true;
  // int f_index = wqu.find((i-1)*size+j);
  // for (int k = 1; k <= size; k++) {
   // if ((dist(i, j, 1, k) <= opened) && isOpen(1, k) && (f_index == wqu.find(k))) {
    // full[(i-1)*size+j] = true;
    // return true;
   // }  
  // } 
  // return false;
  
  // return full[(i-1)*size+j];
  return wqu.connected((i-1)*size+j, 0);
 }
 
private boolean connectToBottom(int i, int j)
 {
  if (i < 1 || i > size || j < 1 || j > size) throw new IndexOutOfBoundsException("index out of bounds");
  if (!isOpen(i, j)) return false;
  // if (bott[(i-1)*size+j]) return true;
  // int f_index = wqu.find((i-1)*size+j);
  // for (int k = 1; k <= size; k++) {
   // if ((dist(i, j, size, k) <= opened) && isOpen(size, k) && (f_index == wqu.find((size-1)*size+k))) {
    // bott[(i-1)*size+j] = true;
    // return true;
   // }
  // }
  // return false;
  
  return bott[(i-1)*size+j];
 }
 
 public boolean percolates()
 {
  // return percolated;
  return wqu.connected(0, size*size+1);
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
