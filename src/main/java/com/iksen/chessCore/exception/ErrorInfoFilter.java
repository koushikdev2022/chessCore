package com.iksen.chessCore.exception;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Filter that logs detailed information about each request and any errors
 * that occur during processing.
 */
@Component
public class ErrorInfoFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(ErrorInfoFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, 
                                   FilterChain filterChain) throws ServletException, IOException {
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
        
        long startTime = System.currentTimeMillis();
        
        try {
            filterChain.doFilter(requestWrapper, responseWrapper);
            
            // Log successful requests
            if (response.getStatus() < 400) {
                logger.debug("Request to {} completed successfully in {}ms", 
                        request.getRequestURI(), 
                        System.currentTimeMillis() - startTime);
            } 
            // Log client errors or server errors
            else {
                logErrorDetails(requestWrapper, responseWrapper, startTime);
            }
        } catch (Exception e) {
            // Log exceptions that are thrown during request processing
            logException(requestWrapper, e, startTime);
            throw e;
        } finally {
            // This is necessary to actually return the response to the client
            responseWrapper.copyBodyToResponse();
        }
    }
    
    private void logErrorDetails(ContentCachingRequestWrapper request, 
                                ContentCachingResponseWrapper response, 
                                long startTime) {
        long duration = System.currentTimeMillis() - startTime;
        
        String requestBody = getContentAsString(request.getContentAsByteArray(), 
                                             request.getCharacterEncoding());
        String responseBody = getContentAsString(response.getContentAsByteArray(), 
                                              response.getCharacterEncoding());
        
        // Get the current thread's stack trace to identify where the error happened
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String errorLocation = findApplicationErrorLocation(stackTrace);
        
        logger.error("Request to {} failed with status {} in {}ms. Error location: {}. " +
                 "Request body: {}. Response body: {}", 
                 request.getRequestURI(), response.getStatus(), duration, 
                 errorLocation, requestBody, responseBody);
    }
    
    private void logException(ContentCachingRequestWrapper request, Exception e, long startTime) {
        long duration = System.currentTimeMillis() - startTime;
        
        // Get location info from the exception's stack trace
        StackTraceElement[] stackTrace = e.getStackTrace();
        String errorLocation = findApplicationErrorLocation(stackTrace);
        
        logger.error("Request to {} failed with exception in {}ms. Error location: {}. Exception: {}", 
                 request.getRequestURI(), duration, errorLocation, e.getMessage(), e);
    }
    
    private String findApplicationErrorLocation(StackTraceElement[] stackTrace) {
        // Find the first stack trace element from your application code
        for (StackTraceElement element : stackTrace) {
            // Replace with your application package pattern
            if (element.getClassName().startsWith("com.yourapp")) {
                return element.getClassName() + "." + 
                       element.getMethodName() + "(" + 
                       element.getFileName() + ":" + 
                       element.getLineNumber() + ")";
            }
        }
        return "Unknown location";
    }
    
    private String getContentAsString(byte[] content, String encoding) {
        if (content.length == 0) return "";
        try {
            return new String(content, encoding);
        } catch (UnsupportedEncodingException e) {
            return "Unable to parse content";
        }
    }
}