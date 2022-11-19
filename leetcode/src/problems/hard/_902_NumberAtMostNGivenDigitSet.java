package problems.hard;

import java.util.HashSet;
import java.util.Set;

public class _902_NumberAtMostNGivenDigitSet {
    public int atMostNGivenDigitSet(String[] digits, int n) {
        int m = n;
        int length = 0;
        while (m != 0) {
            length++;
            m = m/10;
        }

        m = n;
        int[] digitsOfN = new int[length];
        for (int i = length-1; i >= 0; i--) {
            digitsOfN[i] = m%10;
            m /= 10;
        }

        int[] lessThan = new int[10];
        int curr = 0;
        for (int i = 1; i < 10; i++) {
            if (curr >= digits.length || i <= Integer.parseInt(digits[curr])) {
                lessThan[i] = lessThan[i-1];
                continue;
            }

            lessThan[i] = lessThan[i-1] + 1;
            curr++;
        }

        Set<Integer> digitSet = new HashSet<>();
        for (int i = 0; i < digits.length; i++) {
            digitSet.add(Integer.parseInt(digits[i]));
        }

        int[] pow = new int[length+1];
        pow[0] = 1;
        for (int i = 1; i <= length; i++) {
            pow[i] = pow[i-1] * digits.length;
        }

        int partialDigitSum = 0;
        for (int i = 1; i < length; i++) {
            partialDigitSum += pow[i];
        }

        int[] allDigits = new int[length];
        allDigits[length-1] = digitSet.contains(digitsOfN[length-1]) ? lessThan[digitsOfN[length-1]] + 1 : lessThan[digitsOfN[length-1]];
        for (int i = length-2; i >= 0; i--) {
            allDigits[i] = lessThan[digitsOfN[i]] * pow[length-i-1];
            if (digitSet.contains(digitsOfN[i])) {
                allDigits[i] += allDigits[i+1];
            }
        }

        return partialDigitSum + allDigits[0];
    }
}
