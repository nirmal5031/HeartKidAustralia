package com.heartkid.oauth;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

@Configuration
public class OAuth2ServerConfiguration {

    private static final String RESOURCE_ID = "restservice";

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource());
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore());
        return tokenServices;
    }

    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends
            AuthorizationServerConfigurerAdapter {

        @Autowired
        @Qualifier("tokenStore")
        public TokenStore tokenStore;

        @Autowired
        @Qualifier("dataSource")
        public DataSource dataSource;

        @Autowired
        @Qualifier("authenticationManagerBean")
        private AuthenticationManager authenticationManager;

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints)
                throws Exception {
            // @formatter:off
            endpoints.tokenStore(tokenStore).authenticationManager(
                    this.authenticationManager);
            // @formatter:on
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients)
                throws Exception {
            // @formatter:off
            /*
             * clients.jdbc(oauthDataSource()).withClient("clientapp")
             * .authorizedGrantTypes("password", "refresh_token")
             * .authorities("USER").scopes("read", "write")
             * .resourceIds(RESOURCE_ID).secret("123456");
             */
            clients.jdbc(dataSource);
            // @formatter:on
        }

    }

    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends
            ResourceServerConfigurerAdapter {

        @Autowired
        private CustomAuthenticator customFilter = new CustomAuthenticator();

        @Autowired
        @Qualifier("tokenStore")
        public TokenStore tokenStore;

        @Autowired
        @Qualifier("dataSource")
        public DataSource dataSource;

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {

            // @formatter:off
            resources.resourceId(RESOURCE_ID)
                    .authenticationManager(customFilter).tokenStore(tokenStore);
            // @formatter:on
        }
        
        public void configure(WebSecurity web) throws Exception {
            web
                .ignoring()
                    .antMatchers("/heartkid/register/**");
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            // @formatter:off
            http.authorizeRequests()
                    .antMatchers("/hearkid")
                    .authenticated()
                    .antMatchers("/token/revoke")
                    .permitAll()
                    .and()
                    .addFilterAfter(customFilter,
                            AbstractPreAuthenticatedProcessingFilter.class);
            // .anyRequest().authenticated()
            // .antMatchers("/**/**").authenticated();

            // .antMatchers("/login").permitAll().antMatchers("/menu/**")
            // .authenticated();
            // @formatter:on
        }

    }

    @Component
    protected static class CustomAuthenticator extends
            OAuth2AuthenticationManager implements Filter {

        @Autowired
        @Qualifier("tokenStore")
        private TokenStore tokenStore;

        @Autowired
        @Qualifier("dataSource")
        private DataSource dataSource;

        // @Autowired FilterChainProxy filterChainProxy;

        // @Override
        public void doFilter(ServletRequest request, ServletResponse response,
                FilterChain filterChain) throws ServletException, IOException {

            Authentication authentication = SecurityContextHolder.getContext()
                    .getAuthentication();

            System.out.println("CustomAuthenticator.........................."
                    + authentication);

            /*
             * System.out.println(
             * "          filterChainProxy.getFilterChainMap().........................."
             * + filterChainProxy.getFilterChainMap().values());
             */

            System.out
                    .println("CustomAuthenticator-------tokenStore--------------------->"
                            + tokenStore);
            System.out
                    .println("CustomAuthenticator- dataSource--------------------------->"
                            + dataSource);

            /*
             * if(null == authentication){ throw new
             * InvalidTokenException("Invalid Session or Session Expired! "); }
             */

            if (authentication instanceof OAuth2Authentication) {

                System.out
                        .println("authentication instanceof OAuth2Authentication..........................");
                System.out.println("tokenStore.........................."
                        + tokenStore);
                /*
                 * System.out.println("tokenServices.........................."
                 * + tokenServices);
                 */

                OAuth2Authentication oAuth = (OAuth2Authentication) authentication;

                // tokenStore = resources.getTokenStore();

                if (tokenStore != null) {

                    OAuth2AccessToken accessToken = tokenStore
                            .getAccessToken(oAuth);

                    System.out.println("isExpired.........................."
                            + accessToken.isExpired());
                    System.out.println("isExpired.........................."
                            + accessToken.getValue());
                    System.out.println("isExpired.........................."
                            + accessToken.getExpiresIn());

                    DefaultOAuth2AccessToken df = new DefaultOAuth2AccessToken(
                            accessToken);

                    df.setExpiration(new Date(System.currentTimeMillis()
                            + (60 * 1000)));

                    tokenStore.storeAccessToken(df, oAuth);
                }

            }

            filterChain.doFilter(request, response);
        }

        @Override
        public Authentication authenticate(Authentication authentication)
                throws AuthenticationException {
            try {
                return super.authenticate(authentication);
            } catch (Exception e) {
                return new CustomAuthentication(authentication.getPrincipal(),
                        authentication.getCredentials());
            }
        }

        // @Override
        public void destroy() {
        }

        // @Override
        public void init(FilterConfig arg0) throws ServletException {

        }

        @Override
        public void afterPropertiesSet() {
        }

        @SuppressWarnings("serial")
        protected static class CustomAuthentication extends
                PreAuthenticatedAuthenticationToken {

            public CustomAuthentication(Object principal, Object credentials) {
                super(principal, credentials);
            }

        }

    }

}
