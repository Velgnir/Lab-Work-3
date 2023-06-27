
import java.util.Scanner;
import java.util.Random;
import static java.lang.Math.max;
//import Quadratic.Quadratic;

public class Main
{
  public static void main (String[]args)
  {
    int ch;
    String C;
    Scanner Input = new Scanner (System.in);
    AvlTree tree = new AvlTree();
    RedBlackTree tree2 = new RedBlackTree ();
    Quadratic table2 = new Quadratic ();
    LinearProbing table = new LinearProbing();
    int NUM_TESTS = 2000000;
	int MAX_NUMBER = 10000;// number of keys to insert	// number of operations to perform
	double INSERT_PROBABILITY = 0.5;	// probability of performing an insert operation
	double SEARCH_PROBABILITY = 0;	// probability of performing a search operation
	double REMOVE_PROBABILITY = 0.5;
    while (true)
      {
		// probability of performing a remove operation
	  System.out.println ("What do you want?");
	  System.out.println ("1. Measure speed of programs.");
	  System.out.println ("2. Test them yourself.");
	  System.out.println ("else. Finish.");
	  C = Input.next();
	long insertTime = 0;
	long searchTime = 0;
	long removeTime = 0;
	if (Integer.valueOf(C)== 1)
	  {
	    System.out.println ("Which one do you want to test?");
	    System.out.println ("1. AvlTree");
	    System.out.println ("2. Black-Red Tree.");
	    System.out.println ("3. Linear Probing hash table");
	    System.out.println ("4. Quadratic hash table");
	    System.out.println ("0. Want to change correlations");
	    C = Input.next();
	    switch (C)
	      {
	      case "0":
	    System.out.println ("What do you want to change?");
	    System.out.println ("1. Number of test");
	    System.out.println ("2. Number of insert operations correlations");
	    System.out.println ("3. Number of search operations correlations");
	    System.out.println ("4. Number of remove operations correlations");
	    C = Input.next();
	    switch(C){
	        case "1":
	            NUM_TESTS = Input.nextInt();
	            break;
	       case "2":
	            INSERT_PROBABILITY = Input.nextDouble();
	           break;
	       case "3":
	           SEARCH_PROBABILITY = Input.nextDouble();
	            break;
	       case "4":
	           REMOVE_PROBABILITY = Input.nextDouble();
	           break;
	    }
	    double all = INSERT_PROBABILITY + SEARCH_PROBABILITY + REMOVE_PROBABILITY;
	    INSERT_PROBABILITY = INSERT_PROBABILITY/all;
	    SEARCH_PROBABILITY = SEARCH_PROBABILITY/all;
	    REMOVE_PROBABILITY = REMOVE_PROBABILITY/all;
	           break;
	      case "1":
		 
        // Create an instance of AvlTree
        
        ATNode Anode = new ATNode();
        Random rand = new Random();

 
        // Insert the integers into the AVL tree and measure the time taken
        long startTime = System.nanoTime();
        Anode = tree.insert(null, rand.nextInt(MAX_NUMBER));
        for (int i = 1; i < NUM_TESTS*INSERT_PROBABILITY; i++) {
            Anode = tree.insert(Anode, rand.nextInt(MAX_NUMBER));
        }
        Anode = tree.rebalance(Anode);
        insertTime = System.nanoTime() - startTime;
        
        // Search for a random integer in the AVL tree and measure the time taken
        startTime = System.nanoTime();
        for (int i = 0; i <NUM_TESTS*SEARCH_PROBABILITY; i++) {
            int randInt = rand.nextInt(MAX_NUMBER);
             tree.search( Anode, randInt);
        }
        searchTime = System.nanoTime() - startTime;
        
        // Remove a random integer from the AVL tree and measure the time taken
        startTime = System.nanoTime();
        for (int i = 0; i < NUM_TESTS*REMOVE_PROBABILITY; i++) {
            System.out.println(i);
            int randInt = rand.nextInt(MAX_NUMBER);
             Anode = tree.remove( Anode, randInt);
        }
        removeTime = System.nanoTime() - startTime;
        
        // Print the execution times
        System.out.println("Insertion time: " + insertTime+ " ns");
        System.out.println("Search time: " + searchTime + " ns");
        System.out.println("Removal time: " + removeTime + " ns");
		break;

		case "2":
		RBTNode Rnode = new RBTNode();

		// Insert random numbers
		rand = new Random ();
		long startInsert = System.nanoTime();
		Rnode = tree2.insert(null,rand.nextInt());
		for (int i = 0; i < NUM_TESTS*INSERT_PROBABILITY; i++)
		  {
		    int num = rand.nextInt (MAX_NUMBER);
		      Rnode =tree2.insert (Rnode, num);
		  }
		long endInsert = System.nanoTime();
		insertTime = endInsert - startInsert;
		System.out.println ("Insert time: " + insertTime + " ns");

		// Search for random numbers
		long startSearch = System.nanoTime();
		for (int i = 0; i < NUM_TESTS*SEARCH_PROBABILITY; i++)
		  {
		    int num = rand.nextInt (MAX_NUMBER);
		    tree2.search (Rnode, num);
		  }
		long endSearch = System.nanoTime();
	    searchTime = endSearch - startSearch;
		System.out.println ("Search time: " + searchTime + " ns");

		// Remove random numbers
		long startRemove = System.nanoTime();
		for (int i = 0; i < NUM_TESTS*REMOVE_PROBABILITY; i++)
		  {
		    int num = rand.nextInt (MAX_NUMBER);
		    Rnode = tree2.remove (Rnode, num);
		  }
		long endRemove =System.nanoTime();
		removeTime = endRemove - startRemove;
		System.out.println ("Remove time: " + removeTime + " ms");
		break;
		
		
	      case "3":
    		
        table.Start(1000000);

        // insert some random keys and values into the table
        rand = new Random();
       /* for (int i = 0; i < NUM_TESTS*INSERT_PROBABILITY; i++) {
            System.out.println(i);
            String key = "key" + i;
            int value = rand.nextInt(MAX_NUMBER);
            table.insert(key, Integer.toString(value));
        }*/

        // perform some random operations on the table and measure their execution time
        for (int i = 0; i < NUM_TESTS; i++) {
             System.out.println(i);
            double op = rand.nextDouble();
            String key = "key" + rand.nextInt(MAX_NUMBER);
            startTime = System.nanoTime();
            if (op < INSERT_PROBABILITY) {
                int value = rand.nextInt(100);
                table.insert(key, Integer.toString(value));
            } else if (op < INSERT_PROBABILITY + SEARCH_PROBABILITY) {
                table.search(key);
            } else {
                table.remove(key);
            }
            long endTime = System.nanoTime();
            if (op < INSERT_PROBABILITY) {
                insertTime += (endTime - startTime);
            } else if (op < INSERT_PROBABILITY + SEARCH_PROBABILITY) {
                searchTime += (endTime - startTime);
            } else {
                removeTime += (endTime - startTime);
            }
        }

// print the execution times of each operation
        System.out.println("Insert time: " + (double) insertTime / 1000000 + " ms");
        System.out.println("Search time: " + (double) searchTime / 1000000 + " ms");
        System.out.println("Remove time: " + (double) removeTime / 1000000 + " ms");
		break;
	      case "4":




		// create a new HashTable object
		
		table2.Start (2000000);
// insert some random keys and values into the table
	rand = new Random ();
		/*	for (int i = 0; i < NUM_TESTS*INSERT_PROBABILITY; i++)
		  {
		    String key = "key" + i;
		    int value = rand.nextInt (MAX_NUMBER);
		    table2.insert (key, Integer.toString (value));
		  }*/
// perform some random operations on the table and measure their execution time

		for (int i = 0; i < NUM_TESTS; i++)
		  {
		    double op = rand.nextDouble ();
		    String key = "key" + rand.nextInt (MAX_NUMBER);
		    startTime = System.nanoTime ();
		    if (op < INSERT_PROBABILITY)
		      {
			int value = rand.nextInt (MAX_NUMBER);
			table2.insert (key, Integer.toString (value));
		      }
		    else if (op < INSERT_PROBABILITY + SEARCH_PROBABILITY)
		      {
			table2.search (key);
		      }
		    else
		      {
			table2.remove (key);
		      }
		    long endTime = System.nanoTime ();
		    if (op < INSERT_PROBABILITY)
		      {
			insertTime += (endTime - startTime);
		      }
		    else if (op < INSERT_PROBABILITY + SEARCH_PROBABILITY)
		      {
			searchTime += (endTime - startTime);
		      }
		    else
		      {
			removeTime += (endTime - startTime);
		      }
		  }
// print the execution times of each operation
		System.out.println ("Insert time: " +
				    (double) insertTime / 1000000 + " ms");
		System.out.println ("Search time: " +
				    (double) searchTime / 1000000 + " ms");
		System.out.println ("Remove time: " +
				    (double) removeTime / 1000000 + " ms");

		break;
	      default:
		// code block
	      }
	  }
	else if (Integer.valueOf(C)== 2)
	  {
	    System.out.println ("Which one do you want to test?");
	    System.out.println ("1. AvlTree");
	    System.out.println ("2. Black-Red Tree.");
	    System.out.println ("3. Linear Probing hash table");
	    System.out.println ("4. Quadratic hash table");
	    String С = Input.next();
	    boolean Out;
	    switch (С)
	      {
	      case "1":
	          Out =false;
	          ATNode node;
	            System.out.println("First integer value");
    		    ch = Input.nextInt ();
    		    node = tree.insert(null,ch);
    		    
    		while(true){
                System.out.println ("What do you want to do?");
	            System.out.println ("1. insert");
	            System.out.println ("2. search");
	            System.out.println ("3. remove");
	            System.out.println ("else. finish");
	            ch = Input.nextInt ();
    		    switch(ch){
    		        case 1:
    		            System.out.println("integer value");
    		            ch = Input.nextInt ();
    		            node = tree.insert(node,ch);
    		            node = tree.rebalance(node);
    		            break;
    		        case 2:
    		            System.out.println("integer value");
    		            ch = Input.nextInt ();
    		            System.out.println(tree.search(node,ch));
    		            break;
    		        case 3:
    		            System.out.println("integer value");
    		            ch = Input.nextInt ();
    		            node = tree.remove(node,ch);
    		            break;
    		        default:
    		        Out = true;
    		            break;
    		    }
    		    if (Out){
    		        break;
    		    }
    		}
    		
		break;

	      case "2":
		Out =false;
	          RBTNode node2;
	            System.out.println("First integer value");
    		    ch = Input.nextInt ();
    		    node2 = tree2.insert(null,ch);
    		    
    		while(true){
                System.out.println ("What do you want to do?");
	            System.out.println ("1. insert");
	            System.out.println ("2. search");
	            System.out.println ("3. remove");
	            System.out.println ("else. finish");
	            ch = Input.nextInt ();
    		    switch(ch){
    		        case 1:
    		            System.out.println("integer value");
    		            ch = Input.nextInt ();
    		            node2 = tree2.insert(node2,ch);
    		            break;
    		        case 2:
    		            System.out.println("integer value");
    		            ch = Input.nextInt ();
    		            System.out.println(tree2.search(node2,ch));
    		            break;
    		        case 3:
    		            System.out.println("integer value");
    		            ch = Input.nextInt ();
    		            node2 = tree2.remove(node2,ch);
    		            break;
    		        default:
    		        Out = true;
    		            break;
    		    }
    		    if (Out){
    		        break;
    		    }
    		}
		break;
	      case "3":
	          Out =false;
		LinearProbing Table1 = new LinearProbing();
		System.out.println("Integer size of table");
    		    ch = Input.nextInt ();
    		    Table1.Start(ch);
    		    
    		while(true){
                System.out.println ("What do you want to do?");
	            System.out.println ("1. insert");
	            System.out.println ("2. search");
	            System.out.println ("3. remove");
	            System.out.println ("else. finish");
	            ch = Input.nextInt ();
    		    switch(ch){
    		        case 1:
    		            System.out.println("Key and value");
    		            Table1.insert(Input.next(), Input.next() );
    		            break;
    		        case 2:
    		            System.out.println("Key to look for");
    		            System.out.println(Table1.search(Input.next()));
    		            break;
    		        case 3:
    		            System.out.println("Key for node to be removed");
    		            Table1.remove(Input.next());
    		            break;
    		        default:
    		        Out = true;
    		            break;
    		    }
    		    if (Out){
    		        break;
    		    }
    		}
		
		break;
	      case "4":
		Out =false;
	    Quadratic Table2 = new Quadratic();
		System.out.println("Integer size of table");
        ch = Input.nextInt ();
    	Table2.Start(ch);
    		    
    		while(true){
                System.out.println ("What do you want to do?");
	            System.out.println ("1. insert");
	            System.out.println ("2. search");
	            System.out.println ("3. remove");
	            System.out.println ("else. finish");
	            ch = Input.nextInt ();
    		    switch(ch){
    		        case 1:
    		            System.out.println("Key and value");
    		            Table2.insert(Input.next(), Input.next() );
    		            break;
    		        case 2:
    		            System.out.println("Key to look for");
    		            System.out.println(Table2.search(Input.next()));
    		            break;
    		        case 3:
    		            System.out.println("Key for node to be removed");
    		            Table2.remove(Input.next());
    		            break;
    		        default:
    		        Out = true;
    		            break;
    		    }
    		    if (Out){
    		        break;
    		    }
    		}
		break;
	  }
	  }
	else
	  {
	    return;
	  }

      }
  }
}
class RBTNode
  {
    int part;
    boolean color;
    RBTNode left;
    RBTNode right;

  }
