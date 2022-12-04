package com.unfi.digital.day3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Day3 {

  public static void part1(String[] args) throws IOException {
    var in = Day3.class.getResourceAsStream("/com/unfi/digital/day3/input.txt");
    var input = new String(in.readAllBytes());
    var rucksacks = input.split("\r\n");
    var value = Arrays.stream(rucksacks).map(r -> {
      var r1 = r.substring(0, r.length()/2).toCharArray();
      var r2 = r.substring(r.length() / 2).toCharArray();
      for (char c: r1) {
        for (char c2: r2){
          if (c == c2){
            return c;
          }
        }
      }
      return '0';
    }).map(c -> Character.isLowerCase(c) ? (int) c - 96 : (int)c - 38).reduce(Integer::sum);
  System.out.println(value);
  System.out.println((int) 'A');
  }

  public static void main(String[] args) throws IOException {
    var in = Day3.class.getResourceAsStream("/com/unfi/digital/day3/input.txt");
    var input = new String(in.readAllBytes());
    var rucksacks = input.split("\r\n");
    var total = 0;
    for (int i = 0; i < rucksacks.length; i += 3){
      var a = rucksacks[i].toCharArray();
      var b = rucksacks[i+1].toCharArray();
      var c = rucksacks[i+2].toCharArray();
      boolean found = false;
      for (char ach : a) {
        for (char bch : b){
          if (ach == bch){
            for (char cch : c){
              if (cch == ach){
                total += Character.isLowerCase(cch) ? (int) cch - 96 : (int) cch - 38;
                found = true;
                break;
              }
            }
          }
          if (found){
            break;
          }
        }
        if (found){
          break;
        }
      }
    }
    System.out.println(total);
  }

}
