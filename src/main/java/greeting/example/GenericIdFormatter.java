package greeting.example;

import org.springframework.format.Formatter;

import java.lang.reflect.Constructor;
import java.text.ParseException;
import java.util.Locale;
import java.util.UUID;

public class GenericIdFormatter<I extends GenericId> implements Formatter<I> {
    private final Constructor<I> constructor;

    public GenericIdFormatter(Class<I> idClass) {
        try {
            constructor = idClass.getConstructor(UUID.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Parse a text String to produce a T.
     *
     * @param text   the text string
     * @param locale the current user locale
     * @return an instance of T
     * @throws ParseException           when a parse exception occurs in a java.text parsing library
     * @throws IllegalArgumentException when a parse exception occurs
     */
    @Override
    public I parse(String text, Locale locale) throws ParseException {
        try {
            UUID uuid = UUID.fromString(text);
            return constructor.newInstance(uuid);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Print the object of type T for display.
     *
     * @param object the instance to print
     * @param locale the current user locale
     * @return the printed text string
     */
    @Override
    public String print(I object, Locale locale) {
        return object.getId().toString();
    }
}
