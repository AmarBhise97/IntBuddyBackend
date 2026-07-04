/*
 * package com.IntBuddy.IntBuddy.Configuration;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import
 * org.springframework.stereotype.Component;
 * 
 * import com.IntBuddy.IntBuddy.Entity.ExperianceEntity; import
 * com.IntBuddy.IntBuddy.Entity.UserEntity; import
 * com.IntBuddy.IntBuddy.Repository.UserRepository;
 * 
 * @Component public class AdminAccess {
 * 
 * @Autowired private UserRepository userrepo;
 * 
 * @Autowired private BCryptPasswordEncoder encoder;
 * 
 * 
 * public void createAdmins() {
 * 
 * String[] emails = {"malusarenamrata88@gmail.com", "maheshgavale@gmail.com"};
 * String[] names = {"Namrata", "Mahesh"};
 * 
 * for (int i = 0; i < emails.length; i++) {
 * 
 * if (userrepo.findByEmail(emails[i]) == null) {
 * 
 * UserEntity admin = new UserEntity();
 *  ExperianceEntity role = new ExperianceEntity();
 * 
 * admin.setEmail(emails[i]); admin.setPassword(encoder.encode("admin123"));
 * admin.setName(names[i]);
 * 
 * role.setRole("ADMIN"); admin.setExperiance(role);
 * 
 * userrepo.save(admin); } } } }
 */