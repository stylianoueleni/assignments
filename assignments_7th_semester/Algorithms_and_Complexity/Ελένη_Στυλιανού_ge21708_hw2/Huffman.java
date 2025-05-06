import java.util.Random;
/**
 * Write a description of class Huffman here.
 *
 * Eleni Stylianou ge21708
 * December 2024
 */
public class Huffman
{
    public Huffman() {};

    public static void main(String args[])
    {
        System.out.print('\u000C');
        Random random = new Random();
        int n=26;
        int size = 15;
        Float freq[] = new Float[n];
        Character chars[] = new Character[n];
        String codes[] = new String[n];
        LinkedBinaryTree tree;
        Huffman huffman = new Huffman();
        
        for(int i=0;i<n;i++){
            chars[i] = (char)('a' + i);
        }
        
        String s = randomString(size,n);
        freq = huffman.findFrequencies(s,n,chars);
        chars = huffman.validateChars(chars,freq);
        freq = huffman.validateFrequencies(freq);
        
        huffman.printFrequencies(chars,freq);
        
        tree = huffman.makeHuffTree(chars,freq);
        
        huffman.encodeTree(tree,tree.root(),codes,"");
        huffman.printCodes(chars,codes);
        
        System.out.println("Initial script (script size: "+size+", alphabet size: "+chars.length+")");
        System.out.println(s);    
        String codedScript = huffman.encodeScript(s,codes);
        System.out.println("Coded script:");
        System.out.println(codedScript);
        String decodedScript = huffman.decodeScript(codedScript,tree);
        System.out.println("Decoded script:");
        System.out.println(decodedScript);
        
        //find average character size
        float avg = 0;
        for(int i=0;i<freq.length;i++)
            avg += freq[i]*codes[chars[i]-'a'].length();
        System.out.println("Average character size after Huffman: " + avg);
    }

    /**
     * construct a Huffman tree using the an alphabet of characters 
     * and their frequencies
     */
    public LinkedBinaryTree makeHuffTree(Character chars[],Float[] freq)
    {
        int n = chars.length;
        //create a tree (with one element) for each character
        LinkedBinaryTree trees[] = new LinkedBinaryTree[n];
        for(int i=0;i<n;i++)
        {
            trees[i] = new LinkedBinaryTree();
            trees[i].addRoot(chars[i],freq[i]);
        }
        //create a PQ
        //each Element in the PQ is a LinkedBinaryTree
        //the Key of each Element is its frequency
        HeapPriorityQueue pq = new HeapPriorityQueue(freq,trees);
        
        //Variables that will be used in the while loop
        LinkedBinaryTree tree;
        Entry<Float,LinkedBinaryTree> x,y;
        Float f;
        
        //Extract the 2 minimum trees T1 and T2 of the PQ and create a new tree
        //In the new tree, T1 is the left subtree and T2 is the right subtree
        while(pq.size() > 1)
        {
            x = pq.removeMin();
            y = pq.removeMin();
            //the frequency of the new tree is the sum of the frequencies of the 2 subtrees
            f = x.getKey()+y.getKey();
            //create the new tree
            tree = new LinkedBinaryTree();
            tree.addRoot(null,f);
            tree.attach(tree.root(),x.getValue(),y.getValue());
            //insert the tree in the PQ
            pq.insert(f,tree);
        }
        
        //extract the last element of the PQ
        x = pq.removeMin();
        
        //return the Huffman tree
        return x.getValue();
    }
    
    /**
     * select only the characters from the alphabet that have strictly positive frequencies
     */
    public Character[] validateChars(Character[] chars, Float[] freq)
    {
        Character[] validChar;
        int count = 0;
        
        for(int i=0;i<chars.length;i++)
        {
            if(freq[i]>0)
                count++;
        }
        
        validChar = new Character[count];
        count = 0;
        for(int i=0;i<chars.length;i++)
        {
            if(freq[i]>0)
            {
                validChar[count] = chars[i];
                count++;
            }
        }
        
        return validChar;
    }
    
    /**
     * select only positive frequencies
     */
    public Float[] validateFrequencies(Float[] freq)
    {
        Float[] validFreq;
        int count = 0;
        
        for(int i=0;i<freq.length;i++)
        {
            if(freq[i]>0)
                count++;
        }
        
        validFreq = new Float[count];
        count = 0;
        for(int i=0;i<freq.length;i++)
        {
            if(freq[i]>0)
            {
                validFreq[count] = freq[i];
                count++;
            }
        }
        
        return validFreq;
    }
    
    /**
     * Find the frequency of each character in the string
     * The given string must contain only characters from a to (a+n) (not capital)
     */
    public Float[] findFrequencies(String s,int n, Character chars[])
    {
        int frequencies[] = new int[n];
        int sum = 0,x;
        Float freq[] = new Float[n];
        
        for(int i=0;i<n;i++)
            frequencies[i] = 0;
            
        for(int i=0;i<s.length();i++)
        {
            x = (int)(s.charAt(i)-'a');
            frequencies[x]++;
        }
            
        for(int i=0;i<n;i++)
            sum += frequencies[i];
        
        for(int i=0;i<n;i++)
            freq[i] = (float)frequencies[i]/sum;
            
        return freq;
    }
    
    /**
     * creates a random string of length n with characters a...('a'+n) (not capital)
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
    
    /**
     * Displaying the characters and their frequencies
     */
    public void printFrequencies(Character chars[],Float freq[])
    {
        for(int i=0;i<freq.length;i++)
            System.out.println(chars[i] + ": " + freq[i]);
        System.out.println();
    }
    
    /**
     * The two given arrays must be of same length
     */
    public void printCodes(Character c[], String s[])
    {
        for(int i=0;i<c.length;i++)
        {
            System.out.println(c[i] + ": " + s[c[i]-'a']);
        }
        System.out.println();
    }
    
    /**
     * find the code for each character using the path from the root to it
     */
    public void encodeTree(LinkedBinaryTree tree, Position pos, String codes[], String cur)
    {
        Position right = tree.right(pos);
        Position left = tree.left(pos);
        if(left != null)
            encodeTree(tree,left,codes,cur+"0");
        if(right != null)
            encodeTree(tree,right,codes,cur+"1");
            
        if(tree.isExternal(pos))
        {
            char c = (char)pos.getElement();
            int x = c-'a';
            codes[x] = cur;
        }
        
    }
    
    /**
     * encode a string s using a code for each character
     */
    public String encodeScript(String s, String codes[])
    {
        String codedScript="";
        char c;
        int x;
        
        for(int i=0;i<s.length();i++)
        {
            c = s.charAt(i);
            x = c-'a';
            codedScript = codedScript+codes[x];
        }
        
        return codedScript;
    }
    
    /**
     * decode a string consisting of 0s and 1s using a Huffman tree
     */
    public String decodeScript(String encodedScript, LinkedBinaryTree tree)
    {
        String script = "";
        Position current = tree.root();
        char binary,c;
        
        for(int i=0;i<encodedScript.length();i++)
        {
            binary = encodedScript.charAt(i);
            if(binary=='0')
                current = tree.left(current);
            else
                current = tree.right(current);
            //check if the current node is leaf
            if(tree.isExternal(current))
            {
                c = (char)current.getElement();
                script += c;
                current = tree.root();
            }
        }
        
        return script;
    }
}
