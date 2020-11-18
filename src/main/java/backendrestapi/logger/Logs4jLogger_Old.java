package backendrestapi.logger;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Logs4jLogger_Old {

    protected static final Logger logger = Logger.getLogger("COMPASAPILOGGER");

    /**
     *
     * @param messsage
     * @param directory
     * @param fileName
     * @param level
     */
    public void LogEngine(String messsage, String directory, String fileName, String level) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String daylog = format.format(new Date());
        messsage = daylog + "::::" + messsage;
        String path = createDailyDirectory(directory);
        String file = path + "/" + fileName + ".xml";

        FileHandler fh = null;
        try {
            fh = new FileHandler(file, true);
            logger.addHandler(fh);
            switch (level) {
                case "severe":
                    logger.log(Level.SEVERE, messsage);
                    break;
                case "warning":
                    logger.log(Level.WARNING, messsage);
                    break;
                case "info":
                    logger.log(Level.INFO, messsage);
                    break;
                case "config":
                    logger.log(Level.CONFIG, messsage);
                    break;
                case "fine":
                    logger.log(Level.FINE, messsage);
                    break;
                case "finer":
                    logger.log(Level.FINER, messsage);
                    break;
                case "finest":
                    logger.log(Level.FINEST, messsage);
                    break;
                default:
                    logger.log(Level.FINE, messsage);
                    break;
            }
        } catch (IOException | SecurityException ex1) {
            logger.log(Level.SEVERE, null, ex1);
        } finally {
            if (fh != null)
                fh.close();
        }
    }


    public String createDailyDirectory(String appdirectory) {
        String dailyDirecory = "";
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String daylog = format.format(new Date());
            dailyDirecory = appdirectory + "/" + daylog;
            new File(dailyDirecory).mkdirs();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dailyDirecory + "/";
    }
}
