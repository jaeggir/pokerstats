package ch.rogerjaeggi.pokerstats.web.config;

/*
@Configuration
@EnableWebSecurity
*/
public class SecurityConfiguration /*extends WebSecurityConfigurerAdapter */{
/*
    @Inject
    private CustomAuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new StandardPasswordEncoder();
    }

    @Bean
    public AuthenticationTokenProcessingFilter authenticationTokenProcessingFilter() throws Exception {
        return new AuthenticationTokenProcessingFilter(authenticationManager());
    }

    @Bean(name = "userService")
    public UserDetailsService userDetailsService() {
        return new UserService();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint).and()
            .addFilterAfter(authenticationTokenProcessingFilter(), BasicAuthenticationFilter.class)
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.NEVER).and()
            .csrf()
                .disable()
            .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/api/**").permitAll()
                .antMatchers("/api/authenticate").permitAll()
                .antMatchers("/api/invalidate").permitAll()
                .antMatchers("/api/socket/**").permitAll()
                .antMatchers("/api/**").authenticated();
    }


    @EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
    private static class GlobalSecurityConfiguration extends GlobalMethodSecurityConfiguration {
    }
*/
}
