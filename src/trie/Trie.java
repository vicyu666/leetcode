package trie;

/**      trie.Trie                vs                   HashMap
 * Time  O(1) for String OR O(n) for Character,    same
 * Space O(k) k<=n                            ,    O(n)
 * Operation  with prefix                     ,  not with */
public class Trie {

    static class TrieNode{
        private TrieNode[] children;
        private boolean isEnd;

        public TrieNode() {
            isEnd = false;
            children = new TrieNode[26];
            for (int i = 0; i < 26; i++) {
                children[i] = null;
            }
        }

        public void insert (String word,int index){
            if (index == word.length()) {
                this.isEnd = true;
                return;
            }
            int pos = word.charAt(index) - 'a';
            if (children[pos] == null) {
                children[pos] = new TrieNode();
            }
            children[pos].insert(word, pos + 1);
        }

        public TrieNode find (String word,int index){
            if (index == word.length())
                return this;
            int pos = word.charAt(index) - 'a';
            if (children[pos] == null)
                return null;
            return children[pos].find(word, index + 1);
        }
    }

    class TrieTree {
        private TrieNode root;

        public TrieTree(){
            root = new TrieNode();
        }

        public void insert(String word) {
            root.insert(word,0);
        }

        public boolean search(String word) {
            TrieNode node = root.find(word,0);
            return node!=null && node.isEnd;
        }

        public boolean startWith(String prefix) {
            TrieNode node = root.find(prefix,0);
            return node!=null;
        }
    }

}
