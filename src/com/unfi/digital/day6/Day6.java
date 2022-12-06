package com.unfi.digital.day6;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Day6 {


  public static void part1(String[] args) throws IOException {
    var in = Day6.class.getResourceAsStream("/com/unfi/digital/day6/input.txt");
    var input = new String(in.readAllBytes()).toCharArray();
    for (int i = 0; i < input.length - 3; i += 1){
      if (input[i] != input[i+1] && input[i+1] != input[i+2] && input[i+2] != input[i+3] && input[i] != input[i+2] && input[i] != input[i+3] && input[i+1] != input[i+3]){
        System.out.println(i + 3 + 1);
        return;
      }
    }
  }

  public static void main(String[] args) throws IOException {
    var in = Day6.class.getResourceAsStream("/com/unfi/digital/day6/input.txt");
    var input = new String(in.readAllBytes());
    var i = 0;
    var j = 13;
    while (j < input.length()){
      var window = input.substring(i, j+1);
      if (window.matches("^(?=.*[a-z])(?!.*([a-z]).*\\1).{14}$")){
        System.out.println(j + 1);
        return;
      }
      i++;
      j++;
    }
  }
}
