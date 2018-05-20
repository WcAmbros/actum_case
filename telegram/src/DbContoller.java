
import java.sql.*;

public class DbContoller {
    public SaveLogs saveLogs = new SaveLogs();
    private static final String USER = "root";
    private static final String PASS = "root";
   // private static final String URL = "jdbc:mysql://185.228.232.27:3306/shopdb?useUnicode=true&useSSL=true&characterEncoding=utf8&serverTimezone=UTC";
     private static final String URL = "jdbc:mysql://localhost:3306/shopdb?useUnicode=true&useSSL=true&characterEncoding=utf8&serverTimezone=UTC";

    private static Connection connection;
    private static Statement statement;
    private static PreparedStatement pst = null;

    public void connect() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASS);

            statement = connection.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static void createNewGoods(int ShopId, int CategoryID, String ItemName, String Article, int price, String Url, String UrlPhoto) {
        SaveLogs saveLogs = new SaveLogs();
        String settingsNewGoods = "CALL shopdb.createNewGoods(?, ?, ?, ?, ?, ?, ?)";
        try {
            //call shopdb.createNewGoods(1, 1, 'testUrl', 'testUrland', 50601, 'testUrl', 'testUrlpHOTO');
            pst = connection.prepareStatement(settingsNewGoods);
            pst.setInt(1, ShopId);
            pst.setInt(2, CategoryID);
            pst.setString(3, ItemName);
            pst.setString(4, Article);
            pst.setInt(5, price);
            pst.setString(6, Url);
            pst.setString(7, UrlPhoto);
            pst.execute();
            //System.out.println("Добавлен новый товар в магазин [" + ShopId + "] : " + ItemName + " (" + Article + ") из категории " + CategoryID + ". Цена: " + price);
            saveLogs.save("Добавлен новый товар в магазин [" + ShopId + "] : " + ItemName + " (" + Article + ") из категории " + CategoryID + ". Цена: " + price);
        } catch (SQLException e) {
            saveLogs.save("Ошибка в DbController - createNewGoods1: " + e);
            e.printStackTrace();
        }
//        finally {
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    saveLogs.save("Ошибка в DbController - createNewGoods2: " + ex);
//                    // Logger.getLogger(JDBCtest.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }

    }

    static void addPrice(int ShopId, String Article, int price) {
        SaveLogs saveLogs = new SaveLogs();
        String settingsNewGoods = "CALL shopdb.setPrice(?, ?, ?)";
        try {
            pst = connection.prepareStatement(settingsNewGoods);
            pst.setInt(1, ShopId);
            pst.setString(2, Article);
            pst.setInt(3, price);
            pst.execute();
            saveLogs.save("Добавлена новая цена " + price + " для товара (" + Article + ") в магазине [" + ShopId + "]");
        } catch (SQLException e) {
            saveLogs.save("Ошибка в DbController - addPrice1: " + e);
            saveLogs.save("Ошибка с аргументами: Shopid: " + ShopId + " Article: " + Article + " Price: " + price);
            e.printStackTrace();
        }
//        finally {
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    saveLogs.save("Ошибка в DbController -addPrice2: " + ex);
//                    // Logger.getLogger(JDBCtest.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }
    }

    static int getPrice(int ShopId, String Article) {
        SaveLogs saveLogs = new SaveLogs();
        String settingsNewGoods = "CALL shopdb.getPrice(?, ?)";
        int price = 0;
        try {
            pst = connection.prepareStatement(settingsNewGoods);
            pst.setInt(1, ShopId);
            pst.setString(2, Article);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                price = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            saveLogs.save("Ошибка в DbController - getPrice1: " + e);
            saveLogs.save("Ошибка с аргументами: Shopid: " + ShopId + " Article: " + Article);
            e.printStackTrace();
        }
//        finally {
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    saveLogs.save("Ошибка в DbController -getPrice2: " + ex);
//                    // Logger.getLogger(JDBCtest.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }
        return price;
    }

    static int CheckGoods(int ShopId, String Article) {
        SaveLogs saveLogs = new SaveLogs();
        String funcheck = "SELECT shopdb.checkhavefile(?, ?)";
        int check = 0;
        try {
            pst = connection.prepareStatement(funcheck);
            pst.setInt(1, ShopId);
            pst.setString(2, Article);
            ResultSet resultSet = pst.executeQuery();

            while (resultSet.next()) {
                check = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            saveLogs.save("Ошибка в DbController - CheckGoods1: " + e);
            saveLogs.save("Ошибка с аргументами: Shopid: " + ShopId + " Article: " + Article);
            e.printStackTrace();
        }
//        finally {
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException ex) {
//                    saveLogs.save("Ошибка в DbController -CheckGoods2: " + ex);
//                    // Logger.getLogger(JDBCtest.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }
        return check;
    }


//    static void addUser(int id, String what) {
//        try {
//            pst = connection.prepareStatement("INSERT INTO user VALUES (?, ?)");
//            pst.setInt(1, id);
//            pst.setString(2, what);
//            pst.execute();
//        } catch (SQLException e) {
//            System.out.println("Пользователь с id(" + id + ") уже есть в базе");
//            e.printStackTrace();
//        }
//
//    }
//
//    public void removeUser(int id) {
//        String deleteTableSQL = "DELETE FROM user WHERE id=?";
//        try {
//            pst = connection.prepareStatement(deleteTableSQL);
//            pst.setInt(1, id);
//            pst.execute();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public void getUser(int id) {
//        String selectTableSQL = "CALL shopdb.getPrice(?, ?)";
//        try {
//            pst = connection.prepareStatement(selectTableSQL);
//            pst.setInt(1, 1);
//            pst.setString(2, "001");
//            ResultSet resultSet = pst.executeQuery();
//            while (resultSet.next()) {
//                String userid = resultSet.getString("Article");
//                //String what = resultSet.getString("what");
//                String row = "";
//                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
//                    row += resultSet.getString(i) + ", ";
//                }
//                System.out.println(row);
//                //System.out.println(resultSet.getMetaData().getColumnCount());
//            }
//
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

//        DbConnector dbConnector = new DbConnector();
//        dbConnector.DBWorker();
//        String query = "SELECT * FROM user";
//        try {
//            Statement statement = dbConnector.getConnection().createStatement();
//            ResultSet resultSet = statement.executeQuery(query);
//            System.out.println(resultSet);
//            while (resultSet.next())
//            {
//                System.out.println();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }


}
