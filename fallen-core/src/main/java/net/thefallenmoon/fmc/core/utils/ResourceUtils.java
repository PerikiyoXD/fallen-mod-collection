package net.thefallenmoon.fmc.core.utils;

import com.google.common.io.CharStreams;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.io.InputStreamReader;

public class ResourceUtils {
    private static final Logger logger = LogManager.getLogger(ResourceUtils.class);

    public static String loadResourceAsString(Object mod, String path) {
        String src = null;
        try (InputStream in = mod.getClass().getResourceAsStream(path)) {
            src = CharStreams.toString(new InputStreamReader(in));
        } catch (Exception e) {
            logger.error("Error reading resource " + path, e);
        }
        return src;
    }
}
