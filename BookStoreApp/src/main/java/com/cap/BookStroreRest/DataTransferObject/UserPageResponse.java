
package com.cap.BookStroreRest.DataTransferObject;

import lombok.Data;
import java.util.List;

@Data
public class UserPageResponse {

    private List<UserDto> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;

}