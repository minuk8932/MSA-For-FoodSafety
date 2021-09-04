package com.example.configuration.jobs;

import com.example.service.OpenApiLinkageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SchedulerConfig {

    public static final String JOB_NAME = "Open-API_Batch";

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    private final OpenApiLinkageServiceImpl recipeServiceImpl;

    @Bean(name = JOB_NAME)
    public Job openApiJob() {
        return jobBuilderFactory.get(JOB_NAME)
                .start(step1())
                .preventRestart()
                .build();
    }

    @Bean(name = JOB_NAME + "_Recipe Open-API")
    public Step step1() {
        return stepBuilderFactory.get(JOB_NAME + "_Recipe Open-API")
                .tasklet((contribution, chunkContext) -> {
                    recipeServiceImpl.updateByOpenApiData();

                    return RepeatStatus.FINISHED;
                })
                .build();
    }

}