class RedBlackTree
{
  
  private boolean Red (RBTNode node)
  {
    if (node == null)
      {
	return false;
      }
    return node.color == true;
  }

  private RBTNode rotateLeft (RBTNode node)
  {
    RBTNode temp = node.right;
    node.right = temp.left;
    temp.left = node;
    temp.color = node.color;
    node.color = true;
    return temp;
  }

  private RBTNode rotateRight (RBTNode node)
  {
    RBTNode temp = node.left;
    node.left = temp.right;
    temp.right = node;
    temp.color = node.color;
    node.color = true;
    return temp;
  }

  private RBTNode flip (RBTNode node)
  {
    node.color = true;
    node.left.color = false;
    node.right.color = false;
    return node;
  }

  public RBTNode insert (RBTNode node, int part)
  {

    if (node == null)
      {
	RBTNode RET = new RBTNode ();
	RET.part = part;
	return RET;
      }

    int cmp = part - node.part;
    if (cmp < 0)
      {
	node.left = insert (node.left, part);
      }
    else if (cmp > 0)
      {
	node.right = insert (node.right, part);
      }
    else
      {
	return node;
      }

    if (Red (node.right) && !Red (node.left))
      {
	node = rotateLeft (node);
      }
    if (Red (node.left) && Red (node.left.left))
      {
	node = rotateRight (node);
      }
    if (Red (node.left) && Red (node.right))
      {
	node = flip (node);
      }

    return node;
  }
  public RBTNode search (RBTNode node, int part)
  {
    if (node == null || node.part == part)
      {
	return node;
      }

    if (part < node.part)
      {
	return search (node.left, part);
      }
    else
      {
	return search (node.right, part);
      }
  }

