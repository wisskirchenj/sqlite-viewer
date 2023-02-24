package de.cofinpro.sqliteviewer.log4j;

import lombok.Getter;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;

import java.util.ArrayList;
import java.util.List;
@Getter
public class MockedAppender extends AbstractAppender {

    List<String> messages = new ArrayList<>();

    public MockedAppender() {
        super("MockedAppender", null, null, true, null);
    }

    @Override
    public void append(LogEvent event) {
        messages.add(event.getMessage().getFormattedMessage());
        messages.add(event.getThrown().toString());
    }
}
