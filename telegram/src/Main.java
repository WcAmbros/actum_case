//import org.telegram.telegrambots.ApiContextInitializer;
//import org.telegram.telegrambots.TelegramBotsApi;
//import org.telegram.telegrambots.exceptions.TelegramApiException;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

// Хозяин, прочти!!!!!!!!!! Поменя данные в ControlNameTokenBot
public class Main {
    public static void main(String[] args) {
        SaveLogs saveLogs = new SaveLogs();
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            saveLogs.save("=====================New Session=========================");
            botsApi.registerBot(new Bot());
            saveLogs.save("Бот запустился Хозяин!");
        } catch (TelegramApiException e) {
            e.printStackTrace();
            saveLogs.save("Ошибка при запуске бота: " +e);
        }

    }
}
