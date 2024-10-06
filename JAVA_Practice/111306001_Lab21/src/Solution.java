
public class Solution {
	
	public String recapitalized(String sentence) {
	// Here is your code //
		String [] word = sentence.split(" ");
		String newSentence = "";
		for(int i = 0; i < word.length; i++) {
			if(word[i].length() < 3) {
				newSentence += word[i].toLowerCase() + " ";
			}
			else {
				newSentence += word[i].substring(0,1).toUpperCase() + word[i].substring(1, word[i].length()).toLowerCase() + " ";
			}
		}
		return newSentence;
	}
	public int counting(String s, int k) {
	// Here is your code //
		char [] letter = s.toCharArray();
		String num = "";
		for(int i = 0; i < letter.length; i++) {
			num += Integer.toString(Character.getNumericValue(letter[i]) - 9);
		}
		long transform = Long.parseLong(num);
		int transit = 0;
		int result = 0;
		for(int j = 0; j < k; j++) {
			transit = 0;
			while(transform >= 10) {
				transit += transform % 10;
				transform /= 10;
			}
			transit += transform;
			transform = transit;
		}
		result = transit;
		return result;
	}
}
