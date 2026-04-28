import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
    boolean a = true;
    for(int i = 0; i < phone_book.length; i++) {
        a = isHead(phone_book, i, 0);
        if(!a) break;
    }
    return a;


    }
    static boolean isHead(String[] phone_book,int index1, int index2) {
        if(index2 == phone_book.length) return true;
        if(index1 == index2) return isHead(phone_book, index1, index2 + 1);

        String phoneNum1 = phone_book[index1];
        String phoneNum2 = phone_book[index2];


        if(phoneNum1.length() > phoneNum2.length()) return isHead(phone_book, index1, ++index2);

        int i = 0;
        for(i=0; i<phoneNum1.length(); i++) {
            if(phoneNum1.charAt(i) != phoneNum2.charAt(i)) break;
        }
        if(i == phoneNum1.length()) return false;
        else return isHead(phone_book, index1, ++index2);

    }



}