package at.petrak.conlibutor;

import org.slf4j.Logger;

// yoink from mc code
public class DefaultUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    private final Logger logger;

    public DefaultUncaughtExceptionHandler(Logger $$0) {
        this.logger = $$0;
    }

    @Override
    public void uncaughtException(Thread $$0, Throwable $$1) {
        this.logger.error("Caught previously unhandled exception :", $$1);
    }
}
