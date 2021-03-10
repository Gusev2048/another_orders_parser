import com.opencsv.bean.CsvBindByName;

public class Order {

    @CsvBindByName
    private long id;
    @CsvBindByName
    private double amount;
    @CsvBindByName
    private String comment;
    @CsvBindByName
    private String filename;
    @CsvBindByName
    private long line;
    @CsvBindByName
    private String result;

    public Order(long id, double amount, String comment, String filename, long line, String result) {
        this.id = id;
        this.amount = amount;
        this.comment = comment;
        this.filename = filename;
        this.line = line;
        this.result = result;
    }
}