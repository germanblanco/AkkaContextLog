import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggingEvent;
import net.logstash.logback.marker.LogstashMarker;
import static net.logstash.logback.marker.Markers.*;
import java.io.IOException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import ch.qos.logback.core.CoreConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonGenerator.Feature;

public class CustomEncoder extends net.logstash.logback.encoder.LogstashEncoder {

    private static final ObjectMapper MAPPER = new ObjectMapper().configure(Feature.ESCAPE_NON_ASCII, true);

    private boolean immediateFlush = false;

    public CustomEncoder() {
    }

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
