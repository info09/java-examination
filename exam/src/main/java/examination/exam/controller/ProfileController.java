package examination.exam.controller;

import examination.exam.dto.ApiResponse;
import examination.exam.dto.profile.LoginRequest;
import examination.exam.dto.profile.LoginResponse;
import examination.exam.dto.profile.ProfileDto;
import examination.exam.dto.profile.RegistrationRequest;
import examination.exam.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profile")
public class ProfileController {
    private final ProfileService profileService;

    @PostMapping("/register")
    public ApiResponse<ProfileDto> register(@RequestBody @Valid RegistrationRequest request) {
        return ApiResponse.<ProfileDto>builder().result(profileService.register(request)).build();
    }

    @GetMapping("/profiles")
    ApiResponse<List<ProfileDto>> getAllProfiles() {
        return ApiResponse.<List<ProfileDto>>builder()
                .result(profileService.getAllProfiles())
                .build();
    }

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@RequestBody LoginRequest request) {
        return ApiResponse.<LoginResponse>builder()
                .result(profileService.login(request))
                .build();
    }

}
