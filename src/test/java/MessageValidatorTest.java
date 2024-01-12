import com.mziuri.message.MessageValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MessageValidatorTest {
    @Test
    public void testValidateMessageIsValid() {
        MessageValidator messageValidator = MessageValidator.getInstance();
        assertTrue(messageValidator.validateMessage("Hello World!"));
    }

    @Test
    public void testValidateMessageIsInvalid() {
        MessageValidator messageValidator = MessageValidator.getInstance();
        assertFalse(messageValidator.validateMessage(null));
        assertFalse(messageValidator.validateMessage("hi \n hello"));
    }
}
