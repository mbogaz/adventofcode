package util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class InputFileUtil {

    public static String readFileAsString(String fileName) throws IOException {
        InputStream is = InputFileUtil.class.getClassLoader().getResourceAsStream(fileName);
        return convertStreamToString(Objects.requireNonNull(is));
    }

    private static String convertStreamToString(InputStream is) throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
            is.transferTo(baos);
            return baos.toString(StandardCharsets.UTF_8);
        }
    }
}