  public RBTNode remove (RBTNode node, int part)
  {
    if (node == null)
      {
	return null;
      }

    int cmp = part - node.part;
    if (cmp < 0)
      {
	node.left = remove (node.left, part);
      }
    else if (cmp > 0)
      {
	node.right = remove (node.right, part);
      }
    else
      {
	if (node.left == null || node.right == null)
	  {
	    RBTNode temp = null;
	    if (node.left == null)
	      {
		temp = node.right;
	      }
	    else
	      {
		temp = node.left;
	      }


	    if (temp == null)
	      {
		temp = node;
		node = null;
	      }
	    else
	      {

		node = temp;
	      }
	  }
	else
	  {

	    RBTNode temp = findMin (node.right);
	    node.part = temp.part;
	    node.right = remove (node.right, temp.part);
	  }
      }

    if (node == null)
      {
	return null;
      }

    if (Red (node.right) && !Red (node.left))
      {
	node = rotateLeft (node);
      }
    if (Red (node.left) && Red (node.left.left))
      {
	node = rotateRight (node);
      }
    if (Red (node.left) && Red (node.right))
      {
	node = flip (node);
      }

    return node;
  }

  private RBTNode findMin (RBTNode node)
  {
    while (node.left != null)
      {
	node = node.left;
      }
    return node;
  }
}

