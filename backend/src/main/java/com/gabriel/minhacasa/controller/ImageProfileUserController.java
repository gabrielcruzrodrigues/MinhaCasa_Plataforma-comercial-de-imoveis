//package com.gabriel.minhacasa.controller;
//
//import com.gabriel.minhacasa.domain.User;
//import com.gabriel.minhacasa.service.ImageProfileService;
//import com.gabriel.minhacasa.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//@RestController
//@RequestMapping("image-profile")
//@RequiredArgsConstructor
//public class ImageProfileUserController {
//
//    private final ImageProfileService imageProfileService;
//    private final UserService userService;
//
//    //class for tests
//    @PostMapping
//    public ResponseEntity<Object> create(@RequestBody MultipartFile image, Long id) {
//        User user = this.userService.findById(id);
//        this.imageProfileService.saveFile(image, user);
//        return ResponseEntity.noContent().build();
//    }
//
//    @PutMapping
//    public ResponseEntity<Object> update(@RequestBody MultipartFile image, Long id) {
//        this.imageProfileService.updateImageProfile(image, id);
//        return ResponseEntity.noContent().build();
//    }
//}
