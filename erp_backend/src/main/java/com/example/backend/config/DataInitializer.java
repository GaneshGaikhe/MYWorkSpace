//package com.example.backend.config;
//
//import com.example.backend.auth.entity.Role;
//import com.example.backend.auth.repository.RoleRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DataInitializer implements CommandLineRunner {
//
//    private final RoleRepository roleRepository;
//
//    public DataInitializer(RoleRepository roleRepository) {
//        this.roleRepository = roleRepository;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        if (roleRepository.count() == 0) {
//            roleRepository.save(new Role("ADMIN"));
//            roleRepository.save(new Role("EMPLOYEE"));
//            roleRepository.save(new Role("HR_MANAGER"));
//            roleRepository.save(new Role("ACCOUNTANT"));
//            System.out.println("Default roles inserted");
//        }
//    }
//}
