package spelling;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * An trie data structure that implements the Dictionary and the AutoComplete
 * ADT
 * 
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements Dictionary, AutoComplete {

	private TrieNode root;
	private int size;

	public AutoCompleteDictionaryTrie() {
		root = new TrieNode();
	}

	/**
	 * Insert a word into the trie. For the basic part of the assignment (part
	 * 2), you should ignore the word's case. That is, you should convert the
	 * string to all lower case as you insert it.
	 */
	public boolean addWord(String word) {

		if (root.getText().equals(word.toLowerCase()) || isWord(word)) {
			return false;

		} else {
			char[] lowerCaseWordCharArray = word.toLowerCase().toCharArray();
			TrieNode nextNode = root;

			for (char lowerCaseChar : lowerCaseWordCharArray) {

				if (nextNode.getValidNextCharacters().contains(lowerCaseChar)) {
					nextNode = nextNode.getChild(lowerCaseChar);

				} else {
					nextNode = nextNode.insert(lowerCaseChar);
				}

			}

			size++;
			nextNode.setEndsWord(true);
			return true;
		}

	}

	/**
	 * Return the number of words in the dictionary. This is NOT necessarily the
	 * same as the number of TrieNodes in the trie.
	 */
	public int size() {
		return size;
	}

	/** Returns whether the string is a word in the trie */
	@Override
	public boolean isWord(String s) {
		char[] lowerCaseWordCharArray = s.toLowerCase().toCharArray();
		TrieNode nextNode = root;

		for (char lowerCaseChar : lowerCaseWordCharArray) {
			nextNode = nextNode.getChild(lowerCaseChar);

			if (nextNode == null)
				return false;
		}

		return nextNode.endsWord();
	}

	/**
	 * * Returns up to the n "best" predictions, including the word itself, in
	 * terms of length If this string is not in the trie, it returns null.
	 * 
	 * @param text
	 *            The text to use at the word stem
	 * @param n
	 *            The maximum number of predictions desired.
	 * @return A list containing the up to n best predictions
	 */
	@Override
	public List<String> predictCompletions(String prefix, int numCompletions) {
		List<String> possibleCompletions = new ArrayList<>();
		
		char[] lowerCasePrefixCharArray = prefix.toLowerCase().toCharArray();
		TrieNode nodeForChar = root;

		for (char lowerCasePrefixChar : lowerCasePrefixCharArray) {
			nodeForChar = nodeForChar.getChild(lowerCasePrefixChar);

			if (nodeForChar == null) {
				return possibleCompletions;
			}
		}

		Queue<TrieNode> candidateNodes = new LinkedList<>();
		boolean enoughCompletions = false;

		candidateNodes.add(nodeForChar);

		while (!candidateNodes.isEmpty() && !enoughCompletions) {
			TrieNode nextCandidate = candidateNodes.remove();

			if (nextCandidate.endsWord())
				possibleCompletions.add(nextCandidate.getText());

			for (Character nextCandidateChildren : nextCandidate.getValidNextCharacters()) {
				candidateNodes.add(nextCandidate.getChild(nextCandidateChildren));
			}

			enoughCompletions = possibleCompletions.size() == numCompletions;
		}

		return possibleCompletions;
	}

	// For debugging
	public void printTree() {
		printNode(root);
	}

	/** Do a pre-order traversal from this node down */
	public void printNode(TrieNode curr) {
		if (curr == null)
			return;

		System.out.println(curr.getText());

		TrieNode next = null;
		for (Character c : curr.getValidNextCharacters()) {
			next = curr.getChild(c);
			printNode(next);
		}
	}

}