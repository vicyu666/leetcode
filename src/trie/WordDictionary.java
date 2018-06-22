package trie;

public class WordDictionary {

    static class TrieNode{
        private TrieNode[] children;
        private boolean isEnd;

        public TrieNode(){
            children = new TrieNode[26];
            for(int i=0;i<26;i++) {
                children[i] = null;
            }
            isEnd = false;
        }

        public void insert(String word, int index) {
            if(index==word.length()) {
                this.isEnd = true;
                return;
            }
            int pos = word.charAt(index) - 'a';
            if(children[pos]==null)
                children[pos] = new TrieNode();
            children[pos].insert(word,index+1);
        }

        public TrieNode find(String word, int index) {
            if(index==word.length())
                return this;
            int pos = 0;
            if(word.charAt(index)=='.') {
                for(TrieNode node : children){
                    if(node!=null){
                        TrieNode tmp=node.find(word,index+1);
                        if(tmp!=null&&tmp.isEnd)
                            return tmp;
                    }
                }
            } else {
                pos = word.charAt(index) - 'a';
            }
            if(children[pos] == null)
                return null;
            return children[pos].find(word,index+1);
        }
    }

    private TrieNode root;
    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        root.insert(word,0);
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        TrieNode node = root.find(word,0);
        return node!=null && node.isEnd;
    }
}

/**
 * Your trie.WordDictionary object will be instantiated and called as such:
 * trie.WordDictionary obj = new trie.WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */