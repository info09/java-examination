package examination.exam.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> {
    private int currentPage;
    private int totalPage;
    private int pageSize;
    private long totalElement;

    @Builder.Default
    private List<T> data = Collections.emptyList();
}
