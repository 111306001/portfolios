import java.util.*;

class Solution {
    public boolean solution(String moves) {
        ArrayList<Character> charList1 = new ArrayList<Character>();
        ArrayList<Character> charList2 = new ArrayList<Character>();
        for (int i = 0; i < moves.length(); i++) {
            charList1.add(moves.charAt(i));
        }
        for (int i = moves.length()-1; i >= 0; i--) {
            charList2.add(moves.charAt(i));
        }
        int left = 0;
        int right = 0;
        int up = 0;
        int down = 0;

        for(int i = 0; i < charList1.size(); i++){
            if(charList1.get(i) == '>'){
                right++;
            }
            else if(charList1.get(i) == '<'){
                left++;
            }
            else if(charList1.get(i) == '^'){
                up++;
            }
            else if(charList1.get(i) == 'v'){
                down++;
            }
        }
        if(right == left && up == down){
        	for (int i = 0; i < moves.length(); i++) {
        		if(!charList1.get(i).equals(charList2.get(i))) {
        			return false;
        		}
        	}
            return true;
        }
        else{
            return false;
        }
    }   
}
