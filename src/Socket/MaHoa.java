package Socket;

import java.util.Base64;

public class MaHoa {
    public String encoded(String text) {
        text = Base64.getEncoder().encodeToString(text.getBytes());
        return text;
    }
    public String decoded(String text) {
        byte[] decodedBytes = Base64.getDecoder().decode(text);
        String decodedText = new String(decodedBytes);
        return decodedText;
    }
}
