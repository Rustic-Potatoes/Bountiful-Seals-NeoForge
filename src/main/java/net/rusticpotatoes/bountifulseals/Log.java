package net.rusticpotatoes.bountifulseals;

import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

public class Log {
    public static final Logger LOGGER = LogUtils.getLogger();

    public static void info(String message) {
        LOGGER.info(message);
    }

    public static void info(Object message) {
        LOGGER.info(String.valueOf(message));
    }

    public static void error(String message) {
        LOGGER.error(message);
    }

    public static void debug(String message) {
        LOGGER.debug(message);
    }
}
