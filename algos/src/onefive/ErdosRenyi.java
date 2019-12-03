package onefive;

import java.security.SecureRandom;
import java.util.Random;

import edu.princeton.cs.algs4.QuickFindUF;
import edu.princeton.cs.algs4.StdRandom;

/**
 * ErdosRenyi
 */
public class ErdosRenyi {

    public static int count(int N){
        QuickFindUF uf=new QuickFindUF(N);
        int cnt=0;
        while(uf.count()!=1){
            SecureRandom r =new SecureRandom();
            int no1= r.nextInt(N);
            int no2=r.nextInt(N);
            cnt++;
            if(!uf.connected(no1, no2)){
                uf.union(no1, no2);
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        int N =Integer.parseInt(args[0]);
        System.out.println(count(N));
    }
}