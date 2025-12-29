package org.example.expert.domain.search;
import java.time.LocalDateTime;

public class SearchRequest {

    private int page;
    private int size;
    private String titleKeyword;
    private String nickname;
    private LocalDateTime start;
    private LocalDateTime end;

    public SearchRequest(int page, int size, String titleKeyword, String nickname, LocalDateTime start, LocalDateTime end) {
        this.page = page;
        this.size = size;
        this.titleKeyword = titleKeyword;
        this.nickname = nickname;
        this.start = start;
        this.end = end;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public String getTitleKeyword() {
        return titleKeyword;
    }

    public String getNickname() {
        return nickname;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }
}
