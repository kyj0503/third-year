import java.util.Scanner;

/** 첫째 줄에는 별 1개, 둘째 줄에는 별 2개, N번째 둘에는 별 N개를 찍는 문제
 *
 */
public class No2438 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int A = sc.nextInt();

        sc.close();

        for(int B = 0; B < A; B++) {
            for(int C = 0; C <= B; C++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
