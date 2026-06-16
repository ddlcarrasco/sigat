package com.sigat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

import com.sigat.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CorsConfigurationSource corsConfigurationSource;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final UserDetailsServiceImpl userDetailsService;

    public SecurityConfig(CorsConfigurationSource corsConfigurationSource,
                          JwtAuthenticationFilter jwtAuthenticationFilter,
                          UserDetailsServiceImpl userDetailsService) {
        this.corsConfigurationSource = corsConfigurationSource;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .cors(cors -> cors.configurationSource(corsConfigurationSource))
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth

                // Login: publico
                .requestMatchers("/auth/**").permitAll()

                // Catalogos de solo lectura: cualquier usuario autenticado
                .requestMatchers(HttpMethod.GET, "/roles/**", "/estados-contrato/**",
                        "/estados-recibo/**", "/estados-tramite/**", "/tipos-tramite/**",
                        "/tipos-pago/**", "/motivos-recibo/**", "/tipos-descuento/**",
                        "/sectores/**", "/categorias/**").authenticated()

                // Catalogos de escritura: solo ADMIN
                .requestMatchers(HttpMethod.POST, "/roles/**", "/estados-contrato/**",
                        "/estados-recibo/**", "/estados-tramite/**", "/tipos-tramite/**",
                        "/tipos-pago/**", "/motivos-recibo/**", "/tipos-descuento/**",
                        "/sectores/**", "/categorias/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/roles/**", "/estados-contrato/**",
                        "/estados-recibo/**", "/estados-tramite/**", "/tipos-tramite/**",
                        "/tipos-pago/**", "/motivos-recibo/**", "/tipos-descuento/**",
                        "/sectores/**", "/categorias/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/roles/**", "/estados-contrato/**",
                        "/estados-recibo/**", "/estados-tramite/**", "/tipos-tramite/**",
                        "/tipos-pago/**", "/motivos-recibo/**", "/tipos-descuento/**",
                        "/sectores/**", "/categorias/**").hasAuthority("ADMIN")

                // Usuarios: solo ADMIN puede crear/editar/listar
                .requestMatchers("/usuarios/**").hasAuthority("ADMIN")

                // Titulares: ADMIN y OPERADOR pueden crear/editar, todos autenticados pueden leer
                .requestMatchers(HttpMethod.GET, "/titulares/**").authenticated()
                .requestMatchers(HttpMethod.POST, "/titulares/**").hasAnyAuthority("ADMIN", "OPERADOR")
                .requestMatchers(HttpMethod.PUT, "/titulares/**").hasAnyAuthority("ADMIN", "OPERADOR")

                // Contratos: ADMIN y OPERADOR pueden crear/editar, todos autenticados pueden leer
                .requestMatchers(HttpMethod.GET, "/contratos/**").authenticated()
                .requestMatchers(HttpMethod.POST, "/contratos/**").hasAnyAuthority("ADMIN", "OPERADOR")
                .requestMatchers(HttpMethod.PUT, "/contratos/**").hasAnyAuthority("ADMIN", "OPERADOR")

                // Tarifas: solo ADMIN puede crear/editar
                .requestMatchers(HttpMethod.GET, "/tarifas/**").authenticated()
                .requestMatchers(HttpMethod.POST, "/tarifas/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/tarifas/**").hasAuthority("ADMIN")

                // Recibos: ADMIN y OPERADOR pueden generar, todos pueden leer
                .requestMatchers(HttpMethod.GET, "/recibos/**").authenticated()
                .requestMatchers(HttpMethod.POST, "/recibos/**").hasAnyAuthority("ADMIN", "OPERADOR")

                // Pagos: ADMIN y OPERADOR pueden registrar, todos pueden leer
                .requestMatchers(HttpMethod.GET, "/pagos/**").authenticated()
                .requestMatchers(HttpMethod.POST, "/pagos/**").hasAnyAuthority("ADMIN", "OPERADOR")

                // Tramites: crear = OPERADOR y TECNICO; leer = todos; VoBo = DIRECTOR y ADMIN; resolver = OPERADOR, TECNICO y ADMIN
                .requestMatchers(HttpMethod.GET, "/tramites/**").authenticated()
                .requestMatchers(HttpMethod.POST, "/tramites/**").hasAnyAuthority("ADMIN", "OPERADOR", "TECNICO")
                .requestMatchers(HttpMethod.PUT, "/tramites/*/vobo").hasAnyAuthority("ADMIN", "DIRECTOR")
                .requestMatchers(HttpMethod.PUT, "/tramites/*/resolver").hasAnyAuthority("ADMIN", "OPERADOR", "TECNICO")

                // Cualquier otra peticion requiere autenticacion
                .anyRequest().authenticated()
            )
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
