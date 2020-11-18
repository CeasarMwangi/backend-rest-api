package backendrestapi;

import java.util.Properties;

import javax.jms.DeliveryMode;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.QosSettings;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import backendrestapi.common.CommonFunctions;
import backendrestapi.request.filter.RequestAndResponseLoggingFilter;




/**
 * Created by CLLSDJACKT013 on 08/05/2018.
 */
@SpringBootApplication(scanBasePackages = "backendrestapi")
@EnableConfigurationProperties(ApplicationService.class)
/*@EnableAutoConfiguration*/
@EnableTransactionManagement
@EntityScan("backendrestapi.models")
@EnableJpaRepositories(basePackages = {"backendrestapi.repositories"})
@EnableJms
@EnableScheduling
public class Application  extends SpringBootServletInitializer{

	@Value("${ACTIVEMQ_MSG_BROKER_URL}")
	private String BROKER_URL;
	
    @Value("${ACTIVEMQ_USER}")
    private String BROKER_USERNAME;
    
    @Value("${ACTIVEMQ_PASS}")
    private String BROKER_PASSWORD;
	@Autowired
	private CommonFunctions commonFunctions;
    
//    @Autowired
//    private OTPRepository otpRepository;
    
    
    private static Logger logger = LoggerFactory.getLogger("timeBased");
    private static Logger logger2 = LoggerFactory.getLogger("fixWindowBased");
    private static Logger logger3 = LoggerFactory.getLogger("sizeAndTimeBased");



    public static void main(String [] args) throws Exception {
        logger.info("timeBased Starting");
        logger2.info("fixWindowBased Starting");
        logger3.info("sizeAndTimeBased Starting");
        Properties p = System.getProperties();
        //System.setProperty(ContextInitializer.CONFIG_FILE_PROPERTY, /path/to/config.xml); //TRY THIS PROP
/*        p.setProperty("logging.level.compas.transaction", "INFO");
        p.setProperty("logging.level.org.springframework.data", "ERROR");
        p.setProperty("logging.level.org.springframework.data", "INFO");
        p.setProperty("logging.file", "C:\\Program Files\\apache-tomcat-8.5.20\\conf\\testing.log");
        p.setProperty("logging.config", "${catalina.home}\\conf\\logback-test.xml");*/
        //String base64tring = DatatypeConverter.printBase64Binary();
//        Properties p = System.getProperties();
//        //System.setProperty(ContextInitializer.CONFIG_FILE_PROPERTY, /path/to/config.xml); //TRY THIS PROP
//        p.setProperty("logging.level.compas.transaction","INFO");
//
        SpringApplication.run(Application.class, args);


/*        Date localDate = new SimpleDateFormat("yyyy-MM-dd").parse("2018-06-12 16:15:32.000");
        //Date localDate = new SimpleDateFormat("yyyyMMdd").parse("2018-08-03 19:00:12".replace("\\","").replace("/","-"));
        logger.info(""+localDate.getDate());
        logger.info("month"+localDate.getMonth());
        logger.info("date"+localDate.toString());
        logger.info(""+localDate.toLocaleString());
        logger.info("Today::"+new Date());
        LocalDateTime localDateTime = LocalDateTime.now();
        logger.info("<<>>"+localDateTime);
        LocalDateTime fromDate = localDateTime.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime toDate = localDateTime.withDayOfMonth(7).withHour(23).withMinute(59).withSecond(59);
        logger.info("from:"+fromDate.toString().replace("T"," "));
        logger.info("to:"+toDate.toString().replace("T"," "));
        LocalDateTime localDateTime1 = LocalDateTime.now();
        logger.info("Day::"+localDateTime1.getDayOfMonth());
        LocalDateTime fastforward = localDateTime1.withMonth(4).withDayOfMonth(30);
        logger.info("Fast forward::"+fastforward.toString());
        LocalDateTime now  =  LocalDateTime.now();
        logger.info("MonthValue::"+now.getMonthValue());
        LocalDateTime lastMonth = now.minusMonths(8);
        logger.info(">>"+lastMonth);
        logger.info("BBB"+lastMonth.getMonthValue());
        logger.info("NN"+lastMonth.getMonth());*/
   }

    
    /*
    public void managePasswordPolicy(){
    	//ApiLogger apiLogger = new ApiLogger();
        long initialDelayOnAppStart = 10000;
        long intervalPeriod = 60000;//1*60*1000 = 60000ms = 1Min
        Timer timer = new Timer();
        Timer switchTimer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
            	//logger.info("managePasswordPolicy...!");
            	//apiLogger.doLog(AppUtilities.LOG_FILE_DEBUG_FILE, "managePasswordPolicy....running!");

                Date currentDate = new Date();
                //1Min = 60000Ms
                //3Min = 60000Ms * 3 = 180000ms
                //get time in milliseconds
                Long currentTimeLapse = currentDate.getTime();
                //fetch times from oTP collection where with active status
                List<OTP> activeOTPS = otpRepository.fetchActiveOTPs();
                activeOTPS.forEach((activeOTP)->{
                    Long timelapsed = activeOTP.getRequest_time().getTime();
                    //@TODO
                    //if((currentTimeLapse-timelapsed)>300000){
//                    if((currentTimeLapse-timelapsed)>300000000){
                    if((currentTimeLapse-timelapsed)>180000){
                        //otpRepository.updateOTPStatus(activeOTP.getPassword());
                        otpRepository.updateUnusedOTPs(activeOTP.getReceipt_number());
                    }
                });
            }
        },initialDelayOnAppStart, intervalPeriod);
    }
    */
    /*
    public void manageDuplicateTransactions(){
    	ApiLogger apiLogger = new ApiLogger();
    	CommonFunctions commonFunctions = new CommonFunctions();
        long initialDelayOnAppStart = 10000;
        long intervalPeriod = 300000;//5*60*1000
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
            	apiLogger.doLog(AppUtilities.LOG_FILE_DEBUG_FILE, "manageDuplicateTransactions....running!");
            	commonFunctions.manageDuplicateTransactions();
            }
        },initialDelayOnAppStart, intervalPeriod);
    }
    
    */
    @Bean
    CommandLineRunner run(){
        return (String... args) ->{
            Application app = new Application();
            //app.managePasswordPolicy();
            //app.manageDuplicateTransactions();
        };
    }
    
