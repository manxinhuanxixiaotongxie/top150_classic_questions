import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 基因序列可以表示为一条由 8 个字符组成的字符串，其中每个字符都是 'A'、'C'、'G' 和 'T' 之一。
 *
 * 假设我们需要调查从基因序列 start 变为 end 所发生的基因变化。一次基因变化就意味着这个基因序列中的一个字符发生了变化。
 *
 * 例如，"AACCGGTT" --> "AACCGGTA" 就是一次基因变化。
 * 另有一个基因库 bank 记录了所有有效的基因变化，只有基因库中的基因才是有效的基因序列。（变化后的基因必须位于基因库 bank 中）
 *
 * 给你两个基因序列 start 和 end ，以及一个基因库 bank ，请你找出并返回能够使 start 变化为 end 所需的最少变化次数。如果无法完成此基因变化，返回 -1 。
 *
 * 注意：起始基因序列 start 默认是有效的，但是它并不一定会出现在基因库中。
 *
 */
public class Code433_MinMutation {
    public int minMutation(String startGene, String endGene, String[] bank) {
        Queue<String> queue = new LinkedList<>();
        // 点集
        Map<String, List<String>> map = new HashMap<>();
        HashSet<String> set = new HashSet<>();
        set.add(startGene);
        queue.add(startGene);
        // 开始进行宽度优先遍历
        for (int step = 0; !queue.isEmpty(); step++) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String gene = queue.poll();
                if (gene.equals(endGene)) {
                    return step;
                }
                // 继续向下遍历
                for (String nextGene : bank) {
                    if (isOneDiff(gene, nextGene) && !set.contains(nextGene)) {
                        queue.add(nextGene);
                        set.add(nextGene);
                    }
                }
                set.add(gene);
            }
        }
        return -1;
    }
    public boolean isOneDiff(String s1, String s2) {
        int diff = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diff++;
            }
        }

        return diff == 1;
    }
}
