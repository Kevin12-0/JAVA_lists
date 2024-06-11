package models;

public class Money {
    private String conversion_result;

    public Money(String conversion_result) {
        this.conversion_result = conversion_result;
    }

    public Money(moneyJson moneyResult) {
        this.conversion_result = moneyResult.conversion_result();
    }

    public String getConversion_result() {
        return conversion_result;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Resultado:" + conversion_result;
    }
}
