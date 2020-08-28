package com.sincosmos.algorithms.swordoffer;

public class P05ReplaceSpace {
    public String replaceSpace(String s) {
        if(s==null) return null;
        return s.replaceAll("\\s", "%20");
    }

    public String replaceSpace2(String s) {
        if(s==null) return null;
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<s.length(); ++i){
            if(s.charAt(i) == ' '){
                sb.append("%20");
            }else{
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
