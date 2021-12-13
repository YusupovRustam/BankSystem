import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormat {
    public DateFormat() {
    }

    public static String stringDateNow(LocalDateTime localDateTime){
    DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    String format = localDateTime.format(dateTimeFormatter);
    return format;
    }
}
