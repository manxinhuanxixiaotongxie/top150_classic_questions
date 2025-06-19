class WordDictionary {
    private CodeTrie root;

    /**
     * 前缀树 + dfs
     */
    public WordDictionary() {
        root = new CodeTrie();
    }

    public void addWord(String word) {
        root.insert(word);
    }

    public boolean search(String word) {
        return process(word, 0, root);
    }

    private boolean process(String word, int index, CodeTrie node) {
        if (index == word.length()) {
            return node.isEnd();
        }
        char ch = word.charAt(index);
        if (Character.isLetter(ch)) {
            int childIndex = ch - 'a';
            CodeTrie child = node.getChildren()[childIndex];
            if (child != null && process(word, index + 1, child)) {
                return true;
            }
        } else {
            for (int i = 0; i < 26; i++) {
                CodeTrie child = node.getChildren()[i];
                if (child != null && process(word, index + 1, child)) {
                    return true;
                }
            }
        }
        return false;
    }
}

class Trie {
    private CodeTrie[] children;
    private boolean isEnd;

    public Trie() {
        children = new CodeTrie[26];
        isEnd = false;
    }

    public void insert(String word) {
        CodeTrie node = this;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                node.children[index] = new CodeTrie();
            }
            node = node.children[index];
        }
        node.isEnd = true;
    }

    public CodeTrie[] getChildren() {
        return children;
    }

    public boolean isEnd() {
        return isEnd;
    }
}
