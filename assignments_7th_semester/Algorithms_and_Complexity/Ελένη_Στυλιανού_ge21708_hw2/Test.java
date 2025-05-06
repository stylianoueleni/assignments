import java.util.*;
/**
 * Write a description of class Test here.
 *
 * Eleni Stylianou ge21708
 * December 2024
 */
public class Test
{
    
    public static void main(String args[])
    {
        System.out.print('\u000C');
        int size=10000;       //string size in each test case
        int n=5;           //size of the alphabet
        int testCases=50;   //number of test cases
        long startTime,endTime,elapsedTime,totalTime;
        long encodingTimes[][] = new long[5][testCases];
        long decodingTimes[][] = new long[5][testCases];
        long encodingAvg[] = {0,0,0,0,0};
        long decodingAvg[] = {0,0,0,0,0};
        long sizes[][] = new long[5][testCases];
        long avgSizes[] = {0,0,0,0,0};
        Float freq[] = new Float[n];
        Character chars[] = new Character[n];
        String codes[] = new String[n];
        LinkedBinaryTree tree;
        for(int i=0;i<n;i++){
            chars[i] = (char)('a' + i);
        }
        
        for(int j=0;j<testCases;j++)
        {
            String s = randomString(size,n);
            String encoded,decoded;
            
            //Huffman algorithm
            //start time for encoding
            startTime = System.nanoTime();
            Huffman huffman = new Huffman();
            freq = huffman.findFrequencies(s,n,chars);
            chars = huffman.validateChars(chars,freq);
            freq = huffman.validateFrequencies(freq);
            tree = huffman.makeHuffTree(chars,freq);
            huffman.encodeTree(tree,tree.root(),codes,"");
            encoded = huffman.encodeScript(s,codes);
            endTime = System.nanoTime();
            //end time for encoding
            elapsedTime = endTime-startTime;
            encodingTimes[0][j] = elapsedTime/1000;
            //save encoded string size
            sizes[0][j] = encoded.length()/8;
            //start time for decoding
            startTime = System.nanoTime();
            decoded = huffman.decodeScript(encoded,tree);
            endTime = System.nanoTime();
            //end time for decoding
            elapsedTime = endTime-startTime;
            decodingTimes[0][j] = elapsedTime/1000;
            
            //AdaptiveHuffman
            AdaptiveHuffman adaptiveHuff = new AdaptiveHuffman();
            //start time for encoding
            startTime = System.nanoTime();
            encoded = adaptiveHuff.encode(s,n);
            endTime = System.nanoTime();
            //end time for encoding
            elapsedTime = endTime-startTime;
            encodingTimes[1][j] = elapsedTime/1000;
            //save encoded string size
            sizes[1][j] = encoded.length()/8;
            //start time for decoding
            startTime = System.nanoTime();
            decoded = adaptiveHuff.decode(encoded,n);
            endTime = System.nanoTime();
            //end time for decoding
            elapsedTime = endTime-startTime;
            decodingTimes[1][j] = elapsedTime/1000;
            
            //LZ77 algorithm
            LZ77 lz77 = new LZ77();
            //start time for encoding
            startTime = System.nanoTime();
            SinglyLinkedList LZ77encoded;
            LZ77encoded = lz77.encode(s,51,50);
            endTime = System.nanoTime();
            //end time for encoding
            elapsedTime = endTime-startTime;
            encodingTimes[2][j] = elapsedTime/1000;
            //save encoded string size
            sizes[2][j] = LZ77encoded.size()*3;
            //start time for decoding
            startTime = System.nanoTime();
            decoded = lz77.decode(LZ77encoded);
            endTime = System.nanoTime();
            //end time for decoding
            elapsedTime = endTime-startTime;
            decodingTimes[2][j] = elapsedTime/1000;
            
            // LZ78 algorithm
            LZ78 lz78 = new LZ78();
            // Encoding
            startTime = System.nanoTime();
            List<LZ78Pair> lz78Encoded = lz78.encode(s);
            endTime = System.nanoTime();
            encodingTimes[3][j] = (endTime - startTime) / 1000;
            sizes[3][j] = lz78Encoded.size() * 3; // Assuming 3 bytes per pair
            // Decoding
            startTime = System.nanoTime();
            decoded = lz78.decode(lz78Encoded);
            endTime = System.nanoTime();
            decodingTimes[3][j] = (endTime - startTime) / 1000;

            // LZW algorithm
            LZW lzw = new LZW();
            // Encoding
            startTime = System.nanoTime();
            List<Integer> lzwEncoded = lzw.encode(s);
            endTime = System.nanoTime();
            encodingTimes[4][j] = (endTime - startTime) / 1000;
            sizes[4][j] = lzwEncoded.size() * 4; // Assuming 4 bytes per integer
            // Decoding
            startTime = System.nanoTime();
            decoded = lzw.decode(lzwEncoded);
            endTime = System.nanoTime();
            decodingTimes[4][j] = (endTime - startTime) / 1000;
        }
        
        for(int i=0;i<5;i++)
        {
            encodingAvg[i] = findSum(encodingTimes[i])/testCases;
            decodingAvg[i] = findSum(decodingTimes[i])/testCases;
            avgSizes[i] = findSum(sizes[i])/testCases;
        }
        
        //Ignore the first test case of Huffman 
        long x = encodingAvg[0];
        x = x*testCases-encodingTimes[0][0];
        x = x/(testCases-1);
        encodingAvg[0] = x;
        
        System.out.println("Experiment with " + testCases + " test cases");
        System.out.println("Length of the random string in each test case: " + size);
        System.out.println("An alphabet of " + n + " characters was used");
        System.out.println("WARNING: In the times below ms means micro-seconds");
        System.out.println();
        
        printTimes(encodingTimes,decodingTimes);
        System.out.println();
        printAvg(encodingAvg,decodingAvg);
        System.out.println();
        printSizes(sizes);
        System.out.println();
        printAvgSizes(avgSizes,size);
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
    
    public static long findSum(long a[])
    {
        long sum=0;
        
        for(int i=0;i<a.length;i++)
            sum += a[i];
        
        return sum;
    }
    
    public static void printTimes(long encodingTimes[][], long decodingTimes[][])
    {
        System.out.println("TIMES");
        int testCases = encodingTimes[0].length;
        String s;
        System.out.print("Test Case:                 ");
        for(int i=0;i<testCases;i++)
            System.out.print(String.format("%9d",i+1));
        System.out.println();
        //Huffman
        System.out.print("Huffman encoding:          ");
        for(int i=0;i<testCases;i++)
        {
            s = String.format("%7d",encodingTimes[0][i]);
            s += "ms";
            System.out.print(s);
        }
        System.out.println();
        System.out.print("Huffman decoding:          ");
        for(int i=0;i<testCases;i++)
        {
            s = String.format("%7d",decodingTimes[0][i]);
            s += "ms";
            System.out.print(s);
        }
        System.out.println();
        
        System.out.print("Adaptive Huffman encoding: ");
        //Adaptive Huffman
        for(int i=0;i<testCases;i++)
        {
            s = String.format("%7d",encodingTimes[1][i]);
            s += "ms";
            System.out.print(s);
        }
        System.out.println();
        System.out.print("Adaptive Huffman decoding: ");
        for(int i=0;i<testCases;i++)
        {
            s = String.format("%7d",decodingTimes[1][i]);
            s += "ms";
            System.out.print(s);
        }
        System.out.println();
        
        System.out.print("LZ77 encoding:             ");
        //LZ77
        for(int i=0;i<testCases;i++)
        {
            s = String.format("%7d",encodingTimes[2][i]);
            s += "ms";
            System.out.print(s);
        }
        System.out.println();
        System.out.print("LZ77 decoding:             ");
        for(int i=0;i<testCases;i++)
        {
            s = String.format("%7d",decodingTimes[2][i]);
            s += "ms";
            System.out.print(s);
        }
        System.out.println();
        //LZ78
        System.out.print("LZ78 encoding:             ");
        for(int i=0;i<testCases;i++)
        {
            s = String.format("%7d",encodingTimes[3][i]);
            s += "ms";
            System.out.print(s);
        }
        System.out.println();
        System.out.print("LZ78 decoding:             ");
        for(int i=0;i<testCases;i++)
        {
            s = String.format("%7d",decodingTimes[3][i]);
            s += "ms";
            System.out.print(s);
        }
        System.out.println();
        
        //LZW
        System.out.print("LZW encoding:              ");
        for(int i=0;i<testCases;i++)
        {
            s = String.format("%7d",encodingTimes[4][i]);
            s += "ms";
            System.out.print(s);
        }
        System.out.println();
        System.out.print("LZW decoding:              ");
        for(int i=0;i<testCases;i++)
        {
            s = String.format("%7d",decodingTimes[4][i]);
            s += "ms";
            System.out.print(s);
        }
        System.out.println();
    }
    
    public static void printAvg(long[] encoding, long[] decoding)
    {
        System.out.println("AVERAGE TIMES");
        System.out.print("Huffman encoding:          ");
        System.out.println(encoding[0]+"ms");
        System.out.print("Huffman decoding:          ");
        System.out.println(decoding[0]+"ms");
        System.out.print("Adaptive Huffman encoding: ");
        System.out.println(encoding[1]+"ms");
        System.out.print("Adaptive Huffman decoding: ");
        System.out.println(decoding[1]+"ms");
        System.out.print("LZ77 encoding:             ");
        System.out.println(encoding[2]+"ms");
        System.out.print("LZ77 decoding:             ");
        System.out.println(decoding[2]+"ms");    
        System.out.print("LZ78 encoding:             ");
        System.out.println(encoding[3] +"ms");
        System.out.print("LZ78 decoding:             ");
        System.out.println(decoding[3] +"ms");
        System.out.print("LZW encoding:              ");
        System.out.println(encoding[4] +"ms");
        System.out.print("LZW decoding:              ");
        System.out.println(decoding[4] +"ms");
    }
    
    public static void printSizes(long[][] sizes)
    {
        System.out.println("SIZES");
        int testCases = sizes[0].length;
        String s;
        System.out.print("Test Case:        ");
        for(int i=0;i<testCases;i++)
            System.out.print(String.format("%11d",i+1));
        System.out.println();
        printRow("Huffman sizes:         ",sizes[0],"bytes","%6d");
        printRow("Adaptive Huffman sizes:",sizes[1],"bytes","%6d");
        printRow("LZ77 sizes:            ",sizes[2],"bytes","%6d");
        printRow("LZ78 sizes:            ", sizes[3], "bytes", "%6d");
        printRow("LZW sizes:             ", sizes[4], "bytes", "%6d");
    }
    
    public static void printRow(String name, long[] a, String metrics, String space)
    {
        System.out.print(name);
        int n = a.length;
        String s;
        for(int i=0;i<n;i++)
        {
            s = String.format(space,a[i]);
            s += metrics;
            System.out.print(s);
        }
        System.out.println();
    }
    
    public static void printAvgSizes(long[] avg,int size)
    {
        System.out.println("AVERAGE SIZES");
        System.out.print("Huffman encoding:          ");
        System.out.println(avg[0]+"bytes (compression rate: " + 100.0*avg[0]/size + "%)");
        System.out.print("Adaptive Huffman encoding: ");
        System.out.println(avg[1]+"bytes (compression rate: " + 100.0*avg[1]/size + "%)");
        System.out.print("LZ77 encoding:             ");
        System.out.println(avg[2]+"bytes (compression rate: " + 100.0*avg[2]/size + "%)");        
        System.out.print("LZ78 encoding:             ");
        System.out.println(avg[3] + "bytes (compression rate: " + 100.0 * avg[0] / size + "%)");
        System.out.print("LZW encoding:              ");
        System.out.println(avg[4] + "bytes (compression rate: " + 100.0 * avg[1] / size + "%)");
    }
}
