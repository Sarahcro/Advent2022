package com.unfi.digital.day10;

import com.unfi.digital.day6.Day6;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class Day10 {

  public static void part1(String[] args) throws IOException {
    var in = Day6.class.getResourceAsStream("/com/unfi/digital/day10/input.txt");
    var input = new String(in.readAllBytes()).split("\r\n");
    var cycleCount = 1;
    var checkpoints = List.of(20, 60, 100, 140, 180, 220);
    var x = 1;
    for (String op : input){
      if (Objects.equals(op, "noop")){
        cycleCount ++;
      } else {
        var command = op.split(" ");
        cycleCount += 2;
        x += Integer.parseInt(command[1]);
        if (checkpoints.contains(cycleCount - 1)){
          System.out.println((cycleCount - 1) * (x - Integer.parseInt(command[1])));
        }
      }
      if (checkpoints.contains(cycleCount)){
        System.out.println(cycleCount * x);
      }
    }
  }

  public static void main(String[] args) throws IOException {
    var in = Day6.class.getResourceAsStream("/com/unfi/digital/day10/input.txt");
    var input = new String(in.readAllBytes()).split("\r\n");
    var cycleCount = 1;
    var checkpoints = List.of(40, 80, 120, 160, 200, 240);
    var sb = new StringBuilder();
    var x = 1;
    var crt = 0;
    for (String op : input) {
      if (Objects.equals(op, "noop")) {
        if (Math.abs(x - crt) < 2){
          sb.append("#");
        } else {
          sb.append(".");
        }
        sb = println(sb);
        crt = resetCrt(crt);
      } else {
        if (Math.abs(x - crt) < 2){
          sb.append("#");
        } else {
          sb.append(".");
        }
        sb = println(sb);
        crt = resetCrt(crt);
        if (Math.abs(x - crt) < 2){
          sb.append("#");
        } else {
          sb.append(".");
        }
        sb = println(sb);
        crt = resetCrt(crt);
        x += Integer.parseInt(op.split(" ")[1]);
      }
    }
  }

  private static StringBuilder println(StringBuilder sb){
    if (sb.length() >= 40){
      System.out.println(sb);
      return new StringBuilder();
    } else {
      return sb;
    }
  }

  private static Integer resetCrt(Integer crt){
    if (crt == 40){
      return 1;
    } else {
      return crt + 1;
    }
  }
}
