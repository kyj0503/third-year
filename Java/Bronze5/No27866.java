import java.util.Scanner;

public class No27866 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str;
        int a;

        str = sc.nextLine();
        a = sc.nextInt();

        sc.close();

        System.out.println(str.charAt(a-1));
    }
}
