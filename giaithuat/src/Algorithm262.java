import java.util.HashMap;
import java.util.Map;

public class Algorithm262 {
    public static void main(String[] args) {
        String str = "aabacsdceeeeead     ";
        Map<Character, Integer> charCount = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if(charCount.containsKey(c)){
                charCount.put(c,charCount.get(c)+1);
            }else{
                charCount.put(c,1);
            }
        }
        for(Map.Entry<Character,Integer> entry : charCount.entrySet()){
            System.out.println(entry.getKey()+" : " +entry.getValue());
        }
    }

}
