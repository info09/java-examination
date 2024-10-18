package examination.exam.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Builder
public class PagingParameters {
    private static final int MAX_PAGE_SIZE = 50;
    private int pageNumber = 1;
    private int pageSize = 10;

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize > MAX_PAGE_SIZE ? MAX_PAGE_SIZE : pageSize;
    }
}
