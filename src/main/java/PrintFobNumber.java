public class PrintFobNumber {

        public static void main(String[] args) {

            int n = 9, firstValue = 0, secondValue = 1;
            System.out.println("Fibonacci Series till " + n );

            for (int i = 1; i <= n; ++i) {
                System.out.print(firstValue + "\n, ");

                int nextTerm = firstValue + secondValue;
                firstValue = secondValue;
                secondValue = nextTerm;
            }
        }
}
