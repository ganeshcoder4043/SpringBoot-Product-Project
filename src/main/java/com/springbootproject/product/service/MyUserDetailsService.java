//package com.springbootproject.product.service;
//
//import com.springbootproject.product.entity.User;
//import com.springbootproject.product.repository.UserRepository;
//import com.springbootproject.product.security.UserPrincipal;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class MyUserDetailsService implements UserDetailsService {
//               /* Database Se User Load Karna Aur Register Karna */
//    @Autowired
//    private UserRepository userRepository;
//
//    // Register New User
//    public User createUser(User user){
//        user.setPassword(new BCryptPasswordEncoder(12).encode(user.getPassword())); // raw password(ganesh123) save nhi hoga encoded formate mein save hoga ex-> dgfusg*&%^*&Dhgew87ew
//        return userRepository.save(user);
//    }
//
//    // Load User For Authentication (method Spring Security ko batata hai ki database se user kaise load karna hai.)
//    @Override   // UserDetails -> yeh ek interface toh direct return nhi hoga eska implementation class banana hoga kisi bhi naame se bna skten UserPrincipal.
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> user = userRepository.findByUsername(username);
//        if (user.isEmpty()) throw new UsernameNotFoundException("User Not Found!!!");
//        return new UserPrincipal(user.get()); //UserPrincipal yeh implementation class banya hai
//
//        /* Ye method database se user load karta hai aur usko UserDetails (UserPrincipal) me convert karke Spring Security ko return karta hai.*/
//    }
//}
