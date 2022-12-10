package com.unfi.digital.day8;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Day8 {


  public static void part1(String[] args) throws IOException {
    var in = Day8.class.getResourceAsStream("/com/unfi/digital/day8/input.txt");
    var input = new String(in.readAllBytes()).split("\r\n");
    Set<String> visibleTrees = new HashSet<>();
    var matrix = Arrays.stream(input).map(String::toCharArray).collect(Collectors.toList());
    //left to right
    for (int i = 0; i < matrix.size(); i++){
      int max = -1;
      for (int j = 0; j < matrix.get(0).length; j++){
        var val = matrix.get(i)[j];
        var number = Integer.parseInt(String.valueOf(val));
        if (number > max){
          visibleTrees.add(i+","+j);
          max = number;
        }
      }
    }
    //right to left
    for (int i = 0; i < matrix.size(); i++){
      int max = -1;
      for (int j = matrix.get(0).length - 1; j > -1; j--){
        var val = matrix.get(i)[j];
        var number = Integer.parseInt(String.valueOf(val));
        if (number > max){
          visibleTrees.add(i+","+j);
          max = number;
        }
      }
    }
    //up to down
    for (int i = 0; i < matrix.get(0).length; i++){
      int max = -1;
      for (int j = 0; j < matrix.size(); j++){
        var val = matrix.get(j)[i];
        var number = Integer.parseInt(String.valueOf(val));
        if (number > max){
          visibleTrees.add(j+","+i);
          max = number;
        }
      }
    }
    //down to up
    for (int i = 0; i < matrix.get(0).length; i++){
      int max = -1;
      for (int j = matrix.size() - 1; j > -1; j--){
        var val = matrix.get(j)[i];
        var number = Integer.parseInt(String.valueOf(val));
        if (number > max){
          visibleTrees.add(j+","+i);
          max = number;
        }
      }
    }
    System.out.println(visibleTrees.size());
  }

  public static void main(String[] args) throws IOException {
    var in = Day8.class.getResourceAsStream("/com/unfi/digital/day8/input.txt");
    var input = new String(in.readAllBytes()).split("\r\n");
    var matrix = Arrays.stream(input).map(String::toCharArray).collect(Collectors.toList());
    Map<String, Integer> map = new HashMap<>();
    var max = 0;
    for (int i = 0; i < matrix.size(); i++){
      for (int j = 0; j < matrix.get(0).length; j++){
        var val = matrix.get(i)[j];
        var number = Integer.parseInt(String.valueOf(val));
        var right = 0;
        var left = 0;
        var up = 0;
        var down = 0;
        //to the right
        for (int k = j+1; k < matrix.get(0).length; k++){
          right++;
          if (Integer.parseInt(String.valueOf(matrix.get(i)[k])) >= number){
            break;
          }
        }
        //to the left
        for (int k = j-1; k >= 0; k--){
          left++;;
          if (Integer.parseInt(String.valueOf(matrix.get(i)[k])) >= number){
            break;
          }
        }
        //down
        for (int k = i+1; k < matrix.size(); k++){
          down++;
          if (Integer.parseInt(String.valueOf(matrix.get(k)[j])) >= number){
            break;
          }
        }
        //up
        for (int k = i-1; k >= 0; k--){
          up++;
          if (Integer.parseInt(String.valueOf(matrix.get(k)[j])) >= number){
            break;
          }
        }
        if (up * down * left * right > max){
          max = up * down * left * right;
        }
      }
    }
    System.out.println(max);
  }
}
