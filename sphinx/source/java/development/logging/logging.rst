.. _java-development-logging-java-logging-label:

Java logging
============
- goals:
    - problem diagnosis by ed ysers ad system administrator
    - problem diagnosis by field service engineers
    - problem diagnosys by the development organization
    - problem diagnosis by developers

- key elements:
    - Logger:
        - the main entity which applications make logging calls
    - LogRecord:
        - used to pass logging requests between the logging framework and individual log handlers 
    - Handler:
        - exports log records objects to variety of destinations, including memory, output streams, consoles, files, sockets
    - Level:
        - defines a set of standards login levels that can be used to control login output
        - programs can be configured to output loging for some levels while ignoring output for others
        - values:
            
    - Filter:
        - provides fine graned control over what gets logged beyond the control provided by log level
    - Formatter:
        - provides support for formatting log records objects
        - there are 2 formatters:
            - simple formatter 
            - xml formatter


Log levels
----------
- SL4j leves:
    - FATAL:
        -  the log level indicates that your application encountered an event that prevents if from working or a crucial part of it from working
    - ERROR:
        - log level that indicates an issue with a system that prevents certain functionality from working
    - WARN:
        - log level that usually indicates a state of the application that might be problemtic or unusual execption is detected
    - INFO:
        - the standard level of log information that indicates normal application action
    - DEBUG:
        - less granular than TRACE level, but still more granular than INFO level, add more detailed than need in nour normal use
    - TRACE:
        - very fine-graned information only used in rare case where you need the full visibility of what is happening

- Java log levels:
    - OFF
    - SEVERE
    - WARNING
    - INFO
    - CONFIG
    - FINE
    - FINER
    - FINEST
    - ALL


    .. image:: ../../../images/java/development/logging/logging-levels-java-vs-slf4j.png
        :align: center


Handlers
--------
- existing handlers:
    - ConsoleHandler:
        - records all the log mesages to System.err
    - FileHandler:
        - is used to record all the log messages to a specific file
    - StreamHandler:
        - publishes all the log messages to an OutputStream
    - SocketHandler:
        - publish the records to a network stream connection
    - MomeryHandler:
        - it is used to keep records into a memory buffer

- to create custom handler:
    - create a new class extending Handler and provide implementation for:
        - publish(LogRecord)
        - flush()
        - close() 


    .. code-block:: python
           :linenos:

            import java.util.logging.Handler;
            import java.util.logging.LogRecord;

            public class CustomHandlerDemo extends Handler {

                @Override
                public void publish(LogRecord logRecord) {
                    System.out.println(
                            String.format("Log level: %s, message: %s", 
                                    logRecord.getLevel().toString(), logRecord.getMessage()));
                }

                @Override
                public void flush() {
                }

                @Override
                public void close() throws SecurityException {
                }
            }


Formatters
----------
- is used to format a LogRecord to String value
- existing formatters:
    - SimpleDateFormat => generate all messages as text
    - XMLFormatter => generates XML output for the log message

- example of custom formatter:


    .. code-block:: python
           :linenos:

        import java.text.SimpleDateFormat;
        import java.util.Date;
        import java.util.logging.Formatter;
        import java.util.logging.Handler;
        import java.util.logging.Level;
        import java.util.logging.LogRecord;

        // this custom formatter formats parts of a log record to a single line
        public class MyHtmlFormatter extends Formatter {
            
            // this method is called for every log records
            @Override
            public String format(LogRecord rec) {
                StringBuffer buf = new StringBuffer(1000);
                buf.append("<tr>\n");

                // colorize any levels >= WARNING in red
                if (rec.getLevel().intValue() >= Level.WARNING.intValue()) {
                    buf.append("\t<td style=\"color:red\">");
                    buf.append("<b>");
                    buf.append(rec.getLevel());
                    buf.append("</b>");
                } else {
                    buf.append("\t<td>");
                    buf.append(rec.getLevel());
                }

                buf.append("</td>\n");
                buf.append("\t<td>");
                buf.append(calcDate(rec.getMillis()));
                buf.append("</td>\n");
                buf.append("\t<td>");
                buf.append(formatMessage(rec));
                buf.append("</td>\n");
                buf.append("</tr>\n");

                return buf.toString();
            }

            private String calcDate(long millisecs) {
                SimpleDateFormat date_format = new SimpleDateFormat("MMM dd,yyyy HH:mm");
                Date resultdate = new Date(millisecs);
                return date_format.format(resultdate);
            }

            // this method is called just after the handler using this
            // formatter is created
            @Override
            public String getHead(Handler h) {
                return "<!DOCTYPE html>\n<head>\n<style>\n"
                    + "table { width: 100% }\n"
                    + "th { font:bold 10pt Tahoma; }\n"
                    + "td { font:normal 10pt Tahoma; }\n"
                    + "h1 {font:normal 11pt Tahoma;}\n"
                    + "</style>\n"
                    + "</head>\n"
                    + "<body>\n"
                    + "<h1>" + (new Date()) + "</h1>\n"
                    + "<table border=\"0\" cellpadding=\"5\" cellspacing=\"3\">\n"
                    + "<tr align=\"left\">\n"
                    + "\t<th style=\"width:10%\">Loglevel</th>\n"
                    + "\t<th style=\"width:15%\">Time</th>\n"
                    + "\t<th style=\"width:75%\">Log Message</th>\n"
                    + "</tr>\n";
              }

            // this method is called just after the handler using this
            // formatter is closed
            public String getTail(Handler h) {
                return "</table>\n</body>\n</html>";
            }
        }

:ref:`Go Back <java-development-logging-label>`.