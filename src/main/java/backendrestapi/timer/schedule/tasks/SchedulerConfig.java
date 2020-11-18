package backendrestapi.timer.schedule.tasks;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 * Created by rajeevkumarsingh on 02/08/17.
 * 
 * Running @Scheduled Tasks in a Custom Thread Pool
By default, all the @Scheduled tasks are executed in a default thread pool of size one created by Spring.

You can verify that by logging the name of the current thread in all the methods -

logger.info("Current Thread : {}", Thread.currentThread().getName());
All the methods will print the following -

Current Thread : pool-1-thread-1
But hey, You can create your own thread pool and configure Spring to use that thread pool for executing all the scheduled tasks.

Create a new package config inside com.example.schedulerdemo, and then create a new class called SchedulerConfig inside config package with the following contents -

package com.example.schedulerdemo.config;

That’s all you need to do for configuring Spring to use your own thread pool instead of the default one.
 */
@Configuration
public class SchedulerConfig implements SchedulingConfigurer {
    private final int POOL_SIZE = 10;

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(POOL_SIZE);
        threadPoolTaskScheduler.setThreadNamePrefix("my-scheduled-task-pool-");
        threadPoolTaskScheduler.initialize();

        scheduledTaskRegistrar.setTaskScheduler(threadPoolTaskScheduler);
    }
}
