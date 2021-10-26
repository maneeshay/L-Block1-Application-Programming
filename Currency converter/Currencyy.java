package def;

public class Currencyy {

    private String [] currencies = {"Japanese yen (JPY)", "Euro (EUR)", "US Dollars (USD)", "Australian Dollars (AUD)",
            "Canadian Dollars (CAD)", "South Korean Won (KRW)", "Thai Baht (THB)",
            "United Arab Emirates Dirham (AED)"};
    private String [] symbols = {"¥", "€", "$", "A$", "C$", "₩", "฿", "د.إ"};
    private double [] factors = {137.52, 1.09, 1.29, 1.78, 1.70, 1537.75, 40.52, 4.75};

    // Getters to return the currencies
    public String[] getCurrencies() {
        return currencies;
    }
    
    //Getter to return the symbols
    public String[] getSymbols() {
        return symbols;
    }
    
    //getter to return the factors
    public double[] getFactors() {
        return factors;
    }

}
