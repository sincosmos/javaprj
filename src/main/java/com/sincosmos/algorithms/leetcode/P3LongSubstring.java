package com.sincosmos.algorithms.leetcode;

/**
 * Given a string, find the length of the longest substring without repeating characters.
 *
 * Example 1:
 *
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 *
 * Input: "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 *
 * Input: "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 *              Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class P3LongSubstring {
    public static int lengthOfLongestSubstring(String s) {
        if(s==null || s.length() == 0) return 0;
        String lastLongest = "", ch;
        StringBuilder curLongest = new StringBuilder();
        for(int i=0; i<s.length(); ++i){
            ch = s.substring(i, i+1);
            int idx = curLongest.indexOf(ch);
            if(idx >= 0){//found
                if(curLongest.length() > lastLongest.length())
                    lastLongest = curLongest.toString();
                curLongest = new StringBuilder(curLongest.substring(idx + 1));
                curLongest.append(ch);
            }else{
                curLongest.append(ch);
            }
        }
        int cur = curLongest.length();
        int last = lastLongest.length();
        return cur > last ? cur : last;
    }

    public static void main(String[] args){
        lengthOfLongestSubstring("abcabcbb");
    }
}