class QNode
  {
    public String key;
    public String part;
    public boolean removed;
  }
class Quadratic
{
  
  private int tableSize;
  private QNode[] table;
  private int numKeys;

  public void Start (int size)
  {
    tableSize = size;
    table = new QNode[tableSize];
    numKeys = 0;
  }

  private int Hash2 (String A)
  {
    int FirstHash = A.hashCode ();	// get the hash code for the string
    FirstHash %= tableSize;	// compute the modulo with the table size to get the hash value
    if (FirstHash < 0)
      FirstHash += tableSize;
    return FirstHash;
  }
  private int positionOn (String key, int i)
  {
    return Math.abs((Hash2 (key) + i * i) % tableSize);
  }

  public void insert (String key, String part)
  {

    if (numKeys == tableSize)
      {
	System.out.println ("Table is full!");
	return;
      }

    int i = 0;
    while (table[positionOn (key, i)] != null
	   && !table[positionOn (key, i)].removed)
      {
	i++;
      }
    QNode node = new QNode ();
    node.part = part;
    node.key = key;
    node.removed = false;
    table[positionOn (key, i)] = node;
    numKeys++;
  }

  public String search (String key)
  {
    int i = 0;
    while (table[positionOn (key, i)] != null)
      {
	if (!table[positionOn (key, i)].removed && table[positionOn (key, i)].key.equals(key))
	  {
	    return table[positionOn (key, i)].part;
	  }
	i++;
      }
    return null;
  }

