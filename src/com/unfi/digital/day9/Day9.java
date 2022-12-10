package com.unfi.digital.day9;

import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Day9 {

  public static void part1(String[] args) throws IOException {
    var in = Day9.class.getResourceAsStream("/com/unfi/digital/day9/input.txt");
    var input = new String(in.readAllBytes()).split("\r\n");
    Set<String> tailCoords = new HashSet<>();
    tailCoords.add("0,0");
    var tailX = 0;
    var tailY = 0;
    var headX = 0;
    var headY = 0;
    for (String m : input){
      var motions = m.split(" ");
      for (int i = 0; i < Integer.parseInt(motions[1]); i++) {
        if (Objects.equals(motions[0], "R")) {
          headX++;
          if (!isHeadAdjacent(headX, headY, tailX, tailY)){
            tailX = headX - 1;
            tailY = headY;
          }
        } else if (Objects.equals(motions[0], "L")) {
          headX--;
          if (!isHeadAdjacent(headX, headY, tailX, tailY)){
            tailX = headX + 1;
            tailY = headY;
          }
        } else if (Objects.equals(motions[0], "U")) {
          headY++;
          if (!isHeadAdjacent(headX, headY, tailX, tailY)){
            tailY = headY - 1;
            tailX = headX;
          }
        } else if (Objects.equals(motions[0], "D")) {
          headY--;
          if (!isHeadAdjacent(headX, headY, tailX, tailY)){
            tailY = headY + 1;
            tailX = headX;
          }
        }
        System.out.println(tailX+","+tailY);
        tailCoords.add(tailX+","+tailY);
      }
    }
    System.out.println(tailCoords.size());
  }

  public static void main(String[] args) throws IOException {
    var in = Day9.class.getResourceAsStream("/com/unfi/digital/day9/input.txt");
    var input = new String(in.readAllBytes()).split("\r\n");
    Set<String> tailCoords = new HashSet<>();
    tailCoords.add("0,0");
    int[] x = new int[10];
    int[] y = new int[10];
    for (String m : input){
      var motions = m.split(" ");
      for (int i = 0; i < Integer.parseInt(motions[1]); i++) {
        if (Objects.equals(motions[0], "R")) {
          x[0]++;
        } else if (Objects.equals(motions[0], "L")) {
          x[0]--;
        } else if (Objects.equals(motions[0], "U")) {
          y[0]++;
        } else if (Objects.equals(motions[0], "D")) {
          y[0]--;
        }
        for (int j = 1; j < 10; j++) {
          if (!isHeadAdjacent(x[j-1], y[j-1], x[j], y[j])){
            if (x[j-1] == x[j]){
              if (y[j-1] < y[j]){
                y[j]--;
              } else {
                y[j]++;
              }
            }
            else if (y[j-1] == y[j]){
              if (x[j-1] < x[j]){
                x[j]--;
              } else {
                x[j]++;
              }
            }
            else {
              if (y[j-1] < y[j]){
                y[j]--;
              } else {
                y[j]++;
              }
              if (x[j-1] < x[j]){
                x[j]--;
              } else {
                x[j]++;
              }
            }
          }
        }
        System.out.println(x[9]+","+y[9]);
        tailCoords.add(x[9] + "," + y[9]);
      }
    }
    System.out.println(tailCoords.size());
  }

  private static boolean isHeadAdjacent(Integer headX,Integer headY,Integer tailX,Integer tailY){
    return (Math.abs(headX - tailX) < 2) && (Math.abs(headY - tailY) < 2);
  }

}
