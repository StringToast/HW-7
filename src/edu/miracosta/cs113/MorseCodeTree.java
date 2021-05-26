package edu.miracosta.cs113;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

/**
 * MorseCodeTree : A BinaryTree, with Nodes of type Character to represent each
 * letter of the English alphabet, and a means of traversal to be used to
 * decipher Morse code.
 *
 * @version 1.0
 */
public class MorseCodeTree extends BinaryTree<String> {
	boolean addReturn;
	// TODO:
	// Build this class, which includes the parent BinaryTree implementation in
	// addition to
	// the `translateFromMorseCode` and `readMorseCodeTree` methods. Documentation
	// has been suggested for the former,
	// where said exceptional cases are to be handled according to the corresponding
	// unit tests.

	/**
	 * Non-recursive method for translating a String comprised of morse code values
	 * through traversals in the MorseCodeTree.
	 *
	 * The given input is expected to contain morse code values, with '*' for dots
	 * and '-' for dashes, representing only letters in the English alphabet.
	 *
	 * This method will also handle exceptional cases, namely if a given token's
	 * length exceeds that of the tree's number of possible traversals, or if the
	 * given token contains a character that is neither '*' nor '-'.
	 *
	 * @param morseCode The given input representing letters in Morse code
	 * @return a String representing the decoded values from morseCode
	 */

	/**
	 * Starter find method
	 * 
	 * @param the Comparable object being saught
	 * @return
	 * @return the object, if found. otherwise null
	 */
	// Creates a new binaryTree constructor, making morseCodeTree have a root node
	// that is null
	public MorseCodeTree() {
		super();
		populateTree("Letters.txt");
	}
	public MorseCodeTree(String fileName) {
		super();
		populateTree(fileName);
		
	}

	@SuppressWarnings("unchecked")
	public void populateTree(String fileName) {
		File file = new File("src/edu/miracosta/cs113/"+fileName);
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String morseCode;
			while ((morseCode = (String) br.readLine()) != null) {
				insertNodeIntoTree(morseCode);
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("file aint here.");
		} catch (IOException e) {
			System.out.println("IO exception");
		}
	}

	private void insertNodeIntoTree(String morseCode) {
		Node<String> current = this.root;
		if (current == null) {// No data in node, add data from text file
			this.root = new Node<String>(morseCode);
		} else {
			for (int i = 2; i < morseCode.length(); i++) {
				char mc = morseCode.charAt(i);
				//System.out.print(mc);
				if (mc == '*' && current.left != null) {
					current = current.left;
				} else if (mc == '-' && current.right != null) {
					current = current.right;
				} else if ((current.left == null) && (mc == '*')) {
					current.left = new Node<String>(morseCode.substring(0, 1));
//					 current.left.data = morseCode;
				} else {
					current.right = new Node<String>(morseCode.substring(0, 1));
//					 current.right.data = morseCode;
				}
			}
			// current = new Node<String>(morseCode.substring(0,1));
		}
		
	}

	public String translateFromMorseCode(String morseCode) {
		String message = "";
		String[] splitCode = morseCode.split(" ");
		Node<String> codeTraveler = root;
		for(int i  = 0; i < splitCode.length; i++) {
			if(splitCode[i].length() > 4) {
				throw new InputMismatchException();
			}
		}
		for (int i = 0; i < (morseCode.length()); i++) {
			if ((morseCode.charAt(i) != '-') && ((morseCode.charAt(i) != '*') && (morseCode.charAt(i) != ' '))) {
				throw new InputMismatchException();
			}
		}
		for (int i = 0; i < (morseCode.length()); i++) {
			codeTraveler = this.root;
			while ((i < (morseCode.length())) && (morseCode.charAt(i) != ' ')) {
				if ((morseCode.charAt(i) == '*')) {
					if (codeTraveler != null) {
						codeTraveler = codeTraveler.left;
					}
					break;
				}else
				{
					if (codeTraveler != null) {
						codeTraveler = codeTraveler.right;
					}
					break;

				}

			}
			if (codeTraveler != null) {
				message += codeTraveler.data;
			}
		}
//		System.out.println(message);
		return message;

	}

} // End of class MorseCodeTree