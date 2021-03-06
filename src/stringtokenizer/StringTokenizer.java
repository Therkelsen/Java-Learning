
package src.stringtokenizer;

public class StringTokenizer {

    String tekst;
    String delim;
    int indeks;

    public StringTokenizer() {
        tekst = "N/A";
        delim = "\n";
        indeks = 0;
    }

    public StringTokenizer(String source) {
        tekst = source;
        delim = "\n";
        indeks = 0;
    }

    public StringTokenizer(String source, String delimiters) {
        tekst = source;
        delim = delimiters;
        indeks = 0;
    }

    public String getSource() {
        return tekst;
    }
    
    //Denne
    public boolean isDelimiter(char tegn) {
        for (int i = 0; i < delim.length(); i++) {
            if (tegn == delim.charAt(i)) {
                return true;
            }
        }
        return false;
    }

    //Denne
    public boolean hasMoreTokens(){
        for (int i = indeks; i < tekst.length(); i++) {
            if (!isDelimiter(tekst.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    //Denne
    public String nextToken() {
        String token = "";
        
        while (indeks < tekst.length() && isDelimiter(tekst.charAt(indeks))) {
            indeks++;
        }

        while (indeks < tekst.length() && !isDelimiter(tekst.charAt(indeks))) {
            token += tekst.charAt(indeks);
            indeks ++;
        }
        
        return token;
    }

    public String nextToken(String delimiters) {
        delim = delimiters;
        return nextToken();
    }

    public int countTokens() {
        int counter = 0;
        while (nextToken() != "") {
            counter++;
        }
        return counter;
    }
}
