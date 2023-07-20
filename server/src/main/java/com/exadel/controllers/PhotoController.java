package com.exadel.controllers;

import com.exadel.dto.PhotoDto;
import com.exadel.services.PhotoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/photos")
@RequiredArgsConstructor
@Tag(name = "Photos")
public class PhotoController {
    private final PhotoService photoService;

    @GetMapping
    public List<PhotoDto> findAll() {
        log.info("Find all photos");
        return photoService.findAll();
    }

    @GetMapping("/{photoId}")
    @Operation(description = "Get endpoint for photo", summary = "This is a summary for photo endpoint")
    @ApiResponses(value = {
            @ApiResponse(description = "Success", responseCode = "200"),
            @ApiResponse(description = "photo not found", responseCode = "404")
    })
    public PhotoDto getPhoto(@PathVariable Long photoId) {
        log.info("Get photo by id={}", photoId);
        photoService.validate(photoId);
        return photoService.findById(photoId);
    }

    @PostMapping
    @Operation(description = "Save a new photo", summary = "This is a summary for photo endpoint")
    @ApiResponses(value = {
            @ApiResponse(description = "photo was successfully added", responseCode = "201"),
    })
    public PhotoDto addPhoto(@RequestBody PhotoDto photoDto) {
        log.info("Save photo");
        PhotoDto dbPhoto = photoService.save(photoDto);

        return dbPhoto;
    }

    @PutMapping
    @Operation(description = "Get update for photo", summary = "This is a summary for photo endpoint")
    @ApiResponses(value = {
            @ApiResponse(description = "Success update", responseCode = "202"),
            @ApiResponse(description = "photo not found", responseCode = "404")
    })
    public PhotoDto updatePhoto(@RequestBody PhotoDto photoDto) {
        log.info("Update photo");
        return photoService.save(photoDto);
    }

    private void deletePhoto(@RequestBody PhotoDto photoDto) {
        log.info("Delete photo");
        photoService.delete(photoDto);
    }

    @DeleteMapping("/{photoId}")
    @Operation(description = "Delete a photo", summary = "This is a summary for photo endpoint")
    @ApiResponses(value = {
            @ApiResponse(description = "photo was successfully deleted", responseCode = "204"),
            @ApiResponse(description = "photo not found", responseCode = "404")
    })
    public void deletePhotoById(@PathVariable Long photoId) {
        log.info("Delete photo by id id={}", photoId);
        photoService.validate(photoId);
        photoService.deleteById(photoId);
    }

    @DeleteMapping
    @Operation(description = "Delete all photos", summary = "This is a summary for photos endpoint")
    @ApiResponses(value = {
            @ApiResponse(description = "photos were successfully deleted", responseCode = "204")
    })
    public void deleteAll() {
        log.info("Delete all photos");
        photoService.deleteAll();
    }
}

