package backendrestapi.timer.schedule.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;



import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * Created by rajeevkumarsingh on 02/08/17.
 */
@Component
public class ScheduledTasks {

	private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);


    
    
	/**
	 * Scheduling a Task with Fixed Rate
You can schedule a method to be executed at a fixed interval by using fixedRate parameter in the @Scheduled annotation. In the following example, The annotated method will be executed every 2 seconds.

The fixedRate task is invoked at the specified interval even if the previous invocation of the task is not finished.
	 */
//	@Scheduled(fixedRate = 2000)//runs every 2 second, //@Scheduled(fixedRateString = "1000")
//	public void scheduleTaskWithFixedRate() {
//		logger.info("Fixed Rate Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
//	}

	/**
	 * Scheduling a Task with Fixed Delay
You can execute a task with a fixed delay between the completion of the last invocation and the start of the next, using fixedDelay parameter.

The fixedDelay parameter counts the delay after the completion of the last invocation.
	 */
//	@Scheduled(fixedDelay = 2000)//Runs 2 second after the previous invocation has finished
//	public void scheduleTaskWithFixedDelay() {
//		logger.info("Fixed Delay Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
//		try {
//			TimeUnit.SECONDS.sleep(5);
//		} catch (InterruptedException ex) {
//			logger.error("Ran into an error {}", ex);
//			throw new IllegalStateException(ex);
//		}
//	}

	/**
	 * Scheduling a Task With Fixed Rate and Initial Delay
You can use initialDelay parameter with fixedRate and fixedDelay to delay the first execution of the task with the specified number of milliseconds.

In the following example, the first execution of the task will be delayed by 5 seconds and then it will be executed normally at a fixed interval of 2 seconds -

	 */
//	@Scheduled(fixedRate = 2000, initialDelay = 5000)//Runs every 2 seconds but waits 5 seconds before it executes for the first time
//	public void scheduleTaskWithInitialDelay() {
//		logger.info("Fixed Rate Task with Initial Delay :: Execution Time - {}",
//				dateTimeFormatter.format(LocalDateTime.now()));
//	}

	/**
	 * scheduled the task to be executed every minute -
	 * 
	 * Below is a breakdown of the components that build a cron expression.

Seconds can have values 0-59 or the special characters , - * / .
Minutes can have values 0-59 or the special characters , - * / .
Hours can have values 0-59 or the special characters , - * / .
Day of month can have values 1-31 or the special characters , - * ? / L W C .
Month can have values 1-12, JAN-DEC or the special characters , - * / .
Day of week can have values 1-7, SUN-SAT or the special characters , - * ? / L C # .
Year can be empty, have values 1970-2099 or the special characters , - * / .

* represents all values. So, if it is used in the second field, it means every second. If it is used in the day field, it means run every day.
? represents no specific value and can be used in either the day of month or day of week field — where using one invalidates the other. If we specify it to trigger on the 15th day of a month, then a ? will be used in the Day of week field.
- represents an inclusive range of values. For example, 1-3 in the hours field means the hours 1, 2, and 3.
, represents additional values. For example, putting MON,WED,SUN in the day of week field means on Monday, Wednesday, and Sunday.
/ represents increments. For example 0/15 in the seconds field triggers every 15 seconds starting from 0 (0, 15, 3,0 and 45).
L represents the last day of the week or month. Remember that Saturday is the end of the week in this context, so using L in the day of week field will trigger on a Saturday. This can be used in conjunction with a number in the day of month field, such as 6L to represent the last Friday of the month or an expression like L-3 denoting the third from the last day of the month. If we specify a value in the day of week field, we must use ? in the day of month field, and vice versa.
W represents the nearest weekday of the month. For example, 15W will trigger on the 15th day of the month if it is a weekday. Otherwise, it will run on the closest weekday. This value cannot be used in a list of day values.
# specifies both the day of the week and the week that the task should trigger. For example, 5#2 means the second Thursday of the month. If the day and week you specified overflows into the next month, then it will not trigger.

Cron Expression Examples
Let us see some examples of cron expression by using the fields and specials characters combinations:

At 12:00 pm (noon) every day during the year 2017:
0 0 12 * * ? 2017

Every 5 minutes starting at 1 pm and ending on 1:55 pm and then starting at 6 pm and ending at 6:55 pm, every day:
0 0/5 13,18 * * ?

Every minute starting at 1 pm and ending on 1:05 pm, every day:
0 0-5 13 * * ?

At 1:15 pm and 1:45 pm every Tuesday in the month of June:
0 15,45 13 ? 6 Tue

At 9:30 am every Monday, Tuesday, Wednesday, Thursday, and Friday:
0 30 9 ? * MON-FRI

At 9:30 am on 15th day of every month:
0 30 9 15 * ?

At 6 pm on the last day of every month:
0 0 18 L * ?

At 6 pm on the 3rd to last day of every month:
0 0 18 L-3 * ?

At 10:30 am on the last Thursday of every month:
0 30 10 ? * 5L

At 6 pm on the last Friday of every month during the years 2015, 2016 and 2017:
0 0 18 ? * 6L 2015-2017

At 10 am on the third Monday of every month:
0 0 10 ? * 2#3

At 12 am midnight on every day for five days starting on the 10th day of the month:
0 0 0 10/5 * ?


	 * @Scheduled(cron = "[Seconds] [Minutes] [Hours] [Day of month] [Month] [Day of week] [Year]")
	 * Fires at 12 PM every day:
@Scheduled(cron = "0 0 12 * * ?")

Fires at 10:15 AM every day in the year 2005:
@Scheduled(cron = "0 15 10 * * ? 2005")

Fires every 20 seconds:
@Scheduled(cron = "0/20 * * * * ?")
	 * 
	 */
