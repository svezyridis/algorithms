package twofive;

import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.MinPQ;

public class Exercise22 {

    public static class Order {
        double amount;
        double price;

        public Order(double amount, double price, boolean isBuyOrder) {
            this.amount = amount;
            this.price = price;
        }
    }

    public static void executeOrder(Order order, boolean isBuyOrder, MinPQ<Order> buyBook, MaxPQ<Order> sellBook) {
        if (isBuyOrder) {
            double amount = order.amount;
            double price = order.price;

        }
    }


    public static void main(String[] args) {

    }


}
