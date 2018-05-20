import java.util.ArrayList;

public class MainVariables {
    public ArrayList<String> getmainmenu() {
        ArrayList<String> pointmenu = new ArrayList<>();
        pointmenu.add("\uD83D\uDC64 Профиль");
        pointmenu.add("\uD83D\uDC65 Команда");
        pointmenu.add("ℹ️ Кейсы");
        pointmenu.add("⬅️ В самое начало");
        return pointmenu;
    } // ОСНОВНОЕ МЕНЮ

    public static ArrayList<String> getstartmenu() {
        ArrayList<String> startmenu = new ArrayList<>();
        startmenu.add("✊️О нас");
        startmenu.add("\uD83C\uDFC5 Авторизация");
        startmenu.add("\uD83D\uDC14 Мотивация здесь и сейчас \uD83D\uDC14");
        startmenu.add("\uD83D\uDCC5 Расписание хаккатонов");
        startmenu.add("\uD83C\uDF0D Наш сайт");
        startmenu.add("☎️ Контакты");

        return startmenu;
    } // НАЧАЛЬНОЕ МЕНЮ

    public static ArrayList<String> getprofile() {
        ArrayList<String> profile = new ArrayList<>();
        profile.add("\uD83D\uDC64 Профиль");
        profile.add("↩️ Сменить специальность");
        profile.add("⬅️ Назад в меню!");
        return profile;
    } //МЕНЮ ПРОФИЛЯ

    public static ArrayList<String> getcase() { //КЕЙСЫ
        ArrayList<String> cases = new ArrayList<>();
        cases.add("Кейс от Actum'a");
        cases.add("Кейс от Мегафона №1");
        cases.add("Кейс от Мегафона №2");
        cases.add("Кейс от АСИ №1");
        cases.add("Кейс от АСИ №2");
        cases.add(" ⬅️ Назад в меню!");
        return cases;
    } // МЕНЮ КЕЙСОВ

    public static ArrayList<String> getTeam() { //МЕНЮ КОМАНДЫ
        ArrayList<String> team = new ArrayList<>();
        team.add("\uD83D\uDC65 Состав команды");
        team.add("Найти команду");
        team.add("Загрузить прототип");
        team.add("Загрузить презентацию");
        team.add(" ⬅️ Назад в меню!");
        return team;
    }
    public static ArrayList<String> findteam() { //МЕНЮ КОМАНДЫ
        ArrayList<String> team = new ArrayList<>();
        team.add("Хакатонщики(Даниил) ищет дизайнера, кто первый ливнет");
        team.add("Название(Лидер) ищет Java-программиста, крутого парня");
        team.add(" ⬅️ Назад в меню!");
        return team;
    }

    public ArrayList getFileIdCase() { // ФАЙЛЫ КЕЙСОВ
        ArrayList<CaseInfo> ListFileIdCase = new ArrayList<>();
        ListFileIdCase.add(new CaseInfo("Кейс от Actum'a", "BQADAgADXAIAAsWVAUjTl_lN554nCAI"));
        ListFileIdCase.add(new CaseInfo("Кейс от Мегафона №1", "BQADAgADVAEAAllxCEjYRzXw3RCBMwI"));
        ListFileIdCase.add(new CaseInfo("Кейс от Мегафона №2", "BQADAgADVQEAAllxCEgOQPDiSH53WAI"));
        ListFileIdCase.add(new CaseInfo("Кейс от АСИ №1", "BQADAgADXgIAAsWVAUiTKrjPlDtIPAI"));
        ListFileIdCase.add(new CaseInfo("Кейс от АСИ №2", "BQADAgADXQIAAsWVAUhGLgMD69Ar7wI"));
        return ListFileIdCase;
    }

    public ArrayList getYouAskIsay() { // СИСТЕМА ДЛЯ ХРАНЕНИЯ ВОПРОСНО ОТВЕТНОЙ ФОРЫ
        ArrayList<youAskIsay> ListyouAskIsay = new ArrayList<>();
        ListyouAskIsay.add(new youAskIsay("kek", "lol"));
        // ListyouAskIsay.add(new youAskIsay("✊️О нас", "Тут будет инфа о Актуме"));
//        ListyouAskIsay.add(new youAskIsay("\uD83C\uDF0D Наш сайт", "http://actum.online/about-us/"));
//        ListyouAskIsay.add(new youAskIsay("☎️ Контакты", "\uD83D\uDCCD 123100, Москва, Пресненская набережная дом 12 Башня Федерация «Восток»\n ☎️+7 (903) 118 51 33\n✉️ info@actum.online"));
        ListyouAskIsay.add(new youAskIsay("\uD83C\uDF0D Наш сайт", "Пока на локалке =("));
        ListyouAskIsay.add(new youAskIsay("☎️ Контакты", "TOP TEAM IN THE WORLD! CREATE siller174@gmail.com + team"));
        ListyouAskIsay.add(new youAskIsay("kek", "lol"));
        ListyouAskIsay.add(new youAskIsay("Загрузить прототип", "Сорян, сервер переполнен. Хочешь помочь? Пиши @NamerPiper"));
        ListyouAskIsay.add(new youAskIsay("Загрузить презентацию", "Сорян, сервер переполнен. Хочешь помочь? Пиши @NamerPiper"));
        return ListyouAskIsay;
    }

    public ArrayList getHacksInfo() { // СИСТЕМА Для хранения информации о хакатонах
        ArrayList<HacksInfo> ListHackInfo = new ArrayList<>();
        ListHackInfo.add(new HacksInfo("Smart-hack",
                "22-23 мая",
                "\"Умный и безопасный город\". Успейте зарегистрироваться и выбрать наиболее интересную тему на: www.hackingdays.ru \n" +
                        "\n" +
                        "Вас ждут выступления экспертов, денежные призы, бешеная энергетика и крутая вечеринка! \n" +
                        "\n" +
                        "Темы разработок: \n" +
                        "- Блокчейн \n" +
                        "- Искусственный Интеллект \n" +
                        "- IoT \n" +
                        "- Биоидентификация", "Пр. Медиков д.5","https://pp.userapi.com/c834204/v834204536/144e22/5Rnbufw9Prc.jpg"));
        ListHackInfo.add(new HacksInfo("Супер Дупер", "02.10", "Совершенно новый подход к хакатонам, с крутым сайтом и телеграм ботом", "Место уточняется","http://pics.wikireality.ru/upload/thumb/a/a1/Calm_down.jpg/300px-Calm_down.jpg"));

        return ListHackInfo;
    }

//    public ArrayList getTeam() { // СИСТЕМА Для хранения информации о хакатонах
//        ArrayList<Team> ListHackInfo = new ArrayList<>();
//        ListHackInfo.add(new HacksInfo("Smart-hack",
//                "22-23 мая",
//                "В самом сердце Петроградки пройдет 2-х дневный Хакатон на тему:Умный и безопасный город. www.hackingdays.ru", "Место уточняется","https://pp.userapi.com/c834204/v834204536/144e22/5Rnbufw9Prc.jpg"));
//        ListHackInfo.add(new HacksInfo("Супер Дупер", "02.10", "Совершенно новый подход к хакатонам, с крутым сайтом и телеграм ботом", "Место уточняется","http://pics.wikireality.ru/upload/thumb/a/a1/Calm_down.jpg/300px-Calm_down.jpg"));
//
//        return ListHackInfo;
//    }
}
