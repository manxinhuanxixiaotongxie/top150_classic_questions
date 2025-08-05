package leetcode75;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Dota2 的世界里有两个阵营：Radiant（天辉）和 Dire（夜魇）
 * <p>
 * Dota2 参议院由来自两派的参议员组成。现在参议院希望对一个 Dota2 游戏里的改变作出决定。他们以一个基于轮为过程的投票进行。在每一轮中，每一位参议员都可以行使两项权利中的 一 项：
 * <p>
 * 禁止一名参议员的权利：参议员可以让另一位参议员在这一轮和随后的几轮中丧失 所有的权利 。
 * 宣布胜利：如果参议员发现有权利投票的参议员都是 同一个阵营的 ，他可以宣布胜利并决定在游戏中的有关变化。
 * <p>
 * 给你一个字符串 senate 代表每个参议员的阵营。字母 'R' 和 'D'分别代表了 Radiant（天辉）和 Dire（夜魇）。然后，如果有 n 个参议员，给定字符串的大小将是 n。
 * <p>
 * 以轮为基础的过程从给定顺序的第一个参议员开始到最后一个参议员结束。这一过程将持续到投票结束。所有失去权利的参议员将在过程中被跳过。
 * <p>
 * 假设每一位参议员都足够聪明，会为自己的政党做出最好的策略，你需要预测哪一方最终会宣布胜利并在 Dota2 游戏中决定改变。输出应该是 "Radiant" 或 "Dire" 。
 */
public class Code649 {

    /**
     * 官解
     * <p>
     * 贪心 + 循环队列
     * <p>
     * 思路与算法
     * <p>
     * 我们以天辉方的议员为例。当一名天辉方的议员行使权利时：
     * <p>
     * 如果目前所有的议员都为天辉方，那么该议员可以直接宣布天辉方取得胜利；
     * <p>
     * 如果目前仍然有夜魇方的议员，那么这名天辉方的议员只能行使「禁止一名参议员的权利」这一项权利。显然，该议员不会令一名同为天辉方的议员丧失权利，所以他一定会挑选一名夜魇方的议员。那么应该挑选哪一名议员呢？
     * 容易想到的是，应该贪心地挑选按照投票顺序的下一名夜魇方的议员。这也是很容易形象化地证明的：既然只能挑选一名夜魇方的议员，那么就应该挑最早可以进行投票的那一名议员；如果挑选了其他较晚投票的议员，
     * 那么等到最早可以进行投票的那一名议员行使权利时，一名天辉方议员就会丧失权利，这样就得不偿失了。
     * <p>
     * 由于我们总要挑选投票顺序最早的议员，因此我们可以使用两个队列 radiant 和 dire 分别按照投票顺序存储天辉方和夜魇方每一名议员的投票时间。随后我们就可以开始模拟整个投票的过程：
     * <p>
     * 如果此时 radiant 或者 dire 为空，那么就可以宣布另一方获得胜利；
     * <p>
     * 如果均不为空，那么比较这两个队列的首元素，就可以确定当前行使权利的是哪一名议员。如果 radiant 的首元素较小，那说明轮到天辉方的议员行使权利，
     * 其会挑选 dire 的首元素对应的那一名议员。因此，我们会将 dire 的首元素永久地弹出，并将 radiant 的首元素弹出，增加 n 之后再重新放回队列，这里 n 是给定的字符串 senate 的长度，即表示该议员会参与下一轮的投票。
     * <p>
     * 为什么这里是固定地增加 n，而不是增加与当前剩余议员数量相关的一个数？读者可以思考一下这里的正确性。
     * <p>
     * +n的原因是可以保证当前轮次之后的轮次中议员投票的相对顺序保持不变，我们需要的只是议员的相对投票次序，而不需要绝对准确的投票时间；
     * 因此，取+n和取+10n、+100n的结果都是正确的。
     * 如果增加的时间小于n，可能会出现一个议员执行在一轮中执行多次权利或者本该后执行却先执行的情况。
     * <p>
     * 同理，如果 dire 的首元素较小，那么会永久弹出 radiant 的首元素，剩余的处理方法也是类似的。
     * <p>
     * 这样一来，我们就模拟了整个投票的过程，也就可以得到最终的答案了。
     * <p>
     * 作者：力扣官方题解
     * 链接：https://leetcode.cn/problems/dota2-senate/solutions/517088/dota2-can-yi-yuan-by-leetcode-solution-jb7l/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param senate
     * @return
     */
    public String predictPartyVictory(String senate) {
        int n = senate.length();
        Queue<Integer> radiant = new LinkedList<Integer>();
        Queue<Integer> dire = new LinkedList<Integer>();
        for (int i = 0; i < n; ++i) {
            if (senate.charAt(i) == 'R') {
                radiant.offer(i);
            } else {
                dire.offer(i);
            }
        }
        // 循环队列
        // RDRD
        while (!radiant.isEmpty() && !dire.isEmpty()) {
            int radiantIndex = radiant.poll(), direIndex = dire.poll();
            if (radiantIndex < direIndex) {
                radiant.offer(radiantIndex + n);
            } else {
                dire.offer(direIndex + n);
            }
        }
        return !radiant.isEmpty() ? "Radiant" : "Dire";
    }

}
