package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.webflow.config.AbstractFlowConfiguration;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.engine.builder.support.FlowBuilderServices;
import org.springframework.webflow.executor.FlowExecutor;
import org.springframework.webflow.mvc.servlet.FlowHandlerAdapter;
import org.springframework.webflow.mvc.servlet.FlowHandlerMapping;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.webflow.mvc.builder.MvcViewFactoryCreator;
import java.util.List;



@Configuration
public class WebFlowConfig extends AbstractFlowConfiguration {

    @Bean
    public MvcViewFactoryCreator mvcViewFactoryCreator(ThymeleafViewResolver thymeleafViewResolver) {
        MvcViewFactoryCreator creator = new MvcViewFactoryCreator();
        creator.setViewResolvers(List.of(thymeleafViewResolver));
        creator.setUseSpringBeanBinding(true);
        return creator;
    }

    @Bean
    public FlowBuilderServices flowBuilderServices(MvcViewFactoryCreator creator) {
        return getFlowBuilderServicesBuilder()
                .setViewFactoryCreator(creator)
                .build();
    }

    @Bean
    public FlowDefinitionRegistry flowRegistry(FlowBuilderServices builder) {
        return getFlowDefinitionRegistryBuilder(builder)
                .setBasePath("classpath:/flows")
                .addFlowLocation("/purchase/purchase.xml", "purchase")
                .build();
    }

    @Bean
    public FlowExecutor flowExecutor(FlowDefinitionRegistry registry) {
        return getFlowExecutorBuilder(registry).build();
    }

    @Bean
    public FlowHandlerMapping flowHandlerMapping(FlowDefinitionRegistry registry) {
        FlowHandlerMapping mapping = new FlowHandlerMapping();
        mapping.setOrder(-1);
        mapping.setFlowRegistry(registry);
        return mapping;
    }

    @Bean
    public FlowHandlerAdapter flowHandlerAdapter(FlowExecutor executor) {
        FlowHandlerAdapter adapter = new FlowHandlerAdapter();
        adapter.setFlowExecutor(executor);
        return adapter;
    }
}
