public class BestTimeToBuySellStocks {
    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int[] answerMaxOneRight = calculateAnswerMaxOneRight(prices);
        int[] answerMaxOneLeft = calculateAnswerMaxOneLeft(prices);
        int answer = Math.max(answerMaxOneLeft[0], answerMaxOneRight[prices.length - 1]);
        for (int i = 0; i < prices.length - 1; i++) {
            answer = Math.max(answer, answerMaxOneRight[i] + answerMaxOneLeft[i + 1]);
        }
        return answer;
    }

    private int[] calculateAnswerMaxOneLeft(int[] prices) {
        int[] answer = new int[prices.length];
        int[] max = new int[prices.length];
        answer[prices.length - 1] = 0;
        max[prices.length - 1] = prices[prices.length - 1];
        for (int i = prices.length - 2; i >= 0; i--) {
            max[i] = Math.max(max[i + 1], prices[i]);
            answer[i] = Math.max(answer[i + 1], max[i]-prices[i]);
        }
        return answer;
    }

    private int[] calculateAnswerMaxOneRight(int[] prices) {
        int[] answer = new int[prices.length];
        int[] min = new int[prices.length];
        answer[0] = 0;
        min[0] = prices[0];
        for (int i = 1; i < prices.length; i++) {
            min[i] = Math.min(min[i - 1], prices[i]);
            answer[i] = Math.max(answer[i - 1], prices[i] - min[i]);
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(new BestTimeToBuySellStocks().maxProfit(new int[]{3,3,5,0,0,3,1,4}));
    }

}
