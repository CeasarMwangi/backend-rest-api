package backendrestapi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * Created by CLLSDJACKT013 on 25/05/2018.
 */

@Service
@PropertySource({"file:C:\\srv\\applications\\externalConfigs\\PBU_Agency_API\\application.properties"})
@ConfigurationProperties
    public class ApplicationService
    {

        @Value("${api.username}")
        private String api_username;
        @Value("${api.password}")
        private String api_password;
        @Autowired
        private Environment environment;
        
        @Value("${server.port}")
        private String PORT;
        private static Logger logger = LoggerFactory.getLogger(ApplicationService.class);
/*        private static String FILES_PATH = System.getProperty("catalina.base")+"/conf/";
        File configDir = new File(System.getProperty("catalina.base"), "conf");
        File configFile = new File(configDir, "application.properties");
        InputStream stream = new FileInputStream(configFile);*/

    }

