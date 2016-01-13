import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggingEvent;
import net.logstash.logback.marker.LogstashMarker;
import net.logstash.logback.encoder.LogstashEncoder;
import static net.logstash.logback.marker.Markers.*;
import java.io.IOException;

public class CustomEncoder extends LogstashEncoder {

    @Override
    public void doEncode(ILoggingEvent event) throws IOException {
        LogstashMarker marker = (LogstashMarker)event.getMarker();
        LoggingEvent ev = (LoggingEvent)event;
        if (marker != null) {
            marker.add(append(Singleton.hello, "world"));
        }
        super.doEncode(ev);
    }
}
