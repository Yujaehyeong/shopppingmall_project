package com.cafe24.config.app;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private DataSource dataSource;
    
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		
		// password or authorization code
//		clients.inMemory() 
//			.withClient("pjmall2")
//			.authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
//			.authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
//			.scopes("read", "write", "trust")
//			.resourceIds("sparklr")
//			.accessTokenValiditySeconds(60);
		
		// client credentials
		clients.inMemory() 
			.withClient("shoppingmall")
			.authorizedGrantTypes("password", "client_credentials")
			.authorities("ROLE_CLIENT")
			.scopes("read", "write", "trust")
			.resourceIds("shoppingmall_api")
			.secret("1234");
		//.accessTokenValiditySeconds(60);
		
//		clients
//			.jdbc(dataSource());
	}
	
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    	// OAuth2 서버가 작동하기 위한 Endpoint에 대한 정보를 설정
        endpoints
        	.tokenStore( new JdbcTokenStore(dataSource) )
        		.authenticationManager(authenticationManager);
    }
    
}
