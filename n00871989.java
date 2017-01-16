import java.io.*;
import java.util.*;

public class n00871989{


   public static void main(String[] args)throws FileNotFoundException{
///////////////////////////////////////////////////////////////////////////////////Array Setup
   //Find Size of Char Array
   int ctr = 0;
   File f = new File(args[0]);
   try{
   FileInputStream inputStream = new FileInputStream(f);
    while(inputStream.available() > 0) {
      char c = (char) inputStream.read();
      if(c >= 'A' && c <= 'G'){
      ctr++;
      }
   }   
   }
   catch (IOException e){
   e.printStackTrace();
   }
   //Initialize Char Array
   char[] charArray = new char[ctr];
   
   //Fill Char Array

   int ctr2 = 0;
   try{
   FileInputStream inputStream2 = new FileInputStream(f);
   while(inputStream2.available() > 0)
    {
      char d = (char) inputStream2.read();
      if(d >= 'A' && d <= 'G'){
      charArray[ctr2] = d;
      ctr2++; 
      }
   }   
   }
   catch(IOException e){
   e.printStackTrace();
   }
   //Print Char Array
  // for(int i = 0; i<charArray.length; i++){
  // System.out.println(charArray[i]);
   //}
////////////////////////////////////////////////////////////////////////////////////////////////////Count Array Setup
//initialize to 0
int[] countArray = new int[7];
for(int i = 0; i<countArray.length; i++){
countArray[i] = 0;
} 
for(int i = 0; i< charArray.length; i++){
char charr = charArray[i];
switch (charr){
case 'A': countArray[0] = countArray[0] + 1;
break;
case 'B': countArray[1] = countArray[1] +1;
break;
case 'C': countArray[2] = countArray[2] +1;
break;
case 'D': countArray[3] = countArray[3] +1;
break;
case 'E': countArray[4] = countArray[4] +1;
break;
case 'F': countArray[5] = countArray[5] +1;
break;
case 'G': countArray[6] = countArray[6] +1;
break;
}
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
Queue nodeQueue = new LinkedList();
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
class tableRow{
char c;
int[] digitArray = new int[10];

public tableRow(){
for (int i = 0; i< digitArray.length; i++){
digitArray[i] = 2;
}
}
String temp = "";
int nitems = 0;

void convertArray(){
for(int i=0; i<digitArray.length; i++){
if(digitArray[i] != 2){
temp += digitArray[i];
}
}
//System.out.print(temp);
}
void display(){
System.out.print(c + " = ");
for(int i=0; i<digitArray.length; i++){
if(digitArray[i] != 2){
System.out.print(digitArray[i]);
}
}
System.out.print(" ");
}
void setChar(char a){
c = a;
}
void arrayInsert(int i){
digitArray[nitems] = i;
nitems++;
}
char getChar(){
return c;
}
String getTemp(){
return temp;
}
}
class table{
tableRow[] tableRows = new tableRow[7];
int nitems = 0;

void insert(tableRow t){
tableRows[nitems] = t;
nitems++;

}
void convertArrays(){
for(int i = 0; i< tableRows.length; i++){
tableRows[i].convertArray();
}
}
void display(){
System.out.println("Character Code");
for(int i = 0; i<tableRows.length; i++){
tableRows[i].display();
}
System.out.println("");
}
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
table codeTable = new table();
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
class node{
int digit = -1;
char letter;
int count;
node left = null;
node right = null;
node parent = null;
node root = null;

void setRoot(node n){
root = n;
}
node findRoot(node n){
   if(n.getParent() == null){
   return n;
   }
   return findRoot(n.getParent());
}
void setChar(char c){
letter = c;
}
void display(){
System.out.println(letter + " " + count);
}


void preOrder(node n){
if(n != null){
n.display();
preOrder(n.left);
preOrder(n.right);
}
}
char getChar(){
return letter;
}
void printParent(node n, tableRow t){
if (n.digit == -1){
return;
}
printParent(n.getParent(), t);
nodeQueue.add(n.digit);
t.arrayInsert(n.digit);
}
/////
void convertBinary(node n, Queue q){

//start at root
if(q.peek()== null){
System.out.print(n.getChar());
return;}
char temp = (char)q.peek();
int i = Character.getNumericValue(temp);
char c = n.getChar();
      if (c >= 'A' && c <= 'G'){
      System.out.print(c);
      convertBinary(n.findRoot(n), q);
      }

      else if (i == 0){
      q.remove();
      convertBinary(n.getLeft(), q);
      }
      
      else if (i == 1){
      q.remove();
      convertBinary(n.getRight(), q);
      }
}//end convertBinary
////
void printBinary(node n){
if(n == null){ 
return;
}
if (n.left == null && n.right == null){
//PUT CODE HERE TO PRINT OUT THE ENTIRE PATH
tableRow t = new tableRow();
t.setChar(n.getLetter());
printParent(n, t);
codeTable.insert(t);
}
//else{
//System.out.println("Not a leaf");
//}
printBinary(n.left);
printBinary(n.right);
}
///
void setDigit(int i){
digit = i;
}
void setLetter(char c){
letter = c;
}
void setCount(int i){
count = i;
}
void setLeft(node i){
left = i;
}
void setRight(node i){
right = i;
}
void setParent(node i){
parent = i;
}
char getLetter(){
return letter;
}
int getCount(){
return count;
}
int getDigit(){
return digit;
}
node getParent(){
return parent;
}
node getLeft(){
return left;
}
node getRight(){
return right;
}
} //End node class
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//Priority Queue
class PriorityQ
{
// array in sorted order, from max at 0 to min at size-1
private int maxSize;
private node[] nodeArray;
private int nItems;
//-------------------------------------------------------------
public PriorityQ(int s) // constructor
{
maxSize = s;
nodeArray = new node[maxSize];
nItems = 0;
}
//-------------------------------------------------------------
public void insert(node item) // insert item
{
int j;
if(nItems==0) // if no items,
nodeArray[nItems++] = item; // insert at 0
else // if items,
{
for(j=nItems-1; j>=0; j--) // start at end,
{
if( item.getCount() > nodeArray[j].getCount() ) // if new item larger,
nodeArray[j+1] = nodeArray[j]; // shift upward
else // if smaller,
break; // done shifting
} // end for
nodeArray[j+1] = item; // insert it
nItems++;
} // end else (nItems > 0)
} // end insert()
//-------------------------------------------------------------
public int getNItems(){
return nItems;
}
//-------------------------------------------------------------
public node remove() // remove minimum item
{ return nodeArray[--nItems]; }
//-------------------------------------------------------------
public int peekMin() // peek at minimum item
{ return nodeArray[nItems-1].getCount(); }
//-------------------------------------------------------------
public boolean isEmpty() // true if queue is empty
{ return (nItems==0); }
//-------------------------------------------------------------
public boolean isFull() // true if queue is full
{ return (nItems == maxSize); }
//-------------------------------------------------------------
} // end class PriorityQ 
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
PriorityQ myQueue = new PriorityQ(charArray.length);
//for(int i = 0; i<countArray.length; i++){
//System.out.println(countArray[i]);
//}
//loop through array, convert to nodes, inseRt into priority queue. If character doesn't have a node, cr


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////Setting up Priority Queue
char t = 'A';
for(int i = 0; i<countArray.length; i++){
node n = new node();
n.setLetter(t);
n.setCount(countArray[i]);
myQueue.insert(n);
//n.display();
t++;
}//end for
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////Build the Huffman Tree
while(!myQueue.isEmpty() && myQueue.getNItems() != 1){

node a = myQueue.remove();
node b = myQueue.remove();
node c = new node();
a.setParent(c);
b.setParent(c);
c.setLeft(a);
c.left.setDigit(0);
c.setRight(b);
c.right.setDigit(1);
c.setLetter('-');
c.setCount(a.getCount() + b.getCount());
myQueue.insert(c);

}// end while
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////Displaying the Tree
node tree = new node();
tree = myQueue.remove();
System.out.println("Pre-order display of the huffman tree:");
tree.preOrder(tree);
tree.printBinary(tree);
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////Displaying the code table
codeTable.display();
codeTable.convertArrays();
//////////////convert code table to arrays

/////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////Code Table Stuff
//loop through table rows to get the a, b, c, etc.
tableRow A = new tableRow();
tableRow B = new tableRow();
tableRow C = new tableRow();
tableRow D = new tableRow();
tableRow E = new tableRow();
tableRow F = new tableRow();
tableRow G = new tableRow();

for (int i = 0; i<codeTable.tableRows.length; i++){
if (codeTable.tableRows[i].getChar() == 'A'){
A = codeTable.tableRows[i];
}
if (codeTable.tableRows[i].getChar() == 'B'){
B = codeTable.tableRows[i];
}
if (codeTable.tableRows[i].getChar() == 'C'){
C = codeTable.tableRows[i];
}
if (codeTable.tableRows[i].getChar() == 'D'){
D = codeTable.tableRows[i];
}
if (codeTable.tableRows[i].getChar() == 'E'){
E = codeTable.tableRows[i];
}
if (codeTable.tableRows[i].getChar() == 'F'){
F = codeTable.tableRows[i];
}
if (codeTable.tableRows[i].getChar() == 'G'){
G = codeTable.tableRows[i];
}
}

String binaryString = "";

for(int j = 0; j<charArray.length; j++){
String temp = "";
   switch (charArray[j]){      
case 'A': temp+= A.getTemp();
break;
case 'B': temp+= B.getTemp();
break;
case 'C': temp+= C.getTemp();
break;
case 'D': temp+= D.getTemp();
break;
case 'E': temp+= E.getTemp();
break;
case 'F': temp+= F.getTemp();
break;
case 'G': temp+= G.getTemp();
break; 
   }//end switch
binaryString+= temp;     
}//end for 
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////Show Binary
Queue binaryQueue = new LinkedList();
for (int i = 0; i<binaryString.length(); i++){
binaryQueue.add(binaryString.charAt(i));
} //end queue for



System.out.println("Binary encoding of the file:");
while(!binaryQueue.isEmpty()){
for(int j = 0; j<3; j++){
   for(int i = 0; i<8; i++){
   if(binaryQueue.isEmpty()){
   break;
   }
   System.out.print(binaryQueue.remove());
   }//end for
   System.out.print(" ");
   
} 
System.out.println("");

} 
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////Re-create based on Huffman
Queue huffmanQueue = new LinkedList();
for (int i = 0; i<binaryString.length(); i++){
huffmanQueue.add(binaryString.charAt(i));
} //end queue for

System.out.println("Original file printed using the Huffman tree");
tree.convertBinary(tree, huffmanQueue);





   }//end main
   
} // end class