
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Date;

public class SaveLogs {
    public static void save(String text) {
        try (FileWriter writer = new FileWriter( "logs.txt", true)) {
            Date date = new Date();
            System.out.println("Добавлено в логи: " +date.toString()+ " "+text);
            writer.write(date.toString()+ " "+text+"\n");
            writer.flush();
        }
        catch (Exception e) {
            System.out.println("Ошибка записи файла Logs:" + e);
        }
    }
    public static void remove()
    {
        try {
            FileWriter fstream1 = new FileWriter("logs.txt");// конструктор с одним параметром - для перезаписи
            BufferedWriter out1 = new BufferedWriter(fstream1); //  создаём буферезированный поток
            out1.write(""); // очищаем, перезаписав поверх пустую строку
            out1.close(); // закрываем
        } catch (Exception e) {
            System.err.println("Error in file cleaning: " + e.getMessage());
        }
    }
}