import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;

public class WordCounter {
	private String urlStr;
    private String content;
    
    public WordCounter(String urlStr){
    	this.urlStr = urlStr;
    }
    
    private String fetchContent() throws IOException{
		URL url = new URL(this.urlStr);
		URLConnection conn = url.openConnection();
		InputStream in = conn.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
	
		String retVal = "";
	
		String line = null;
		
		while ((line = br.readLine()) != null){
		    retVal = retVal + line + "\n";
		}
		return retVal;
    }
    
    public int BoyerMoore(String T, String P){
        int i = P.length() - 1;
        int j = P.length() - 1;
        // Bonus: Implement Boyer-Moore Algorithm
        int l;
        int result = 0;
        while(T.length() - 1 > i) {
        	if(Character.compare(T.charAt(i), P.charAt(j)) == 0) {
        		if(j == 0) {
        			result++;
            		i += P.length();
            		j = P.length() - 1;
        		}
        		else {
        			i--;
        			j--;
        		}
        	}
        	else {
        		l = last(T.charAt(i), P);
        		i = i + P.length() - min(j, l + 1);
        		j = P.length() - 1;
        	}
        } 
        return result;
    }

    public int last(char c, String P){
    	// Bonus: Implement last occurence function
    	return P.lastIndexOf(c); 
    }

    public int min(int a, int b){
        if (a < b)
            return a;
        else if (b < a)
            return b;
        else 
            return a;
    }
    
    public int countKeyword(String keyword) throws IOException{
		if (content == null){
		    content = fetchContent();
		}
		
		//To do a case-insensitive search, we turn the whole content and keyword into upper-case:
		content = content.toUpperCase();
		keyword = keyword.toUpperCase();
	
		int retVal = 0; 
		// 1. calculates appearances of keyword (Bonus: Implement Boyer-Moore Algorithm
		retVal = BoyerMoore(content, keyword);
		return retVal;
    }
}
