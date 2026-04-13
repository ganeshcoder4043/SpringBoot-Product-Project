//package com.springbootproject.product.security;
//
//import com.springbootproject.product.service.MyUserDetailsService;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.List;
//
//@Component                            // OncePerRequestFilter -> Har request pe ek baar filter chalta hai
//public class JwtRequestFilter extends OncePerRequestFilter {
//                  /* Har Request Ko Intercept Karke Token Validate Karna */
//                            // flow of filter
//               /*  Request Aaya → Header Check → Token Extract → Username Extract
//                   → User Load → Roles Extract → Token Validate → SecurityContext Set → Controller Tak Pahuncha */
//
//    @Autowired
//    private MyUserDetailsService userDetailsService;
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//        // Step 1: Authorization Header Nikalo
//        String authHeader = request.getHeader("Authorization");
//        String username = null;
//        String token = null;
//        // Step 2: Header Check Karo Aur Token Extract Karo
//        if (authHeader != null && authHeader.startsWith("Bearer ")){
//            token = authHeader.substring(7);
//            username =jwtUtil.extractUsername(token);
//        }
//        // Step 3: Agar Username Hai Aur Pehle Se Authenticated Nahi Hai
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
//            // Step 4: Database Se User Details Load Karo
//            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//            // Step 5: Token Se Roles Extract Karo
//            List<String> roles = jwtUtil.extractRole(token);
//            List<SimpleGrantedAuthority> authorities = roles.stream().map(role -> new SimpleGrantedAuthority(role)).toList();
//
//            // Step 6: Token Validate Karo
//            if (jwtUtil.validToken(token, userDetails.getUsername())){
//                // Step 7: Authentication Object Banao
//                UsernamePasswordAuthenticationToken authToken =
//                        new UsernamePasswordAuthenticationToken(userDetails, null,authorities);
//                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                // Step 8: Security Context Mein Set Karo (User Logged In)
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//            }
//
//        }
//        // Step 9: Request Aage Badhao
//        filterChain.doFilter(request, response);
//
//    }
//}
//
//
