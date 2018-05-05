package com.hack.TransmissionLineService.web;


import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConnectorConfig {
	final int port = 8443;
	final String keystoreFile = "classpath:keystore.p12";
	final String keystorePass = "12345678";
	final String keystoreType = "pkcs12";
	final String keystoreAlias = "tomcat";
	@Bean
    public ServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };
        tomcat.addConnectorCustomizers( new TomcatConnectorCustomizer() {
            public void  customize(Connector con) {
                Http11NioProtocol proto = (Http11NioProtocol) con.getProtocolHandler();
                    proto.setSSLEnabled(true);
                con.setScheme("https");
                con.setPort(port);
                con.setSecure(true);
                proto.setKeystoreFile(keystoreFile);
                proto.setKeystorePass(keystorePass);
                proto.setKeystoreType(keystoreType);
                proto.setKeyAlias(keystoreAlias);
            }
        });
        
        tomcat.addAdditionalTomcatConnectors(redirectConnector());
        return tomcat;
    }

    private Connector redirectConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(8080);
        connector.setSecure(false);
        connector.setRedirectPort(8443);
        return connector;
    }
}