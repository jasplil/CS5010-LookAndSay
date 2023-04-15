package lookandsay;

import java.math.BigInteger;
import java.util.HashMap;

public class LookAndSayIterator<T> implements RIterator<T> {
    private BigInteger startSeed;
    private BigInteger endValue;
    private BigInteger currNum;

    /**
     * a constructor that takes two arguments
     * @param startSeed a number at which the sequence must begin
     * @param endValue an end value of type BigInteger
     */
    public LookAndSayIterator(BigInteger startSeed, BigInteger endValue) {
        if (startSeed.signum() == -1 || startSeed.compareTo(endValue) == 1 || startSeed.toString().contains("0")) throw new IllegalArgumentException("start seed is illegal");
        this.startSeed = startSeed;
        this.endValue = endValue;
    }

    public LookAndSayIterator(BigInteger startSeed) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            sb.append("9");
        }
        BigInteger end = new BigInteger(sb.toString());
        this.endValue = end;

        if (startSeed.signum() == -1 || startSeed.compareTo(end) == 1 || startSeed.toString().contains("0")) throw new IllegalArgumentException("start seed is illegal");
        this.startSeed = startSeed;
    }

    public LookAndSayIterator() {
        BigInteger start = new BigInteger("1");
        this.startSeed = start;
    }

    @Override
    public T prev() {
        BigInteger temp = this.startSeed;
        this.currNum = temp;

        String res = this.startSeed.toString();

        String t = "";
        for (int i = 1; i < res.length();) {
            char co = (res.charAt(i-1));
            int count = Character.getNumericValue(co);
            char letter = res.charAt(i);
            for (int j = 0;j  <count; j++){
                t += "" + letter;
            }
            i = i + 2;
        }
        res = t;

        BigInteger num = new BigInteger(res);
        this.startSeed = num;

        return (T) temp;
    }

    @Override
    public boolean hasPrevious() {
        HashMap<Character, Integer> map = new HashMap<>();
        String temp = this.currNum.toString();

        for (int i = 0; i < temp.length(); i++) {
            map.put(temp.charAt(i), map.getOrDefault(temp.charAt(i), 0) + 1);
        }

        for (int i = 0; i < temp.length(); i++) {
            if (map.get(temp.charAt(i)) > 1) return true;
        }

        return false;
    }

    @Override
    public boolean hasNext() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            sb.append("9");
        }
        BigInteger end = new BigInteger(sb.toString());
        this.endValue = end;

        String temp = this.currNum.toString();
        BigInteger next = new BigInteger(temp);
        return next.compareTo(endValue) == -1;
    }

    @Override
    public T next() {
        BigInteger temp = this.startSeed;
        this.currNum = temp;

        String res = this.startSeed.toString();
        String t = "";
        for (int j = 0; j < res.length();) {
            int k = j+1;
            while (k < res.length() && res.charAt(k) == res.charAt(j)) k++;
            t += "" + (k-j) + res.charAt(j);
            j = k;
        }
        res = t;

        BigInteger num = new BigInteger(res);
        this.startSeed = num;

        return (T) temp;
    }
}
