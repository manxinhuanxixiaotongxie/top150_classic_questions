package leetcode75;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 给你一个下标从 0 开始、大小为 n x n 的整数矩阵 grid ，返回满足 Ri 行和 Cj 列相等的行列对 (Ri, Cj) 的数目。
 * <p>
 * 如果行和列以相同的顺序包含相同的元素（即相等的数组），则认为二者是相等的。
 */
public class Code2352 {
    public int equalPairs(int[][] grid) {
        int ans = 0;
        int row = grid.length;
        int col = grid[0].length;
        List<List<Integer>> list = new ArrayList<>();
        for (int c = 0; c < col; c++) {
            List<Integer> temp = new ArrayList<>();
            for (int r = 0; r < row; r++) {
                temp.add(grid[r][c]);
            }
            list.add(temp);
        }
        for (int r = 0; r < row; r++) {
            List<Integer> temp = new ArrayList<>();
            for (int c = 0; c < col; c++) {
                temp.add(grid[r][c]);
            }
            // 当前行在列中进行查找
            for (List<Integer> l : list) {
                boolean isEqual = true;
                for (int i = 0; i < l.size(); i++) {
                    if (!l.get(i).equals(temp.get(i))) {
                        isEqual = false;
                        break;
                    }
                }
                if (isEqual) ans++;
            }
        }
        return ans;
    }

    /**
     * 这个方法会忽略掉相同的行 比如的 第0行的数据是 3 2 1   第二行的数据也是3 2 1 这应该计算两次
     * @param grid
     * @return
     */
    public int equalPairs2(int[][] grid) {
        int ans = 0;
        int row = grid.length;
        int col = grid[0].length;
        Set<String> set = new HashSet<>();
        // 行数据法处理
        for (int r = 0; r < row; r++) {
            StringBuilder sb = new StringBuilder();
            for (int c = 0; c < col; c++) {
                sb.append(grid[r][c]);
            }
            set.add(sb.toString());
        }
        // 列数据处理
        for (int c = 0;c < col;c++) {
            StringBuilder sb = new StringBuilder();
            for (int r = 0; r < row; r++) {
                sb.append(grid[r][c]);
            }
            if (set.contains(sb.toString())) ans++;
        }
        return ans;
    }

    public int equalPairs3(int[][] grid) {
        int ans = 0;
        int row = grid.length;
        int col = grid[0].length;
        Map<String, Integer> map = new HashMap<>();
        // 行数据法处理
        for (int r = 0; r < row; r++) {
            StringBuilder sb = new StringBuilder();
            for (int c = 0; c < col; c++) {
                sb.append(grid[r][c]).append(" ");
            }
            map.merge(sb.toString(), 1, Integer::sum);
        }
        // 列数据处理
        for (int c = 0;c < col;c++) {
            StringBuilder sb = new StringBuilder();
            for (int r = 0; r < row; r++) {
                // 注意这里要加一个分隔符 不然 1 11 1 11 这种情况会被误认为是相同的
                sb.append(grid[r][c]).append(" ");
            }
            ans += map.getOrDefault(sb.toString(), 0);
        }
        return ans;
    }
    
    
}
