package com.shaikhabdulgani.ConnectHub.controller;

import com.shaikhabdulgani.ConnectHub.dto.FriendRequestData;
import com.shaikhabdulgani.ConnectHub.dto.UnfriendRequest;
import com.shaikhabdulgani.ConnectHub.exception.AlreadyExistsException;
import com.shaikhabdulgani.ConnectHub.exception.HeaderNotFoundException;
import com.shaikhabdulgani.ConnectHub.exception.NotFoundException;
import com.shaikhabdulgani.ConnectHub.exception.UnauthorizedAccessException;
import com.shaikhabdulgani.ConnectHub.service.HeaderExtractorService;
import com.shaikhabdulgani.ConnectHub.service.FriendService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for handling friend-related operations.
 */
@RestController
@RequestMapping("/api/friends")
@RequiredArgsConstructor
@Slf4j
public class FriendController {

    private final FriendService friendService;
    private final HeaderExtractorService headerExtractorService;

    /**
     * Generate and saves a new friend request from sender's user ID and receiver's user ID.
     *
     * @param friendRequest The FriendRequestData containing friend request information
     * @param request The HttpServletRequest object
     * @throws HeaderNotFoundException If the JWT cookie is not found
     * @throws AlreadyExistsException If the friend request already exists
     * @throws UnauthorizedAccessException If user is unauthorized to send friend request
     * @throws NotFoundException If the user is not found by given user ID.
     */
    @PostMapping
    public void sendFriendRequest(
            @RequestBody @Valid FriendRequestData friendRequest,
            HttpServletRequest request
    ) throws HeaderNotFoundException, AlreadyExistsException, UnauthorizedAccessException, NotFoundException {
        friendService.newFriendRequest(friendRequest, headerExtractorService.extractJwtHeader(request));
    }

    /**
     * Accepts a friend request.
     *
     * @param requestId The ID of the friend request to accept
     * @param request The HttpServletRequest object
     * @throws HeaderNotFoundException If the JWT cookie is not found
     * @throws UnauthorizedAccessException If user is unauthorized to accept friend request
     * @throws NotFoundException If the user is not found by given user ID.
     */
    @PutMapping("/requests/{requestId}/accept")
    public void acceptFriendRequest(@PathVariable String requestId, HttpServletRequest request) throws HeaderNotFoundException, UnauthorizedAccessException, NotFoundException {
        friendService.acceptFriendRequest(requestId, headerExtractorService.extractJwtHeader(request));
    }

    /**
     * Rejects a friend request.
     *
     * @param requestId The ID of the friend request to reject
     * @param request The HttpServletRequest object
     * @throws HeaderNotFoundException If the JWT cookie is not found
     * @throws UnauthorizedAccessException If user is unauthorized to reject friend request
     * @throws NotFoundException If the user is not found by given user ID.
     */
    @DeleteMapping("/requests/{requestId}/reject")
    public void rejectFriendRequest(@PathVariable String requestId, HttpServletRequest request) throws HeaderNotFoundException, UnauthorizedAccessException, NotFoundException {
        friendService.rejectFriendRequest(requestId, headerExtractorService.extractJwtHeader(request));
    }

    /**
     * Rejects a friend request.
     *
     * @param requestId The ID of the friend request to delete
     * @param request The HttpServletRequest object
     * @throws HeaderNotFoundException If the JWT cookie is not found
     * @throws UnauthorizedAccessException If user is unauthorized to delete friend request
     * @throws NotFoundException If the user is not found by given user ID.
     */
    @DeleteMapping("/requests/{requestId}")
    public void deleteFriendRequest(@PathVariable String requestId, HttpServletRequest request) throws HeaderNotFoundException, UnauthorizedAccessException, NotFoundException {
        friendService.deleteFriendRequest(requestId, headerExtractorService.extractJwtHeader(request));
    }

    /**
     * Unfriends a user.
     *
     * @param unfriendRequest The UnfriendRequest containing unfriend request information
     * @param request The HttpServletRequest object
     * @throws HeaderNotFoundException If the JWT cookie is not found
     * @throws UnauthorizedAccessException If user is unauthorized to delete friend request
     * @throws NotFoundException If the user is not found by given user ID.
     */
    @PutMapping
    public void unfriend(@RequestBody @Valid UnfriendRequest unfriendRequest, HttpServletRequest request) throws HeaderNotFoundException, UnauthorizedAccessException, NotFoundException {
        friendService.unfriend(unfriendRequest, headerExtractorService.extractJwtHeader(request));
    }

}
