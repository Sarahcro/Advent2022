package com.unfi.digital.day12;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;
import javax.script.ScriptException;

public class Day12 {

  public static void main(String[] args)
      throws IOException, ScriptException, NoSuchMethodException {
    var in = Day12.class.getResourceAsStream("/com/unfi/digital/day12/test.txt");
    var map = new String(in.readAllBytes()).split("\r\n");
    var matrix = Arrays.stream(map).map(String::toCharArray).collect(Collectors.toList());
    var x = 0;
    var y = 0;
    for (int i = 0; i < map.length; i++) {
      if (matrix.get(i)[0] == 'a') {
        x = i;
        break;
      }
    }
    var visited = new HashSet<String>();
    visited.add(x + "," + y);

    var q = new ArrayDeque<int[]>();
    q.add(new int[]{x - 1, y, (int) 'a', 1});
    q.add(new int[]{x + 1, y, (int) 'a', 1});
    q.add(new int[]{x, y - 1, (int) 'a', 1});
    q.add(new int[]{x, y + 1, (int) 'a', 1});
    var done = false;
    while (!q.isEmpty() && !done) {
      var val = q.poll();
      var x2 = val[0];
      var y2 = val[1];
      if (x2 < 0 || y2 < 0 || x2 >= matrix.size() || y2 >= matrix.get(0).length) {
        continue;
      }
      if (visited.contains(x2 + "," + y2)) {
        continue;
      }
      var prev = val[2];
      var currentChar = matrix.get(x2)[y2];
      /* part 1
      if (prev == 'S' && (currentChar != 'a' && currentChar != 'b')) {
        continue;
      }*/
      if (currentChar == 'E' && (prev == 'z' || prev == 'y')) {
        done = true;
        System.out.println(val[3]);
      } else if (currentChar == 'E') {
        continue;
      }
      if (prev != 'S' && currentChar - prev > 1) {
        continue;
      }
      //part 2
      if (currentChar == 'a') {
        q.addFirst(new int[]{x2, y2 + 1, currentChar, 1});
        q.addFirst(new int[]{x2 + 1, y2, currentChar, 1});
        q.addFirst(new int[]{x2, y2 - 1, currentChar, 1});
        q.addFirst(new int[]{x2 - 1, y2, currentChar, 1});
      } else {
        q.add(new int[]{x2, y2 + 1, currentChar, val[3] + 1});
        q.add(new int[]{x2 + 1, y2, currentChar, val[3] + 1});
        q.add(new int[]{x2, y2 - 1, currentChar, val[3] + 1});
        q.add(new int[]{x2 - 1, y2, currentChar, val[3] + 1});
      }
      visited.add(x2 + "," + y2);
    }

  }

}
