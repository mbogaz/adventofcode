import org.apache.commons.lang3.StringUtils;
import util.InputFileUtil;

import java.io.IOException;
import java.util.*;

public class Day1 {
    public static void main(String[] args) throws IOException {
        String inputStr = InputFileUtil.readFileAsString("inputs/day1");
        String[] calories = inputStr.split("\n");

        List<Integer> elvesCalories = new ArrayList<>();
        int calorieCarriedByElf = 0;

        for (String calory : calories) {
            if (StringUtils.isNotBlank(calory)) {
                calorieCarriedByElf += Integer.parseInt(StringUtils.normalizeSpace(calory));
            } else {
                elvesCalories.add(calorieCarriedByElf);
                calorieCarriedByElf = 0;
            }
        }

        elvesCalories.sort(Collections.reverseOrder());
        int top3elvesCalories = elvesCalories.stream().limit(3).mapToInt(value -> value).sum();
        System.out.println("The elf with the most calories carries " + elvesCalories.get(0) + " calories");
        System.out.println("Top 3 elves carrying " + top3elvesCalories + " calories");
    }
}
