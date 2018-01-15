import java.util.*;
public class Demo {
    public static void main(String[] args){
        System.out.println("Please enter the text to encode");
        Scanner inputText = new Scanner(System.in);
        String text = inputText.nextLine();
        System.out.println("Please enter the shiftValue");
        Scanner inputShiftValue = new Scanner(System.in);
        int shiftValue = inputShiftValue.nextInt();
        System.out.println("Please enter the letterPerGroup");
        Scanner inputLetterPerGroup = new Scanner(System.in);
        int letterPerGroup = inputLetterPerGroup.nextInt();
        String finalString = encryptString(text,shiftValue,letterPerGroup);
        System.out.println("The encoded text is: "+finalString);
    }

    public static String normalizeText(String inputString){
        String normalizedText;
        StringBuffer letter = new StringBuffer();
        for (int i = 0; i < inputString.length(); i++){
            char temp = inputString.charAt(i);
            if (temp <= 'z' && temp >= 'a'|| temp <= 'Z' && temp >= 'A'){
                letter.append(temp);
            }
        }
        normalizedText = letter.toString();
        normalizedText = normalizedText.toUpperCase();
        return normalizedText;
    }

    public static String oblify(String normalizedText){
        String oblifiedText;
        StringBuffer letter = new StringBuffer();
        for (int i = 0; i < normalizedText.length(); i++){
            char temp = normalizedText.charAt(i);
            if (temp == 'A' || temp == 'E'|| temp == 'I' || temp == 'O' || temp == 'U' || temp == 'Y'){
                letter.append('O');
                letter.append('B');
                letter.append(temp);
            }
            else {
                letter.append(temp);
            }
        }
        oblifiedText = letter.toString();
        return oblifiedText;
    }

    public static String ceasarify(String oblifiedText, int shiftValue){
        String ceasarifiedText;
        String shiftAlphabetList = shiftAlphabet(shiftValue);
        StringBuffer letter = new StringBuffer();
        for (int i =0; i < oblifiedText.length(); i++){
            char temp = oblifiedText.charAt(i);
            letter.append(shiftAlphabetList.charAt((int)temp - 65));
        }
        ceasarifiedText = letter.toString();
        return ceasarifiedText;
    }

    public static String shiftAlphabet(int shift) {
        int start = 0;
        if (shift < 0) {
            start = (int) 'Z' + shift + 1;
        } else {
            start = 'A' + shift;
        }
        String result = "";
        char currChar = (char) start;
        for(; currChar <= 'Z'; ++currChar) {
            result = result + currChar;
        }
        if(result.length() < 26) {
            for(currChar = 'A'; result.length() < 26; ++currChar) {
                result = result + currChar;
            }
        }
        return result;
    }


    public static String groupify(String ceasarifiedText, int letterPerGroup){
        int xNum = letterPerGroup - ceasarifiedText.length()%letterPerGroup;
        StringBuffer letter = new StringBuffer();
        for (int i = 0; i < ceasarifiedText.length(); i++){
            char temp =ceasarifiedText.charAt(i);
            if (i%letterPerGroup == 0 && i != 0){
                letter.append(' ');
            }
            letter.append(temp);
        }
        for (;xNum > 0; xNum--){
            if (letterPerGroup ==1){
                break;
            }
            letter.append('x');
        }
        String groupifiedText = letter.toString();
        return groupifiedText;
    }

    public static String encryptString(String text,int shiftValue ,int letterPerGroup){
        String normalizeText = normalizeText(text);
        String oblifiedText = oblify(normalizeText);
        String ceasarifiedText = ceasarify(oblifiedText, shiftValue);
        String groupifiedText = groupify(ceasarifiedText, letterPerGroup);
        return groupifiedText;
    }
}
