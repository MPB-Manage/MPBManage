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

/**
 *  Security configuration
 * */
@Configuration
public class SecurityConfig {

  /**
   *  Token secret
   * */
  @Value("${app.secret-key}")
  private String tokenSecret;

  @Autowired
  CorsConfigurationSource corsConfigurationSource;

  /**
   *  Security filter chain
   * */
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
    MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
    http
            .cors(Customizer.withDefaults())
            .csrf(csrf -> csrf.disable())
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
            .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.GET,"/*")).permitAll()
            .requestMatchers(mvcMatcherBuilder.pattern("/error")).permitAll()

            // USER ENDPOINTS
            .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.GET, "/api/users/user-as-authenticated")).hasAuthority("USER")
            .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.PUT, "/api/users/user-as-authenticated")).hasAuthority("USER")
            .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.DELETE, "/api/users/user-as-authenticated")).hasAuthority("USER")
            .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.GET, "/api/users")).hasAuthority("ADMIN")
            .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.DELETE, "/api/users/{username}")).hasAuthority("ADMIN")

             // PROPERTY ENDPOINTS
            .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.GET, "/properties")).hasAuthority("ADMIN")
            .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.POST, "/properties")).hasAuthority("ADMIN")

             // RESERVATION ENDPOINTS
             .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.GET, "/reservations")).hasAuthority("USER")
             .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.GET, "/reservations/{year}")).hasAuthority("USER")
             .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.POST, "/reservations")).hasAuthority("USER")
             .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.DELETE, "/reservations")).hasAuthority("USER")

             // CLIENT ENDPOINTS
             .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.GET, "/clients")).hasAuthority("USER")
             .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.POST, "/clients")).hasAuthority("USER")
             .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.DELETE, "/clients")).hasAuthority("USER")

             // RESERVATION SETTING ENDPOINTS
             .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.PATCH, "/reservation_settings")).hasAuthority("USER")

             // PROPERTY SETTING ENDPOINTS
             .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.PATCH, "/property_settings")).hasAuthority("USER")

             // ADDITIONAL EXPENSES ENDPOINTS
             .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.GET, "/additionalexpenses")).hasAuthority("USER")
             .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.POST, "/additionalexpenses")).hasAuthority("USER")
             .requestMatchers(mvcMatcherBuilder.pattern(HttpMethod.PUT, "/additionalexpenses")).hasAuthority("USER")

            .anyRequest().authenticated());

    return http.build();
  }

  /**
   *  Password encoder
   * */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /**
   *  Authentication converter
   * */
  @Bean
  public JwtAuthenticationConverter authenticationConverter() {
    JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
    jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("roles");
    jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");

    JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
    jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
    return jwtAuthenticationConverter;
  }

  /**
   *  Secret key
   * */
  @Bean
  public SecretKey secretKey() {
    return new SecretKeySpec(tokenSecret.getBytes(), "HmacSHA256");
  }

  /**
   *  Jwt decoder
   * */
  @Bean
  public JwtDecoder jwtDecoder() {
    return NimbusJwtDecoder.withSecretKey(secretKey()).build();
  }

  /**
   *  Jwt encoder
   * */
  @Bean
  public JwtEncoder jwtEncoder() {
    return new NimbusJwtEncoder(
            new ImmutableSecret<SecurityContext>(secretKey())
    );
  }

  /**
   *  Authentication manager
   * */
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
          throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

}
