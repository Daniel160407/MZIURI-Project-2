package com.mziuri;

import com.mziuri.JDBC.JDBCController;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;
import java.sql.SQLException;

public class Application {

    public static void main(String[] args) throws LifecycleException, SQLException {

        Tomcat tomcat = new Tomcat();

        tomcat.enableNaming();
        tomcat.setPort(8989);
        tomcat.getConnector();

        String ctxPath = "/messenger";
        String webappDir = new File("src/main/webapp").getAbsolutePath();

        StandardContext ctx = (StandardContext) tomcat.addWebapp(ctxPath, webappDir);

        //declare an alternate location for your "WEB-INF/classes" dir:
        File additionWebInfClasses = new File("build/classes");
        WebResourceRoot resources = new StandardRoot(ctx);
        resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes", additionWebInfClasses.getAbsolutePath(), "/"));
        ctx.setResources(resources);

        JDBCController jdbcController = new JDBCController();
        jdbcController.createSchema();

        tomcat.start();
        tomcat.getServer().await();

    }

}
