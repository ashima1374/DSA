class Solution {
    public long countCommas(long n) {

        long comma = 0;
        long start = 1000;
        long commasPerNumber = 1;

        while (start <= n) {

            long next = start * 1000;

            long end = Math.min(n, next - 1);

            comma += (end - start + 1) * commasPerNumber;

            start = next;
            commasPerNumber++;
        }

        return comma;
    }
}