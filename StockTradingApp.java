import java.util.*;

class Stock {
    String symbol;
    double price;

    Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }
}

class Portfolio {
    Map<String, Integer> holdings = new HashMap<>();
    double balance;

    Portfolio(double balance) {
        this.balance = balance;
    }

    void buyStock(Stock stock, int quantity) {
        double cost = stock.price * quantity;
        if (balance >= cost) {
            balance -= cost;
            holdings.put(stock.symbol, holdings.getOrDefault(stock.symbol, 0) + quantity);
            System.out.println("Bought " + quantity + " shares of " + stock.symbol);
        } else {
            System.out.println("Insufficient balance to buy stock.");
        }
    }

    void sellStock(Stock stock, int quantity) {
        if (holdings.getOrDefault(stock.symbol, 0) >= quantity) {
            holdings.put(stock.symbol, holdings.get(stock.symbol) - quantity);
            balance += stock.price * quantity;
            System.out.println("Sold " + quantity + " shares of " + stock.symbol);
        } else {
            System.out.println("Not enough shares to sell.");
        }
    }

    void displayPortfolio(Map<String, Stock> market) {
        System.out.println("Portfolio:");
        for (String symbol : holdings.keySet()) {
            int qty = holdings.get(symbol);
            double value = qty * market.get(symbol).price;
            System.out.println(symbol + ": " + qty + " shares, Value: $" + value);
        }
        System.out.println("Balance: $" + balance);
    }
}

public class StockTradingApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Stock> market = new HashMap<>();
        market.put("AAPL", new Stock("AAPL", 150.0));
        market.put("GOOG", new Stock("GOOG", 2800.0));
        market.put("TSLA", new Stock("TSLA", 700.0));

        Portfolio portfolio = new Portfolio(5000.0);

        while (true) {
            System.out.println("\n1. Display Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            if (choice == 5) break;

            switch (choice) {
                case 1:
                    for (Stock stock : market.values()) {
                        System.out.println(stock.symbol + ": $" + stock.price);
                    }
                    break;
                case 2:
                    System.out.print("Enter stock symbol to buy: ");
                    String buySymbol = scanner.next();
                    System.out.print("Enter quantity: ");
                    int buyQty = scanner.nextInt();
                    if (market.containsKey(buySymbol)) {
                        portfolio.buyStock(market.get(buySymbol), buyQty);
                    } else {
                        System.out.println("Stock not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter stock symbol to sell: ");
                    String sellSymbol = scanner.next();
                    System.out.print("Enter quantity: ");
                    int sellQty = scanner.nextInt();
                    if (market.containsKey(sellSymbol)) {
                        portfolio.sellStock(market.get(sellSymbol), sellQty);
                    } else {
                        System.out.println("Stock not found.");
                    }
                    break;
                case 4:
                    portfolio.displayPortfolio(market);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }

        scanner.close();
    }
}



