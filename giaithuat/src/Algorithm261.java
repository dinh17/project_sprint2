public class Algorithm261 {
    public static void main(String[] args) {
        int sum = 0;
        int[] arrays = {3, 5, 7, 13, 19};
        for (int array : arrays) {
            if (isPrime(array)) {
                sum += array;
            }
        }
        System.out.println("tổng số nguyên :" + sum);
    }

    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
