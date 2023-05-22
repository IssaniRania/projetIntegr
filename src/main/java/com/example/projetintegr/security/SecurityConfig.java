package com.example.projetintegr.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // TODO Auto-generated method stub
        PasswordEncoder passwordEncoder = passwordEncoder ();
        auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder.encode("123")).roles("ADMIN");
        auth.inMemoryAuthentication().withUser("etudiant").password(passwordEncoder.encode("123")).roles("ETUDIANT");

    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/showCreate","/","/evenement","/certification","/formation","/TableInscrit").hasAnyRole("ADMIN")
                .antMatchers("/ListeClub","/saveClub","/supprimerClub","/TableClub","/modifierClub","/updateClub").hasAnyRole("ADMIN")
                .antMatchers("/saveEvenement","/TableEvenement","/supprimerEvenement","/updateEvenement","/modifierEvenement").hasAnyRole("ADMIN")
                .antMatchers("/saveCertif","/modifierCertif","/updateCertif","/TableCertif","/supprimerCertif").hasAnyRole("ADMIN")
                .antMatchers("/TableFormation","/saveFormation","/updateFormation","/modifierFormation","/supprimerFormation").hasAnyRole("ADMIN")
                .antMatchers("/UserFormation","/userformation","/saveUserFormation","/updateUsersFormation","/searchF").hasAnyRole("ETUDIANT")
                .antMatchers("/UserCertif","/usercertif","/saveUserCertif","/updateUsersCertif","/search").hasAnyRole("ETUDIANT")
                .antMatchers("/UserEvenement","/usercertif","/saveUserCertif","/updateUsersCertif","/searchE").hasAnyRole("ETUDIANT")
                .antMatchers("/UserClub","/userclub","/saveUserCLub","/updateUsersClub","/searchCL").hasAnyRole("ETUDIANT")
                .anyRequest().authenticated();

        http.formLogin();

        http.exceptionHandling().accessDeniedPage("/accessDenied");
    }
    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }


}