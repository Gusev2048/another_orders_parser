import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.separator.DefaultRecordSeparatorPolicy;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Creates reader for converting RAW CSV data to Data DTO.
 */
public class OrderCsvReaderFactory extends Thread{

    public static ItemStreamReader<Order> csvOrderReader(File file) {
        // Columns sorted by their index

        Resource resource = new ClassPathResource(file.getAbsolutePath());
        SortedSet<OrdersColumns> columns = new TreeSet<>(Comparator
                .comparingInt(OrdersColumns -> OrdersColumns.columnIndex));
        // Here we select the columns we want in the target object.
        columns.addAll(Stream.of(
                OrdersColumns.COLUMN_1,
                OrdersColumns.COLUMN_2,
                OrdersColumns.COLUMN_3,
                OrdersColumns.COLUMN_4)
                .collect(Collectors.toSet()));

        FlatFileItemReader<Order> reader = new FlatFileItemReader<>();

        // skip header line
        reader.setLinesToSkip(0);
        // Important ! --> Allowing multi-line token support <--
        reader.setRecordSeparatorPolicy(new DefaultRecordSeparatorPolicy());
        reader.setResource(resource);
        reader.setLineMapper(new OrderDefaultLineMapper(columns));

        System.out.println(reader);
        return reader;
    }

    private static class OrderDefaultLineMapper extends DefaultLineMapper<Order> {
        public OrderDefaultLineMapper(SortedSet<OrdersColumns> columns) {
            /*String[] columnNames = columns.stream()
                    .map(OrdersColumns -> OrdersColumns.fieldName)
                    .toArray(String[]::new);
             */
            int[] includedFields =
                    Arrays.stream(columns.stream()
                            .map(OrdersColumns -> OrdersColumns.columnIndex)
                            .toArray(Integer[]::new))
                            .mapToInt(Integer::intValue).
                            toArray();

            setLineTokenizer(new DelimitedLineTokenizer() {
                {
                    //setNames(columnNames);
                    setQuoteCharacter('"');
                    setIncludedFields(includedFields);
                    setDelimiter(",");
                }
            });

            setFieldSetMapper(new BeanWrapperFieldSetMapper<Order>() {
                {
                    setTargetType(Order.class);
                }
            });
        }
    }
}