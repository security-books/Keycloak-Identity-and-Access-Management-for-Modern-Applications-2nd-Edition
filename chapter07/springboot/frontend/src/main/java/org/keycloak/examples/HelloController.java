package org.keycloak.examples;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {
    @RequestMapping("/")
    public String index(@AuthenticationPrincipal OAuth2User principal) {
        return "Greetings from Spring Boot!; "+principal.getAttribute("name");
    }

}