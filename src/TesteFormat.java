import java.text.SimpleDateFormat;
import java.util.Date;

public class TesteFormat {
	private static String PATTERN = "YYYY-MM-dd'T'HH:mm:ss.SSSZZ";
	private final static SimpleDateFormat dateFormat = new SimpleDateFormat(
			PATTERN);

	public static void main(String[] args) {
		System.out.println(dateFormat.format(new Date()));
	}
}
