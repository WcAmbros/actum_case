import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Spam {

    public void startspam(String mes) {
        // заполнить база данных
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("chat_id_botID.txt"), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) { // цикл, благодаря, которому мы пробигаемся по базе пользоватлей и ищим совпадения.
                {
                    Bot bot = new Bot();
                    bot.SendMes(line, mes);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
