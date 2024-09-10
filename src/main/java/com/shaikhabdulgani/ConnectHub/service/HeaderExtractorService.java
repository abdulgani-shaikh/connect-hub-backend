package com.shaikhabdulgani.ConnectHub.service;

import com.shaikhabdulgani.ConnectHub.exception.HeaderNotFoundException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for handling cookies, including extraction, generation, and deletion.
 */
@Service
@RequiredArgsConstructor
public class HeaderExtractorService {

    private static final String JWT_HEADER_NAME = "Authorization";
    private static final String REFRESH_TOKEN_HEADER_NAME = "refresh-token";

//    @Value( "${backend.domain:localhost}" )
    private String domain = "localhost";

    /**
     * Extracts the value of the specified header from the provided HttpServletRequest.
     *
     * @param request    The HttpServletRequest from which to extract the header.
     * @param headerName The name of the header to extract.
     * @return The value of the specified header.
     * @throws HeaderNotFoundException If the specified header is not found in the request.
     */
    public String extractHeader(HttpServletRequest request, String headerName) throws HeaderNotFoundException {
        String headerValue = request.getHeader(headerName);
        if (headerValue == null || headerValue.isBlank())
            throw new HeaderNotFoundException("Required header '" + headerName + "' not found.");
        return headerValue;
    }

    /**
     * Extracts the value of the JWT (JSON Web Token) header from the provided HttpServletRequest.
     *
     * @param request The HttpServletRequest from which to extract the header.
     * @return The value of the JWT header.
     * @throws HeaderNotFoundException If the JWT header is not found in the request.
     */
    public String extractJwtHeader(HttpServletRequest request) throws HeaderNotFoundException {
        return extractHeader(request, JWT_HEADER_NAME);
    }

    /**
     * Extracts the value of the Refresh Token header from the provided HttpServletRequest.
     *
     * @param request The HttpServletRequest from which to extract the header.
     * @return The value of the Refresh Token header.
     * @throws HeaderNotFoundException If the Refresh Token header is not found in the request.
     */
    public String extractRefreshTokenHeader(HttpServletRequest request) throws HeaderNotFoundException {
        return extractHeader(request, REFRESH_TOKEN_HEADER_NAME);
    }

}