	/**
	 * Here we are creating an instance of BCryptPasswordEncoder by implementing
	 * a method that generates an instance of BCryptPasswordEncoder
	 *
	 * Thus spring boot DI/IoC will be able to auto-wire this bean whenever its
	 * required
	 *
	 * @return
	 */
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(BROKER_URL);
        connectionFactory.setPassword(BROKER_USERNAME);
        connectionFactory.setUserName(BROKER_PASSWORD);
        return connectionFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setMessageConverter(jacksonJmsMessageConverter());
        jmsTemplate.setConnectionFactory(connectionFactory());
        // jmsTemplate.setDefaultDestinationName(orderDestination);
        jmsTemplate.setReceiveTimeout(80000);//45000 milliseconds(10000+60000+10000)
        // template.setPubSubDomain(true);//Enable Spring boot JMS Topic
        // messages sent by this producer will be retained for 5s (5000ms)
        // before expiration
        // producer.setTimeToLive(5000);
        // jmsTemplate.setTimeToLive(10000);
        // jmsTemplate.setReceiveTimeout(10000);
        // jmsTemplate.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        QosSettings qosSettings = new QosSettings();
        qosSettings.setTimeToLive(10000);//35000 milliseconds
        qosSettings.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        jmsTemplate.setQosSettings(qosSettings);
		/*
		 * public void sendMessage(String message){ QosSettings qosSettings =
		 * new QosSettings(); Random random = new Random();
		 * qosSettings.setTimeToLive(random.nextInt((60000 - 10000) + 1) +
		 * 10000); jmsTemplate.setQosSettings(qosSettings);
		 * jmsTemplate.convertAndSend(destinationQueue, message); }
		 */

        return jmsTemplate;
    }

    @Bean
    public JmsTemplate jmsLoggingPersistentTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setMessageConverter(jacksonJmsMessageConverter());
        jmsTemplate.setConnectionFactory(connectionFactory());
        QosSettings qosSettings = new QosSettings();
        qosSettings.setTimeToLive(30 * 24 * 60 * 60 * 1000);// TTL= 30 days =
        // 30*24*60*60*1000
        // ms
        qosSettings.setDeliveryMode(DeliveryMode.PERSISTENT);
        jmsTemplate.setQosSettings(qosSettings);
        return jmsTemplate;
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setConcurrency("1-1");
        // factory.setPubSubDomain(true);//Enable Spring boot JMS Topic
        return factory;
    }

    // Serialize message content to json using TextMessage
    @Bean
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }
    
    
    @Bean
    public FilterRegistrationBean dawsonApiFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(new AppGenericFilterBean());
//        registration.setFilter(new LogRequestFilter());
        registration.setFilter(new RequestAndResponseLoggingFilter(commonFunctions));
        
        
// In case you want the filter to apply to specific URL patterns only
//        registration.addUrlPatterns("/dawson/*");
        return registration;
    }


}
