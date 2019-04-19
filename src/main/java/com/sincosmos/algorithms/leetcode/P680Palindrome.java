package com.sincosmos.algorithms.leetcode;

/**
 * Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
 *
 * Example 1:
 * Input: "aba"
 * Output: True
 * Example 2:
 * Input: "abca"
 * Output: True
 * Explanation: You could delete the character 'c'.
 * Note:
 * The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
 *
 * "ebcb b e ce ca b ba cec b bcbe"
 */
public class P680Palindrome {

    public boolean palindrome(String s){
        int i = 0, j= s.length() -1;
        while(i<j){
            if(s.charAt(i) ==  s.charAt(j)){
                i++;
                j--;
            }else{
                return false;
            }
        }
        return true;
    }

    public boolean validPalindrome(String s) {
        int i = 0, j= s.length() -1;
        boolean del = false;
        while(i<j){
            if(s.charAt(i) ==  s.charAt(j)){
                i++;
                j--;
            }else{
                if(!del){
                    return palindrome(s.substring(i, j-1)) || palindrome(s.substring(i+1,j));
                }else{
                    return false;
                }
            }
        }
        return true;
    }
}
