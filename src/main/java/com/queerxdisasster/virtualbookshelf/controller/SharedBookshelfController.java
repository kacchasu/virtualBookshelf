package com.queerxdisasster.virtualbookshelf.controller;

import com.queerxdisasster.virtualbookshelf.entity.SharedBookshelf;
import com.queerxdisasster.virtualbookshelf.entity.User;
import com.queerxdisasster.virtualbookshelf.entity.UserBookshelf;
import com.queerxdisasster.virtualbookshelf.repository.UserBookshelfRepository;
import com.queerxdisasster.virtualbookshelf.service.UserService;
import com.queerxdisasster.virtualbookshelf.service.SharedBookshelfService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/shared-bookshelves")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class SharedBookshelfController {
    private final SharedBookshelfService sharedBookshelfService;
    private final UserService userService;
    private final UserBookshelfRepository userBookshelfRepository;

    @GetMapping
    public List<SharedBookshelf> getAllSharedBookshelves(Authentication authentication) {
        String username = authentication.getName();
        Long userId = userService.findByUsername(username).orElseThrow().getId();
        return sharedBookshelfService.getSharedBookshelvesByUserId(userId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SharedBookshelf> getSharedBookshelfById(@PathVariable Long id, Authentication authentication) {
        String username = authentication.getName();
        Long userId = userService.findByUsername(username).orElseThrow().getId();

        // Check if the user has access to this bookshelf
        boolean hasAccess = userBookshelfRepository.findBySharedBookshelfIdAndUserId(id, userId).isPresent();

        if (!hasAccess) {
            return ResponseEntity.status(403).build();
        }

        return sharedBookshelfService.getSharedBookshelfById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).build());
    }

    @PostMapping
    public SharedBookshelf saveSharedBookshelf(@RequestBody SharedBookshelf sharedBookshelf, Authentication authentication) {
        String username = authentication.getName();
        User owner = userService.findByUsername(username).orElseThrow();
        return sharedBookshelfService.saveSharedBookshelf(sharedBookshelf, owner);
    }

    @PostMapping("/{id}/invite")
    public ResponseEntity<?> inviteUserToBookshelf(@PathVariable Long id, @RequestParam String inviteeUsername, Authentication authentication) {
        String username = authentication.getName();
        Long userId = userService.findByUsername(username).orElseThrow().getId();
        if (!sharedBookshelfService.isOwner(id, userId)) {
            return ResponseEntity.status(403).build();
        }
        return sharedBookshelfService.getSharedBookshelfById(id)
                .map(bookshelf -> {
                    User invitee = userService.findByUsername(inviteeUsername).orElseThrow();
                    UserBookshelf userBookshelf = new UserBookshelf();
                    userBookshelf.setSharedBookshelf(bookshelf);
                    userBookshelf.setUser(invitee);
                    userBookshelf.setOwner(false);
                    userBookshelfRepository.save(userBookshelf);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.status(404).build());
    }

    @DeleteMapping("/{id}/remove")
    public ResponseEntity<?> removeUserFromBookshelf(@PathVariable Long id, @RequestParam String username, Authentication authentication) {
        String authUsername = authentication.getName();
        Long authUserId = userService.findByUsername(authUsername).orElseThrow().getId();
        if (!sharedBookshelfService.isOwner(id, authUserId)) {
            return ResponseEntity.status(403).build();
        }
        User userToRemove = userService.findByUsername(username).orElseThrow();
        sharedBookshelfService.removeUserFromBookshelf(id, userToRemove.getId());
        return ResponseEntity.ok().build();
    }
}