  public void remove (String key)
  {
    int i = 0;
    while (table[positionOn (key, i)] != null)
      {
	if (!table[positionOn (key, i)].removed
	    && table[positionOn (key, i)].key.equals( key))
	  {
	    table[positionOn (key, i)].removed = true;
	    numKeys--;
	    return;
	  }
	i++;
      }
  }
}

class LinearProbing
{
  private int SizeNow, maxSize;
  private String[] ALLkeys;
  private String[] ALLvals;

  public void Start (int M)
  {
    maxSize = M;
    ALLkeys = new String[maxSize];
    ALLvals = new String[maxSize];
    SizeNow = 0;
  }
  public void insert (String key, String val)
  {
    int tmp = Math.abs (key.hashCode () % maxSize);
    int i = tmp;

    do
      {
	if (ALLkeys[i] == null)
	  {
	    ALLkeys[i] = key;
	    ALLvals[i] = val;
	    SizeNow++;
	    return;
	  }

	if (ALLkeys[i].equals (key))
	  {
	    ALLvals[i] = val;
	    return;
	  }

	i = (i + 1) % maxSize;

      }
    while (i != tmp);
  }
  public String search (String key)
  {
    int i = Math.abs (key.hashCode () % maxSize);
    while (ALLkeys[i] != null)
      {
	if (ALLkeys[i].equals (key))
	  return ALLvals[i];
	i = (i + 1) % maxSize;
      }
    return null;
  }
  public void remove (String key)
  {
    if (search (key) == null)
      return;

    int i = Math.abs (key.hashCode () % maxSize);

    while (!key.equals (ALLkeys[i]))
      i = (i + 1) % maxSize;

    ALLkeys[i] = null;
    ALLvals[i] = null;

    for (i = (i + 1) % maxSize; ALLkeys[i] != null; i = (i + 1) % maxSize)
      {
	String temp1 = ALLkeys[i];
	String temp2 = ALLvals[i];
	ALLkeys[i] = ALLvals[i] = null;
	insert (temp1, temp2);
	SizeNow--;

      }
    SizeNow--;
  }
}
class ATNode {
    int part;
    int height;
    ATNode left;
    ATNode right;

}
class AvlTree {
    
    
    private int height(ATNode node) {
        if (node ==null){
            return -1;
        }else{
            int l_s_h = height(node.left);
            int r_s_h = height(node.right);

            if (l_s_h > r_s_h){
                return (l_s_h + 1);
            }else{
                return (r_s_h + 1);
            }
        }
    }

