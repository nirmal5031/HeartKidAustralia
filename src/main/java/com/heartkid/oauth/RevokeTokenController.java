package com.heartkid.oauth;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RevokeTokenController {

    public static final String ENDPOINT_ID = "token";

    @Autowired
    @Qualifier("tokenStore")
    private TokenStore tokenStore;

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    private DefaultTokenServices tokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore);
        return tokenServices;
    }

    @RequestMapping(value = "token/revoke", method = RequestMethod.DELETE)
    public void revoke(@RequestHeader(value = "Authorization") String authToken) {
        String token = authToken.substring(authToken.indexOf(" ")).trim();
       /* System.out.println("Revoking token..." + tokenServices());
        System.out.println("Revoking token..." + tokenStore);
        System.out.println("Revoking token..." + dataSource);

        System.out.println("Revoking token...token=" + token);
*/
        boolean success = tokenServices().revokeToken(token);
        if (!success) {
            throw new InvalidTokenException("Invalid access token: " + token);
        }

        System.out.println("Successfully revoked token");
    }

}
