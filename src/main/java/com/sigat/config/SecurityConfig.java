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

                // Tipos de descuento: ADMIN y DIRECTOR pueden crear/editar
                .requestMatchers(HttpMethod.POST,   "/tipos-descuento/**").hasAnyAuthority("ADMIN", "DIRECTOR")
                .requestMatchers(HttpMethod.PUT,    "/tipos-descuento/**").hasAnyAuthority("ADMIN", "DIRECTOR")

                // Asignacion de descuentos a contratos: solo ADMIN y DIRECTOR
                .requestMatchers(HttpMethod.POST,   "/contratos/*/descuentos").hasAnyAuthority("ADMIN", "DIRECTOR")
                .requestMatchers(HttpMethod.DELETE, "/contratos/*/descuentos/**").hasAnyAuthority("ADMIN", "DIRECTOR")

                // Categorias, sectores y tarifas: ADMIN y DIRECTOR pueden crear/editar
                .requestMatchers(HttpMethod.POST, "/categorias/**", "/sectores/**", "/tarifas/**").hasAnyAuthority("ADMIN", "DIRECTOR")
                .requestMatchers(HttpMethod.PUT,  "/categorias/**", "/sectores/**", "/tarifas/**").hasAnyAuthority("ADMIN", "DIRECTOR")

                // Otros catalogos de escritura: solo ADMIN
                .requestMatchers(HttpMethod.POST, "/roles/**", "/estados-contrato/**",
                        "/estados-recibo/**", "/estados-tramite/**", "/tipos-tramite/**",
                        "/tipos-pago/**", "/motivos-recibo/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/roles/**", "/estados-contrato/**",
                        "/estados-recibo/**", "/estados-tramite/**", "/tipos-tramite/**",
                        "/tipos-pago/**", "/motivos-recibo/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/roles/**", "/estados-contrato/**",
                        "/estados-recibo/**", "/estados-tramite/**", "/tipos-tramite/**",
                        "/tipos-pago/**", "/motivos-recibo/**",
                        "/sectores/**", "/categorias/**", "/tarifas/**").hasAuthority("ADMIN")

                // Usuarios: GET es accesible para todos (se usa en formularios de asignacion)
                // Crear/editar/desactivar/cambiar-password: solo ADMIN
                .requestMatchers(HttpMethod.GET,    "/usuarios/**").authenticated()
                .requestMatchers(HttpMethod.POST,   "/usuarios/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.PUT,    "/usuarios/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.PATCH,  "/usuarios/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/usuarios/**").hasAuthority("ADMIN")

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
