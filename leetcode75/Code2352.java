package leetcode75;

import java.util.ArrayList;
import java.util.List;

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
}
