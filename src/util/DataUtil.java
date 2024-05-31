package util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DataUtil {

    public static String converterData(String dataSaida) {
        if (dataSaida == null) {
            return "";
        }

        LocalDate data = LocalDate.parse(dataSaida);
        return data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static String transformarData(String dataEntrada){
        if (dataEntrada != null) {
            DateTimeFormatter formats = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate data = LocalDate.parse(dataEntrada, formats);
            Date dataC = Date.from(data.atStartOfDay(ZoneId.systemDefault()).toInstant());
            SimpleDateFormat formatoo = new SimpleDateFormat("yyyy-MM-dd");
            return formatoo.format(dataC);
        }
        return "";
    }
}
