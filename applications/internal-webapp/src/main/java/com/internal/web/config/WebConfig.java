/**
 * 
 */
package com.internal.web.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.directwebremoting.annotations.DataTransferObject;
import org.directwebremoting.annotations.GlobalFilter;
import org.directwebremoting.annotations.RemoteProxy;
import org.directwebremoting.extend.Configurator;
import org.directwebremoting.spring.DwrClassPathBeanDefinitionScanner;
import org.directwebremoting.spring.DwrController;
import org.directwebremoting.spring.DwrHandlerMapping;
import org.directwebremoting.spring.SpringConfigurator;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.jasperreports.JasperReportsMultiFormatView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

/**
 * @author EFRAIN
 * @dateCreated 5 mar. 2017 14:19:08
 */
@EnableWebMvc
@Configuration
@ComponentScan({"com.internal"})
@Import({SpringSecurityConfig.class})
@ImportResource({"classpath:/com/internal/web/config/application-context-web.xml"
	,"classpath:/com/internal/web/config/dwr-config.xml"})
public class WebConfig extends WebMvcConfigurerAdapter {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry){
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	@Bean
	public JasperReportsViewResolver getJasperReportsViewResolver() {
	  JasperReportsViewResolver resolver = new JasperReportsViewResolver();
	  resolver.setPrefix("/report/");
	  resolver.setSuffix(".jasper");
	  resolver.setReportDataKey("datasource");
	  resolver.setViewNames("rpt_*");
	  resolver.setViewClass(JasperReportsMultiFormatView.class);
	  resolver.setOrder(0);
	  return resolver;
	} 
	
//	@Bean
//	public InternalResourceViewResolver viewResolver(){
//		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//		viewResolver.setViewClass(JstlView.class);
//		viewResolver.setPrefix("/WEB-INF/jsp/");
//		viewResolver.setSuffix(".jsp");
//		return viewResolver;
//	}
	
	/**
     * Configure TilesConfigurer.
     */
    @Bean
    public TilesConfigurer tilesConfigurer(){
        TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setDefinitions(new String[] {"/WEB-INF/jsp/**/tiles.xml"});
        tilesConfigurer.setCheckRefresh(true);
        return tilesConfigurer;
    }
 
    @Bean
    public ViewResolver getViewResolver(){
    	UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
    	viewResolver.setViewClass(TilesView.class);
    	return viewResolver;
    }
    
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    
//    /**
//     * Configure ViewResolvers to deliver preferred views.
//     */
//    @Override
//    public void configureViewResolvers(ViewResolverRegistry registry) {
//        TilesViewResolver viewResolver = new TilesViewResolver();
//        registry.viewResolver(viewResolver);
//    }
    
    
    
    
    
    
    
    // for DWR
    @Bean
    public DwrController dwrController(ApplicationContext applicationContext){
    
        BeanDefinitionRegistry beanDefinitionRegistry = (BeanDefinitionRegistry)applicationContext.getAutowireCapableBeanFactory();
        Map<String,String> configParam = new HashMap<String, String>();
        configParam.put("activeReverseAjaxEnabled","true");
        
        configParam.put("allowScriptTagRemoting", "true");
		configParam.put("crossDomainSessionSecurity", "false");
		configParam.put("accessLogLevel", "CALL");
		configParam.put("debug", "true");
    
        ClassPathBeanDefinitionScanner scanner = new DwrClassPathBeanDefinitionScanner(beanDefinitionRegistry);
        scanner.addIncludeFilter(new AnnotationTypeFilter(RemoteProxy.class));
        scanner.addIncludeFilter(new AnnotationTypeFilter(DataTransferObject.class));
        scanner.addIncludeFilter(new AnnotationTypeFilter(GlobalFilter.class));
        scanner.scan("com.internal");
    
        DwrController dwrController = new DwrController();
        dwrController.setDebug(true);
        dwrController.setConfigParams(configParam);
    
        SpringConfigurator springConfigurator = new SpringConfigurator();
        List<Configurator> configurators = new ArrayList<Configurator>();
        configurators.add(springConfigurator);
        dwrController.setConfigurators(configurators);
    
    
        return dwrController;
    }
    
   @Bean
    public BeanNameUrlHandlerMapping beanNameUrlHandlerMapping(){
        BeanNameUrlHandlerMapping beanNameUrlHandlerMapping = new BeanNameUrlHandlerMapping();
        return beanNameUrlHandlerMapping;
    }
    
    @Bean
    public DwrHandlerMapping dwrHandlerMapping(DwrController dwrController){
        Map<String,DwrController> urlMap = new HashMap<String, DwrController>();
        urlMap.put("/dwr/**/*",dwrController);
    
        DwrHandlerMapping dwrHandlerMapping = new DwrHandlerMapping();
        dwrHandlerMapping.setAlwaysUseFullPath(true);
        dwrHandlerMapping.setUrlMap(urlMap);
        return dwrHandlerMapping;
    }
}
