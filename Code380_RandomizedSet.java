import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Code380_RandomizedSet {

    List<Integer> dataList;
    Map<Integer, Integer> indexMap;
    Random random;

    public Code380_RandomizedSet() {
        dataList = new ArrayList<>();
        indexMap = new HashMap<>();
        random = new Random();
    }

    public boolean insert(int val) {
        if (indexMap.containsKey(val)) {
            return false;
        }
        int size = indexMap.size();
        dataList.add(val);
        indexMap.put(val, size);
        return true;
    }

    public boolean remove(int val) {
        if (!indexMap.containsKey(val)) {
            return false;
        }
        // 获取当前数的下标位置
        int index = indexMap.get(val);
        // 获取变成数组的最后数字
        int last = dataList.get(dataList.size() - 1);
        // 交换index位置与最后一个位置的数
        dataList.set(index, last);
        indexMap.put(last, index);

        dataList.remove(dataList.size() - 1);
        indexMap.remove(val);
        return true;
    }

    public int getRandom() {
        int i = random.nextInt(dataList.size());
        return dataList.get(i);
    }
}
