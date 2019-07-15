package ua.kiev.prog.oauth2.loginviagoogle.dto;

public class PageCountDTO {
    private final long count;
    private final int pageSize;

    private PageCountDTO(long count, int pageSize) {
        this.count = count;
        this.pageSize = pageSize;
    }

    public static PageCountDTO of(long count, int pageSize) {
        return new PageCountDTO(count, pageSize);
    }

    public long getCount() {
        return count;
    }

    public int getPageSize() {
        return pageSize;
    }
}
