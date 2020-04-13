package twofive;

import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdIn;

public class OrderBook {
    MinPQ<Order> buyBook;
    MaxPQ<Order> sellBook;

    public static class Order {
        double amount;
        double price;

        public Order(double amount, double price) {
            this.amount = amount;
            this.price = price;
        }
    }

    public OrderBook() {
        buyBook = new MinPQ<>();
        sellBook = new MaxPQ<>();
    }

    public void makeOrder(double amount, double price, boolean isBuyOrder) {
        if (isBuyOrder) {
            while (!sellBook.isEmpty() && sellBook.max().price <= price) {
                if (sellBook.max().amount > amount) {
                    sellBook.max().amount -= amount;
                    System.out.println("Bought " + amount + " at " + sellBook.max().price);
                    amount = 0;
                    break;
                } else if (sellBook.max().amount < amount) {
                    System.out.println("Bought " + sellBook.max().amount + " at " + sellBook.max().price);
                    amount -= sellBook.delMax().amount;
                } else {
                    System.out.println("Bought " + amount + " at " + sellBook.max().price);
                    amount = 0;
                    sellBook.delMax();
                    break;
                }
            }
            if (amount != 0) {
                System.out.println("Buy order for " + amount + " added at " + price + " price");
                buyBook.insert(new Order(amount, price));
            }

        } else {
            while (!buyBook.isEmpty() && buyBook.min().price >= price) {
                if (buyBook.min().amount > amount) {
                    buyBook.min().amount -= amount;
                    System.out.println("Sold " + amount + " at " + buyBook.min().price);
                    amount = 0;
                    break;
                } else if (buyBook.min().amount < amount) {
                    System.out.println("Sold " + buyBook.min().amount + " at " + buyBook.min().price);
                    amount -= buyBook.delMin().amount;
                } else {
                    System.out.println("Sold " + amount + " at " + buyBook.min().price);
                    amount = 0;
                    buyBook.delMin();
                    break;
                }
            }
            if (amount != 0) {
                System.out.println("Sell order for " + amount + " added at " + price + " price");
                sellBook.insert(new Order(amount, price));
            }

        }
    }


    public static void main(String[] args) {
        OrderBook orderBook = new OrderBook();
        while (StdIn.hasNextLine()) {
            String[] params = StdIn.readLine().split(" ");
            double amount = Double.parseDouble(params[0]);
            double price = Double.parseDouble(params[1]);
            boolean isBuyOrder = Boolean.parseBoolean(params[2]);
            orderBook.makeOrder(amount, price, isBuyOrder);
        }
    }
}
