package problems.hard;

public class _123_BestTimeToBuyAndSellStockIII {
    public int maxProfit(int[] prices) {
        int n = prices.length;

        int minPrice = prices[0];
        int[] forwards = new int[n];

        for (int i = 1; i < n; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            }

            forwards[i] = Math.max(prices[i] - minPrice, forwards[i-1]);
        }

        int maxPrice = prices[n-1];
        int[] backwards = new int[n];
        for (int i = n-2; i >= 0; i--) {
            if (prices[i] > maxPrice) {
                maxPrice = prices[i];
            }

            backwards[i] = Math.max(maxPrice - prices[i], backwards[i+1]);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, forwards[i] + backwards[i]);
        }

        return ans;
    }
}
