import java.util.Random;
/**
* Write a description of class AdaptiveHuffman here.
*
* Eleni Stylianou ge21708
* December 2024
*/

    public class AdaptiveHuffman
    {
     public AdaptiveHuffman() {};
        
     public static void main(String args[])
     {
         System.out.print('\u000C');
         AdaptiveHuffman ah = new AdaptiveHuffman();
         int n=26,size=1000;
         String s = randomString(size,n);
         //String s="abracadabra";
         System.out.println("Initial script: (size: " + size + " bytes)");
         System.out.println(s);
         String encoded = ah.encode(s,n);
         System.out.println("Encoded script: ");
         System.out.println(encoded);
         String decoded = ah.decode(encoded,n);
         System.out.println("Decoded script: ");
         System.out.println(decoded);
         System.out.println("Size of encoded script: " + encoded.length() + " bits");
     }
            
     /**
     * creates a random string of length n with characters a...z (not capital)
     */
     public static String randomString(int size,int n)
     {
         Random random = new Random();
         String s = "";
         int randomInt;
              
         for(int i=0;i<size;i++)
            {
             randomInt = random.nextInt(n);
             s = s+(char)('a'+randomInt);
            }
                
            return s;
     }
            
     public String encode(String s,int n)
     {
         String encodedScript = "";
         Position NYT,newNYT,newChar;
         //n is the size of the alphabet
         int k;      //the order of a letter in the alphabet
         int m=log2(n);  //the size of the binary representation of each k
         Position charPos[] = new Position[n];   //store for each letter its position in the tree
         for(int i=0;i<n;i++)
            charPos[i] = null;
        
         LinkedBinaryTree tree = new LinkedBinaryTree();
         NYT = tree.addRoot("NYT",0);
        
         for(int i=0;i<s.length();i++)
         {
            k = s.charAt(i)-'a';
            if(charPos[k]==null)
            {
                encodedScript += findPath(tree,NYT);
                //send the agreed code for the symbol
                encodedScript += digitsFix(Integer.toBinaryString(k),m);
                //add the character to the tree
                newNYT = tree.addLeft(NYT,"NYT",0);
                newChar = tree.addRight(NYT,s.charAt(i),1);
                //update
                update(tree,tree.parent(NYT));
                charPos[k] = newChar;
                //set the new NYT node
                tree.set(NYT,null,1);
                NYT = newNYT;
            }
            else
            {
                //send the code for the corresponding node
                encodedScript += findPath(tree,charPos[k]);
                //update
                update(tree,charPos[k]);
            }
            //tree.printSubtree(tree.root());
        }
        
        //tree.printSubtree(tree.root());
        
        return encodedScript;
    }
    
    public String decode(String s, int n)
    {
        String decodedScript = "",binaryForm;
        int i=0;
        Position NYT,newNYT,newChar,current;
        LinkedBinaryTree tree = new LinkedBinaryTree();
        NYT = tree.addRoot("NYT",0);
        char c;
        //n is the size of the alphabet
        int k;      //the order of a letter in the alphabet
        int m=log2(n);  //the size of the binary representation of each k
        Position charPos[] = new Position[n];   //store for each letter its position in the tree
        
        current = tree.root();
        while(i<s.length())
        {
            while(tree.isInternal(current))
            {
                c = s.charAt(i);
                if(c=='0')
                    current = tree.left(current);
                else
                    current = tree.right(current);
                i++;
            }
            //now current is a leaf
            if(current==NYT)
            {
                binaryForm = "";
                for(int j=0;j<m;j++)
                    binaryForm += s.charAt(i+j);
                k = binaryToInt(binaryForm);
                //add the character to the decodedScript
                i += m;
                //add the character to the tree
                newNYT = tree.addLeft(NYT,"NYT",0);
                c = (char)('a'+k);
                newChar = tree.addRight(NYT,c,1);
                tree.set(NYT,null,1);
                //update
                update(tree,tree.parent(NYT));
                charPos[k] = newChar;
                //set the new NYT node
                NYT = newNYT;
            }
            else
            {
                c = (char)current.getElement();
                k = c-'a';
                update(tree,charPos[k]);
            }
            
            decodedScript += (char)('a'+k);
            current = tree.root();
        }
        
        return decodedScript;
    }
    
    /**
     * convert a binary number (in String form) to its decimal equal
     */
    public static int binaryToInt(String s)
    {
        int k=0,size=s.length(),j=1,digit;
     
        for(int i=size-1;i>=0;i--)
        {
            digit = 0;
            if(s.charAt(i)=='1')
                digit = 1;
            k += digit*j;
            j *= 2;
        }
        
        return k;
    }
    
    /**
     * find the path from the root to node pos using 0-1 symbols
     * 0 is for left
     * 1 is for right
     */
    public static String findPath(LinkedBinaryTree tree, Position pos)
    {
        String path = "";
        Position parent;
        
        while(pos != tree.root())
        {
            parent = tree.parent(pos);
            if(tree.left(parent) == pos)
                path += "0";
            else
                path += "1";
            pos = parent;
        }
        
        return reverseString(path);
    }
    
    /**
     * reverse a string
     */
    public static String reverseString(String s)
    {
        StringBuilder rev = new StringBuilder(s).reverse();
        return rev.toString();
    }
    
    /**
     * add zeros in the front of the string s until its size becomes n
     */
    public static String digitsFix(String s,int n)
    {
        String newS = s;
        
        while(newS.length()<n)
            newS = "0"+newS;
        
        return newS;
    }
    
    /**
     * find the log2 of a given integer
     */
    public static int log2(int m)
    {
        int x = m-1, result = 0;
        
        while(x>0)
        {
            x = x/2;
            result++;
        }
        
        return result;
    }
    
    /**
     * the update process of a Huffman tree for a node pos
     */
    public static void update(LinkedBinaryTree tree, Position pos)
    {
        Position parent,root=tree.root();
        float freq;
        //number the nodes of the tree in non-increasing order using bfs
        Position order[] = bfsOrder(tree);
        //find the position of the node pos searching from right
        int num = order.length-1;   //initialization
        int highest;
        
        while(pos != null && num!=-1)  //stop if pos == root
        {
            num = findNodeNumber(order,pos,num);
            //find the highest numbered node in block
            highest = maxInBlock(tree,order,num);
            //Switch node with highest numbered node in block if necessary
            if(highest != num && tree.parent(pos)!=order[highest]){
                tree.swap(order[num],order[highest]);
            }
            freq = tree.getFrequency(pos);
            tree.set(pos,pos.getElement(),freq+1);
            
            pos = tree.parent(pos);
        }
    }
    
    public static Position[] bfsOrder(LinkedBinaryTree tree)
    {
        int n = tree.size();
        int i=0,size;
        Position[] order = new Position[n];
        Position right;
        
        order[0] = tree.root();
        size = 1;
        while(size<n)
        {
            if(order[i]==null)
            {
                System.out.println("size = " + size + " n = " + n + " i = " + i);
                printOrder(order,tree);
            }
            right = tree.right(order[i]);
            if(right != null)
            {
                order[size] = right;
                order[size+1] = tree.left(order[i]);
                size+=2;
            }
            i++;
        }
        
        return order;
    }
    
    /**
     * used only in testing
     */
    public static void printOrder(Position[] order,LinkedBinaryTree tree)
    {
        for(int i=0;i<order.length;i++)
        {   
            if(order[i] != null)
                System.out.println(i + ": " + order[i].getElement() + " " + tree.getFrequency(order[i]));
            else
                System.out.println(i + ": " + "null");
        }
            
    }
    
    /**
     * Find the pos in the array order searching from right to left
     */
    public static int findNodeNumber(Position[] order, Position pos, int start)
    {
        for(int i=start;i>=0;i--)
            if(order[i] == pos)
                return i;
        return -1;
    }
    
    /**
     * find the max number in the block of a node
     */
    public static int maxInBlock(LinkedBinaryTree tree,Position order[], int pos)
    {
        int i = pos;
        
        while(i>0 && tree.getFrequency(order[i]) == tree.getFrequency(order[i-1]))
            i--;
        
        return i;
    }
}
