package com.chursinov.beautysalon.listener;

import com.chursinov.beautysalon.constants.Constants;
import com.chursinov.beautysalon.controller.action.ActionFactory;
import com.chursinov.beautysalon.exception.StartupLoadException;
import com.chursinov.beautysalon.repository.impl.AppointmentRepositoryImpl;
import com.chursinov.beautysalon.repository.impl.ProductRepositoryImpl;
import com.chursinov.beautysalon.repository.impl.ReviewRepositoryImpl;
import com.chursinov.beautysalon.repository.impl.UserRepositoryImpl;
import com.chursinov.beautysalon.service.AppointmentService;
import com.chursinov.beautysalon.service.ProductService;
import com.chursinov.beautysalon.service.ReviewService;
import com.chursinov.beautysalon.service.UserService;
import com.chursinov.beautysalon.service.impl.AppointmentServiceImpl;
import com.chursinov.beautysalon.service.impl.ProductServiceImpl;
import com.chursinov.beautysalon.service.impl.ReviewServiceImpl;
import com.chursinov.beautysalon.service.impl.UserServiceImpl;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.util.Properties;

@WebListener
public class StartupContextListener implements ServletContextListener {
    private static final Logger logger = Logger.getLogger(StartupContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        BasicConfigurator.configure();
        ActionFactory.init();
        ServletContext servletContext = servletContextEvent.getServletContext();
        try {
            Properties log4jProps = new Properties();
            log4jProps.load(getClass().getClassLoader()
                    .getResourceAsStream("log4j.properties"));
            PropertyConfigurator.configure(log4jProps);

            Properties properties = new Properties();
            properties.load(getClass().getClassLoader()
                    .getResourceAsStream(Constants.Settings.APPLICATION_PROPERTIES));

            servletContext.setAttribute("appSettings", properties);

            AppointmentService appointmentService = new AppointmentServiceImpl(new AppointmentRepositoryImpl());
            servletContext.setAttribute("AppointmentService",  appointmentService);

            ReviewService reviewService = new ReviewServiceImpl(new ReviewRepositoryImpl());
            servletContext.setAttribute("ReviewService", reviewService);


            ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());
            servletContext.setAttribute("ProductService", productService);

            UserService userService = new UserServiceImpl(new UserRepositoryImpl());
            servletContext.setAttribute("UserService", userService);

        } catch (IOException e) {
            logger.error("Load application is interrupted: " + e.getMessage());
            throw new StartupLoadException(e.getMessage(), e);
        }
        logger.info("All beans was initialized successfully");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
