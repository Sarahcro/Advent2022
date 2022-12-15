package com.unfi.digital.day14;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;

public class Day14 {

  public static void main(String[] args) throws IOException {
    var in = Day14.class.getResourceAsStream("/com/unfi/digital/day14/input.txt");
    var commands = new String(in.readAllBytes()).split("\r\n");
    var rocks = new HashSet<String>();
    var maxY = 0;
    for (String command : commands) {
      var coords = Arrays.stream(command.split(" -> "))
          .map(s -> Arrays.stream(s.split(",")).map(Integer::parseInt).collect(
              Collectors.toList()))
          .collect(Collectors.toList());
      for (int i = 0; i < coords.size(); i++) {
        rocks.add(coords.get(i).get(0) + "," + coords.get(i).get(1));
        if (coords.get(i).get(1) > maxY){
          maxY = coords.get(i).get(1);
        }
        if (i + 1 < coords.size()) {
          while (!Objects.equals(coords.get(i).get(0), coords.get(i + 1).get(0))) {
            rocks.add(coords.get(i).get(0) + "," + coords.get(i).get(1));
            var newVal = coords.get(i).get(0);
            if (coords.get(i).get(0) > coords.get(i + 1).get(0)){
              newVal--;
            } else {
              newVal++;

            }
            coords.get(i).set(0, newVal);
          }
          while (!Objects.equals(coords.get(i).get(1), coords.get(i + 1).get(1))) {
            rocks.add(coords.get(i).get(0) + "," + coords.get(i).get(1));
            var newVal = coords.get(i).get(1);
            if (coords.get(i).get(1) > coords.get(i + 1).get(1)){
              newVal--;
            } else {
              newVal++;
            }
            coords.get(i).set(1, newVal);
          }
        }
      }
    }
    System.out.println(rocks.size());
    var sandX = 500;
    var sandY = 0;
    while (sandY < (maxY + 2) && !rocks.contains("500,0")){
      if (!rocks.contains(sandX+","+(sandY+1)) && (sandY + 1) < (maxY + 2)){
        sandY++;
      } else if (!rocks.contains((sandX-1)+","+(sandY+1)) && (sandY + 1) < (maxY + 2)){
        sandX--;
        sandY++;
      } else if (!rocks.contains((sandX+1)+","+(sandY+1)) && (sandY + 1) < (maxY + 2)){
        sandX++;
        sandY++;
      } else {
        rocks.add(sandX+","+sandY);
        sandX = 500;
        sandY = 0;
      }
    }
    System.out.println(rocks.size());

  }

}
