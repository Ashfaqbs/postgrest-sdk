package com.ashfaq.pgrestsdk;


import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class PgRestSdk {
    private final String baseUrl;
    private final RestTemplate restTemplate;

    public PgRestSdk(String baseUrl) {
        this.baseUrl = baseUrl;
        this.restTemplate = new RestTemplate();
        // Ensure JSON message conversion is enabled (typically auto-configured via spring-web)
    }

    /**
     * Create a new record in the specified table.
     *
     * @param tableName Name of the table.
     * @param data      DTO representing the record.
     * @param clazz     Class type of the DTO.
     * @param <T>       Type parameter.
     * @return Created record as returned by PostgREST.
     */
    public <T> T create(String tableName, T data, Class<T> clazz) {
        String url = baseUrl + "/" + tableName;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<T> entity = new HttpEntity<>(data, headers);
        return restTemplate.postForObject(url, entity, clazz);
    }

    /**
     * Read a record from the specified table by an identifier.
     * (Assumes that you use a query parameter for filtering, e.g., id=eq.<value>)
     *
     * @param tableName Name of the table.
     * @param id        The identifier to filter on.
     * @param arrayClass     Class type of the DTO.
     * @param <T>       Type parameter.
     * @return The record if found.
     */


    public <T> T read(String tableName, String id, Class<T[]> arrayClass) {
        String url = String.format("%s/%s?id=eq.%s", baseUrl, tableName, id);
        T[] results = restTemplate.getForObject(url, arrayClass);
        if (results != null && results.length > 0) {
            return results[0]; // Return the first item in the array
        }
        return null;
    }



    /**
     * Update a record in the specified table.
     * (Uses PATCH as PostgREST accepts PATCH for updates)
     *
     * @param tableName Name of the table.
     * @param id        The identifier of the record.
     * @param data      DTO containing update data.
     * @param <T>       Type parameter.
     when in request, all the variables myst be passed with other updated vars
     */

    public <T> T update(String tableName, String id, T data, Class<T> responseType) {
        String url = String.format("%s/%s?id=eq.%s", baseUrl, tableName, id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Prefer", "resolution=merge-duplicates"); // Required for updates

        HttpEntity<T> entity = new HttpEntity<>(data, headers);

        ResponseEntity<T> response = restTemplate.exchange(
                url, HttpMethod.POST, entity, responseType
        );

        return response.getBody();
    }


    /**
     * Delete a record from the specified table by identifier.
     *
     * @param tableName Name of the table.
     * @param id        The identifier of the record.
     */
    public void delete(String tableName, String id) {
        String url = String.format("%s/%s?id=eq.%s", baseUrl, tableName, id);
        restTemplate.delete(url);
    }




    public <T> List<T> findByParams(String tableName, Map<String, String> params, Class<T> responseType) {
        String baseUrl = "http://localhost:3000/" + tableName;

        // Build query parameters (e.g., ?id=eq.2&emp_code=eq.102&name=eq.Jane%20Smith)
        String queryString = params.entrySet()
                .stream()
                .map(entry -> entry.getKey() + "=eq." + entry.getValue())
                .collect(Collectors.joining("&"));

        String fullUrl = baseUrl + "?" + queryString;

        System.out.println("Calling URL: " + fullUrl); // Debugging

        ResponseEntity<List<T>> response = restTemplate.exchange(
                fullUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<T>>() {}
        );

        return response.getBody() != null ? response.getBody() : Collections.emptyList();
    }









    public <T> List<T> findAllbyLimitandOffset(String tableName, int limit, int offset, Class<T> responseType) {
        String url = baseUrl + "/" + tableName + "?limit=" + limit + "&offset=" + offset;

        System.out.println("Calling URL: " + url); // Debugging

        ResponseEntity<List<T>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<T>>() {}
        );

        return response.getBody() != null ? response.getBody() : Collections.emptyList();
    }




    public <T> List<T> findAll(String tableName, Class<T> responseType) {
        String url = baseUrl +"/"+ tableName;
        System.out.println("Calling URL: " + url); // Debugging
        ResponseEntity<List<T>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<T>>() {}
        );

        return response.getBody() != null ? response.getBody() : Collections.emptyList();
    }

}



