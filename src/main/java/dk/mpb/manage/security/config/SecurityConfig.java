package dk.mpb.manage.security.config;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.proc.SecurityContext;
import dk.mpb.manage.security.error.CustomOAuth2AuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)

@Configuration
public class SecurityConfig {

  @Value("${app.secret-key}")
  private String tokenSecret;

  @Autowired
  CorsConfigurationSource corsConfigurationSource;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
    MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
    http
            .cors(Customizer.withDefaults()) //Will use the CorsConfigurationSource bean declared in CorsConfig.java
            .csrf(csrf -> csrf.disable())  //We can disable csrf, since we are using token based authentication, not cookie based
            .httpBasic(Customizer.withDefaults())
            .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .exceptionHandling((exceptions) -> exceptions
                            .authenticationEntryPoint(new CustomOAuth2AuthenticationEntryPoint())
            )
            .oauth2ResourceServer((oauth2ResourceServer) ->
                    oauth2ResourceServer
                            .jwt((jwt) -> jwt.decoder(jwtDecoder())
                                    .jwtAuthenticationConverter(authenticationConverter())
                            )
                            .authenticationEntryPoint(new CustomOAuth2AuthenticationEntryPoint()));

    http
            .authorizeHttpRequests((authorize) -> authorize
            .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.POST, "/api/auth/login")).permitAll()

            //Allow index.html and everything else on root level. So make sure to put ALL your endpoints under /api
            .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.GET,"/*")).permitAll()

            .requestMatchers(mvcMatcherBuilder.pattern("/error")).permitAll()


            // USER ENDPOINTS
            .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.GET, "/api/users/user-as-authenticated")).hasAuthority("USER")
            .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.PUT, "/api/users/user-as-authenticated")).hasAuthority("USER")
            .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.DELETE, "/api/users/user-as-authenticated")).hasAuthority("USER")
            .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.GET, "/api/users")).hasAuthority("ADMIN")
            .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.DELETE, "/api/users/{username}")).hasAuthority("ADMIN")

            .anyRequest().authenticated());

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public JwtAuthenticationConverter authenticationConverter() {
    JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
    jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("roles");
    jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");

    JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
    jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
    return jwtAuthenticationConverter;
  }

  @Bean
  public SecretKey secretKey() {
    return new SecretKeySpec(tokenSecret.getBytes(), "HmacSHA256");
  }

  @Bean
  public JwtDecoder jwtDecoder() {
    return NimbusJwtDecoder.withSecretKey(secretKey()).build();
  }

  @Bean
  public JwtEncoder jwtEncoder() {
    return new NimbusJwtEncoder(
            new ImmutableSecret<SecurityContext>(secretKey())
    );
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
          throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

}
