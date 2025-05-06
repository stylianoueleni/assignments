import java.util.*;
/**
 * Write a description of class LZ77 here.
 *
 * Eleni Stylianou ge21708
 * December 2024
 */
public class LZ77
{
    public static void main(String args[])
    {
        System.out.print('\u000C');
        String s="abracadabra";
        LZ77 lz77 = new LZ77();
        SinglyLinkedList coded = lz77.encode(s,7,6);
        System.out.println("Initial script: ");
        System.out.println(s);
        System.out.println("Encoded script: ");
        System.out.println(coded.toString());
        String decoded = lz77.decode(coded);
        System.out.println("Decoded script: ");
        System.out.println(decoded);
    }
    
    /**
     * constructor
     */
    public LZ77() {}
    
    /**
     * encoding process
     * returns a SinglyLinkedList that contains the triplets of the LZ77 encoding 
     */
    public SinglyLinkedList encode(String s,int searchBufferSize, int lookAheadBufferSize)
    {
        SinglyLinkedList<LZ77triplet> list = new SinglyLinkedList<>();
        int i=0,start,end,size=s.length();
        LZ77triplet triplet;
        
        while(i<size)
        {
            //find the biggest match and add it to the list
            start = Math.max(0,i-searchBufferSize);
            end = Math.min(size-1,i+lookAheadBufferSize-1);
            triplet = findMatch(s,i,start,end);
            list.addLast(triplet);
            i += triplet.length()+1;
        }
        
        return list;
    }
    
    /**
     * finds the longest match of strings using a search buffer and a look-ahead buffer
     */
    public LZ77triplet findMatch(String s, int i, int start, int end)
    {
        LZ77triplet triplet = new LZ77triplet(0,0,s.charAt(i));
        int k,maxk=0;
        
        for(int j=start;j<i;j++)
        {
            k=0;
            while(i+k<=end && s.charAt(j+k)==s.charAt(i+k))
                k++;
            if(k>maxk)
            {
                maxk = k;
                triplet.setLength(k);
                triplet.setOffset(i-j);
                if(i+k==s.length())
                    triplet.setCharacter('\0'); //empty character
                else
                    triplet.setCharacter(s.charAt(i+k));
            }
        }
        
        return triplet;
    }
    
    /**
     * decoding process
     * returns the decoded string using a SinglyLinkedList as encoded form
     */
    public String decode(SinglyLinkedList<LZ77triplet> list)
    {
        String decoded = "";
        LZ77triplet triplet;
        int k;  //the current character in the string
        int length,offset;
        char c;
        
        while(!list.isEmpty())
        {
            triplet = list.removeFirst();
            offset = triplet.offset();
            k = decoded.length();
            for(int j=0;j<triplet.length();j++)
                decoded += decoded.charAt(k-triplet.offset()+j);
            decoded += triplet.character();    
        }
        
        return decoded;
    }
}