//	@Scheduled(cron = "* * * * * ?")//Run every second
//	@Scheduled(cron = "0 * * * * ?")//Run every 0th second === Every Minute
//	@Scheduled(cron = "0 0/5 * * * ?")//Run every 5 minutes
//	@Scheduled(cron = "0/5 * * * * ?")//Run every 5 sec
    @Scheduled(cron = "0 0/10 * * * ?")//Run every 10 min
	public void scheduleTaskWithCronExpression() {
//		logger.info("cardRequestProcessor.pingSwitch()...Cron Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
        //cardRequestProcessor.pingSwitch();

	}

    
    /*
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test0() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test1() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test2() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test3() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test4() {        cardRequestProcessor.pingSwitch();	}
	
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test5() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test6() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test7() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test8() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test9() {        cardRequestProcessor.pingSwitch();	}
	//10
	
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test10() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test11() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test12() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test13() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test14() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test15() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test16() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test17() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test18() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test19() {        cardRequestProcessor.pingSwitch();	}
	// 20
	
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test20() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test21() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test22() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test23() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test24() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test25() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test26() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test27() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test28() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test29() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test30() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test31() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test32() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test33() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test34() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test35() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test36() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test37() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test38() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test39() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test40() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test41() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test42() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test43() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test44() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test45() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test46() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test47() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test48() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test49() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test50() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test51() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test52() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test53() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test54() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test55() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test56() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test57() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test58() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test59() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test60() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test61() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test62() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test63() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test64() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test65() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test66() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test67() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test68() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test69() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test70() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test71() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test72() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test73() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test74() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test75() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test76() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test77() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test78() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test79() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test80() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test81() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test82() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test83() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test84() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test85() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test86() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test87() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test88() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test89() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test90() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test91() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test92() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test93() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test94() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test95() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test96() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test97() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test98() {        cardRequestProcessor.pingSwitch();	}
	@Scheduled(cron = "* * * * * ?")	public void scheduleTaskWithCronExpression_Test99() {        cardRequestProcessor.pingSwitch();	}
*/


	
	/*
	 * Cron Special Strings
In addition to the fields specified in the cron expression, there’s also support for some special, pre-defined values – which can be used instead of the fields:

@reboot – run once at the start-up
@yearly or @annualy – run once a year
@monthly – run once a month
@weekly – run once a week
@daily or @midnight – run once a day
@hourly – run hourly
*/
    
    @Scheduled(cron = "0 0/1 * * * ?")//Run every 2 min
	public void scheduleTaskWithCronExpressionTestSwitchTitleFetch() {
//		logger.info("cardRequestProcessor.pingSwitch()...Cron Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));
        //cardRequestProcessor.testSwitchTitleFetch();

	}

    
    
}
