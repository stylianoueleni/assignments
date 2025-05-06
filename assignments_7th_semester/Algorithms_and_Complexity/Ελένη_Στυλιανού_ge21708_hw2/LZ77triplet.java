class LZ77triplet
    {
        private int offset;
        private int length;
        private char character;
        
        public LZ77triplet(int o, int l, char c)
        {
            offset = o;
            length = l;
            character = c;
        }
        
        public LZ77triplet(char c)
        {
            offset=0;
            length = 0;
            character = c;
        }
        
        public int offset() {return offset;}
        public int length() {return length;}
        public char character() {return character;}
        public void setOffset(int o) {offset = o;}
        public void setLength(int l) {length = l;}
        public void setCharacter(char c) {character = c;}
        public String toString()
        {
            return "(" + offset + ", " + length + ", " + character + ")";
        }
    }