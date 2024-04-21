package com.webapp.bankingportal.controller;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import com.webapp.bankingportal.entity.ERole;
import com.webapp.bankingportal.entity.Role;
import com.webapp.bankingportal.payload.request.SignupRequest;
import com.webapp.bankingportal.payload.response.JwtResponse;
import com.webapp.bankingportal.payload.response.MessageResponse;
import com.webapp.bankingportal.repository.RoleRepository;
import com.webapp.bankingportal.repository.UserRepository;
import com.webapp.bankingportal.security.JwtUtils;
import com.webapp.bankingportal.service.UserDetailsImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.webapp.bankingportal.dto.LoginRequest;
import com.webapp.bankingportal.dto.OtpRequest;
import com.webapp.bankingportal.dto.OtpVerificationRequest;
import com.webapp.bankingportal.dto.UserResponse;
import com.webapp.bankingportal.entity.User;
import com.webapp.bankingportal.security.JwtTokenUtil;
import com.webapp.bankingportal.service.OTPService;
import com.webapp.bankingportal.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {

	private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private final OTPService otpService;
    PasswordEncoder encoder;

    @Autowired
    UserRepository userRepository;


    @Autowired
    RoleRepository roleRepository;

    public UserController(UserService userService, AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil,
                          UserDetailsService userDetailsService, OTPService otpService, JwtUtils jwtUtils) {
        this.userService =  userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.otpService = otpService;
        this.jwtUtils = jwtUtils;
    }



    @PostMapping("/signup")
    public ResponseEntity<?> registerUser2(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "customer":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {




    user.setUsername(user.getName());


    Set<Role> roles  = new HashSet<>() ;

        Role modRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(modRole);





        User registeredUser = userService.registerUser(user);





        UserResponse userResponse = new UserResponse();
        userResponse.setName(registeredUser.getName());
        userResponse.setEmail(registeredUser.getEmail());
        userResponse.setAccountNumber(registeredUser.getAccount().get(0).getAccountNumber());
        userResponse.setIFSC_code(registeredUser.getAccount().get(0).getIFSC_code());
        userResponse.setBranch(registeredUser.getAccount().get(0).getBranch());
        userResponse.setAccount_type(registeredUser.getAccount().get(0).getAccount_type());


        return ResponseEntity.ok(userResponse);
    }
    /*
    @PostMapping("/register2")
    public ResponseEntity<?> registerUser2(@RequestBody SignupRequest signUpRequest) {


        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "customer":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        //userRepository.save(user);






        User registeredUser = userService.registerUser(user);




        
        UserResponse userResponse = new UserResponse();
        userResponse.setName(registeredUser.getName());
        userResponse.setEmail(registeredUser.getEmail());
        userResponse.setAccountNumber(registeredUser.getAccount().getAccountNumber());
        userResponse.setIFSC_code(registeredUser.getAccount().getIFSC_code());
        userResponse.setBranch(registeredUser.getAccount().getBranch());
        userResponse.setAccount_type(registeredUser.getAccount().getAccount_type());
        

        return ResponseEntity.ok(userResponse);
    }
    */
    @PostMapping("/login")
    public ResponseEntity<?> login2(@RequestBody LoginRequest loginRequest) {
        try {

            // If authentication successful, generate JWT token
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getAccountNumber());
            System.out.println(userDetails);
            //String token = jwtTokenUtil.generateToken(userDetails);
            Map<String, String> result =  new HashMap<>();


            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getAccountNumber(), loginRequest.getPassword()));
            System.out.println(authentication.getName());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);
            result.put("token", jwt);
            // Return the JWT token in the response
            return new ResponseEntity<>(result , HttpStatus.OK);
        } catch (BadCredentialsException e) {
            // Invalid credentials, return 401 Unauthorized
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid account number or password");
        }


    }


    @PostMapping("/signin2")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getAccountNumber(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }
    
    @PostMapping("/generate-otp")
    public ResponseEntity<?> generateOtp(@RequestBody OtpRequest otpRequest) {

    	 String accountNumber = otpRequest.getAccountNumber();

         // Fetch the user by account number to get the associated email
         User user = userService.getUserByAccountNumber(accountNumber);
         if (user == null) {
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found for the given account number");
         }

         // Generate OTP and save it in the database
         String otp = otpService.generateOTP(accountNumber);


        // Send the OTP to the user's email address asynchronously
        CompletableFuture<Boolean> emailSendingFuture = otpService.sendOTPByEmail(user.getEmail(), user.getName(), accountNumber, otp);

        // Wait for the email sending process to complete and handle the response
        try {
            boolean otpSent = emailSendingFuture.get(); // This will block until the email sending is complete

            if (otpSent) {
                // Return JSON response with success message
                return ResponseEntity.ok().body("{\"message\": \"OTP sent successfully\"}");
            } else {
                // Return JSON response with error message
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Failed to send OTP\"}");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            // Return JSON response with error message
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Failed to send OTP\"}");
        }
    }

    
    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtpAndLogin(@RequestBody OtpVerificationRequest otpVerificationRequest) {
        String accountNumber = otpVerificationRequest.getAccountNumber();
        String otp = otpVerificationRequest.getOtp();
        
        System.out.println(accountNumber+"  "+otp);

        // Validate OTP against the stored OTP in the database
        boolean isValidOtp = otpService.validateOTP(accountNumber, otp);
        System.out.println(isValidOtp);

        if (isValidOtp) {
            // If OTP is valid, generate JWT token and perform user login
        	
            // If authentication successful, generate JWT token
            UserDetails userDetails = userDetailsService.loadUserByUsername(accountNumber);
            String token = jwtTokenUtil.generateToken(userDetails);
            Map<String, String> result = new HashMap<>();
            result.put("token", token);
            // Return the JWT token in the response
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            // Invalid OTP, return 401 Unauthorized
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\": \"Invalid OTP\"}");
        }
    }

    @PostMapping("/update")
    public ResponseEntity<UserResponse> updateUser(@RequestBody User user) {
        User updateUser = userService.updateUser(user);

        UserResponse userResponse = new UserResponse();
        userResponse.setName(updateUser.getName());
        userResponse.setEmail(updateUser.getEmail());
        userResponse.setAccountNumber(updateUser.getAccount().get(0) .  getAccountNumber());
        userResponse.setIFSC_code(updateUser.getAccount().get(0) . getIFSC_code());
        userResponse.setBranch(updateUser.getAccount().get(0) .  getBranch());
        userResponse.setAccount_type(updateUser.getAccount().get(0) . getAccount_type());


        return ResponseEntity.ok(userResponse);
    }

}
