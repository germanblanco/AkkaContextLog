import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.classic.spi.LoggingEvent;
import net.logstash.logback.marker.LogstashMarker;
import net.logstash.logback.marker.LogstashBasicMarker;
import org.slf4j.Marker;
import java.util.Iterator;

public class CustomAppender<E> extends ConsoleAppender<E> {

	public CustomAppender() {
		
	}

        private void printMarker(LogstashMarker m) {
                System.out.println(m);
                System.out.println(m.getName());
                if (m.hasReferences()) {
                        for (Iterator<Marker> iter = m.iterator(); iter.hasNext();) {
                                LogstashMarker mi = (LogstashMarker)iter.next();
                                printMarker(mi);
                        }
                }
        }

        private void printLoggingEvent(LoggingEvent le) {
                System.out.println(le.getArgumentArray());
                System.out.println(le.getCallerData());
                System.out.println(le.getFormattedMessage());
                System.out.println(le.getMessage());
                System.out.println(le.getMDCPropertyMap());
        }

        @Override
        protected void append(E eventObject) {

                LoggingEvent le = (LoggingEvent)eventObject;

                Object o = le.getMarker();

                if (!isStarted()) {
                        return;
                }

                System.out.println(o.toString());

                subAppend(eventObject);
        }
}
