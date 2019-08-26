import java.util.ArrayList;

public class CreateMaximumNumber {

    ArrayList<Integer>[][] storedAnswersOne;
    ArrayList<Integer>[][] storedAnswersTwo;

    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        this.storedAnswersOne = new ArrayList[nums1.length + 1][k + 1];
        this.storedAnswersTwo = new ArrayList[nums2.length + 1][k + 1];
        int[] array = new int[k];
        int index = 0;

        ArrayList<Integer> maximum = null;
        for (int i = 0; i <= k; i++) {
            if (i <= nums1.length && k - i <= nums2.length) {
                ArrayList<Integer> answerOne = maxNumberRecursive(0, nums1, i, this.storedAnswersOne);
                ArrayList<Integer> answerTwo = maxNumberRecursive(0, nums2, k - i, this.storedAnswersTwo);
                ArrayList<Integer> mergedList = merge(answerOne, answerTwo, k);
                maximum = max(maximum, mergedList, k);
            }
        }
        for (Integer integer : maximum) {
            array[index++] = integer;
        }
        return array;
    }

    /**
     * Need to optimize it.
     */
    private ArrayList<Integer> merge(ArrayList<Integer> answerOne, ArrayList<Integer> answerTwo, int k) {
        ArrayList<Integer> mergedResult = new ArrayList<>();
        int x = 0, y = 0;
        for (int index = 0; index < k; index++) {
            if (x < answerOne.size() && y < answerTwo.size()) {
                if (answerOne.get(x) > answerTwo.get(y)) {
                    mergedResult.add(answerOne.get(x++));
                } else if (answerOne.get(x) == answerTwo.get(y)) {
                    if (x == answerOne.size() - 1) {
                        mergedResult.add(answerTwo.get(y++));
                    } else if (y == answerTwo.size() - 1) {
                        mergedResult.add(answerOne.get(x++));
                    } else {
                        ArrayList<Integer> list1 = merge(new ArrayList<>(answerOne.subList(x + 1, answerOne.size())),
                                new ArrayList<>(answerTwo.subList(y, answerTwo.size())), k - index - 1);
                        ArrayList<Integer> list2 = merge(new ArrayList<>(answerOne.subList(x, answerOne.size())),
                                new ArrayList<>(answerTwo.subList(y + 1, answerTwo.size())), k - index - 1);
                        if (max(list1, list2, k - index - 1) == list1) {
                            mergedResult.add(answerOne.get(x++));
                        } else {
                            mergedResult.add(answerTwo.get(y++));
                        }
                    }
                } else {
                    mergedResult.add(answerTwo.get(y++));
                }
            } else if (x < answerOne.size()) {
                mergedResult.add(answerOne.get(x++));
            } else {
                try {
                    mergedResult.add(answerTwo.get(y++));
                } catch (Exception e) {
                    throw e;
                }
            }
        }
        return mergedResult;
    }

    private ArrayList<Integer> maxNumberRecursive(int arrayIndex, int[] nums, int k, ArrayList<Integer>[][] storedAnswers) {
        //arrayIndex <= nums.length
        //k<= nums.length-arrayIndex
        if (storedAnswers[arrayIndex][k] != null) {
            return new ArrayList<>(storedAnswers[arrayIndex][k]);
        }

        if (k == 0) {
            return new ArrayList<>();
        }
        if (k == 1) {
            int max = Integer.MIN_VALUE;
            for (int index = arrayIndex; index < nums.length; index++) {
                max = Math.max(max, nums[index]);
            }
            ArrayList<Integer> linkedList = new ArrayList<>();
            linkedList.add(max);
            return linkedList;
        }
        int remainingElement = nums.length - arrayIndex;
        ArrayList<Integer> probableAnswer1 = null;
        if (arrayIndex < nums.length && remainingElement - 1 >= k) {
            probableAnswer1 = maxNumberRecursive(arrayIndex + 1, nums, k, storedAnswers);
        }
        ArrayList<Integer> probableAnswer2 = null;
        if (arrayIndex < nums.length) {
            probableAnswer2 = maxNumberRecursive(arrayIndex + 1, nums, k - 1, storedAnswers);
            probableAnswer2.add(0, nums[arrayIndex]);
        }
        ArrayList<Integer> solution = max(probableAnswer1, probableAnswer2, k);
        //storedAnswers[arrayIndex][k] = new ArrayList<>(solution);
        return solution;
    }

    private ArrayList<Integer> max(ArrayList<Integer> list1, ArrayList<Integer> list2, int k) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        for (int i = 0; i < k; i++) {
            int l1 = list1.get(i), l2 = list2.get(i);
            if (l1 > l2) {
                return list1;
            } else if (l2 > l1) {
                return list2;
            }
        }
        return list1;
    }


    //[6,5,4,6,3,7,5,6,4,5,6,4]
    //[8,8,6,0]
    //16
    public static void main(String[] args) {
        new CreateMaximumNumber().maxNumber(new int[]{6, 5, 4, 6, 3, 7, 5, 6, 4, 5, 6, 4}, new int[]{8, 8, 6, 0}, 16);
    }
}
