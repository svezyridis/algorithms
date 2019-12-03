package onefive;

import onepointthree.RandomBag;

/**
 * Excercise18
 */
public class Excercise18 {

    public static Connection[] generate(int N){
        RandomBag<Connection> bag =new RandomBag<Connection>();
        for(int i=0;i<N;i++){
            for(int j=0; j<N;j++){
                if(j!=i){
                    Connection con=new Connection(i, j);
                    bag.add(con);
                }
            }
        }
        Connection[] connections = new Connection[bag.size()];
        int index = 0;

        for(Connection connection : bag) {
            connections[index] = connection;
            index++;
        }
        return connections;
    }



    public static void main(String[] args) {
        int N =Integer.parseInt(args[0]);
        Connection[] bag=generate(N);
        for(Connection con:bag){
            System.out.println(con.p+" "+con.q);
        }

    }
}