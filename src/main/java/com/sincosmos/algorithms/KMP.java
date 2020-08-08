package com.sincosmos.algorithms;

public class KMP {
    public int[] next(String pattern){
        int[] next = new int[pattern.length()];
        return null;
    }

    public int strStr(String haystack, String needle) {
        int[] next = this.next(needle);
        int i=0, j=0;
        while(i<haystack.length() && j<needle.length()){
            if(j == -1 || haystack.charAt(i) == needle.charAt(j)){
                i++;
                j++;
            }else{
                j = next[j];
            }
        }
        if(j== needle.length()){
            return i-j;
        }else{
            return -1;
        }
    }
}
