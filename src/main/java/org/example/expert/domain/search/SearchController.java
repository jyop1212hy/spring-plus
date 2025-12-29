package org.example.expert.domain.search;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/totalSearch")
    public void search(
            @RequestParam(defaultValue = "1")int page,
            @RequestParam(defaultValue = "10")int size,
            @RequestParam(required = false) String titleKeyword,
            @RequestParam(required = false) String nickname,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime start,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime end
            ){
        SearchRequest searchRequest = new SearchRequest(page, size, titleKeyword, nickname, start, end);
        searchService.search(searchRequest);
    }

}
