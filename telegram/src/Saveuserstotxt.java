import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class Saveuserstotxt {



    public void saveid(String chat_id) throws IOException {
        String line;// строка в которую мы считывает ид из базы
        boolean HaveId = false; // проверка на наличие пользователя в базе
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("chat_id_botID.txt"), StandardCharsets.UTF_8))) {
            while ((line = reader.readLine()) != null) { // цикл, благодаря, которому мы пробигаемся по базе пользоватлей и ищим совпадения.
                String lts = String.valueOf(chat_id);
                if (line.contains(lts)) {
                    HaveId = true;
                }
            }
        } catch (IOException e) {
            // log error
        }
        if (!HaveId) // если не найден пользователь добавляем его в базу
        {
            FileWriter writer = new FileWriter("chat_id_botID.txt",true);
            Date currentDate = new Date();
            String date = currentDate.toString();
            String text = "\n" +chat_id;
            System.out.println("Добавил: " + text + " " + date);
            writer.write(text);
            writer.flush();
        }
    }
}
