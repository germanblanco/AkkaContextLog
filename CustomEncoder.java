import ch.qos.logback.classic.spi.ILoggingEvent;
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
        ObjectNode eventNode = MAPPER.createObjectNode();
        eventNode.put(Singleton.hello, "world");
        eventNode.put("@message", event.getFormattedMessage());
        
        outputStream.write(MAPPER.writeValueAsBytes(eventNode));
        outputStream.write(CoreConstants.LINE_SEPARATOR.getBytes());
        
        if (immediateFlush) {
            outputStream.flush();
        }
    }
}
