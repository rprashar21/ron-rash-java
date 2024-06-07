import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class projects {
}


class CurrencyConverter {
    private Map<String, BigDecimal> exchangeRates;

    public CurrencyConverter() {
        exchangeRates = new HashMap<>();
    }

    public void setExchangeRate(String currency, BigDecimal rate) {
        exchangeRates.put(currency, rate);
    }

    public BigDecimal convert(BigDecimal amount, String fromCurrency, String toCurrency) {
        BigDecimal fromRate = exchangeRates.getOrDefault(fromCurrency, BigDecimal.ONE);
        BigDecimal toRate = exchangeRates.getOrDefault(toCurrency, BigDecimal.ONE);

        return amount.divide(fromRate, 2, RoundingMode.HALF_UP)
                .multiply(toRate)
                .setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal convertCurrency(final String sourceCurrency, final String destinationCurrency, BigDecimal amountToBeconverted) {
        BigDecimal fromRate = exchangeRates.getOrDefault(sourceCurrency, BigDecimal.ONE);
        BigDecimal toRate = exchangeRates.getOrDefault(destinationCurrency, BigDecimal.ONE);

        BigDecimal scale = amountToBeconverted.multiply(toRate).setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal convertedAmount = scale.divide(fromRate).setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println(convertedAmount);

        return convertedAmount;
    }

    public static void main(String[] args) {
        CurrencyConverter converter = new CurrencyConverter();

        // Set exchange rates using BigDecimal
        converter.setExchangeRate("USD", new BigDecimal("1.0"));
        converter.setExchangeRate("EUR", new BigDecimal("0.85"));
        converter.setExchangeRate("GBP", new BigDecimal("0.72"));

        // Convert USD to EUR using BigDecimal
        BigDecimal amountInUSD = new BigDecimal("100.023");
        BigDecimal convertedAmountInEUR = converter.convert(amountInUSD, "GBP", "EUR");
      //  BigDecimal convertToEuros = converter.convertCurrency("GBP", "EUR", new BigDecimal("100.023"));
        System.out.println("Converted amount in EUR: " + convertedAmountInEUR);
     //   System.out.println("Converted amount in EUR: " + convertToEuros);

        // Convert GBP to USD using BigDecimal
        BigDecimal amountInGBP = new BigDecimal("50.0");
        BigDecimal convertedAmountInUSD = converter.convert(amountInGBP, "GBP", "USD");
        System.out.println("Converted amount in USD: " + convertedAmountInUSD);
    }
}
