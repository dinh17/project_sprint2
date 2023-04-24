package com.example.jssport_back_end.controller;

import com.example.jssport_back_end.dto.request.ChangePasswordDto;
import com.example.jssport_back_end.dto.request.SignInForm;
import com.example.jssport_back_end.dto.request.SignUpForm;
import com.example.jssport_back_end.dto.response.JwtResponse;
import com.example.jssport_back_end.dto.response.ResponseMessage;
import com.example.jssport_back_end.jwt.JWTProvider;
import com.example.jssport_back_end.model.account.Account;
import com.example.jssport_back_end.model.account.Role;
import com.example.jssport_back_end.model.account.RoleName;
import com.example.jssport_back_end.service.IAccountService;
import com.example.jssport_back_end.service.IRoleService;
import com.example.jssport_back_end.service.principle.AccountPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthRestController {
    @Autowired
    private IAccountService iAccountService;
    @Autowired
    private IRoleService iRoleService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTProvider jwtProvider;


    @PostMapping("/sign-in")
    public ResponseEntity<?> login(@Valid @RequestBody SignInForm signInForm) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInForm.getUsername(), signInForm.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.createToken(authentication);
        AccountPrinciple accountPrinciple = (AccountPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<String> roles = accountPrinciple.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(token,
                accountPrinciple.getName(),
                accountPrinciple.getId(),
                accountPrinciple.getUsername(),
                accountPrinciple.getEmail(),
                accountPrinciple.getAvatar(),
                roles));
    }


    @PostMapping("/sign-up")
    public ResponseEntity<?> register(@Valid @RequestBody SignUpForm signUpForm) {
        if (iAccountService.existsAccountByUsername(signUpForm.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("Tên đăng nhập " + signUpForm.getUsername() +
                    " đã được sử dụng, vui lòng chọn tên khác."), HttpStatus.BAD_REQUEST);
        }
        if (iAccountService.existsAccountByEmail(signUpForm.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("Email đã tồn tại."), HttpStatus.BAD_REQUEST);
        }
        Account account = new Account(signUpForm.getUsername(), passwordEncoder.encode(signUpForm.getPassword()), signUpForm.getName(), signUpForm.getEmail());
        Set<Role> roles = new HashSet<>();
        Role role = iRoleService.findByName(RoleName.ROLE_CUSTOMER).orElseThrow(() -> new RuntimeException("Role not found"));
        roles.add(role);
        account.setRoles(roles);
        iAccountService.save(account);
        return new ResponseEntity<>(new ResponseMessage("Đăng kí thành công."), HttpStatus.OK);
    }

    @PatchMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDto changePasswordDto) throws Exception {
        iAccountService.changePassword(changePasswordDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/info/{accountId}")
    public ResponseEntity<Account> getInfoCustomer(@PathVariable Long accountId) {
        Account account = iAccountService.findByUserId(accountId).orElse(null);
        if (account == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PatchMapping("/updateAvatar")
    public ResponseEntity<?> changeAvatar(@RequestBody Account account) {
        iAccountService.changeAvatar(account.getAccountId(), account.getAvatar());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/updateInfo")
    public ResponseEntity<?> changeInfo(@RequestBody Account account) {
        Account account1 = iAccountService.findByUserId(account.getAccountId()).orElse(null);
        if (account1 == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iAccountService.changeInfo(account.getAccountId(), account.getName(), account.getPhoneNumber(), account.getAddress(), account.getEmail());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
