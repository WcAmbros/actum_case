import org.telegram.telegrambots.api.methods.GetFile;
import org.telegram.telegrambots.api.methods.ParseMode;
import org.telegram.telegrambots.api.methods.send.SendDocument;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.PhotoSize;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static java.lang.Math.incrementExact;
import static java.lang.Math.toIntExact;


// Хозяин, прочти!!!!!!!!!! Поменя данные в ControlNameTokenBot
public class Bot extends TelegramLongPollingBot {
    ControlToken controlToken = new ControlToken();
    MainVariables mainVariables = new MainVariables();
    SaveLogs saveLogs = new SaveLogs();
    Spam Spam = new Spam();
    Boolean startauth = false;
    private String answer1, answer2, ask;
    private int ans1 = 0, ans2 = 0;// переменные для голосования


    @Override
    public void onUpdateReceived(Update update) {
//        System.out.println(update.getMessage());
        ArrayList<String> mainmenu = mainVariables.getmainmenu();
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();
            getWhatBotSay(message_text, chat_id); // Для нахождения вопрсно ответной инфы
            getInfoCase(message_text, chat_id); // для определения и отправки кесов
            getInfoHack(message_text, chat_id);
// /start АВТОРИЗАЦИЯ


            //Комманды организаторов
            if (message_text.equals("/admcmd")) {
                SendMes(chat_id, "/startVoring вопрос, ответ1, ответ2" + "\n" +
                        "/getVoteState статистика" + "\n" +
                        "/ory текст");
            }
            if (message_text.contains("/startVoting")) {
                message_text = message_text.replace("/startVoting ", "");
                System.out.println();
                String textText = "";
                int o = 1;
                String part1 = "";
                String part2 = "";
                String part3 = "";
                for (int z = 0; z < message_text.length(); z++) // пробигаемся по строке
                {
                    if (message_text.charAt(z) == ',' && o == 1) {
                        z++;
                        part1 = part1 + textText;
                        textText = "";
                        o++;
                    }
                    if (message_text.charAt(z) == ',' && o == 2) {
                        z++;
                        part2 = textText;
                        textText = "";
                        o++;
                    }
                    textText = textText + message_text.charAt(z);
                    if (o == 3) {
                        part3 = textText;

                    }

                }
                StartVoting(chat_id, part1, part2, part3);
            }
            if (message_text.equals("/a")) {
                StartVoting(chat_id, "Пицца или суши?", "Суши", "Пицца");
            }
            if (message_text.equals("/a2") || message_text.equals("/getVoteState")) {
                getVoteState();
            }
            if (message_text.contains("/ory ")) {
                message_text = message_text.replace("/ory ", " ");
                Spam.startspam(message_text);
            }
            // END Комманды организаторов

            if (message_text.equals("\uD83D\uDCC5 Расписание хаккатонов")) {

            }

            if (update.getMessage().getText().equals("ℹ️ Кейсы")) {
                ArrayList<String> cases = MainVariables.getcase();
                setbutton(chat_id, "Выбирай скорее", cases);
            }
            if (message_text.equals("\uD83D\uDCC5 Расписание хаккатонов")) {
                ArrayList<HacksInfo> ListHackInfo = mainVariables.getHacksInfo();
                ArrayList<String>namehacks = new ArrayList<>();
                for (int i = 0; i < ListHackInfo.size(); i++) {
                   namehacks.add(ListHackInfo.get(i).getName());
                }
                namehacks.add("⬅️ В самое начало");
                setbutton(chat_id, "Смотри вниз",namehacks);
            }

            if (update.getMessage().getText().equals("/start")) // НАЧАЛЬНОЕ МЕНЮ
            {

                Saveuserstotxt Saveuserstotxt = new Saveuserstotxt();   // сохраняем временно в txt дабы сделать рассылку
                try {
                    Saveuserstotxt.saveid(String.valueOf(chat_id));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                setbutton(chat_id, "Привет, друг! Хочешь что-то узнать о хаккатоне?", MainVariables.getstartmenu());
            }

            if (message_text.equals("⬅️ Назад") || message_text.equals("⬅️ В самое начало")) // НАЧАЛЬНОЕ МЕНЮ
            {
                setbutton(chat_id, "Начальное меню \uD83D\uDC47", MainVariables.getstartmenu());
            }

            if (message_text.equals("⬅️ Назад в меню!")) { // ВОЗВРЩЕНИЕ В ОСНОВНОЕ МЕНЮ
                setbutton(update.getMessage().getChatId(), "Туда \uD83D\uDC47", mainmenu);
            }

            if (update.getMessage().getText().equals("\uD83C\uDFC5 Авторизация")) {
                SendMessage sendMessage = new SendMessage()
                        .setChatId(chat_id)
                        .setText("Сначала сделай выбор, потом сделай все команды!\uD83D\uDC47");
                // create keyboard
                ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
                sendMessage.setReplyMarkup(replyKeyboardMarkup);
                replyKeyboardMarkup.setSelective(true);
                replyKeyboardMarkup.setResizeKeyboard(true);
                replyKeyboardMarkup.setOneTimeKeyboard(true);
                // new list
                List<KeyboardRow> keyboard = new ArrayList<>();
                // first keyboard line
                KeyboardRow keyboardFirstRow = new KeyboardRow();
                KeyboardButton keyboardButton = new KeyboardButton();
                keyboardButton.setText("\uD83D\uDCF1 Авторизоваться через телефон").setRequestContact(true);
                keyboardFirstRow.add(keyboardButton);

                KeyboardRow keyboardRow = new KeyboardRow();
                keyboardRow.add(new KeyboardButton("✉️ Авторизоваться через email"));
                keyboard.add(keyboardFirstRow);

                KeyboardRow keyboardRow2 = new KeyboardRow();
                keyboardRow2.add(new KeyboardButton("⬅️ Назад"));

                keyboard.add(keyboardRow);
                keyboard.add(keyboardRow2);
                // add list to our keyboard
                replyKeyboardMarkup.setKeyboard(keyboard);
                try {
                    sendMessage(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

            if (message_text.equals("\uD83D\uDC14 Мотивация здесь и сейчас \uD83D\uDC14")) { //МОТИВАЦИЯ
                SendMessage message = new SendMessage() // Create a message object object
                        .setChatId(update.getMessage().getChatId())
                        .setParseMode(ParseMode.MARKDOWN)
                        .setText("Вася, мотивируйся давай!\nhttps://www.youtube.com/watch?v=h0yeXemxS2c&app=desktop");
                try {
                    execute(message); // Sending our message object to user
                } catch (TelegramApiException e) {
                    saveLogs.save("Ошибка отправки bot-SendMes1 " + e);
                }
            }
            if (message_text.equals("✉️ Авторизоваться через email")) { //ЗАКОНЧИТЬ АВТОРИЗАЦИЮ ЧЕРЕЗ ЕМАИЛ
                SendMes(chat_id, "Ну, тут закрыто пока...");
                //ПРОВЕРКА ЕМАИЛА В БД

                setbutton(update.getMessage().getChatId(), "Добро пожаловать в личный кабинет!", mainmenu);
            }

            if (message_text.equals("\uD83D\uDC64 Профиль")) {
                ArrayList<String> profile = mainVariables.getprofile();
                setbutton(update.getMessage().getChatId(), "*Профиль* \uD83D\uDC64 \n" +
                        "FirstName" + " " + "SecondName\n" +
                        "*Специальность:* " + "Атвичаю крутой пасан!(Специально для I Апрельский)" +
                        "\n" + "*Обо мне:* я тащу в дотке, сливаю в лоле, ХАКАТОНЫ ЭТО ТОП" +
                        "\n*SoftSkills:* Креативность: 40%. Упорство: 20%. Гибкость: 40%" +
                        "\n*ProfSkills:* Java: 35%. Mysql: 30%. Android: 35%", profile);
            }
            if (message_text.equals("↩️ Сменить специальность")) {
                ArrayList<String> profile = mainVariables.getprofile();
                setbutton(update.getMessage().getChatId(), "В рамках презентации редактирование закрыто, чтобы люди не спамили!(Это плохо!)", profile);
            }
            if (message_text.equals("\uD83D\uDC65 Команда")) {

                setbutton(update.getMessage().getChatId(), "Команда - это важно!", mainVariables.getTeam());
            }
            if (message_text.equals("\uD83D\uDC65 Состав команды")) {

                setbutton(update.getMessage().getChatId(), "Похоже вы один в команде =(", mainVariables.getTeam());
            }
            if (message_text.equals("Найти команду")) {

                setbutton(update.getMessage().getChatId(), "Выбирай с умом!", mainVariables.findteam());
            }
            if (message_text.equals("Хакатонщики(Даниил) ищет дизайнера, кто первый ливнет")) {

                setbutton(update.getMessage().getChatId(), "Telegram: @namerpiper", mainVariables.findteam());
            }
            if (message_text.equals("Название(Лидер) ищет Java-программиста, крутого парня")) {

                setbutton(update.getMessage().getChatId(), "Telegram: @КОНТАКТЫ", mainVariables.findteam());
            }

            if (message_text.equals("✊️О нас")) {
                SendMes(chat_id, "Мегафон, конечно, классная компания, но с наклейками обманула! Так что Актум топ ин зе волд!");
                SendPhoto(chat_id, "https://memepedia.ru/wp-content/uploads/2017/05/%D0%B7%D0%B0%D0%B2%D0%BE%D1%80%D0%B0%D0%B6%D0%B8%D0%B2%D0%B0%D1%8E%D1%89%D0%B5%D0%B5-%D0%BF%D1%80%D0%B5%D0%B2%D0%BE%D1%81%D1%85%D0%BE%D0%B4%D1%81%D1%82%D0%B2%D0%BE.jpg");
            }


        } else if (update.hasCallbackQuery())

        { // ПРОВЕДЕНИЕ ГОЛОСОВАНИЯ
            String call_data = update.getCallbackQuery().getData();
            long message_id = update.getCallbackQuery().getMessage().getMessageId();
            long chat_id = update.getCallbackQuery().getMessage().getChatId();

            if (call_data.equals(answer1)) {
                ans1++;
            } else if (call_data.equals(answer2)) {
                ans2++;
            }
            EditMessageText new_message = new EditMessageText()
                    .setChatId(chat_id)
                    .setParseMode(ParseMode.MARKDOWN)
                    .setMessageId(toIntExact(message_id))
                    .setText("Хороший выбор!");
            try {
                execute(new_message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }


        } else if (update.getMessage().

                hasPhoto())

        {
            getPhoto(update);

        } else

        { // АВТОРИЗАЦИЯ ЧЕРЕЗ ТЕЛЕФОН
            String phonenumber = update.getMessage().getContact().getPhoneNumber();
            //System.out.println(update.getMessage().getContact().getPhoneNumber()); // получение номера телефона
            // РЕАЛИЗОВАТЬ ЗАПРОС К БД И СРАВНЕНИЕ НОМЕРА ТЕЛЕФОНА
            if (phonenumber.equals("+79995281992")) {
                setbutton(update.getMessage().getChatId(), "Добро пожаловать в личный кабинет!", mainmenu);
            } else {
                SendMessage sendMessage = new SendMessage()
                        .setChatId(update.getMessage().getChatId())
                        .setText("Парень, не обманывай меня!");
                // create keyboard
                ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
                sendMessage.setReplyMarkup(replyKeyboardMarkup);
                replyKeyboardMarkup.setSelective(true);
                replyKeyboardMarkup.setResizeKeyboard(true);
                replyKeyboardMarkup.setOneTimeKeyboard(true);
                // new list
                List<KeyboardRow> keyboard = new ArrayList<>();
                // first keyboard line
                KeyboardRow keyboardFirstRow = new KeyboardRow();
                KeyboardButton keyboardButton = new KeyboardButton();
                keyboardButton.setText("\uD83D\uDCF1 Авторизоваться через телефон").setRequestContact(true);
                keyboardFirstRow.add(keyboardButton);

                KeyboardRow keyboardRow = new KeyboardRow();
                keyboardRow.add(new KeyboardButton("✉️ Авторизоваться через email"));
                keyboard.add(keyboardFirstRow);

                KeyboardRow keyboardRow2 = new KeyboardRow();
                keyboardRow2.add(new KeyboardButton("⬅️ Назад"));

                keyboard.add(keyboardRow);
                keyboard.add(keyboardRow2);
                // add list to our keyboard
                replyKeyboardMarkup.setKeyboard(keyboard);
                try {
                    sendMessage(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

        }


    }


    //СОХРАНЕНИЕ ФОТОГРАФИЙ


    // СООБЩЕНИЯ
    public void SendMes(long chatid, String text) {
        SendMessage message = new SendMessage() // Create a message object object
                .setChatId(chatid)
                .disableWebPagePreview()
                .setParseMode(ParseMode.MARKDOWN)
                .setText(text);
        try {
            execute(message); // Sending our message object to user
        } catch (TelegramApiException e) {
            saveLogs.save("Ошибка отправки bot-SendMes1 " + e);
        }
    }

    public void SendMes(String chatid, String text) {
        SendMessage message = new SendMessage() // Create a message object object
                .setParseMode(ParseMode.MARKDOWN)
                .setChatId(chatid)
                .disableWebPagePreview()
                .setText(text);
        try {
            execute(message); // Sending our message object to user
        } catch (TelegramApiException e) {

            saveLogs.save("Ошибка отправки bot-SendMes2 " + e);
        }
    }

    public void SendPhoto(long chatid, String url) {
        SendPhoto msg = new SendPhoto()
                .setChatId(chatid)
                .setPhoto(url);
        try {
            sendPhoto(msg); // отправляем сообщение пользователю
        } catch (TelegramApiException e) {
            saveLogs.save("Ошибка отправки bot-SendPhoto " + e);
        }
    }

    public void SendFile(long chatid, String fileid) {
        SendDocument sendDocument = new SendDocument();
        sendDocument.setChatId(chatid);
        sendDocument.setDocument(fileid);
        try {
            sendDocument(sendDocument);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    //КНОПКИ В МЕНЮ

    public synchronized void setbutton(Long chatId, String s, ArrayList<String> NameButton) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);
        // Создаем клавиуатуру
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        // Создаем список строк клавиатуры
        List<KeyboardRow> keyboard = new ArrayList<>();
        for (int i = 0; i < NameButton.size(); i++) {
            KeyboardRow keyboardRow = new KeyboardRow();
            keyboardRow.add(new KeyboardButton(NameButton.get(i)));
            keyboard.add(keyboardRow);
        }
        replyKeyboardMarkup.setKeyboard(keyboard);
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            SaveLogs.save(e.toString());
        }
    } // кнопки меню

    public void startTest(Long chatid, ArrayList<String> list, String question) {
        SendMessage message = new SendMessage()
                .setChatId(chatid)
                .setText(question);
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText(list.get(0)).setCallbackData(list.get(0)));
        rowInline.add(new InlineKeyboardButton().setText(list.get(1)).setCallbackData(list.get(1)));
        rowInline.add(new InlineKeyboardButton().setText(list.get(2)).setCallbackData(list.get(2)));
        rowInline.add(new InlineKeyboardButton().setText(list.get(3)).setCallbackData(list.get(3)));
        rowsInline.add(rowInline);
        markupInline.setKeyboard(rowsInline);
        message.setReplyMarkup(markupInline);
        try {
            execute(message); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    public void startTheory(Long chatid) {
        int userlvl; // получаем уровень пользователя из бд
        // тут получаем последнее время, когда пользователь решал тест из бд
        ArrayList<String> theorybutton = new ArrayList<>();
        theorybutton.add("Тестик");
        theorybutton.add("Главное меню");
        // ПОЛУЧАЕМ ТЕОРИЮ ИЗ БАЗЫ ДАННЫХ
        setbutton(chatid, "ТУТ БУДЕТ ТЕОРИЯ", theorybutton);

    }

    // ПОИСК ШАБЛОННЫХ ОТВЕТОВ(ALL TEXT ANSWER)
    public void getWhatBotSay(String youAsk, Long chatid) {
        ArrayList<youAskIsay> ListyouAskIsay = mainVariables.getYouAskIsay();
        for (int i = 0; i < ListyouAskIsay.size(); i++) {
            if (ListyouAskIsay.get(i).getYouAsk().equals(youAsk)) {
                SendMes(chatid, ListyouAskIsay.get(i).getIsay());
            }
        }
    }

    // ПОИСК ШАБЛОННЫХ ОТВЕТОВ(CASE)
    public void getInfoCase(String namecase, Long chatid) {
        ArrayList<CaseInfo> ListFileIdCase = mainVariables.getFileIdCase();
        for (int i = 0; i < ListFileIdCase.size(); i++) {
            if (ListFileIdCase.get(i).getName().equals(namecase)) {
                SendFile(chatid, ListFileIdCase.get(i).getId());
            }
        }
    }

    public void getInfoHack(String namehack, Long chatid) {
        ArrayList<HacksInfo> ListHackInfo = mainVariables.getHacksInfo();
        for (int i = 0; i < ListHackInfo.size(); i++) {
            if (ListHackInfo.get(i).getName().equals(namehack)) {
                SendMes(chatid, ListHackInfo.get(i).getDate() + "\n" +
                        ListHackInfo.get(i).getName() + "\n" +
                        ListHackInfo.get(i).getInfo() + "\n" +
                        ListHackInfo.get(i).getPlace());
                SendPhoto(chatid,ListHackInfo.get(i).getUrlImg());
            }
        }
    }

    //СОХРАНЕНИЮ ФОТОГРАФИИЙ ИЗ ТЕЛЕГРАММА
    public PhotoSize getPhoto(Update update) {
        if (update.hasMessage() && update.getMessage().hasPhoto()) {
            List<PhotoSize> photos = update.getMessage().getPhoto();
            getFilePath(photos.stream()
                    .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
                    .findFirst()
                    .orElse(null), update.getMessage().getChatId());
        }
        return null;
    }

    public String getFilePath(PhotoSize photo, Long chatid) {
        Objects.requireNonNull(photo);
        if (photo.hasFilePath()) { // If the file_path is already present, we are done!
            return photo.getFilePath();
        } else { // If not, let find it
            // We create a GetFile method and set the file_id from the photo
            GetFile getFileMethod = new GetFile();
            getFileMethod.setFileId(photo.getFileId());
            try {
                // We execute the method using AbsSender::execute method.
                org.telegram.telegrambots.api.objects.File file = execute(getFileMethod);
                // We now have the file_path
                //System.out.println(file.getFileUrl(controlToken.getToken()));
                downloadPhotoByFilePath(file.getFilePath(), chatid);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public File downloadPhotoByFilePath(String filePath, Long chatid) {
        String namefile;
        try {
            BufferedImage image = ImageIO.read(downloadFile(filePath));
            Date date = new Date();
            Random rand = new Random();
            namefile = "Ot " + date.toString() + ".jpg";
            File out = new File(namefile);

            if (out.exists()) {
                Thread.sleep(1000);
                namefile = "Ot " + date.toString() + ".jpg";
                out = new File(namefile);
            }
            ImageIO.write(image, "jpg", out);
            System.out.println("Фотография(" + namefile + ") сохранена!");
            SendMes(chatid, "Фотография(" + namefile + ") сохранена!");
            // return downloadPhotoByFilePath(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    //СОЗДАНИЕ ГОЛОСОВАНИЯ
    public synchronized void StartVoting(long chatId, String ask, String answer1, String answer2) {

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("chat_id_botID.txt"), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) { // цикл, благодаря, которому мы пробигаемся по базе пользоватлей и ищим совпадения.

                this.ask = ask;
                this.answer1 = answer1;
                this.answer2 = answer2;
                ans1 = 0;
                ans2 = 0;
                SendMessage message = new SendMessage() // Create a message object object
                        .setChatId(line)
                        .setText(ask);
                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                List<InlineKeyboardButton> rowInline = new ArrayList<>();
                rowInline.add(new InlineKeyboardButton().setText(answer1).setCallbackData(answer1));
                rowInline.add(new InlineKeyboardButton().setText(answer2).setCallbackData(answer2));
                // Set the keyboard to the markup
                rowsInline.add(rowInline);
                // Add it to the message
                markupInline.setKeyboard(rowsInline);
                message.setReplyMarkup(markupInline);
                try {
                    execute(message); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

            }
        } catch (IOException e) {
            System.out.println("ОШИБКА В СТАРТВОТИНГ" + e);
        }


    }

    public void getVoteState() {
        SendMes(388776391,  ask +"\n"+ answer1 + ":" + ans1 + "\n" + answer2 + ":" + ans2);
    }


    //ПРОЧЕЕ

    @Override
    public String getBotUsername() {
        return controlToken.getNameBot();
    }

    @Override
    public String getBotToken() {
        return controlToken.getToken();
    }


}