package g2501_2600.s2584_split_the_array_to_make_coprime_products;

// #Medium #Breadth_First_Search #Tree #Binary_Search
// #2023_08_22_Time_13_ms_(99.83%)_Space_60.3_MB_(92.18%)

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    private final int[] divideTo = new int[(int) (1e6) + 1];

    private void fillDivideArray() {
        for (int i = 1; i < divideTo.length; i++) {
            divideTo[i] = i;
        }
        for (int i = 2; i * i < divideTo.length; i++) {
            if (divideTo[i] != i) {
                continue;
            }
            for (int j = i + i; j < divideTo.length; j += i) {
                if (divideTo[j] == j) {
                    divideTo[j] = i;
                }
            }
        }
    }

    public int findValidSplit(int[] nums) {
        if (divideTo[1] == 0) {
            fillDivideArray();
        }
        Map<Integer, Integer> dMap = new HashMap<>();
        List<Integer>[] dividers = new List[nums.length];
        for (int i = 0; i < nums.length; i++) {
            dividers[i] = new ArrayList<>();
            while (nums[i] != 1) {
                dividers[i].add(divideTo[nums[i]]);
                dMap.put(divideTo[nums[i]], i);
                nums[i] = nums[i] / divideTo[nums[i]];
            }
        }
        int nextEnd = 0;
        int i = 0;
        while (i <= nextEnd) {
            for (int j = 0; j < dividers[i].size(); j++) {
                nextEnd = Math.max(nextEnd, dMap.get(dividers[i].get(j)));
            }
            i++;
        }
        if (i == nums.length) {
            return -1;
        }
        return i - 1;
    }
}
