public class MaximumGap {

    private class Bucket {
        private Integer smallest;
        private Integer largest;

        public Bucket() {
            this.largest = null;
            this.smallest = null;
        }

        void insert(int val) {
            if (this.smallest == null) {
                this.smallest = val;
                this.largest = val;
            } else if (val < this.smallest) {
                this.smallest = val;
            } else if (val > this.largest) {
                this.largest = val;
            }
        }

    }

    public int maximumGap(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }
        int smallest = findSmallest(nums);
        int largest = findLargest(nums);
        int minVal = (int) Math.ceil(((double) largest - (double) smallest) / ((double) (nums.length - 1)));
        Bucket[] buckets = new Bucket[nums.length];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new Bucket();
        }
        for (int val : nums) {
            int index = (int) Math.floor(((double) val - (double) smallest) / ((double) minVal));
            buckets[index].insert(val);
        }
        int val = minVal;
        Integer comparisonValue = null;
        for (int i = 1; i < buckets.length; i++) {
            if (buckets[i - 1].largest != null) {
                comparisonValue = buckets[i - 1].largest;
            }
            if (buckets[i].smallest != null && comparisonValue != null && buckets[i].smallest - comparisonValue > val) {
                val = buckets[i].smallest - comparisonValue;
            }
        }
        return val;
    }

    private int findLargest(int[] nums) {
        int largest = Integer.MIN_VALUE;
        for (int val : nums) {
            if (val > largest) {
                largest = val;
            }
        }
        return largest;
    }

    private int findSmallest(int[] nums) {
        int smallest = Integer.MAX_VALUE;
        for (int val : nums) {
            if (val < smallest) {
                smallest = val;
            }
        }
        return smallest;
    }


    public static void main(String[] args) {
        System.out.println(new MaximumGap().maximumGap(new int[]{15252,16764,27963,7817,26155,20757,3478,22602,20404,6739,16790,10588,16521,6644,20880,15632,27078,25463,20124,15728,30042,16604,17223,4388,23646,32683,23688,12439,30630,3895,7926,22101,32406,21540,31799,3768,26679,21799,23740}));
    }

}
