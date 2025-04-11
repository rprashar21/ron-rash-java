package com.corejava.corejava.staticsample;

public class StaticUsefulAreas {

    // Used as `constants thruout ir application
    // Prevents hardcoding values across the codebase.
    public class AppConstants {
        public static final String DB_URL = "jdbc:mysql://localhost:3306/mydb";
        public static final int TIMEOUT = 5000;
    }

    //2. Static Utility Logger (Singleton-like)
    public class Logger {
        private Logger() {}  // private constructor

//        public static void log(String msg) {
//            System.out.println("[LOG] " + msg);
//        }
   //     Logger.log("Server started...");
    }


    // 3. Static Factory Method
//    public class ConnectionManager {
//        public static Connection getConnection() {
//            // create and return DB connection (mocked here)
//            return new Connection();
//        }
//    }

 // Connection conn = ConnectionManager.getConnection();

///  Another examplie is in caching
// suppose we create a caching class

//  public class AppCache {
//    private static final AppCache INSTANCE = new AppCache();
//
//    private AppCache() {}
//
//    public static AppCache getInstance() {
//        return INSTANCE;
//    }
//
//    public void put(String key, Object value) {
//        // caching logic
//    }
//}

// AppCache.getInstance().put("userId", 1234); -- Used of singleton class
// Ensures single instance — shared globally — great for caching, config, etc.
}
