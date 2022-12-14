package com.unfi.digital.day13;

public class Packet implements Comparable<Packet>{

  public Packet(String s){
    this.chars = s.toCharArray();
  }
  char[] chars;

  @Override
  public int compareTo(Packet o) {
    char[] left = this.chars;
    char[] right = o.chars;
    var l = 0;
    var r = 0;
    while (l < left.length && r < right.length){
      if (left[l] == ','){
        l++;
        continue;
      }
      if (right[r] == ','){
        r++;
        continue;
      }
      if (left[l] == '[' && right[r] == '[' || left[l] == ']' && right[r] == ']'){
        l++;
        r++;
        continue;
      }
      else if (Character.isDigit(left[l]) && Character.isDigit(right[r])){
        var lnum = 0;
        if (l+1 < left.length && Character.isDigit(left[l+1])){
          l++;
          lnum = 10;
        } else {
          lnum = Integer.parseInt(String.valueOf(left[l]));
        }
        var rnum = 0;
        if (r+1 < right.length && Character.isDigit(right[r+1])){
          r++;
          rnum = 10;
        } else {
          rnum = Integer.parseInt(String.valueOf(right[r]));
        }
        if (lnum < rnum){
          return -1;
        } else if (lnum > rnum){
          return 1;
        } else{
          l++;
          r++;
          continue;
        }
      }
      else if (left[l] == ']'){
        return -1;
      }
      else if (right[r] == ']'){
        return 1;
      }
      else if (left[l] == '[' && Character.isDigit(right[r])){
        while (l + 1 < left.length && left[l+1] == '['){
          l++;
        }
        //left is empty list
        if (l + 1 < left.length && left[l+1] == ']'){
          return -1;
        } else if (l + 2 < left.length && left[l+2] == ']'){
          //left is single digit list
          var rnum = 0;
          if (r+1 < right.length && Character.isDigit(right[r+1])){
            r++;
            rnum = 10;
          } else {
            rnum = Integer.parseInt(String.valueOf(right[r]));
          }
          if ( Integer.parseInt(String.valueOf(left[l+1])) < rnum) {
            return -1;
          } else if ( Integer.parseInt(String.valueOf(left[l+1])) > rnum){
            return 1;
          } else {
            l += 3;
            r++;
            continue;
          }
        }
        else if (l + 3 < left.length && left[l+3] == ']'){
          //single digit list but it's a 10
          l++;
          var rnum = 0;
          if (r+1 < right.length && Character.isDigit(right[r+1])){
            r++;
            rnum = 10;
          } else {
            rnum = Integer.parseInt(String.valueOf(right[r]));
          }
          if (10 < rnum) {
            return -1;
          } else if (10 > rnum){
            return 1;
          } else {
            l += 3;
            r++;
            continue;
          }
        } else {
          //r is a single digit list, l is multi-digit list
          l++;
          var lnum = 0;
          if (l+1 < left.length && Character.isDigit(left[l+1])){
            lnum = 10;
          } else {
            lnum = Integer.parseInt(String.valueOf(left[l]));
          }
          var rnum = 0;
          if (r+1 < right.length && Character.isDigit(right[r+1])){
            rnum = 10;
          } else {
            rnum = Integer.parseInt(String.valueOf(right[r]));
          }
          if (lnum < rnum){
            return -1;
          } else {
            return 1;
          }
        }
      } else if (right[r] == '[' && Character.isDigit(left[l])){
        while (r + 1 < right.length && right[r+1] == '['){
          r++;
        }
        if (r + 1 < right.length && right[r+1] == ']'){
          return 1;
        } else if (r + 2 < right.length && right[r+2] == ']'){
          var lnum = 0;
          if (l+1 < left.length && Character.isDigit(left[l+1])){
            l++;
            lnum = 10;
          } else {
            lnum = Integer.parseInt(String.valueOf(left[l]));
          }
          if ( Integer.parseInt(String.valueOf(right[r+1])) < lnum) {
            return 1;
          } else if ( Integer.parseInt(String.valueOf(right[r+1])) > lnum){
            return -1;
          } else {
            r += 3;
            l++;
            continue;
          }
        } else if (r + 3 < right.length && right[r+3] == ']'){
          r++;
          var lnum = 0;
          if (l+1 < left.length && Character.isDigit(left[l+1])){
            l++;
            lnum = 10;
          } else {
            lnum = Integer.parseInt(String.valueOf(left[l]));
          }
          if (10 < lnum) {
            return 1;
          } else if (10 > lnum){
            return -1;
          } else {
            r += 3;
            l++;
            continue;
          }
        } else {
          //l is a single digit list, r is multi-digit list
          r++;
          var rnum = 0;
          if (r+1 < right.length && Character.isDigit(right[r+1])){
            rnum = 10;
          } else {
            rnum = Integer.parseInt(String.valueOf(right[r]));
          }
          var lnum = 0;
          if (l+1 < left.length && Character.isDigit(left[l+1])){
            lnum = 10;
          } else {
            lnum = Integer.parseInt(String.valueOf(left[l]));
          }
          if (lnum < rnum){
            return -1;
          } else if (lnum > rnum){
            return 1;
          } else {
            return -1;
          }
        }
      } else {
        l++;
        r++;
      }
    }
    return 0;
  }
}