    private ATNode updateCurrentHeight(ATNode node) {
        int lCH = height(node.left);
        int rCH = height(node.right);
        node.height = max(lCH, rCH) + 1;
        return node;
    }

    private int Balancer(ATNode node) {
        if (node == null)
            return 0;
        return height(node.right) - height(node.left);
    }

   ATNode rotateRight(ATNode node) {
    if (node == null) {
        return null;
    }
    ATNode tempL = node.left;
    ATNode tempR = tempL.right;
    
    tempL.right = node;
    node.left = tempR;
    
    node.height = Math.max(height(node.left), height(node.right)) + 1;
    tempL.height = Math.max(height(tempL.left), height(tempL.right)) + 1;
    
    return tempL;
}

    ATNode rotateLeft(ATNode node) {
        ATNode tempR = node.right;
        ATNode tempL = tempR.left;
        
        tempR.left = node;
        node.right = tempL;
        
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        tempR.height = Math.max(height(tempR.left), height(tempR.right)) + 1;
        return tempR;
    }

    public ATNode rebalance(ATNode node) {
        if (node == null) {
            return null;
        }
        node = updateCurrentHeight(node);
        int balance = Balancer(node);

        if (balance < -1) {
            if (Balancer(node.left) <= 0) {
                node = rotateRight(node);
            } else {
                node.left = rotateLeft(node.left);
                node = rotateRight(node);
            }
        }
        if (balance > 1) {
            if (Balancer(node.right) >= 0) {
                node = rotateLeft(node);
            } else {
                node.right = rotateRight(node.right);
                node = rotateLeft(node);
            }
        }

        return node;
    }
ATNode insert(ATNode node, int part) {
        if (node == null) {
            node = new ATNode();
            node.part= part;
            return node;
        }
        if (part < node.part) {
            node.left = insert(node.left, part);
        } else if (part > node.part) {
            node.right = insert(node.right, part);
        }
        return node;
    }
    public ATNode search(ATNode node, int part) {
        if (node == null) {
            return null;
        }
        if (part < node.part) {
            return search(node.left, part);
        } else if (part > node.part) {
            return search(node.right, part);
        } else {
            return node;
        }
    }
    
    public ATNode remove(ATNode node, int part) {
        if (node == null) {
            return null;
        }
        if (part < node.part) {
            node.left = remove(node.left, part);
        } else if (part > node.part) {
            node.right = remove(node.right, part);
        } else {
            if (node.left == null && node.right == null) {
                node = null;
            } else if (node.left == null) {
                node = node.right;
            } else if (node.right == null) {
                node = node.left;
            } else {
                ATNode temp = findMin(node.right);
                node.part = temp.part;
                node.right = remove(node.right, temp.part);
            }
        }
        return rebalance(node);
    }
    
    private ATNode findMin(ATNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}