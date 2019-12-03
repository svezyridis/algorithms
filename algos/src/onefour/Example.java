package onefour;
/**
 * Example
 */
public class Example {

    public static void main(String[] args) {
        int sum = 0;
        int N=Integer.parseInt(args[0]);
        for (int n = N; n > 0; n /= 2)
          for (int i = 0; i < n; i++)
            sum++; 
        System.out.println(sum);
    }
}