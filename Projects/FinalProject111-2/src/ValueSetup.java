import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//統一GUI的FRAME大小和字體
public interface ValueSetup {
	int FRAME_WIDTH =900;
	int FRAME_HEIGHT=600;
	String fontTypeString="\"SansSerif\"";
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd"); 
	DateTimeFormatter dtfNoYear = DateTimeFormatter.ofPattern("MM/dd");
	LocalDateTime now = LocalDateTime.now();  
	
	//日立會用到
	//DateTimeFormatter dayOfWeekFormatter = DateTimeFormatter.ofPattern("EEE");
	LocalDate currentDate = LocalDate.now().with(DayOfWeek.MONDAY);
	
	//星期對應的日期
	String mon = dtfNoYear .format(currentDate);
	String tue = dtfNoYear .format(currentDate.plusDays(1));
	String wed = dtfNoYear .format(currentDate.plusDays(2));
	String thu = dtfNoYear .format(currentDate.plusDays(3));
	String fri = dtfNoYear .format(currentDate.plusDays(4));
	String sat = dtfNoYear .format(currentDate.plusDays(5));
	String sun = dtfNoYear .format(currentDate.plusDays(6));
}
